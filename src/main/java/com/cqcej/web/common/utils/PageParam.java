package com.cqcej.web.common.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用分页查询条件
 * 
 * @author: 61jun.com
 * @create: 2017-10-25
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;

    /**
     * Where Map (存放搜索条件, key对应实体的字段, value对应值)
     */
    private ObjectMap whereMap = new ObjectMap();

    /**
     * Sort Key (对应实体字段)
     */
    private String sortKey;

    /**
     * Sort Direction 1 正 0 逆
     */
    private Integer sortDirection = 0;

    // --- 以下字段非必须, 为自定义时需要 ---

    /**
     * 自定义where语句 (sql以AND开头)
     * 不提供原生set方法, 防止前端直接传值进行sql注入. 只能后端通过safeSetCustomWhereSql(sql)设置
     */
    private String customWhereSql;

    /**
     * 自定义select语句 sql以,开头
     */
    private String customSelectSql;

    /**
     * 自定义from语句 sql以,开头
     */
    private String customFromSql;

    /**
     * group key
     */
    private String groupKey;

    /**
     * data Map (存放一些额外的数据)
     */
    private ObjectMap dataMap;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ObjectMap getWhereMap() {
        return whereMap;
    }

    public void setWhereMap(ObjectMap whereMap) {
        this.whereMap = whereMap;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public Integer getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Integer sortDirection) {
        this.sortDirection = sortDirection;
    }

    /**
     * 设置正序排序
     */
    public void setSortAsc(){
        this.sortDirection = 1;
    }

    /**
     * 设置倒叙排序
     */
    public void setSortDesc(){
        this.sortDirection = 0;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public ObjectMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(ObjectMap dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * 转换为map
     */
    public Map<String, Object> toMap() {
        this.whereMap.put("sortKey", this.sortKey);
        this.whereMap.put("sortDirection", this.sortDirection);
        return whereMap;
    }

    /**
     * 自定义where语句的赋值
     */
    public PageParam safeSetCustomWhereSql(String sqlPart) {
        this.customWhereSql = sqlPart;
        return this;
    }

    /**
     * 自定义select语句的赋值
     */
    public PageParam safeSetCustomSelectSql(String sqlPart) {
        this.customSelectSql = sqlPart;
        return this;
    }

    /**
     * 自定义from语句的赋值
     */
    public PageParam safeSetCustomFromSql(String sqlPart) {
        this.customFromSql = sqlPart;
        return this;
    }

}
