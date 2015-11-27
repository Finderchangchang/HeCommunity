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
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.custom.method.volley.BitmapCache;
import liuliu.he.community.R;
import liuliu.he.community.adapter.RecycleAdapter;
import liuliu.he.community.adapter.RecycleViewHolder;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.ui.activity.FragActivity;
import liuliu.he.community.view.FullyGridLayoutManager;
import liuliu.he.community.view.FullyLinearLayoutManager;

/**
 * 分类
 * Created by Administrator on 2015/11/25.
 */
public class FenleiFragment extends BaseFragment {
    @CodeNote(id = R.id.fenlei_view)
    RecyclerView recyclerView;//商品分类
    @CodeNote(id = R.id.fenlei_good_type_detail_view)
    RecyclerView good_type_detail;//商品详细分类
    Context mContext;
    List mDatas;
    int mGoodTypeClick;//被点击的项
    List<Button> good_type_list;


    //商品分类详细
    List<String> mTypeDetailList;
    String[] detail = {"全部商品", "精选大米"};
//    String[] detail = {"全部商品", "精选大米", "面粉挂面", "食用油", "五谷杂粮", "主食"
//            , "调味品"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fenlei_frag, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = FragActivity.mIntails;
        mQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mQueue, new BitmapCache());
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        good_type_list = new ArrayList<>();
        mDatas = new ArrayList<String>();
        String[] s = {"米面粮油", "生鲜蔬菜", "新鲜水果", "干果炒货", "鱼肉蛋禽", "豆制品"
                , "乳品饮料", "日化美护", "休闲食品", "馈赠礼包"};
        for (int i = 0; i < s.length; i++) {
            mDatas.add(s[i]);
        }

        //设置adapter
        recyclerView.setAdapter(
                new RecycleAdapter(mContext, mDatas,
                        R.layout.recycle_view_item_good_type) {
                    @Override
                    public void convert(RecycleViewHolder holder, final List list, final int position) {
                        Button btn = holder.getView(R.id.good_type_rv_button);
                        btn.setText(mDatas.get(position).toString());
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
        return viewRoot;
    }

    ImageLoader mImageLoader;
    private RequestQueue mQueue;

    private void refreshList(int position) {

        mTypeDetailList = new ArrayList<>();
        for (int s = 0; s < detail.length; s++) {
            mTypeDetailList.add(detail[s] + position);
        }
        good_type_detail.setLayoutManager(new FullyLinearLayoutManager(mContext));
        good_type_detail.setAdapter(new RecycleAdapter(mContext, mTypeDetailList, R.layout.item_fenlei_detail) {
            @Override
            public void convert(RecycleViewHolder holder, final List list, final int position) {
                holder.setText(R.id.item_fenlei_detail_tv, mTypeDetailList.get(position).toString());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener((ImageView) holder.getView(R.id.item_fenlei_detail_iv), 0, R.mipmap.ic_launcher);
                mImageLoader.get("http://pic24.nipic.com/20120920/10361578_112230424175_2.jpg", listener);
            }
        });
        good_type_detail.setLayoutManager(new FullyGridLayoutManager(mContext, 3));
    }
}
