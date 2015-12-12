package liuliu.he.community.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.ui.fragment.FenleiFragment;
import liuliu.he.community.ui.fragment.GouwucheFragment;
import liuliu.he.community.ui.fragment.ShouyeFragment;
import liuliu.he.community.ui.fragment.TypeListFragment;
import liuliu.he.community.ui.fragment.WodeFragment;

/**
 * 商品列表
 * Created by Administrator on 2015/12/10.
 */
public class DetailListsActivity extends BaseActivity {
    public static DetailListsActivity mIntails;
    @CodeNote(id = R.id.frag_ll)
    FrameLayout frameLayout;
    String param;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_detail_lists);
        mIntails = this;
        param = mUtils.IntentGet(getIntent(), "param");//获得传递过来的参数
    }

    @Override
    public void initEvents() {
        vv(0);
    }

    private void vv(int position) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        final FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.frag_ll, new TypeListFragment());
                break;
            case 1:
                break;
        }
        transaction.commit();
    }

    public String getParam() {
        return param;
    }
}
