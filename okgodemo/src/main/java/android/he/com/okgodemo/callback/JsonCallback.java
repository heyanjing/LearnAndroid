package android.he.com.okgodemo.callback;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private static final String TAG ="He"+ JsonCallback.class.getSimpleName();

    @Override
    public T convertSuccess(Response response) throws Exception {
        //android.he.com.okgodemo.callback.DialogCallback<android.he.com.okgodemo.bean.PageResponse<android.he.com.okgodemo.bean.Person>>得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        // android.he.com.okgodemo.bean.PageResponse<android.he.com.okgodemo.bean.Person>
        Type type = params[0];//相当于泛型参数T

        // 这里这么写的原因是，我们需要保证上面我解析到的type泛型，仍然还具有一层参数化的泛型，也就是两层泛型
        // 如果你不喜欢这么写，不喜欢传递两层泛型，那么以下两行代码不用写，并且javabean按照
        // https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md 这里的第一种方式定义就可以实现
        if (type instanceof ParameterizedType){
            //如果确实还有泛型，那么我们需要取出真实的泛型，得到如下结果
            //class com.lzy.demo.model.LzyResponse
            //此时，rawType的类型实际上是 class，但 Class 实现了 Type 接口，所以我们用 Type 接收没有问题
            //class android.he.com.okgodemo.bean.PageResponse
            Type rawType = ((ParameterizedType) type).getRawType();//相当于T的类型
            //这里获取最终内部泛型的类型  class android.he.com.okgodemo.bean.Person
            Type typeArgument0 = ((ParameterizedType) type).getActualTypeArguments()[0];//T里面的泛型
        }

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        String result=response.body().string();
//        JsonReader jsonReader = new JsonReader(response.body().charStream());
        Log.e(TAG, "convertSuccess: "+result,null );
//        if(rawType.equals(PageResponse.class)){
            response.close();
            return (T)JSON.parseObject(result,type);
//        }else if(rawType==Result.class){
//            return
//        }else {
//            throw new IllegalStateException("基类错误无法解析!");
//        }
    }
}
