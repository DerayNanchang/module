package com.lsn.lib_dialog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lsn.lib_dialog.blurred.Blurred;
import com.lsn.lib_dialog.dialog.DialogLayer;
import com.lsn.lib_dialog.floats.FloatLayer;
import com.lsn.lib_dialog.guide.GuideLayer;
import com.lsn.lib_dialog.notification.NotificationLayer;
import com.lsn.lib_dialog.popup.PopupLayer;
import com.lsn.lib_dialog.toast.ToastLayer;


public final class AnyLayer {

    public static void init(@NonNull Application application) {
        ActivityHolder.init(application);
    }

    public static void initBlurred(@NonNull Context context) {
        Blurred.init(context);
    }

    public static void recycleBlurred() {
        Blurred.recycle();
    }

    @NonNull
    public static GlobalConfig globalConfig() {
        return GlobalConfig.get();
    }

    public static void dialog(@NonNull LayerActivity.OnLayerCreatedCallback callback) {
        LayerActivity.start(ActivityHolder.getApplication(), callback);
    }

    @NonNull
    public static DialogLayer dialog() {
        return new DialogLayer(ActivityHolder.requireCurrentActivity());
    }

    @NonNull
    public static DialogLayer dialog(@NonNull Class<Activity> clazz) {
        return new DialogLayer(ActivityHolder.requireActivity(clazz));
    }

    @NonNull
    public static DialogLayer dialog(@NonNull Context context) {
        return new DialogLayer(context);
    }

    @NonNull
    public static PopupLayer popup(@NonNull Context context) {
        return new PopupLayer(context);
    }

    @NonNull
    public static PopupLayer popup(@NonNull View targetView) {
        return new PopupLayer(targetView);
    }

    @NonNull
    public static ToastLayer toast() {
        return new ToastLayer(ActivityHolder.requireCurrentActivity());
    }

    @NonNull
    public static ToastLayer toast(@NonNull Context context) {
        return new ToastLayer(context);
    }

    @NonNull
    public static FloatLayer floats(@NonNull Context context) {
        return new FloatLayer(context);
    }

    @NonNull
    public static GuideLayer guide(@NonNull Context context) {
        return new GuideLayer(context);
    }

    @NonNull
    public static NotificationLayer notification(@NonNull Context context) {
        return new NotificationLayer(context);
    }

}
