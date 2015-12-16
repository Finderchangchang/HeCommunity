package liuliu.he.community.ui.last_frag;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.control.refresh.LoadListView;
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
public class TypeListFragment extends BaseFragment implements ITypeListView<GoodModel> {
    @CodeNote(id = R.id.type_list_grid_view)
    LoadListView listView;
    @CodeNote(id = R.id.type_list_iv)
    MyItemView title;
    CommonAdapter adapterBase;
    ImageLoader imageLoader = null;
    TypeListListener<GoodModel> listListener;
    int page = 1;
    DetailListsActivity mIntails;
    String term = "";//查询显示的
    List<GoodModel> mGoods;
    private boolean isBottom = false;//是否滑动到最底部


    @Override
    public void initViews() {
        setContentView(R.layout.frag_type_list);
        mIntails = DetailListsActivity.mIntails;
        listListener = new TypeListListener(this, mIntails);
        term = "?page=" + page + "&number=10&" + mIntails.getDesc().split("\\?")[1];
        listListener.loadList(term, "GoodModel");//页面加载的时候加载数据
        mGoods = new ArrayList<>();
        imageLoader = ImageLoaderFactory.create(mIntails);
    }

    @Override
    public void initEvents() {

    }

    /**
     * 加载商品信息集合
     *
     * @param result 是否滑动到底部（true,到达底部。false,没有到达底部）
     * @param list   需要加载的数据
     */
    @Override
    public void loadGoodList(boolean result, List<GoodModel> list) {
        isBottom = result;
        for (GoodModel model : list) {
            mGoods.add(model);
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                showList(mGoods);
                page++;
                listView.loadComplete(isBottom);//关闭底部进度条
            }
        }, 3000);
    }

    /**
     * 用来显示数据
     *
     * @param list 商品信息集合
     */
    private void showList(List<GoodModel> list) {
        if (adapterBase == null) {
            adapterBase = new CommonAdapter<GoodModel>(mIntails, list, R.layout.item_good_desc) {
                @Override
                public void convert(CommonViewHolder holder, GoodModel goodModel, int position) {
                    holder.loadImage(R.id.good_iv, imageLoader, goodModel.getImage());
                    holder.setText(R.id.item_good_list_name, goodModel.getName());
                    holder.setText(R.id.item_good_list_desc, goodModel.getFeature());
                    holder.setText(R.id.item_good_list_month_sales, goodModel.getSales());
                    holder.setText(R.id.item_good_list_stock, goodModel.getStock());
                    holder.setText(R.id.item_good_list_price, goodModel.getPrice());
                }
            };
            listView.setAdapter(adapterBase);
            //上划加载更多数据
            listView.setOnLoadListener(new LoadListView.onLoadListener() {
                @Override
                public void onLoad() {
                    if (!isBottom) {
                        listListener.loadList("?page=" + page + "&number=10&" + mIntails.getDesc().split("\\?")[1], "GoodModel");
                    }
                }
            });
            adapterBase.notifyDataSetChanged();
        } else {
            adapterBase.notifyDataSetChanged();
        }
    }
}
