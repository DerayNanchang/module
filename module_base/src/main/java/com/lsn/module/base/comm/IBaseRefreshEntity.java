package com.lsn.module.base.comm;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/2
 * Description
 */
public interface IBaseRefreshEntity {

    void setBody(InconstantView inconstantView);

    void setRefreshView(SwipeRefreshLayout srlView);
}
