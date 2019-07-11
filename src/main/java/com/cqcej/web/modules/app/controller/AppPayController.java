package com.cqcej.web.modules.app.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.IPUtils;
import com.cqcej.web.common.utils.Utils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.utils.*;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import com.cqcej.web.modules.common.service.ServiceOrderService;
import com.cqcej.web.modules.common.service.UserService;
import com.cqcej.web.modules.common.service.WorkerService;
import com.cqcej.web.modules.common.service.WxService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-20 16:31
 */
@RestController
@RequestMapping("/app/pay")
@Api(description = "支付回调接口")
@Login
public class AppPayController extends AbstractController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserService userService;

    @Autowired
    private WxService wxService;

    @Autowired
    private AppConfig config;

    /**
     * 支付宝回调地址
     */
    @PostMapping("/alipay")
    @Login(allowAnonymous = true)
    public String alipayCallback(HttpServletRequest request) {
        String resultFail = "fail";
        String resultSuccess = "success";

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, config.getAlipay().getAlipayPublicKey(), "utf-8", "RSA2");
            if (flag) {
                ServiceOrderEntity order = serviceOrderService.getOrderByNo(params.get("out_trade_no"));
                // 1、订单是否存在
                if (order == null) {
                    // 找不到此订单，不用继续通知
                    return resultSuccess;
                }

                // 2、验证支付金额
                if (!params.get("total_amount").equals(order.getPayAmount().toString())) {
                    // 失败，不用继续通知
                    return resultSuccess;
                }

                // 3、TODO 校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）

                // 4、验证app_id是否为该商户本身。
                if (!params.get("app_id").equals(AppConstant.Alipay.APP_ID)) {
                    // 失败，不同继续通知
                    return resultSuccess;
                }

                if (params.get("trade_status").equals("TRADE_SUCCESS") || params.get("trade_status").equals("TRADE_FINISHED")) {
                    Runnable run = () -> paySuccess(order, params.get("trade_no"));
                    new Thread(run).start();
                }

                return resultSuccess;
            } else {
                logger.info("支付宝支付签名验证失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info("支付宝支付签名验证错误");
        }

        return resultFail;
    }

    /**
     * 支付宝回调地址
     */
    @PostMapping("/wechat")
    @Login(allowAnonymous = true)
    public String wechatPayCallback(HttpServletRequest request) throws IOException {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String data = new String(outSteam.toByteArray(), StandardCharsets.UTF_8);
        Map<String, String> result = wxService.payBack(data, config.getWxpay());

        if (result == null) {
            return setXML("FAIL", "报文为空");
        } else {
            ServiceOrderEntity order = serviceOrderService.getOrderByNo(result.get("orderNo"));
            Runnable runnable = () -> paySuccess(order, result.get("tradeNo"));
            new Thread(runnable).start();
            return setXML("SUCCESS", "OK");
        }
    }

    private static String setXML(String returnCode, String message) {
        return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + message + "]]></return_msg></xml>";
    }

    @ApiOperation("订单支付")
    @PostMapping("/order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "需要支付的订单id", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "paymentId", value = "支付方式Id", dataType = "short", paramType = "query")
    })
    public BaseResponse<String> payInfo(HttpServletRequest request, @RequestBody ServiceOrderEntity order, Short paymentId) {
        // 添加新订单
        Long userId = (Long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        // ServiceOrderEntity order = serviceOrderService.getOrder(userId, 2L);

        if (!order.getUserId().equals(userId)) {
            throw new CTException("非法请求！");
        }

        // 新订单，如果有用积分，则不管是否支付，先扣除积分
        if (order.getOrderId() == null || order.getOrderId() == 0) {
            if (order.getScoreDiscount() != null && order.getScoreDiscount() > 0) {
                // 扣除用户积分
                userService.reduceScore(userId, order.getScoreDiscount());
            }

            // FIXME: 如果有使用优惠券，则不管是否支付，先修改状态不可用

            // 设置订单号
            order.setOrderNo(Utils.getOrderIdByTime());
            order.setCreateAt(new Date());

            // 设置支付方式
            order.setPaymentId(paymentId);

            // 新增订单
            serviceOrderService.insert(order);
        } else {
            if (order.getPaymentId() != paymentId) {
                // 更新支付方式
                serviceOrderService.updateField(order.getOrderId(), "paymentId", String.valueOf(paymentId));
            }
        }

        String result;
        if (paymentId == 1) {
            // 支付宝支付
            result = alipay(order);

            if (TextUtils.isEmpty(result)) {
                throw new CTException("系统错误，请稍后再试");
            }
        } else if (paymentId == 2) {
            // 微信支付
            String ip = IPUtils.getIpAddr(request);
            result = wechatPay(order, ip);
        } else if (paymentId == 3) {
            // 银联支付
            result = unionPay(order);
        } else {
            throw new CTException("未知支付方式");
        }


        return new BaseResponse<>(result);
    }

    /**
     * 支付宝支付
     *
     * @param order 订单
     * @return string
     */
    private String alipay(ServiceOrderEntity order) {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                AppConstant.Alipay.APP_ID,
                config.getAlipay().getRsaPrivateKeyPkcs8(),
                "json",
                "utf-8",
                config.getAlipay().getRsaPublicKey(),
                "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(order.getSubject());
        model.setSubject(order.getSubject());
        model.setOutTradeNo(order.getOrderNo());
        model.setTimeoutExpress("1h");
        model.setTotalAmount(order.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://" + config.getNotifyUrl() + "/app/pay/alipay");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String wechatPay(ServiceOrderEntity order, String ip) {
        // 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据，暂时不用
        String attach = "Android Order";
        //请求预支付订单
        Map<String, String> result = wxService.doUnifiedOrder(order, config, ip, attach);
        if (result.size() == 2) {
            // 失败了
            throw new CTException(result.get("return_msg"));
        }

        // 保存prepayID
        serviceOrderService.updateField(order.getOrderId(), "prepayId", result.get("prepay_id"));

        Map<String, String> map = new HashMap<>();

        WxMD5Util md5Util = new WxMD5Util();
        //返回APP端的数据
        //参加调起支付的签名字段有且只能是6个，分别为appid、partnerid、prepayid、package、noncestr和timestamp，而且都必须是小写
        map.put("appid", result.get("appid"));
        map.put("partnerid", result.get("mch_id"));
        map.put("prepayid", result.get("prepay_id"));
        map.put("package", "Sign=WXPay");
        map.put("noncestr", result.get("nonce_str"));
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//单位为秒
//      这里不要使用请求预支付订单时返回的签名
//      这里不要使用请求预支付订单时返回的签名
//      这里不要使用请求预支付订单时返回的签名
        map.put("sign", md5Util.getSign(map, config.getWxpay().getKey()));
        map.put("extdata", attach);
        return new Gson().toJson(map);
    }

    private String unionPay(ServiceOrderEntity order) {
        return "";
    }

    /**
     * 支付成功后
     *
     * @param order   订单
     * @param tradeNo 支付宝交易号
     */
    private void paySuccess(ServiceOrderEntity order, String tradeNo) {
        if (order.getIsPay().equals(1)) {
            // 已经支付
            logger.info("订单号\"" + order.getOrderNo() + "\"已处理，跳过");
            return;
        }

        logger.info("订单号\"" + order.getOrderNo() + "\"已支付");

        // 设置已支付
        serviceOrderService.paySuccess(order.getOrderId(), tradeNo);

        // 发送通知
        if (order.getOrderSubtype() == ServiceOrderEntity.ORDER_SUBTYPE_CLINIC_ONDOOR
                || order.getOrderSubtype() == ServiceOrderEntity.ORDER_SUBTYPE_CLINIC_CONSULTATION_ONLINE
                || order.getOrderSubtype() == ServiceOrderEntity.ORDER_SUBTYPE_CLINIC_CONSULTATION_TELEPHONE
                || order.getOrderSubtype() == ServiceOrderEntity.ORDER_SUBTYPE_HEALTH_WALK_DOG) {
            // 上门就诊，聊天咨询，电话咨询，遛狗服务，都是通知一个人
            WorkerEntity worker = workerService.selectById(order.getWorkerId());
            PushUtils.unicastNotification(worker.getUserId(), "您有一个新的服务订单", "点击查看详情");
            sendSystemMessage(worker.getUserId(), order.getOrderId(), "您有一个新的服务订单", "", "点击查看详情");
        }

        if (order.getOrderSubtype() == ServiceOrderEntity.ORDER_SUBTYPE_BEAUTY_TRANS) {
            // 美容接送
            WorkerEntity worker = workerService.selectById(order.getWorkerId());
            PushUtils.unicastNotification(worker.getUserId(), "您有一个新的服务订单", "点击查看详情");
            sendSystemMessage(worker.getUserId(), order.getOrderId(), "您有一个新的服务订单", "", "点击查看详情");

            // 通知接宠人员
            if (order.getPickupId() == null) {
                // TODO 分配接宠人员
            } else {
                WorkerEntity pickUp = workerService.selectById(order.getPickupId());
                PushUtils.unicastNotification(pickUp.getUserId(), "您有一个新的接宠订单", "点击查看详情");
                // 发送消息
                sendSystemMessage(pickUp.getUserId(), order.getOrderId(), "您有一个新的接宠订单", "", "点击查看详情");
            }
        }

        // TODO 后续任务待补充
    }
}
