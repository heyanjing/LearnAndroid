package android.he.com.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void play(View view) {
        Intent intent=new Intent(this,PlayService.class);
        intent.putExtra("action","play");
        startService(intent);
    }

    public void pause(View view) {
        Intent intent=new Intent(this,PlayService.class);
        intent.putExtra("action","pause");
        startService(intent);
    }

    public void stop(View view) {
        Intent intent=new Intent(this,PlayService.class);
        intent.putExtra("action","stop");
        startService(intent);
    }

    public void exit(View view) {
        Intent intent=new Intent(this,PlayService.class);
        stopService(intent);
        finish();

    }
}
