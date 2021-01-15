package com.lsn.module.base.comm;

import android.text.TextUtils;
import com.lsn.lib_db.manager.CacheManager;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public abstract class BasePresenter<VIEW extends IBaseNetResp> {

    //private CompositeDisposable disposableList;
    public static String MODE_UPDATE = "update";
    public static String MODE_MORE = "more";
    public static int page = 1;
    public static int count = 10;
    public static boolean isCache = true;
    private VIEW view;

    public BasePresenter(VIEW view) {
        this.view = view;
        //disposableList = new CompositeDisposable();
    }

    /*protected void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposableList.add(disposable);
        }
    }*/

    protected <CACHE> Boolean setCache(String key, CACHE cache) {
        // 缓存数据
        if (isCache) {
            if (!TextUtils.isEmpty(key) && cache != null) {
                return CacheManager.get().setCache(key, cache);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /*public void destroy() {
        // 1. 解除所有的订阅
        if (disposableList != null) {
            disposableList.clear();
        }
        disposableList = null;
        // 2. 销毁 View 引用
        view = null;
        // 3. 子类记得要销毁 model 层的引用
    }*/


    public void setIsCache(boolean isCache) {
        this.isCache = isCache;
    }


    public boolean isCache() {
        return isCache;
    }
}
