package android.he.com.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    private static final String TAG = "He" + MainActivity.class.getSimpleName();
    ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void unbindService(View view) {
        if (connection != null) {
            unbindService(connection);
            connection = null;
        }
    }

    public void bindService(View view) {
        if (connection == null) {
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.i(TAG, "onServiceConnected: ");
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.i(TAG, "onServiceDisconnected: ");
                }
            };
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }

    public void play(View view) {
        startActivity(new Intent(this, PlayActivity.class));
    }
}
