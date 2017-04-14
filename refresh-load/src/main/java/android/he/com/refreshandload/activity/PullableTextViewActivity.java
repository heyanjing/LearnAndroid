package android.he.com.refreshandload.activity;

import android.app.Activity;
import android.he.com.refreshandload.MyListener;
import android.he.com.refreshandload.PullToRefreshLayout;
import android.he.com.refreshandload.R;
import android.os.Bundle;


public class PullableTextViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener(this));
	}
}
