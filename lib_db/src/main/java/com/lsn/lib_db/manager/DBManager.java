package com.lsn.lib_db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lsn.lib_db.DBConstant;
import com.lsn.lib_db.dao.BHttpCacheDao;
import com.lsn.lib_db.dao.DaoMaster;
import com.lsn.lib_db.dao.DaoSession;


/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
public class DBManager {

    public final static String DB_NAME = DBConstant.NAME;
    private DaoSession mDaoSession;

    public void init(Context context) {

        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    public BHttpCacheDao getCacheDao() {
        return mDaoSession.getBHttpCacheDao();
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    private DBManager() {
    }

    private static class getInstance {
        private static DBManager manager = new DBManager();
    }

    public static DBManager get() {
        return getInstance.manager;
    }
}
