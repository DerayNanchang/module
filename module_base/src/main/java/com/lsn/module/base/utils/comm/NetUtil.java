package com.lsn.module.base.utils.comm;

import com.lsn.lib_util.util.NetworkUtils;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/11
 * Description
 */
public class NetUtil {

    public static String netStr() {

        switch (NetworkUtils.getNetworkType()) {
            case NETWORK_2G:
                return "正在使用2G网络，注意流量";
            case NETWORK_3G:
                return "正在使用3G网络，注意流量";
            case NETWORK_4G:
                return "正在使用4G网络，注意流量";
            case NETWORK_5G:
                return "正在使用5G网络，注意流量";
            case NETWORK_WIFI:
                return "正在使用WIFI";
            case NETWORK_ETHERNET:
                return "正在使用以太网";
            case NETWORK_UNKNOWN:
                return "未知网络";
            case NETWORK_NO:
                return "已断开光波";
        }
        return "未知网络";
    }
}
