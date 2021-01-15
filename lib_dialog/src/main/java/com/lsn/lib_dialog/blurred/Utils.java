package com.lsn.lib_dialog.blurred;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/12
 * Description
 */
class Utils {

    static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    static <T> T requireNonNull(T obj) {
        return requireNonNull(obj, "");
    }
}
