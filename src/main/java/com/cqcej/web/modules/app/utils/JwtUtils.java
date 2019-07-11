package com.cqcej.web.modules.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2017/9/21 22:21
 */
@ConfigurationProperties(prefix = "cqcej.jwt")
@Component
public class JwtUtils {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String secret;
	private long expire;
	private String header;
	private String startWith;
	
	/**
	 * 生成jwt token
	 */
	public String generateToken(long userId) {
		Date nowDate = new Date();
		//过期时间
		Date expireDate = new Date(nowDate.getTime() + expire * 1000);
		
		String token = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject(String.valueOf(userId))
				.setIssuedAt(nowDate)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		return startWith.concat(token);
	}
	
	public Claims getClaimByToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			logger.debug("validate is token error ", e);
			return null;
		}
	}
	
	/**
	 * token是否过期
	 *
	 * @return true：过期
	 */
	public boolean isTokenExpired(Date expiration) {
		return expiration.before(new Date());
	}
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public long getExpire() {
		return expire;
	}
	
	public void setExpire(long expire) {
		this.expire = expire;
	}
	
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getStartWith() {
		return startWith;
	}
	
	public void setStartWith(String startWith) {
		this.startWith = startWith;
	}
}
