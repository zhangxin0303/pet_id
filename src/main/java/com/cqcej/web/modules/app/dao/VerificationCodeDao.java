/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.VerificationCodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-03 16:23:26
 */
@Mapper
public interface VerificationCodeDao extends BaseMapper<VerificationCodeEntity> {
	
	/**
	 * 通过验证码查询记录
	 * @param code 验证码
	 * @return
	 */
	VerificationCodeEntity queryByCode(String code);
	
	/**
	 * 通过账号查询记录
	 * @param account 验证码
	 * @return
	 */
	VerificationCodeEntity queryByAccount(String account);
	
	void clearOutOfDateCode(String date);
}
