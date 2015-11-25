package liuliu.he.community.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.ui.fragment.ShouyeFragment;

/**
 * Created by Administrator on 2015/11/25.
 */
public class FragActivity extends BaseActivity {
    @CodeNote(id = R.id.frag_ll)
    FrameLayout frag_ll;
    ShouyeFragment shoye;

    @Override
    public void initViews() {
        setContentView(R.layout.frag);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        shoye = new ShouyeFragment();
        transaction.replace(R.id.frag_ll, shoye);
        transaction.commit();
    }

    @Override
    public void initEvents() {

    }
}
