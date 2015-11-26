package liuliu.he.community.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
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
import liuliu.he.community.ui.fragment.FenleiFragment;
import liuliu.he.community.ui.fragment.GouwucheFragment;
import liuliu.he.community.ui.fragment.ShouyeFragment;
import liuliu.he.community.ui.fragment.WodeFragment;

/**
 * Created by Administrator on 2015/11/25.
 */
public class FragActivity extends BaseActivity {
    public static FragActivity mIntails;
    @CodeNote(id = R.id.frag_ll)
    FrameLayout frag_ll;
    @CodeNote(id = R.id.frag_ll_recycle_view)
    RecyclerView bottom_view;
    List<ChangeItemModel> listbtn;//生成的按钮集合（需要颜色改变的view）
    List<ItemModel> mItems;
    int mClick;//被点击的项
    ShouyeFragment shouye = null;

    @Override
    public void initViews() {
        setContentView(R.layout.frag);
        mIntails = this;
        listbtn = new ArrayList<>();
        mItems = new ArrayList();
        mItems.add(new ItemModel("首页", R.mipmap.shouye_normal, R.mipmap.shouye_normal_pressed));
        mItems.add(new ItemModel("分类", R.mipmap.fenlei_normal, R.mipmap.fenlei_normal_pressed));
        mItems.add(new ItemModel("我的", R.mipmap.wode_normal, R.mipmap.wode_normal_pressed));
        mItems.add(new ItemModel("购物车", R.mipmap.gouwuche_normal, R.mipmap.gouwuche_normal_pressed));
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        shouye = new ShouyeFragment();
        shouye.setOnItemClick(new ShouyeFragment.OnItemClick() {
            @Override
            public void onItemClick(Object value) {
                setItem((Integer) value);
            }
        });
        transaction.replace(R.id.frag_ll,shouye);
        transaction.commit();
    }

    @Override
    public void initEvents() {
        //设置布局管理器
        bottom_view.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        bottom_view.setAdapter(new RecycleAdapter(mIntails, mItems, R.layout.recycle_view_item_bottom) {
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
                        setItem(position);
                    }
                });
            }
        });
        bottom_view.setLayoutManager(new GridLayoutManager(this, 4));
    }

    private void setItem(int posi) {
        //恢复成未点击状态
        listbtn.get(mClick).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_normal));
        listbtn.get(mClick).getIv().setImageResource(mItems.get(mClick).getNormal_img());
        //设置为点击状态
        listbtn.get(posi).getTv().setTextColor(mIntails.getResources().getColor(R.color.main_item_pressed));
        listbtn.get(posi).getIv().setImageResource(mItems.get(posi).getPressed_img());
        mClick = posi;
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        final FragmentTransaction transaction = fm.beginTransaction();
        switch (posi) {
            case 0:
                shouye = new ShouyeFragment();
                transaction.replace(R.id.frag_ll, shouye);
                shouye.setOnItemClick(new ShouyeFragment.OnItemClick() {
                    @Override
                    public void onItemClick(Object value) {
                        setItem((Integer) value);
                    }
                });
                break;
            case 1:
                transaction.replace(R.id.frag_ll, new FenleiFragment());
                break;
            case 2:
                transaction.replace(R.id.frag_ll, new WodeFragment());
                break;
            case 3:
                transaction.replace(R.id.frag_ll, new GouwucheFragment());
                break;
        }
        transaction.commit();
    }
}
