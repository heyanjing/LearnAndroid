package android.he.com.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service {
    MediaPlayer mediaPlayer;

    public PlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getStringExtra("action");
        if ("play".equals(action)) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.a);
            }
            mediaPlayer.start();
        } else if ("pause".equals(action)) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } else if ("stop".equals(action)) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();//mediaPlayer对象不为空但是内部的媒体资源不存在了
                mediaPlayer = null;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();//mediaPlayer对象不为空但是内部的媒体资源不存在了
            mediaPlayer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
