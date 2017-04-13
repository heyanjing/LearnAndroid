package android.he.com.okgodemo.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/12.
 */

public class BaseBean implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
