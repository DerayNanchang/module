package com.lsn.module.base.comm;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lsn.base.Constants;
import com.lsn.module.base.R;
import com.lsn.module.base.annotation.YaoYaoAnnotation;
import com.lsn.module.base.manager.ActivityManager;
import com.lsn.module.base.receiver.NetConnectReceiver;
import com.lsn.module.base.utils.Toast;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2018/12/3
 * Description
 */
abstract public class ObsoleteBaseActivity extends AppCompatActivity implements IBaseNetResp {

    private NetConnectReceiver receiver;
    private View statusBarView;
    private SwipeRefreshLayout refreshLayout;
    private AlertDialog alertDialog;
    private View baseDialog;
    //private CompositeDisposable disposableList;
    private InconstantView inconstantView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityManager.get().addActivity(this);
        //NetManager.get().markPageAlive(getClass().getName());


        //disposableList = new CompositeDisposable();
        initWindow();
        super.onCreate(savedInstanceState);
        registerNetReceiver();
        YaoYaoAnnotation.get().initAnnotation(this);
        createDialog();
        init(savedInstanceState);
        initBaseEvent();
    }

    protected void setBaseTitle(String title) {
        if (findViewById(R.id.tvCommToolbarName) != null) {
            TextView name = findViewById(R.id.tvCommToolbarName);
            name.setText(title);
        }
    }

    private void initBaseEvent() {
        if (findViewById(R.id.rlCommToolbarBack) != null && isBaseFinish()) {
            findViewById(R.id.rlCommToolbarBack).setOnClickListener(v -> {
                onBackPressed();
            });
        }
    }

    protected boolean isBaseFinish() {
        return true;
    }

    /*protected void addDisposable(Disposable disposable) {
        if (disposable != null) {
            disposableList.add(disposable);
        }
    }*/

    protected String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    public String getTime(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    protected String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public AlertDialog getDialog() {
        return alertDialog;
    }


    private void initStatusBar() {
        if (statusBarView == null) {
            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
            statusBarView = getWindow().findViewById(identifier);
        }
        if (statusBarView != null) {
            statusBarView.setBackgroundDrawable(null);//在设置前将背景设置为null;
            /*statusBarView.setBackgroundResource(MVPConfig.statusDrawable);*/
        }
    }

/*    @NotNull
    @Override
    public String msg(int msg) {
        return "";
    }*/


    protected void initBody(InconstantView inconstantView) {
        // 添加空状态与无网络
        this.inconstantView = inconstantView;
        if (inconstantView != null) {
            inconstantView.addContent(R.layout.view_default_content);
            inconstantView.addEmptyState(R.layout.view_default_empty_state);
            inconstantView.addNoConnect(R.layout.view_default_no_connect);
            //inconstantView.addLoading(R.layout.view_custom_wrap_progress);
            inconstantView.addLoading(R.layout.view_comm_progress);
            inconstantView.setBodyTransform(InconstantView.Type.LOADING);
        }
    }



    @Override
    public void onEmptyStatusResponse(@NotNull String tag, @NotNull String msg) {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        dismissDialog();
        if (inconstantView != null) {
            inconstantView.setBodyTransform(InconstantView.Type.EMPTY_STATE);
        }
    }

    @Override
    public void onSuccess(String tag, String mode, boolean isCache, Object entity) {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        dismissDialog();
    }


    @Override
    public void onFailed(int mode, String msg) {
        // 取消刷新
        dismissDialog();
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        if (mode == Constants.Http.FAILED_CONNECT) {
            //Toast.show("电波无法到达服务器");
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.show(msg);
                }
            });
        }
    }

    protected void setRefreshView(SwipeRefreshLayout srlView) {
        refreshLayout = srlView;
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.theme_color));
    }

    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 过渡动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
    }


    private void registerNetReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            receiver = new NetConnectReceiver();
            //注册广播接收
            registerReceiver(receiver, filter);
        }
    }


    protected abstract void init(Bundle savedInstanceState);


    protected void createDialog() {
        baseDialog = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null, false);
        alertDialog = new AlertDialog.Builder(this).setView(baseDialog)
                .create();
    }

    protected void showDialog(String msg) {
        if (baseDialog == null) {
            baseDialog = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null, false);
        }
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this).setView(baseDialog)
                    .create();
        }
        alertDialog.show();
        updateWidth();
    }


    protected void showDialog() {
        if (baseDialog == null) {
            baseDialog = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null, false);
        }
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this).setView(baseDialog)
                    .create();
        }
        alertDialog.show();
        updateWidth();
    }

    private void updateWidth() {
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.width = getResources().getDimensionPixelSize(R.dimen.dp_200);
        lp.height = getResources().getDimensionPixelSize(R.dimen.dp_200);
        alertDialog.getWindow().setAttributes(lp);
    }


    protected void dismissDialog() {
        if (alertDialog != null) {
            if (baseDialog.getParent() != null) {
                if (baseDialog.getParent() instanceof ViewGroup) {
                    ((ViewGroup) baseDialog.getParent()).removeView(baseDialog);
                }
            }
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    public void copyText(String text, String desc) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

        Toast.show(desc);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
        /*if (disposableList != null) {
            disposableList.clear();
            disposableList = null;
        }
        if (receiver != null) unregisterReceiver(receiver);
        NetManager.get().markPageDestroy(getClass().getName());
        ActivityManager.get().removeActivity(this);*/
    }
}
