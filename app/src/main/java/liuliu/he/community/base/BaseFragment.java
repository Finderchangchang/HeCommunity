package liuliu.he.community.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tsz.afinal.FinalActivity;

import liuliu.he.community.R;

/**
 * Created by Administrator on 2015/11/25.
 */
public abstract class BaseFragment extends Fragment {
    int mLayId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        View viewRoot = inflater.inflate(mLayId, container, false);
        FinalActivity.initInjectedView(this, viewRoot);

        initEvents();
        return viewRoot;
    }

    public abstract void initViews();

    public abstract void initEvents();

    public void setContentView(int layoutId) {
        mLayId = layoutId;
    }
}
