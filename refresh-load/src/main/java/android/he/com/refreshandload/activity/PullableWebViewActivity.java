package android.he.com.refreshandload.activity;

import android.app.Activity;
import android.he.com.refreshandload.MyListener;
import android.he.com.refreshandload.PullToRefreshLayout;
import android.he.com.refreshandload.R;
import android.os.Bundle;
import android.webkit.WebView;


public class PullableWebViewActivity extends Activity
{
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener(this));
		webView = (WebView) findViewById(R.id.content_view);
		webView.loadUrl("http://blog.csdn.net/zhongkejingwang");
	}
}
