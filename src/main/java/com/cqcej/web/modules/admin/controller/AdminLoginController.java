package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.R;
import com.cqcej.web.common.utils.Utils;
import com.cqcej.web.modules.admin.entity.SysUserEntity;
import com.cqcej.web.modules.admin.form.SysLoginForm;
import com.cqcej.web.modules.admin.service.CaptchaService;
import com.cqcej.web.modules.admin.service.UserService;
import com.cqcej.web.modules.admin.service.UserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
@RequestMapping("/")
public class AdminLoginController extends AbstractController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserTokenService userTokenService;
	@Autowired
	private CaptchaService captchaService;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = captchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	@GetMapping("admin/login")
	public String login(Model model) {
		model.addAttribute("uuid", Utils.getUUID());
		return "admin/login";
	}

	/**
	 * 登录
	 */
	@PostMapping("admin/login")
	public R login(@RequestBody SysLoginForm form) throws IOException {
		boolean captcha = captchaService.validate(form.getUuid(), form.getCaptcha());
		if (!captcha) {
			return R.error("验证码不正确");
		}

		//用户信息
		SysUserEntity user = userService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if (user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = userTokenService.createToken(user.getUserId());
		return r;
	}

	/**
	 * 退出
	 */
	@PostMapping("/admin/logout")
	public R logout() {
		userTokenService.logout(getUserId());
		return R.ok();
	}
	
}
