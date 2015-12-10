package liuliu.he.community.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import net.tsz.afinal.annotation.view.CodeNote;


import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.view.MyItemView;

/**
 * Created by Administrator on 2015/12/10.
 */
public class AddressListActivity extends BaseActivity {
    @CodeNote(id = R.id.address_list_title)
    MyItemView title;
    @CodeNote(id = R.id.address_list_add)
    Button add;//添加地址
    @CodeNote(id = R.id.address_list_lv)
    ListView lv;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_address_list);
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
    private void setData(){

    }
}
