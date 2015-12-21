package liuliu.he.community.base;

import android.os.Bundle;
import android.os.StrictMode;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;

import liuliu.custom.method.Utils;
import liuliu.he.community.R;

/**
 * BaseActivity声明相关通用方法
 * <p/>
 * Created by LiuWeiJie on 2015/7/22 0022.
 * Email:1031066280@qq.com
 */
public abstract class BaseActivity extends FinalActivity {
    public FinalDb finalDb;
    public Utils mUtils;
    public FinalBitmap finalBitmap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finalDb = FinalDb.create(BaseActivity.this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mUtils = new Utils(this);
        initViews();
        initDatas();
        initEvents();
    }

    /**
     * 加载图片缓存
     */
    private void initDatas() {
        finalBitmap = FinalBitmap.create(this);//初始化
        finalBitmap.configBitmapLoadThreadSize(3);//定义线程数量
        String path = this.getFilesDir().toString();
        finalBitmap.configDiskCachePath(path);//设置缓存目录；
        finalBitmap.configDiskCacheSize(1024 * 1024 * 10);//设置缓存大小
        finalBitmap.configLoadingImage(R.mipmap.fenlei_normal);//设置加载图片
    }

    public abstract void initViews();

    public abstract void initEvents();

    /*activity销毁处理*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
