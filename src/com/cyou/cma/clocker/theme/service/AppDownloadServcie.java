
package com.cyou.cma.clocker.theme.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AppDownloadServcie extends Service {
    public static final String SERVICE_NAME = "appdownload";

    private String mDownloadUrl = "";
    private String mDownloadPath = "";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d("jiangibn", "tsd onCreate service");
    }

    // @Override
    // protected void onHandleIntent(Intent intent) {
    // DownLoadNotify
    // .registerDownLoadNotify(
    // this,
    // FileUtil.getFileKey("http://test.designer.c-launcher.com/resources/lockscreentheme/846/53ccfb6e0cf28d8468733f86/53ccfb6e0cf28d8468733f86_1405942668581.apk"),
    // "123");
    // Log.d("jiangibn", "ts after registerDownLoadNotify");
    // DownLoadUtil
    // .downLoadFile(
    // this,
    // "123",
    // "http://test.designer.c-launcher.com/resources/lockscreentheme/846/53ccfb6e0cf28d8468733f86/53ccfb6e0cf28d8468733f86_1405942668581.apk",
    // new File(Environment.getExternalStorageDirectory() + File.separator
    // + "test2.apk").getAbsolutePath(), new MyDownloadListener());
    // }

//    private static class MyDownloadListener extends DownLoadListener<File> {
//        @Override
//        public void onSuccess(File t, String key) {
//            super.onSuccess(t, key);
//            Log.d("jiangibn", "ts onSuccess");
//        }
//
//        @Override
//        public void onDownLoading(long count, long current, String key) {
//            super.onDownLoading(count, current, key);
//            Log.d("jiangibn", "ts onDownLoading");
//            DownLoadNotify.notifyProgress(key, (int) (current * 100 / count));
//        }
//
//        @Override
//        public void onFailure(int errorNo, String key) {
//            Log.d("jiangibn", "ts onFailure");
//            super.onFailure(errorNo, key);
//        }
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("jiangibn", "tsd onStartCommand service");
//        DownLoadNotify
//                .registerDownLoadNotify(
//                        this,
//                        FileUtil.getFileKey("http://test.designer.c-launcher.com/resources/lockscreentheme/846/53ccfb6e0cf28d8468733f86/53ccfb6e0cf28d8468733f86_1405942668581.apk"),
//                        "123");
//        Log.d("jiangibn", "ts after registerDownLoadNotify");
//        DownLoadUtil
//                .downLoadFile(
//                        this,
//                        "123",
//                        "http://test.designer.c-launcher.com/resources/lockscreentheme/846/53ccfb6e0cf28d8468733f86/53ccfb6e0cf28d8468733f86_1405942668581.apk",
//                        new File(Environment.getExternalStorageDirectory() + File.separator
//                                + "test2.apk").getAbsolutePath(), new MyDownloadListener());

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
