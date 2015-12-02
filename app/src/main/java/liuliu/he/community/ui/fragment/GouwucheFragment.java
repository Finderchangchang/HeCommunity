package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.he.community.R;
import liuliu.he.community.test.DataAdapterBase;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.test.ViewHolderBase;
import liuliu.he.community.ui.activity.ListDemoActivity;
import liuliu.he.community.ui.demo.MyGridView;

/**
 * 购物车
 * Created by Administrator on 2015/11/25.
 */
public class GouwucheFragment extends BaseFragment {
    @CodeNote(id = R.id.good_list_grid_view)
    MyGridView good_list;
    Context mContext;
    List<GoodModel> mDatas;
    DataAdapterBase adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_gouwuche, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mDatas = new ArrayList<GoodModel>();
        mDatas.add(new GoodModel(0, "中秋礼品", "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg"
                , "$110.00", "101KG", 2));
        mDatas.add(new GoodModel(0, "米面粮油", "http://cdn.duitang.com/uploads/blog/201401/07/20140107223310_LH3Uy.thumb.jpeg"
                , "$120.00", "102KG", 2));
        mDatas.add(new GoodModel(0, "生鲜蔬菜", "http://img5.duitang.com/uploads/item/201405/09/20140509222156_kVexJ.thumb.jpeg"
                , "$130.00", "103KG", 2));
        mContext = ListDemoActivity.mIntails;
        final ImageLoader imageLoader = ImageLoaderFactory.create(mContext);
        adapter = new DataAdapterBase<GoodModel>(mContext, R.layout.item_gouwuche, mDatas) {
            @Override
            public void convert(ViewHolderBase holder, final GoodModel model, int position) {
                Button btn = (Button) holder.getView(R.id.count_jia_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDemoActivity.mIntails.mUtils.ToastShort("model:" + model.getGoodName());
                    }
                });
                holder.loadImage(R.id.good_img, imageLoader, model.getGoodImgUrl());
            }
        };
        good_list.setNumColumns(1);
        good_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return viewRoot;
    }
}
