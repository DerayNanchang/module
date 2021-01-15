package com.lsn.lib_share.bean;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/15
 * Description
 */
public class ShareMWXBean extends ShareBaseBean {
    private String compatibilityH5Path;
    private String appletId;

    public String getAppletId() {
        return appletId;
    }

    public void setAppletId(String appletId) {
        this.appletId = appletId;
    }

    public String getCompatibilityH5Path() {
        return compatibilityH5Path;
    }

    public void setCompatibilityH5Path(String compatibilityH5Path) {
        this.compatibilityH5Path = compatibilityH5Path;
    }
}
