package liuliu.he.community.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;

/**
 * Created by Administrator on 2015/11/25.
 */
public class WodeFragment extends BaseFragment {
    @CodeNote(id = R.id.wode_tv)
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.wode_frag, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        tv.setText("我的1");
        return viewRoot;
    }
}
