package com.lsn.module.base.comm;

import androidx.fragment.app.Fragment;

/**
 * Author: Chris
 * Blog: https://www.jianshu.com/u/a3534a2292e8
 * Date: 2020/7/2
 * Description
 */
public abstract class BaseDialogFragment extends Fragment implements IBaseDialogEntity {
    @Override
    public void showDialog() {
        if (getActivity() != null) {
            if (getActivity() instanceof ObsoleteBaseActivity) {
                ObsoleteBaseActivity activity = (ObsoleteBaseActivity) getActivity();
                if (activity.getDialog() != null) {
                    activity.showDialog();
                }
            }
        }
    }

    @Override
    public void dismissDialog() {
        if (getActivity() != null) {
            if (getActivity() instanceof ObsoleteBaseActivity) {
                ObsoleteBaseActivity activity = (ObsoleteBaseActivity) getActivity();
                if (activity.getDialog() != null) {
                    activity.dismissDialog();
                }
            }
        }
    }
}
