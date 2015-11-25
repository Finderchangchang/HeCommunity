package liuliu.he.community.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.R;
import liuliu.he.community.adapter.RecycleAdapter;
import liuliu.he.community.adapter.RecycleViewHolder;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.model.ChangeItemModel;
import liuliu.he.community.model.ItemModel;

/**
 * 商品类型Activity
 * Created by Administrator on 2015/11/18.
 */
public class GoodTypeActivity extends BaseActivity {
    public static GoodTypeActivity mIntails;
    @CodeNote(id = R.id.good_type_recycle_view)
    RecyclerView good_type_view;
    @CodeNote(id = R.id.good_type_recycle_viewss)
    RecyclerView good_type_viewss;
    List mDatas;
    List<ItemModel> mItems;
    int mClick;//被点击的项
    int mGoodTypeClick;//商品类型点击项
    List<ChangeItemModel> listbtn;//生成的按钮集合（需要颜色改变的view）
    List<Button> good_type_list;

    @Override
    public void initViews() {
        setContentView(R.layout.activtiy_good_type);
        mIntails = this;
        listbtn = new ArrayList<>();
        good_type_list = new ArrayList<>();
    }

    @Override
    public void initEvents() {
        //设置布局管理器
        good_type_view.setLayoutManager(new LinearLayoutManager(this));
        initData();
        //设置adapter
        good_type_view.setAdapter(new RecycleAdapter(mIntails, mDatas, R.layout.recycle_view_item_good_type) {
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
                        good_type_list.get(mGoodTypeClick).setBackgroundResource(R.mipmap.good_type_item);
                        good_type_list.get(position).setBackgroundResource(R.mipmap.good_type_item_pressed);
                        mGoodTypeClick = position;
                    }
                });
            }
        });
        good_type_view.setLayoutManager(new GridLayoutManager(this, 4));


        //设置布局管理器
        good_type_viewss.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        good_type_viewss.setAdapter(new RecycleAdapter(mIntails, mItems, R.layout.recycle_view_item_bottom) {
            @Override
            public void convert(RecycleViewHolder holder, List list, final int position) {
                final List<ItemModel> itemModel = list;
                ImageView imageView = holder.getView(R.id.item_bottom_iv);
                TextView textView = holder.getView(R.id.item_bottom_tv);
                listbtn.add(new ChangeItemModel(textView, imageView));//添加组件到listview
                textView.setText(itemModel.get(position).getTitle());
                imageView.setImageResource(itemModel.get(position).getNormal_img());
                if (position == mClick) {//第一次打开的时候选择的项
                    imageView.setImageResource(itemModel.get(position).getPressed_img());
                    textView.setTextColor(mIntails.getResources().getColor(R.color.main_item_pressed));
                }
                holder.setOnClickListener(R.id.item_bottom_ll, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //恢复成未点击状态
                        listbtn.get(mClick).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_normal));
                        listbtn.get(mClick).getIv().setImageResource(itemModel.get(mClick).getNormal_img());
                        //设置为点击状态
                        listbtn.get(position).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_pressed));
                        listbtn.get(position).getIv().setImageResource(itemModel.get(position).getPressed_img());
                        mClick = position;
                    }
                });
            }
        });
        good_type_viewss.setLayoutManager(new GridLayoutManager(this, 4));
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        String[] s = {"米面粮油", "生鲜蔬菜", "新鲜水果", "干果炒货", "鱼肉蛋禽", "豆制品"
                , "乳品饮料", "日化美护", "休闲食品", "馈赠礼包"};
        for (int i = 0; i < s.length; i++) {
            mDatas.add(s[i]);
        }
        mItems = new ArrayList();
        mItems.add(new ItemModel("首页", R.mipmap.shouye_normal, R.mipmap.shouye_normal_pressed));
        mItems.add(new ItemModel("分类", R.mipmap.fenlei_normal, R.mipmap.fenlei_normal_pressed));
        mItems.add(new ItemModel("我的", R.mipmap.wode_normal, R.mipmap.wode_normal_pressed));
        mItems.add(new ItemModel("购物车", R.mipmap.gouwuche_normal, R.mipmap.gouwuche_normal_pressed));
    }
}
