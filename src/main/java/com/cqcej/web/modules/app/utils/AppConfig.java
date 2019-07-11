package com.cqcej.web.modules.app.utils;

import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.Utils;
import com.github.wxpay.sdk.WXPayConfig;
import com.tls.tls_sigature.tls_sigature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 配置
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-31 10:44
 */
@ConfigurationProperties(prefix = "cqcej")
@Component
public class AppConfig {
	
	private final Alipay alipay = new Alipay();
	
	private final Wxpay wxpay = new Wxpay();
	
	private final Umeng umeng = new Umeng();
	
	private final TencentCloudIM tencentCloudIM = new TencentCloudIM();
	
	private String notifyUrl;
	
	public String getNotifyUrl() {
		return notifyUrl;
	}
	
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	public Alipay getAlipay() {
		return alipay;
	}
	
	public Wxpay getWxpay() {
		return wxpay;
	}
	
	public Umeng getUmeng() {
		return umeng;
	}
	
	public TencentCloudIM getTencentCloudIM() {
		return tencentCloudIM;
	}
	
	/**
	 * 支付宝支付
	 */
	public static class Alipay {
		private String rsaPrivateKey;
		private String rsaPrivateKeyPkcs8;
		private String rsaPublicKey;
		private String alipayPublicKey;
		
		public String getAlipayPublicKey() {
			return alipayPublicKey;
		}
		
		public void setAlipayPublicKey(String alipayPublicKey) {
			this.alipayPublicKey = Utils.readResourceFile(alipayPublicKey);
		}
		
		public String getRsaPrivateKey() {
			return rsaPrivateKey;
		}
		
		public void setRsaPrivateKey(String rsaPrivateKey) {
			this.rsaPrivateKey = Utils.readResourceFile(rsaPrivateKey);
		}
		
		public String getRsaPrivateKeyPkcs8() {
			return rsaPrivateKeyPkcs8;
		}
		
		public void setRsaPrivateKeyPkcs8(String rsaPrivateKeyPkcs8) {
			this.rsaPrivateKeyPkcs8 = Utils.readResourceFile(rsaPrivateKeyPkcs8);
		}
		
		public String getRsaPublicKey() {
			return rsaPublicKey;
		}
		
		public void setRsaPublicKey(String rsaPublicKey) {
			this.rsaPublicKey = Utils.readResourceFile(rsaPublicKey);
		}
	}
	
	/**
	 * 微信支付
	 */
	public static class Wxpay implements WXPayConfig {
		private String appId;
		private String key;
		private String mchId;
		private String certKeyData;
		private byte[] certData;
		
		@Override
		public String getAppID() {
			return appId;
		}
		
		public void setAppId(String appId) {
			this.appId = appId;
		}
		
		@Override
		public String getMchID() {
			return mchId;
		}
		
		public void setMchId(String mchId) {
			this.mchId = mchId;
		}
		
		@Override
		public String getKey() {
			return key;
		}
		
		public void setKey(String key) {
			this.key = key;
		}
		
		public void setCertKeyData(String certKeyData) {
			this.certKeyData = Utils.readResourceFile(certKeyData, true);
		}
		
		@Override
		public InputStream getCertStream() {
			//从微信商户平台下载的安全证书存放的目录
			String certPath = "src/main/resources/keys/wechat/apiclient_cert.p12";
			ByteArrayInputStream certBis = null;
			try {
				File file = new File(certPath);
				InputStream certStream = new FileInputStream(file);
				this.certData = new byte[(int) file.length()];
				certStream.read(this.certData);
				certStream.close();
				certBis = new ByteArrayInputStream(this.certData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return certBis;
		}
		
		@Override
		public int getHttpConnectTimeoutMs() {
			return 8000;
		}
		
		@Override
		public int getHttpReadTimeoutMs() {
			return 10000;
		}
	}
	
	/**
	 * 友盟
	 */
	public static class Umeng {
		private String appKey;
		private String secret;
		
		public String getAppKey() {
			return appKey;
		}
		
		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}
		
		public String getSecret() {
			return secret;
		}
		
		public void setSecret(String secret) {
			this.secret = secret;
		}
	}
	
	/**
	 * 云通讯IM
	 */
	public static class TencentCloudIM {
		private String privateKey;
		private String publicKey;
		
		public String getPrivateKey() {
			return privateKey;
		}
		
		public void setPrivateKey(String privateKey) {
			this.privateKey = Utils.readResourceFile(privateKey, true);
		}
		
		public String getPublicKey() {
			return publicKey;
		}
		
		public void setPublicKey(String publicKey) {
			this.publicKey = Utils.readResourceFile(publicKey, true);
		}
		
		public String generateSign(String userId) {
			tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(AppConstant.Tencent.CloudIM.APP_KEY, userId, privateKey);
			if (result != null && "".equals(result.errMessage)) {
				return result.urlSig;
			} else {
				throw new CTException("腾讯云签名失败", 500);
			}
		}
	}
}
