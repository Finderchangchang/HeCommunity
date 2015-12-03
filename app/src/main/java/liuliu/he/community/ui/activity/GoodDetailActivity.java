package liuliu.he.community.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;

/**
 * 商品详情
 * Created by Administrator on 2015/12/2.
 */
public class GoodDetailActivity extends BaseActivity {
    public static GoodDetailActivity mIntails;
    @CodeNote(id = R.id.count_jia_btn, click = "onClick")
    Button count_jia_btn;
    @CodeNote(id = R.id.count_jian_btn, click = "onClick")
    Button count_jian_btn;
    @CodeNote(id = R.id.count_gouwuche_btn, click = "onClick")
    Button count_gouwuche_btn;
    @CodeNote(id = R.id.num_count_et)
    EditText num_count;
    int count;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_good_detail);
        mIntails = this;
    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.count_jia_btn:
                count = Integer.parseInt(num_count.getText().toString());
                num_count.setText((count + 1));
                break;
            case R.id.count_jian_btn:
                count = Integer.parseInt(num_count.getText().toString());
                if (count == 1) {
                    num_count.setText(count);
                } else {
                    num_count.setText((count - 1));
                }
                break;
            case R.id.count_gouwuche_btn:
                break;
        }
    }
}
