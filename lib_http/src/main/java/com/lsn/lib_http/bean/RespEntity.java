package com.lsn.lib_http.bean;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/4/21 16:07
 * Description
 */
public class RespEntity<T> extends BaseEntity {
    private T data;
    private String time;
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RespEntity{" +
                "data=" + data +
                ", time='" + time + '\'' +
                '}';
    }
}
