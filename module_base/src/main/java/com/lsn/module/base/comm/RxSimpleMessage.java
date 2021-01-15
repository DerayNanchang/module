package com.lsn.module.base.comm;

/**
 * Created by Chris on 2018/3/20.
 */

public class RxSimpleMessage<T> extends BaseMessage {

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
