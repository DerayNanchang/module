package com.lsn.lib_dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lsn.lib_dialog.dialog.DialogLayer;
import com.lsn.lib_dialog.utils.Utils;


public class LayerActivity extends Activity implements Layer.OnVisibleChangeListener {

    @Nullable
    private static OnLayerCreatedCallback sOnLayerCreatedCallback = null;

    static void start(@NonNull Context context, OnLayerCreatedCallback callback) {
        sOnLayerCreatedCallback = callback;
        Intent intent = new Intent(context, LayerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        Utils.transparent(this);
        DialogLayer dialogLayer = AnyLayer.dialog(this);
        dialogLayer.onVisibleChangeListener(this);
        if (sOnLayerCreatedCallback != null) {
            sOnLayerCreatedCallback.onLayerCreated(dialogLayer);
        }
    }

    @Override
    public void onShow(@NonNull Layer layer) {
    }

    @Override
    public void onDismiss(@NonNull Layer layer) {
        finish();
        overridePendingTransition(0, 0);
    }

    public interface OnLayerCreatedCallback {
        /**
         * 浮层已创建，可在这里进行浮层的初始化和数据绑定
         */
        void onLayerCreated(@NonNull DialogLayer dialogLayer);
    }

}
