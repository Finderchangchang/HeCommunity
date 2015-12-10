package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.typelist.ITypeListView;
import liuliu.he.community.control.typelist.TypeListListener;
import liuliu.he.community.model.GoodListModel;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.MyGridView;
import liuliu.he.community.test.DataAdapterBase;
import liuliu.he.community.test.ViewHolderBase;
import liuliu.he.community.ui.activity.DetailListsActivity;
import liuliu.he.community.view.MyItemView;

/**
 * 商品列表
 * Created by Administrator on 2015/12/10.
 */
public class TypeListFragment extends BaseFragment implements ITypeListView {
    @CodeNote(id = R.id.type_list_grid_view)
    MyGridView gridview;
    @CodeNote(id = R.id.type_list_iv)
    MyItemView title;
    DataAdapterBase adapterBase;
    ImageLoader imageLoader = null;
    Context mContext;
    TypeListListener listListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_type_list, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = DetailListsActivity.mIntails;
        listListener = new TypeListListener(this, mContext);
        imageLoader = ImageLoaderFactory.create(mContext);
        return viewRoot;
    }

    private void refreshList(List list) {
        adapterBase = new DataAdapterBase<GoodTypeModel>(mContext, R.layout.item_good_types, list) {
            @Override
            public void convert(ViewHolderBase holder, GoodTypeModel model, int position) {
                holder.loadImage(R.id.good_iv, imageLoader, model.getImage());
                String title = "全部商品";
                if (position != 0) {
                    title = model.getTitle();
                }
                holder.setText(R.id.good_name_tv, title);
            }
        };
        gridview.setAdapter(adapterBase);
        gridview.setNumColumns(3);
        adapterBase.notifyDataSetChanged();
    }

    List<GoodModel> mGoods;
    private boolean isBottom = false;//是否滑动到最底部

    @Override
    public void loadGoodList(boolean result, List<GoodModel> list) {
        isBottom = result;
        for (GoodModel model : list) {//执行数据刷新操作
            mGoods.add(model);
        }
    }
}
