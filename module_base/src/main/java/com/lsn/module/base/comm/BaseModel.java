package com.lsn.module.base.comm;


import java.util.HashMap;
import java.util.Map;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public class BaseModel {


    protected enum Type {
        POST, GET;
    }

    public String actName;

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActName() {
        return actName;
    }

    // key 格式  基础路径 + 请求路径 + [参数=值][请求类型]
    private String getCacheKey(Type type, String baseUrl, String path, HashMap<String, String> parameters) {
        StringBuffer parametersStr = new StringBuffer();
        parametersStr.append(baseUrl);
        parametersStr.append(path);
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            parametersStr.append("[");
            parametersStr.append(parameter.getKey());
            parametersStr.append("=");
            parametersStr.append(parameter.getValue());
            parametersStr.append("]");
        }
        parametersStr.append("[");
        parametersStr.append(type);
        parametersStr.append("]");
        return parametersStr.toString();
    }

    private String getCacheKey(Type type, String baseUrl, String path) {
        StringBuffer parametersStr = new StringBuffer();
        parametersStr.append(baseUrl);
        parametersStr.append(path);
        return parametersStr.toString();
    }

    // todo 配置地址
 /*   protected String getCacheKey(Type type, String path, HashMap<String, String> parameters) {
        return getCacheKey(type, BuildConfig.API_SERVER_URL, path, parameters);
    }

    protected String getCacheKey(Type type, String path) {
        return getCacheKey(type, BuildConfig.API_SERVER_URL, path);
    }*/
    protected String getCacheKey(Type type, String path, HashMap<String, String> parameters) {
        return getCacheKey(type, "1", path, parameters);
    }

    protected String getCacheKey(Type type, String path) {
        return getCacheKey(type, "1", path);
    }

    protected String getCacheKeyGet(String path) {
        return getCacheKey(Type.GET, path);
    }

    protected String getCacheKeyGet(String path, HashMap<String, String> parameters) {
        return getCacheKey(Type.GET, path, parameters);
    }

    protected String getCacheKeyPost(String path, HashMap<String, String> parameters) {
        return getCacheKey(Type.POST, path, parameters);
    }
}
