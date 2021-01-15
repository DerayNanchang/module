package com.lsn.module.base.annotation;

import android.app.Activity;
import android.content.Context;

import com.lsn.module.base.R;
import com.lsn.module.base.utils.StatusBarUtil;
import com.lsn.module.base.utils.Toast;
import com.lsn.module.base.utils.comm.NetUtil;

import java.lang.reflect.Method;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public class YaoYaoAnnotation {

    private YaoYaoAnnotation() {

    }


    private static class getInstance {
        private static final YaoYaoAnnotation annotation = new YaoYaoAnnotation();
    }

    public static YaoYaoAnnotation get() {
        return getInstance.annotation;
    }


    public void initAnnotation(Context context) {
        //initView(context);
        // 状态栏
        initStatusTextColor(context);
        // 网络
        //initNetListen(context);
    }


    /**
     * 高版本的 Gradle 不可用，如果想用建议降到5.0+或以下版本
     *
     * @param context
     */
    private void initView(Context context) {
        try {
            Class<? extends Context> clazz = context.getClass();
            Method method = clazz.getMethod("setContentView", int.class);
            AntLayoutResId layoutResId = clazz.getAnnotation(AntLayoutResId.class);
            if (layoutResId != null) {
                method.invoke(context, layoutResId.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initNetListen(Context context) {
        if (context != null) {
            Class<? extends Context> clazz = context.getClass();
            AntNetListen netListen = clazz.getAnnotation(AntNetListen.class);
            if (netListen != null) {
                if (netListen.value()) {
                    // 开启网络监听
                    String networkType = NetUtil.netStr();
                    Toast.show(networkType);
                }
            }
        }
    }


    /**
     * 状态栏颜色及字体颜色
     *
     * @param context 上下文
     */
    private void initStatusTextColor(Context context) {
        if (context != null) {
            Class<? extends Context> clazz = context.getClass();
            AntStatusBarTextColor statusBarTextColor = clazz.getAnnotation(AntStatusBarTextColor.class);
            if (statusBarTextColor != null) {
                // TODO 要拓展则从后面继续添加配置
                if (statusBarTextColor.statusColor() == AntConstant.WHITE_COLOR) {
                    // 白色状态栏黑色字体
                    StatusBarUtil.setStatusBarMode((Activity) context, true, R.color.white);
                } else if (statusBarTextColor.statusColor() == AntConstant.BLACK_COLOR) {
                    // 黑色状态栏白色字体
                    StatusBarUtil.setStatusBarMode((Activity) context, false, R.color.black);
                } else if (statusBarTextColor.statusColor() == AntConstant.THEME_COLOR) {
                    // 可以配置主题色及字体颜色
                    StatusBarUtil.setStatusBarMode((Activity) context, false, R.color.theme_color);
                }
            } else {
                // 默认设置白色状态栏黑色字体
                StatusBarUtil.setStatusBarMode((Activity) context, true, R.color.white);
            }
        }
    }
}
