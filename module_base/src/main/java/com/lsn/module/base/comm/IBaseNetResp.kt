package com.lsn.module.base.comm

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/11
 *  Description
 *
 */
interface IBaseNetResp {

    fun onEmptyStatusResponse(tag: String, msg: String)

    fun onSuccess(
        tag: String, mode: String, isCache: Boolean, entity: Any
    )

    fun onFailed(mode: Int, msg: String)
}