package com.lsn.lib_http.manager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/15
 * Description 处理所有请求是否中断
 */
public class ActNetManager {
    private ConcurrentHashMap<String, Boolean> actLiveMap = new ConcurrentHashMap<>(); // 标记Activity是否存活

    private ActNetManager() {
    }

    private static class getInstance {
        private static final ActNetManager manager = new ActNetManager();
    }

    public static ActNetManager get() {
        return ActNetManager.getInstance.manager;
    }

    public void markPageAlive(String actName) {
        actLiveMap.put(actName, true);
    }

    public void markPageDestroy(String actName) {
        actLiveMap.put(actName, false);
    }


    public ConcurrentHashMap<String, Boolean> getActLiveMap() {
        return actLiveMap;
    }

}
