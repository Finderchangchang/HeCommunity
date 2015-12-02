package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tsz.afinal.FinalActivity;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.ui.activity.ListDemoActivity;

/**
 * Created by Administrator on 2015/11/25.
 */
public class WodeFragment extends BaseFragment {
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_wode, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = ListDemoActivity.mIntails;
        return viewRoot;
    }
}
