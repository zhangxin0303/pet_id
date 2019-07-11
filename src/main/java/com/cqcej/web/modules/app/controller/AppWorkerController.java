package com.cqcej.web.modules.app.controller;

import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.PasswordCheck;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatInfoDTO;
import com.cqcej.web.modules.app.entity.home.AppWorkerCommentEntity;
import com.cqcej.web.modules.app.entity.home.AppWorkerEntity;
import com.cqcej.web.modules.app.form.MechBeauticianForm;
import com.cqcej.web.modules.app.form.MechDoctorForm;
import com.cqcej.web.modules.app.form.MechPickupForm;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppWorkerCommentService;
import com.cqcej.web.modules.app.service.AppWorkerService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.AppVerification;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作者
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-01 17:36
 */
@RestController
@RequestMapping("/app/worker")
@Api(description = "App工作者，医生，美容师，遛狗，接送人员")
public class AppWorkerController extends AbstractController {

    @Autowired
    private AppWorkerService appWorkerService;

    @Autowired
    private AppWorkerCommentService appWorkerCommentService;

    @GetMapping("/detail/{workerId}")
    @ApiOperation("获取工作者详情")
    @ApiImplicitParam(name = "workerId", value = "工作者ID", dataType = "long", paramType = "path")
    public BaseResponse<AppWorkerEntity> getWorkerDetail(@PathVariable("workerId") Long workerId) {
        AppWorkerEntity entity = appWorkerService.getWorkerDetail(workerId);
        return new BaseResponse<>(entity);
    }

    @GetMapping("/detail/user/{userId}")
    @ApiOperation("获取工作者详情")
    @ApiImplicitParam(name = "userId", value = "工作者ID", dataType = "long", paramType = "path")
    public BaseResponse<WorkerEntity> getWorkerDetailWithUserId(@PathVariable("userId") Long userId) {
        WorkerEntity entity = appWorkerService.getWorkerDetailWithUserId(userId);
        return new BaseResponse<>(entity);
    }

    @GetMapping("/hot/beautician")
    @ApiOperation("获取最热美容师")
    public BaseResponse<List<AppWorkerEntity>> getHotBeautician() {
        List<AppWorkerEntity> entity = appWorkerService.getHotBeautician();
        return new BaseResponse<>(entity);
    }

    /**
     * 工作者列表
     */
    @GetMapping("/list/{type}")
    @ApiOperation("工作人员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "工作者类型，医生，美容师，接送者", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "mechanismId", value = "机构ID，如果为0，则筛选所有分类(配送人员有效)", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "petClassId", value = "宠物分类ID，如果为0，则筛选所有分类(医生类型有效)", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sortType", value = "排序类型，0综合排序，1距离最近，2优质筛选", defaultValue = "0", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<AppPage<AppWorkerEntity>> getWorkers(@PathVariable("type") int type, long mechanismId, int petClassId, int sortType, int page, int size, double longitude, double latitude) {
        if (sortType < 0 || sortType > 2) {
            throw new CTException("参数错误");
        }
        AppPage<AppWorkerEntity> appPage = appWorkerService.getWorkers(type, mechanismId, petClassId, sortType, page, size, longitude, latitude);
        return new BaseResponse<>(appPage);
    }

    /**
     * 医生列表
     */
    @GetMapping("/comment")
    @ApiOperation("评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workerId", value = "工作者id，参见worker表", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<AppPage<AppWorkerCommentEntity>> workerComment(Integer workerId, Integer page, Integer size) {
        AppPage<AppWorkerCommentEntity> pages = appWorkerCommentService.getWorkerComments(workerId, page, size);
        return new BaseResponse<>(pages);
    }

    @Login
    @PostMapping("/save/goodat")
    @ApiOperation("修改用户擅长")
    @ApiImplicitParam(name = "goodat", value = "擅长", required = true, dataType = "string", paramType = "query")
    public BaseResponse<Boolean> saveGoodAt(HttpServletRequest request, String goodat) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appWorkerService.saveUserGoodAt(userId, goodat);
        return new BaseResponse<>(result);
    }

    @Login
    @PostMapping("/save/introduce")
    @ApiOperation("修改用户介绍")
    @ApiImplicitParam(name = "introduce", value = "介绍", required = true, dataType = "string", paramType = "query")
    public BaseResponse<Boolean> saveIntroduce(HttpServletRequest request, String introduce) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appWorkerService.saveUserIntroduce(userId, introduce);
        return new BaseResponse<>(result);
    }

