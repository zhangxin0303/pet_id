package com.cqcej.web.common.aspect;

import com.cqcej.web.common.annotation.CheckCodeFrequency;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.AppConstants;
import com.cqcej.web.common.utils.DateUtils;
import com.cqcej.web.modules.app.entity.VerificationCodeEntity;
import com.cqcej.web.modules.app.service.AppVerificationCodeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 检查验证码发送频率
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-04 17:25
 */
@Aspect
@Component
public class CheckCodeFrequencyAspect {
	@Autowired
	private AppVerificationCodeService appVerificationCodeService;
	
	
	@Before("@annotation(check)")// 拦截被CheckCodeFrequency注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
	public void beforeSendCode(JoinPoint point, CheckCodeFrequency check) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		
		String account = request.getParameter("account");
		
		VerificationCodeEntity entity = appVerificationCodeService.queryByAccount(account);
		if (entity != null && !DateUtils.isOutOfCurrentTime(DateUtils.addDateSeconds(entity.getSendTime(), AppConstants.SEND_CODE_PERIOD))) {
			throw new CTException("访问频率太频繁", 403);
		}
	}
}
