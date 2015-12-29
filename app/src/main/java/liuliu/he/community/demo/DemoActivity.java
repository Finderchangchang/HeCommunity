package liuliu.he.community.demo;

import android.widget.ImageView;
import android.widget.TextView;
import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;

/**
 * Created by Administrator on 2015/12/21.
 */
public class DemoActivity extends BaseActivity {
//    @CodeNote(id = R.id.iv)
    ImageView iv;

    String url = "http://www.iteye.com/upload/logo/blog_wiki/658539/b7c2eb31-a8ea-3973-a517-d00141f39b89.jpg?1333197277";

    @Override
    public void initViews() {
        setContentView(R.layout.activity_demo);
    }

    @Override
    public void initEvents() {
        finalBitmap.display(iv, url);//第一个参数为iamgeview组件，第二个为加载的url地址
    }
}
