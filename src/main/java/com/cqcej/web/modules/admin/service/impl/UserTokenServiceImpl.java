package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.dao.SysUserTokenDao;
import com.cqcej.web.modules.admin.entity.LoginEntity;
import com.cqcej.web.modules.admin.entity.SysUserTokenEntity;
import com.cqcej.web.modules.admin.oauth2.TokenGenerator;
import com.cqcej.web.modules.admin.service.UserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class UserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements UserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;
	
	
	@Override
	public R<LoginEntity> createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
		
		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.selectById(userId);
		if (tokenEntity == null) {
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			
			//保存token
			this.insert(tokenEntity);
		} else {
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			
			//更新token
			this.updateById(tokenEntity);
		}

		LoginEntity loginEntity = new LoginEntity(token, EXPIRE);
		
		return R.ok(loginEntity);
	}
	
	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		
		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}
}
