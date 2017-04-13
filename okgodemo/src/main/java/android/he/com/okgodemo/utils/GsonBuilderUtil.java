package android.he.com.okgodemo.utils;

import android.he.com.okgodemo.gson.DateDeserializer;
import android.he.com.okgodemo.gson.DateSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * Created by Administrator on 2017/4/12.
 */

public class GsonBuilderUtil {

    public static Gson create() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        Gson gson = gb.create();
        return gson;
    }
}
