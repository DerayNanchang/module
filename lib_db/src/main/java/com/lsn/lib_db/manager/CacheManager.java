package com.lsn.lib_db.manager;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lsn.lib_db.bean.BHttpCache;
import com.lsn.lib_db.dao.BHttpCacheDao;

import java.util.List;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public class CacheManager {

    private static long DAY_MILLISECOND = 24 * 60 * 60 * 1000;
    private static long FOR_EVER = -1L;                        // 永久
    private static long THREE_DAYS = 3 * DAY_MILLISECOND;       // 3 天
    private static long Seven_Days = 7 * DAY_MILLISECOND;       // 7 天
    private static long THIRTY_DAYS = 30 * DAY_MILLISECOND;      // 30 天
    private static long M_180_DAYS = 180 * DAY_MILLISECOND;     // 180 天


    private long getDayMillisecond() {
        return 24 * 60 * 60 * 1000;
    }


    public long forEver() {
        return FOR_EVER;
    }


    public <ENTITY> Boolean setCache(String key, ENTITY entity) {
        try {
            BHttpCache cache = new BHttpCache(key, JSON.toJSONString(entity), (entity instanceof List), System.currentTimeMillis(), FOR_EVER);
            String cacheJSON = getCacheStr(key);
            // 返回是否插入了，前台凭这个判断是否需要更新数据
            if (!TextUtils.isEmpty(cacheJSON)) {
                if (cacheJSON.equals(cache.getJsonEntity())) {
                    //如果两次的数据一致，说明没有数据更新，就不插入了，也不刷新页面了
                    return false;
                } else {
                    // 数据不一致,就更新插入数据
                    DBManager.get().getCacheDao().insertOrReplace(cache);
                    return true;
                }
            } else {
                // 第一次插入
                DBManager.get().getCacheDao().insertOrReplace(cache);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCacheStr(String key) {
        BHttpCache cache = getCache(key);
        if (cache != null) {
            return cache.getJsonEntity();
        } else {
            return "";
        }
    }

    private BHttpCache getCache(String key) {
        return DBManager.get().getCacheDao().queryBuilder().where(BHttpCacheDao.Properties.Key.eq(key))
                .build().unique();
    }

    public Boolean isList(String key) {
        BHttpCache cache = getCache(key);
        if (cache != null) {
            return cache.getIsList();
        } else {
            return false;
        }
    }


    private CacheManager() {
    }

    private static class getInstance {
        private static CacheManager manager = new CacheManager();
    }

    public static CacheManager get() {
        return getInstance.manager;
    }


}
