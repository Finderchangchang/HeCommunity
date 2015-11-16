package liuliu.he.community.base;

import android.os.Bundle;
import android.os.StrictMode;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;

import liuliu.custom.method.Utils;

/**
 * BaseActivity声明相关通用方法
 * <p>
 * Created by LiuWeiJie on 2015/7/22 0022.
 * Email:1031066280@qq.com
 */
public abstract class BaseActivity extends FinalActivity {
    public FinalDb finalDb;
    public Utils mUtils;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finalDb = FinalDb.create(BaseActivity.this);
        initViews();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initEvents();
        mUtils = new Utils(this);
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
