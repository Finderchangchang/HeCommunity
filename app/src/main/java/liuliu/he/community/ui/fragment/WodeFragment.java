package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import in.srain.cube.views.list.ListViewDataAdapter;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.ui.demo.ListDemoActivity;

/**
 * Created by Administrator on 2015/11/25.
 */
public class WodeFragment extends BaseFragment {
    @CodeNote(id = R.id.good_list_grid_view)
    GridView good_list_gv;
    ListViewDataAdapter adapter;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_wode, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = ListDemoActivity.mIntails;
//        ImageLoader imageLoader = ImageLoaderFactory.create(mContext);
//        adapter = new ListViewDataAdapter<String>();
//        adapter.setViewHolderClass(this, StringMiddleImageViewViewHolder.class, imageLoader);
//        adapter.getDataList().addAll(ImageDemo.getImages());
//        good_list_gv.setNumColumns(3);
//        good_list_gv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        return viewRoot;
    }
}
