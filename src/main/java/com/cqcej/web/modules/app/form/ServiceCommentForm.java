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

package com.cqcej.web.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author Li HuanLing
 * @author 503580622@qq.com
 * @date 2018-06-11 10:15
 */
@ApiModel(value = "服务订单评价表单")
public class ServiceCommentForm {
	@ApiModelProperty(value = "是否有接送评价", notes = "默认没有接送评价")
	private int is_pickup = 0;
	
	@ApiModelProperty(value = "接送评价分数")
	private int pickup_number = 0;
	
	@ApiModelProperty(value = "是否有美容评价", notes = "默认没有美容评价")
	private int is_beauty = 0;
	
	@ApiModelProperty(value = "美容评价分数")
	private int beauty_number = 0;
	
	@ApiModelProperty(value = "店铺评价分数")
	@NotBlank(message = "店铺评价分数不能为空")
	private int store_number;
	
	@ApiModelProperty(value = "是否匿名", notes = "默认非匿名")
	private int is_anonymous = 0;
	
	@ApiModelProperty(value = "评价内容")
	@NotBlank(message = "评价内容不能为空")
	private String content;
	
	public int getIs_pickup() {
		return is_pickup;
	}
	
	public void setIs_pickup(int is_pickup) {
		this.is_pickup = is_pickup;
	}
	
	public int getPickup_number() {
		return pickup_number;
	}
	
	public void setPickup_number(int pickup_number) {
		this.pickup_number = pickup_number;
	}
	
	public int getIs_beauty() {
		return is_beauty;
	}
	
	public void setIs_beauty(int is_beauty) {
		this.is_beauty = is_beauty;
	}
	
	public int getBeauty_number() {
		return beauty_number;
	}
	
	public void setBeauty_number(int beauty_number) {
		this.beauty_number = beauty_number;
	}
	
	public int getStore_number() {
		return store_number;
	}
	
	public void setStore_number(int store_number) {
		this.store_number = store_number;
	}
	
	public int getIs_anonymous() {
		return is_anonymous;
	}
	
	public void setIs_anonymous(int is_anonymous) {
		this.is_anonymous = is_anonymous;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
