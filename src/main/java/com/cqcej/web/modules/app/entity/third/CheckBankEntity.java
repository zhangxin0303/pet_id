package com.cqcej.web.modules.app.entity.third;

/**
 * 银行四要素检查
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-17 17:29
 */
public class CheckBankEntity {
	
	/**
	 * name : 李欢
	 * cardNo : 6215583100007608384
	 * idNo : 511325199109235619
	 * phoneNo : 17318203891
	 * respMessage : 信息匹配
	 * respCode : 0000
	 */
	
	private String name;
	private String cardNo;
	private String idNo;
	private String phoneNo;
	private String respMessage;
	private String respCode;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getIdNo() {
		return idNo;
	}
	
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getRespMessage() {
		return respMessage;
	}
	
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	
	public String getRespCode() {
		return respCode;
	}
	
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
}
