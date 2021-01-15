package com.lsn.base;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/12
 * Description
 */
public interface Constants {

    interface DB {


    }

    public interface Net {
        public int BASE_TIME = 1000;

        public boolean IS_SSL = true;          // 加载SSL
        public boolean IS_CONN_ACT = true;     // 是否与activity建立联系(当Activity 退出是就终止请求)
        public int FAILED_SERVICE = 1;         // 请求成功，但是返回失败
        public int FAILED_CONNECT = 2;         // 请求失败

        public int CONNECT_TIMEOUT = BASE_TIME;             // 连接时间设置
        public int READ_TIMEOUT = BASE_TIME;                // 读取时间设置
        public int WRITE_TIMEOUT = BASE_TIME;               // 写入时间设置

    }


    public interface Ant {
        // 状态栏颜色
        public int WHITE_COLOR = 0;    // 白色
        public int BLACK_COLOR = 1;    // 黑色
        public int THEME_COLOR = -1;   // 主题色
    }


    public interface Comm {

        public int themeColor = 3;


        // 传值Key
        public String KEY_STR_ID = "STR_ID";
        public String KEY_STR_ID_2 = "STR_ID_2";
        public String KEY_STR_ID_3 = "STR_ID_3";
        public String KEY_INT_ID = "INT_ID";
        public String KEY_INT_ID_2 = "INT_ID_2";
        public String KEY_INT_ID_3 = "INT_ID_3";
        public String LONG_ID = "LONG_ID";
        public String LONG_ID_2 = "LONG_ID_2";
        public String BOOLEAN_ID = "BOOLEAN_ID";
        public String BOOLEAN_ID_2 = "BOOLEAN_ID_2";
        public String STR_LIST_1 = "STR_LIST_1";
        public String STR_LIST_2 = "STR_LIST_2";

        public String JD = "JD";
        public String PDD = "PDD";
        public String TB = "TB";
    }


    public interface Http {
        public int FAILED_SERVICE = 1; // 请求成功，但是返回失败
        public int FAILED_CONNECT = 2; // 请求失败
    }
}
