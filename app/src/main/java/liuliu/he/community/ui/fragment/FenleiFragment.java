package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.method.volley.BitmapCache;
import liuliu.he.community.R;
import liuliu.he.community.adapter.RecycleAdapter;
import liuliu.he.community.adapter.RecycleViewHolder;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.fenlei.FenLeiListener;
import liuliu.he.community.control.fenlei.IFenLeiView;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.ImageDemo;
import liuliu.he.community.model.MyGridView;
import liuliu.he.community.test.DataAdapterBase;
import liuliu.he.community.test.ViewHolderBase;
import liuliu.he.community.ui.activity.MainActivity;

/**
 * 分类
 * Created by Administrator on 2015/11/25.
 */
public class FenleiFragment extends BaseFragment implements IFenLeiView {
    @CodeNote(id = R.id.fenlei_view)
    RecyclerView recyclerView;//商品分类
    @CodeNote(id = R.id.fenlei_grid_view)
    MyGridView gridview;
    Context mContext;
    List mDatas;
    int mGoodTypeClick;//被点击的项
    List<Button> good_type_list;
    FenLeiListener mListener;


    //商品分类详细
    List<String> mTypeDetailList;
    String[] detail = {"全部商品", "精选大米"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_fenlei, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = MainActivity.mIntails;
        mListener = new FenLeiListener(mContext, this);
        mQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mQueue, new BitmapCache());
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        good_type_list = new ArrayList<>();
        return viewRoot;
    }

    ImageLoader mImageLoader;
    private RequestQueue mQueue;
    DataAdapterBase adapterBase;
    in.srain.cube.image.ImageLoader imageLoader = null;

    private void refreshList(int position) {
        mTypeDetailList = new ArrayList<>();
        for (int s = 0; s < detail.length; s++) {
            mTypeDetailList.add(detail[s] + position);
        }
        imageLoader = ImageLoaderFactory.create(mContext);
        adapterBase = new DataAdapterBase<String>(mContext, R.layout.item_main_hot_good, ImageDemo.getSmallImages()) {
            @Override
            public void convert(ViewHolderBase holder, String url, int position) {
                holder.loadImage(R.id.good_iv, imageLoader, url);
            }
        };
        gridview.setAdapter(adapterBase);
        gridview.setNumColumns(3);
        adapterBase.notifyDataSetChanged();
    }

    /**
     * 加载所有数据
     * @param list 所有类型集合
     * @param type 顶部分类
     */
    @Override
    public void loadFenLei(List list[], final List type) {
//设置adapter
        recyclerView.setAdapter(
                new RecycleAdapter(mContext, type,
                        R.layout.recycle_view_item_good_type) {
                    @Override
                    public void convert(RecycleViewHolder holder, final List list, final int position) {
                        GoodTypeModel model= (GoodTypeModel) type.get(position);
                        Button btn = holder.getView(R.id.good_type_rv_button);
                        btn.setText(model.getTitle().toString());
                        btn.setBackgroundResource(R.mipmap.good_type_item);
                        good_type_list.add(btn);//将所有按钮添加到list中
                        if (position == mGoodTypeClick) {//设置置顶按钮被点击
                            btn.setBackgroundResource(R.mipmap.good_type_item_pressed);
                        }
                        holder.setOnClickListener(R.id.good_type_rv_button, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refreshList(position);
                                        good_type_list.get(mGoodTypeClick).setBackgroundResource(R.mipmap.good_type_item);
                                        good_type_list.get(position).setBackgroundResource(R.mipmap.good_type_item_pressed);
                                        mGoodTypeClick = position;
                                    }
                                }
                        );
                    }
                }

        );
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        refreshList(mGoodTypeClick);
    }
}
