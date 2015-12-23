/**
 * 
 */

package com.cyou.cma.clocker.theme.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.provider.CallLog;

/**
 * @author jiangbin
 */
public class KeyguardCallback implements KeyuardUnlock {
    private Activity mActivity;

    public KeyguardCallback(Context context) {
        // mContext = context;
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        } else {
            mActivity = (Activity) ((ContextWrapper) context).getBaseContext();
        }
    }

    public void pokeWakelock() {

    }

    public void pokeWakelock(int millis) {

    }

    @Override
    public void unlockScreen() {
        mActivity.finish();
        mActivity.overridePendingTransition(0, 0);

    }

    @Override
    public void unlockMessage(int count) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.setType("vnd.android-dir/mms-sms");
        try {
            mActivity.startActivity(intent);
            mActivity.overridePendingTransition(0, 0);
        } catch (Exception e) {

        }
        unlockScreen();

    }

    @Override
    public void unlockCall(int count) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType(CallLog.Calls.CONTENT_TYPE);

        try {
            mActivity.startActivity(intent);
            mActivity.overridePendingTransition(0, 0);
        } catch (Exception e) {
        }

        unlockScreen();
    }

}
