package com.cqcej.web.modules.index;

import com.cqcej.web.common.utils.DateUtils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.common.entity.ArticleEntity;
import com.cqcej.web.modules.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 默认信息
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-19 11:18
 */
@Controller
@RequestMapping("/")
@Login(allowAnonymous = true)
public class IndexController {
	@Autowired
	private ArticleService articleService;
	

	@GetMapping("index")
	public @ResponseBody String index() {
		return "宠它接口系统,最后更新于2019年2月12日14:11:38";
	}
	
	@RequestMapping(value = "app/article/detail/{id}", method = RequestMethod.GET)
	public String articleDetail(@PathVariable Long id, Model model) {
		ArticleEntity article = articleService.selectById(id);
		
		// 增加浏览次数
		ArticleEntity update = new ArticleEntity();
		update.setArticleId(id);
		update.setViewCount(article.getViewCount() + 1);
		articleService.updateById(update);
		
		model.addAttribute("title", article.getArticleTitle());
		model.addAttribute("content", article.getArticleContent());
		model.addAttribute("date", DateUtils.format(article.getCreateAt(), "YYYY-MM-dd HH:mm:ss"));
		return "detail";
	}
}
