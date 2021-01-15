package com.lsn.lib_pay;

import com.alipay.sdk.app.PayTask;
import com.lsn.lib_pay.alipay.h5.MH5PayResultModel;

/**
 * Author: lsn
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2021/1/14
 * Description
 */
public class PayClient {

    public boolean payInterceptorWithUrl(Object mPayTask, String url, boolean isShowPayLoading, MH5PayCallback mh5PayCallback) {

        final PayTask task = (PayTask) mPayTask;
        return task.payInterceptorWithUrl(url, isShowPayLoading, result -> {
            MH5PayResultModel mh5PayResultModel = new MH5PayResultModel(result.getReturnUrl(), result.getResultCode());
            mh5PayCallback.onPayResult(mh5PayResultModel);
        });
    }


    public interface MH5PayCallback {
        void onPayResult(MH5PayResultModel result);
    }


    private PayClient() {

    }

    private static class getInstance {
        private static final PayClient manager = new PayClient();
    }

    public static PayClient get() {
        return PayClient.getInstance.manager;
    }
}
