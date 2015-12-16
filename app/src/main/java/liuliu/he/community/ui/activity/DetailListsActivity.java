package liuliu.he.community.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.ui.last_frag.GoodDetailFragment;
import liuliu.he.community.ui.last_frag.HelpFragment;
import liuliu.he.community.ui.last_frag.TypeListFragment;

/**
 * 点击进入的页面的Fragment
 * Created by Administrator on 2015/12/10.
 */
public class DetailListsActivity extends BaseActivity {
    public static DetailListsActivity mIntails;
    @CodeNote(id = R.id.frag_ll)
    FrameLayout fra;
    @CodeNote(id = R.id.total_botton)
    LinearLayout bottom_ll;
    String mDesc;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_detail_lists);
        mIntails = this;
        mDesc = mUtils.IntentGet(getIntent(), "desc");//获得传递过来的参数
    }

    @Override
    public void initEvents() {
        // 开启Fragment事务
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        bottom_ll.setVisibility(View.VISIBLE);
        switch (mDesc.split("\\?")[0]) {
            case "spfl"://跳转到商品分类列表
                transaction.replace(R.id.frag_ll, new TypeListFragment());
                break;
            case "xq"://跳转到商品详情页面
                transaction.replace(R.id.frag_ll, new GoodDetailFragment());
                bottom_ll.setVisibility(View.GONE);
                break;
            case "help"://跳转到帮助中心页面
                transaction.replace(R.id.frag_ll, new HelpFragment());
                break;

        }
        transaction.commit();
    }


    /**
     * 1.跳转到详情页面（xq=123）
     */
    public String getDesc() {
        return mDesc;
    }
}