    @Login
    @PostMapping("/save/fee")
    @ApiOperation("修改价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", allowableValues = "chat, telephone, ondoor", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "fee", value = "金额", required = true, dataType = "double", paramType = "form")
    })
    public BaseResponse<Boolean> saveFee(HttpServletRequest request, String type, Double fee) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appWorkerService.saveUserFee(userId, type, fee);
        return new BaseResponse<>(result);
    }


    //-------------------------------------------------商家端(jm)-----------------------------------------------------

    /**
     * 更新坐标m
     *
     * @param request
     * @param longitude
     * @param latitude
     * @return
     */
    @Login
    @GetMapping("/update/coordinate")
    @ApiOperation("更新坐标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "string", paramType = "query")
    })
    public BaseResponse<Boolean> updateCoordinate(HttpServletRequest request, String longitude, String latitude) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        return new BaseResponse(appWorkerService.updateCoordinate(userId, longitude, latitude) > 0 ? true : false);
    }

    /**
     * mech人员管理m
     */
    @Login
    @GetMapping("/mech/list")
    @ApiOperation("mech人员管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mechId", value = "商户ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "workerType", value = "工作者类型", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<AppWorkerEntity> workerList(HttpServletRequest request, Long mechId, Integer workerType) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppVerification.check(userId, mechId);
        List<AppWorkerEntity> data = appWorkerService.workerList(mechId, workerType);
        return new BaseResponse(data);
    }

    /**
     * 批量操作m (修改状态is_delete为1)
     */
    @DeleteMapping("worker/delete")
    @ApiOperation("批量操作(员工)")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workerId", value = "工作人员Id(多个用逗号隔开)", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "mechId", value = "商户Id", required = true, dataType = "long", paramType = "query")
    })
    public BaseResponse<Boolean> deleteService(HttpServletRequest request, @RequestParam Long mechId, @RequestParam String workerId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppVerification.check(userId, mechId);
        List<Long> list = new ArrayList<>();
        for (String s : workerId.split(",")) {
            list.add(Long.valueOf(s));
        }
        return new BaseResponse(appWorkerService.deleteByWorkerId(list));
    }

    /**
     * 查看工作人员信息(编辑时使用)
     */
    @GetMapping("/info/{workerId}")
    @ApiOperation("查看工作人员信息(编辑时使用)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workerId", value = "工作者ID", required = true, dataType = "long", paramType = "query")
    })
    public BaseResponse<AppWorkerEntity> info(@PathVariable Long workerId) {
        AppWorkerEntity data = appWorkerService.info(workerId);
        return new BaseResponse<>(data);
    }


    /**
     * 医师订单统计
     */
    @GetMapping("/doctor/statistics")
    @ApiOperation("医师订单统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workerId", value = "工作者ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "日期(如：2018-11)", required = true, dataType = "string", paramType = "query")
    })
    public BaseResponse<MechDoctorStatInfoDTO> statistics(@RequestParam Long workerId, @RequestParam String date) {
        MechDoctorStatInfoDTO data = appWorkerService.statistics(workerId, date);
        return new BaseResponse<>(data);
    }

    /**
     * 新增(编辑)医师
     */
    @PostMapping("/update/doctor")
    @ApiOperation("新增(编辑)医师")
    @Login
    public BaseResponse updateDoctor(HttpServletRequest request, @RequestBody MechDoctorForm form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppVerification.check(userId, form.getMechanismId());
        form.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        if (form.getWorkerId() != null) {
            return new BaseResponse<>(appWorkerService.updateDoctor(form) > 0 ? true : false);
        }
        PasswordCheck.check(form.getPassword());
        return new BaseResponse<>(appWorkerService.insertDoctor(form) > 0 ? true : false);
    }

    /**
     * 新增美容师
     */
    @PostMapping("/update/beautician")
    @ApiOperation("新增(编辑)美容师")
    @Login
    public BaseResponse<Boolean> updateBeautician(HttpServletRequest request, @RequestBody MechBeauticianForm form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppVerification.check(userId, form.getMechanismId());
        if (form.getWorkerId() != null) {//编辑
            return new BaseResponse<>(appWorkerService.updateBeautician(form));
        }
        return new BaseResponse<>(appWorkerService.insertBeautician(form));
    }

    /**
     * 新增接送店员
     */
    @PostMapping("/update/pickup")
    @ApiOperation("新增(编辑)接送店员")
    @Login
    public BaseResponse<Boolean> insertPickUp(HttpServletRequest request, @RequestBody MechPickupForm form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppVerification.check(userId, form.getMechanismId());
        form.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        if (form.getWorkerId() != null) {//编辑
            return new BaseResponse<>(appWorkerService.updatePickup(form));
        }
        PasswordCheck.check(form.getPassword());
        return new BaseResponse<>(appWorkerService.insertPickUp(form));
    }

}








