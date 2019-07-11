package com.cqcej.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域配置
 */

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
	
//		@Override
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**")
//					.allowedOrigins("*")
//					.allowCredentials(true)
//					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//					.allowedHeaders("Access-Control-Allow-Headers", "token,Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization")
//					.maxAge(3600);
//	}
	
	// CORS
	@Bean
	FilterRegistrationBean corsFilter() {
		return new FilterRegistrationBean(new Filter() {
			public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
					throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
				response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
				response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Headers", "token,Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				if ("OPTIONS".equals(request.getMethod())) {
					response.setStatus(HttpStatus.OK.value());
				}
				else {
					chain.doFilter(req, res);
				}
			}

			public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
			}

			public void destroy() {
			}
		});
	}

}
