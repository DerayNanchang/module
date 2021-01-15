package com.lsn.module.base.libs.router

/**
 *
 *  Author: lsener
 *  Date: 2020/12/30
 *  Description
 *
 */
object RouterPaths {

    private const val ACT = "/act"  // activity
    private const val FRA = "/fra"  // fragment

    // 引导页相关
    private const val MODULE_WELCOME = "/welcome"
    const val ACT_PATH_WELCOME = ACT + MODULE_WELCOME
    const val ACT_PATH_WELCOME_INTRO = ACT_PATH_WELCOME + "/intro"

    // 用户模块
    private const val USER = "/user"
    private const val ACT_PATH_USER = ACT + USER
    const val ACT_PATH_USER_REGISTER = ACT_PATH_USER + "/register"
    const val ACT_PATH_USER_LOGIN = ACT_PATH_USER + "/login"


    // Web
    private const val WEB = "/web"
    const val ACT_WEB_COMM = ACT + WEB + "/comm"


}