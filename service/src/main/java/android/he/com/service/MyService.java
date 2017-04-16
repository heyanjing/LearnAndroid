package android.he.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/16.
 */

public class MyService extends Service {
    private static final String TAG ="He"+ MyService.class.getSimpleName();
    public MyService() {
        super();
        Log.i(TAG, "MyService: ");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    /**
     * 启动服务后而未关闭，再次启动时，直接进入该方法
     */
    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {

        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return new Binder();
    }
    /**
     * 当最后一个与该服务绑定的组件解绑时，才调用该方法
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
}
