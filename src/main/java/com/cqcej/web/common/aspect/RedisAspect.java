package com.cqcej.web.common.aspect;

import com.cqcej.web.common.exception.CTException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切面处理类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-07-17 23:30
 */
@Aspect
@Configuration
public class RedisAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//是否开启redis缓存  true开启   false关闭
	@Value("${spring.redis.open: false}")
	private boolean open;
	
	@Around("execution(* com.cqcej.web.common.utils.RedisUtils.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		if (open) {
			try {
				result = point.proceed();
			} catch (Exception e) {
				logger.error("redis error", e);
				throw new CTException("Redis服务异常");
			}
		}
		return result;
	}
}
