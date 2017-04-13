package android.he.com.okgodemo.callback;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.lzy.okgo.request.BaseRequest;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class DialogCallback<T> extends JsonCallback<T> {
    private ProgressDialog dialog;

    private void initDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
    public DialogCallback(Context context) {
        initDialog(context);
    }

    @Override
    public void onBefore(BaseRequest request) {
        dialog.show();
        super.onBefore(request);
    }

    @Override
    public void onAfter(T o, Exception e) {
        super.onAfter(o, e);
        dialog.dismiss();
    }
}
