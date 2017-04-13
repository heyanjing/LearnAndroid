package android.he.com.glide.singleton;

import android.util.Log;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Singleton implements RequestListener {
    private static final String TAG ="He"+ Singleton.class.getSimpleName();
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    @Override
    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
        Log.e(TAG, "onException: -----"+model+"----"+target+"----"+isFirstResource, e);
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
        Log.e(TAG, "onException: "+"-----"+resource+"-------"+model+"----"+target+"----"+isFromMemoryCache+"----"+isFirstResource, null);
        return false;
    }
}
