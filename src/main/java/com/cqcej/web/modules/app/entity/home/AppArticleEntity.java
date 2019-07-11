package com.cqcej.web.modules.app.entity.home;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cqcej.web.modules.common.entity.ArticleEntity;
import io.swagger.annotations.ApiModel;

/**
 * 资讯
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
@TableName("ct_article")
@SuppressWarnings("unused")
@ApiModel("资讯详情")
public class AppArticleEntity extends ArticleEntity {

}
