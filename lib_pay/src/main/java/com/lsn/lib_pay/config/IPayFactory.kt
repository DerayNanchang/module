package com.lsn.lib_pay.config

import com.lsn.lib_pay.bean.AliPay
import com.lsn.lib_pay.bean.WeChat

/**
 *
 *  Author: lsn
 *  Blog: https://www.jianshu.com/u/a3534a2292e8
 *  Date: 2021/1/14
 *  Description
 *
 */
interface IPayFactory {
    fun createAliPay(): AliPay
    fun createWeChatPay(): WeChat
}