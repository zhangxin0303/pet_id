package com.cqcej.web.modules.admin.oauth2;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-20 13:22
 */
public class OAuth2Token implements AuthenticationToken {
	private String token;
	
	public OAuth2Token(String token) {
		this.token = token;
	}
	
	@Override
	public String getPrincipal() {
		return token;
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}
}
