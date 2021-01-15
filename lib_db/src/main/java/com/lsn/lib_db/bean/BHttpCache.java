package com.lsn.lib_db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/12
 * Description
 */
@Entity
public class BHttpCache {
    @Id
    private String key;
    private String jsonEntity;
    private Boolean isList;
    private Long lastTime;
    private Long storageTime;
    @Generated(hash = 883252217)
    public BHttpCache(String key, String jsonEntity, Boolean isList, Long lastTime,
            Long storageTime) {
        this.key = key;
        this.jsonEntity = jsonEntity;
        this.isList = isList;
        this.lastTime = lastTime;
        this.storageTime = storageTime;
    }
    @Generated(hash = 1435370686)
    public BHttpCache() {
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getJsonEntity() {
        return this.jsonEntity;
    }
    public void setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
    }
    public Boolean getIsList() {
        return this.isList;
    }
    public void setIsList(Boolean isList) {
        this.isList = isList;
    }
    public Long getLastTime() {
        return this.lastTime;
    }
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }
    public Long getStorageTime() {
        return this.storageTime;
    }
    public void setStorageTime(Long storageTime) {
        this.storageTime = storageTime;
    }
}
