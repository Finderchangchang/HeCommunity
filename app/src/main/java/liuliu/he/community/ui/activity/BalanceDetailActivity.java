package liuliu.he.community.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.TitleImagesModel;
import liuliu.he.community.view.MyItemView;

/**
 * Created by Administrator on 2015/12/10.
 */
public class BalanceDetailActivity extends BaseActivity {
    @CodeNote(id = R.id.banlance_detail_number)
    TextView number;
    @CodeNote(id = R.id.balance_detail_title)
    MyItemView title;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_balance_detail_layout);
        new Thread(new loadbalanceNumber()).start();
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

    class loadbalanceNumber implements Runnable {

        @Override
        public void run() {
            new VolloyTask(BalanceDetailActivity.this).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {
                    if (model.getReturnX().equals("OK")) {
                        String yue = model.getData().toString();
                        System.out.println("yue:" + yue);
                        number.setText(yue);
                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/user/cash.php");
        }
    }
}
