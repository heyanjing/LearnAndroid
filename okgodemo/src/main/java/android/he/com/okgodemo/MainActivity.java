package android.he.com.okgodemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.he.com.okgodemo.bean.PageResponse;
import android.he.com.okgodemo.bean.Person;
import android.he.com.okgodemo.bean.Result;
import android.he.com.okgodemo.callback.DialogCallback;
import android.he.com.okgodemo.callback.JsonCallback;
import android.he.com.okgodemo.utils.DateUtil;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends Activity {
    public static final String TAG = "He" + MainActivity.class.getSimpleName();
    String url = "http://192.168.70.110:8909/spring/person/pageByName";
    String uploadStrUrl = "http://192.168.70.110:8909/spring/person/savex";
    String uploadImgUrl = "http://192.168.70.110:8909/spring/upload/test";
    String downloadImgUrl = "http://192.168.70.110:8909/spring/static/img/test/f5.jpg";
    String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492071035615&di=a64941c6af633d55e2819c5720f604eb&imgtype=0&src=http%3A%2F%2Fi7.download.fd.pchome.net%2Ft_960x600%2Fg1%2FM00%2F0D%2F06%2FooYBAFSQ7e-IKtNAAAVVjC9sMPEAACKZACrFRAABVWk138.jpg";
    String imgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492071557060&di=b3288451d2158fc59bc8d9a2eee2da14&imgtype=0&src=http%3A%2F%2Fp5.image.hiapk.com%2Fuploads%2Fallimg%2F150331%2F7730-1503311G205-51.jpg";
    ListView lv;
    MyAdapter adapter;
    ImageView iv;
    public List<Person> content = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        iv = (ImageView) findViewById(R.id.iv);
        adapter = new MyAdapter();
        lv.setAdapter(adapter);
//        get(url);
//post(url);
//        uploadJson();
//    downImgFromNet();
//        showImg();
//        uploadImg();
//        downImg();
    }

    public void downImg(View view) {
        OkGo.get(downloadImgUrl)//
                .tag(this)//
                .execute(new FileCallback(getFilesDir().getAbsolutePath()+"/he","mm2copy.jpg") {

                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        iv.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        Toast.makeText(MainActivity.this, "downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed, Toast.LENGTH_SHORT).show();
                        System.out.println();

                    }
                });
    }
    public void uploadImg(View view) {
        File f=new File(getFilesDir().getAbsoluteFile() + "/he/mm2.jpg");
//        File f2=new File(getFilesDir().getAbsoluteFile() + "/he/f5.jpg");
        List<File> fList=new ArrayList<>();
        fList.add(f);
//        fList.add(f2);
        OkGo.post(uploadImgUrl)//
                .tag(this)//
//                .params("file1",new File("文件路径"))   //这种方式为一个key，对应一个文件
//                .params("file2",new File("文件路径"))
//                .params("file",f)
                .addFileParams("file", fList)           // 这种方式为同一个key，上传多个文件
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        Toast.makeText(MainActivity.this, "总大小:" +  Formatter.formatFileSize(getApplicationContext(), totalSize) + " 当前大小: " + Formatter.formatFileSize(getApplicationContext(), currentSize) + " 进度: " + progress + " 网速: " + Formatter.formatFileSize(getApplicationContext(), networkSpeed), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void showImg() {
        Bitmap bitmap = BitmapFactory.decodeFile(getFilesDir().getAbsoluteFile() + "/he/mm2.jpg");
        iv.setImageBitmap(bitmap);
    }

    public void downImgFromNet(View view) {
        OkGo.get(imgUrl2)//
                .tag(this)//
                .execute(new FileCallback(getFilesDir().getAbsolutePath()+"/he","mm2.jpg") {

                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        iv.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        Toast.makeText(MainActivity.this, "downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed, Toast.LENGTH_SHORT).show();

                    }
                });
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                File f = new File(getFilesDir().getAbsolutePath() + "/he");
//                if (!f.exists()) {
//                    f.mkdirs();
//                }
//                boolean b = DownloadImgUtils.downloadImgByUrl(imgUrl2, new File(f.getAbsolutePath() + "/mm2.jpg"));
//                if(b){
//                    showImg();
//                }
//            }
//        }).start();
    }




    public void uploadJson(View view) {
        Person p = new Person("okgo", 1, new Date());
        OkGo.post(uploadStrUrl)
                .tag(this)
                .params("json", JSON.toJSONString(p))//直接在web后台参数获取
//            .upJson(JSON.toJSONString(p))//web后台需要读取getContentLength ，从中获取
//                .isMultipart(true)
                .execute(new DialogCallback<Result>(this) {
                    @Override
                    public void onSuccess(Result result, Call call, Response response) {
                        Log.e(TAG, "onSuccess: " + result, null);
                        Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void get(View view) {
        OkGo.get(url)//
                .tag(this)//
                .params("name", "name1")//?name=name1&pageNumber=1&pageSize=10
                .params("pageNumber", "1")//?name=name1&pageNumber=1&pageSize=10
                .params("pageSize", "2")//?name=name1&pageNumber=1&pageSize=10
                .execute(new DialogCallback<PageResponse<Person>>(this) {
                    @Override
                    public void onSuccess(PageResponse<Person> personPageResponse, Call call, Response response) {
                        content = personPageResponse.getContent();
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void post(View view) {
        OkGo.post(url)//
                .tag(this)//
                .params("name", "name1")//?name=name1&pageNumber=1&pageSize=10
                .params("pageNumber", "1")//?name=name1&pageNumber=1&pageSize=10
                .params("pageSize", "2")//?name=name1&pageNumber=1&pageSize=10
                .execute(new DialogCallback<PageResponse<Person>>(this) {
                    @Override
                    public void onSuccess(PageResponse<Person> personPageResponse, Call call, Response response) {
                        content = personPageResponse.getContent();
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public Object getItem(int position) {
            return content.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.id = (TextView) convertView.findViewById(R.id.tv_id);
                viewHolder.age = (TextView) convertView.findViewById(R.id.tv_age);
                viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.birthday = (TextView) convertView.findViewById(R.id.tv_birthday);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Person person = content.get(position);
            viewHolder.id.setText(person.getId());
            viewHolder.name.setText(person.getName());
            viewHolder.age.setText(person.getAge() + "");
            viewHolder.birthday.setText(DateUtil.date2Str(person.getBirthday()));
            return convertView;
        }
    }

    class ViewHolder {
        public TextView id;
        public TextView name;
        public TextView age;
        public TextView birthday;
    }

}
