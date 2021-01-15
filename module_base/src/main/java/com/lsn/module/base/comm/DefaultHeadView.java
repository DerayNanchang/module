package com.lsn.module.base.comm;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lsn.module.base.R;
import com.lsn.module.base.annotation.AntLayoutResId;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/6/1
 * Description
 */
// todo 资源文件配置
@AntLayoutResId(1)
public class DefaultHeadView extends HeadOrFootItemView<String> {

    private TextView tvMsg;

    public DefaultHeadView(Context context) {
        super(context);
    }

    public DefaultHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tvMsg = itemView.findViewById(R.id.tvMsg);
    }

    @Override
    public void setMsg(int size) {
        if (size == 0) {
            tvMsg.setText(" 我也是有顶线的 ");
        } else {
            tvMsg.setText(" 正在加载数据 ... ");
        }
    }
}
