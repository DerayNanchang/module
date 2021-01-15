package com.lsn.module.base.comm;



/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/3
 * Description
 */
public interface NCacheModelResponse<RESULT, EXCEPTION> {
    void onSuccess(RESULT result);

    void onFailed(EXCEPTION exception);
}
