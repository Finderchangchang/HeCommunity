package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.control.refresh.LoadListView;
import liuliu.custom.control.refresh.LoadMoreListView;
import liuliu.he.community.R;
import liuliu.he.community.adapter.CommonAdapter;
import liuliu.he.community.adapter.CommonViewHolder;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.typelist.ITypeListView;
import liuliu.he.community.control.typelist.TypeListListener;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.ui.activity.DetailListsActivity;
import liuliu.he.community.view.MyItemView;

/**
 * 商品列表
 * Created by Administrator on 2015/12/10.
 */
public class TypeListFragment extends BaseFragment implements ITypeListView {
    @CodeNote(id = R.id.type_list_grid_view)
    LoadListView listView;
    @CodeNote(id = R.id.type_list_iv)
    MyItemView title;
    CommonAdapter adapterBase;
    ImageLoader imageLoader = null;
    Context mContext;
    TypeListListener listListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_type_list, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = DetailListsActivity.mIntails;
        listListener = new TypeListListener(this, mContext);
        listListener.loadList(term);
        imageLoader = ImageLoaderFactory.create(mContext);
        return viewRoot;
    }

    String term = "?page=1&number=5&bid=14";//查询显示的
    List<GoodModel> mGoods;
    private boolean isBottom = false;//是否滑动到最底部

    boolean isFirst;

    @Override
    public void loadGoodList(boolean result, List<GoodModel> list) {
        isBottom = result;
        refreshList(list);
//        listView.loadComplete();//关闭底部进度条
        isFirst = false;
    }

    private void refreshList(final List<GoodModel> list) {
        adapterBase = new CommonAdapter<GoodModel>(mContext, list, R.layout.item_good_desc) {

            @Override
            public void convert(CommonViewHolder holder, GoodModel goodModel, int position) {
                holder.loadImage(R.id.good_iv, imageLoader, goodModel.getImage());
                holder.setText(R.id.item_good_list_name, goodModel.getName());
            }
        };
        listView.setAdapter(adapterBase);
        listView.setOnLoadListener(new LoadListView.onLoadListener() {
            @Override
            public void onLoad() {
                listListener.loadList("?page=2&number=5&bid=14");
            }
        });
        adapterBase.notifyDataSetChanged();
    }
}
