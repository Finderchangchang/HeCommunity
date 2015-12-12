package liuliu.he.community.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import org.json.JSONException;
import org.json.JSONObject;

import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.TitleImagesModel;
import liuliu.he.community.model.WoDeModel;
import liuliu.he.community.view.MyItemView;

/**
 * 积分详细页面显示
 * Created by Administrator on 2015/12/10.
 */
public class JiFenDetailActivity extends BaseActivity {
    @CodeNote(id = R.id.jifen_detail_number)
    TextView number;
    @CodeNote(id = R.id.jifen_detail_title)
    MyItemView title;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_jifen_detail_layout);
        new Thread(new loadfifenNumber()).start();
    }

    @Override
    public void initEvents() {
        title.setRightImageGone();
        title.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.IntentPost(MainActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("BottomId", "2");
                    }
                });
                finish();
            }
        });
    }


    class loadfifenNumber implements Runnable {

        @Override
        public void run() {
            new VolloyTask(JiFenDetailActivity.this).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {
                    if (model.isReturnX()) {
                        String jifen = model.getData().toString();
                        System.out.println("jifen:" + jifen);
                        number.setText(jifen);
                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/user/point.php");


        }
    }
}
