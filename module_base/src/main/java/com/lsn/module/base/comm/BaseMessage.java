package com.lsn.module.base.comm;

/**
 * Created by Chris on 2018/3/20.
 */

public abstract class BaseMessage {

    int MessageTag;

    public int getMessageTag() {
        return MessageTag;
    }

    public void setMessageTag(int messageTag) {
        MessageTag = messageTag;
    }
}
