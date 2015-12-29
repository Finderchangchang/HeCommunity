package liuliu.he.community.ui.first_frag;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.adapter.RecycleAdapter;
import liuliu.he.community.adapter.RecycleViewHolder;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.fenlei.FenLeiListener;
import liuliu.he.community.control.fenlei.IFenleiView;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.MyGridView;
import liuliu.he.community.adapter.DataAdapterBase;
import liuliu.he.community.adapter.ViewHolderBase;
import liuliu.he.community.ui.activity.DetailListsActivity;
import liuliu.he.community.ui.activity.MainActivity;

/**
 * 分类
 * Created by Administrator on 2015/11/25.
 */
public class FenleiFragment extends BaseFragment implements IFenleiView {
    @CodeNote(id = R.id.fenlei_view)
    RecyclerView recyclerView;//商品分类
    @CodeNote(id = R.id.fenlei_grid_view)
    MyGridView gridview;
    int mGoodTypeClick;//被点击的项
    List<Button> good_type_list;
    FenLeiListener mListener;
    MainActivity mActivity;
    DataAdapterBase adapterBase;
    ImageLoader imageLoader = null;

    @Override
    public void initViews() {
        setContentView(R.layout.frag_fenlei);
    }

    @Override
    public void initEvents() {
        mActivity = MainActivity.mIntails;
        mListener = new FenLeiListener(mActivity, this);
        //设置布局管理器
        good_type_list = new ArrayList<>();
    }

    /**
     * 加载所有数据
     *
     * @param list 所有类型集合
     */
    @Override
    public void loadFenLei(final List list[]) {
        List<String> title = new ArrayList();
        for (int i = 0; i < list.length; i++) {
            GoodTypeModel model = (GoodTypeModel) list[i].get(0);
            title.add(model.getTitle());
        }
        //设置adapter
        recyclerView.setAdapter(
                new RecycleAdapter(mActivity, title,
                        R.layout.recycle_view_item_good_type) {
                    @Override
                    public void convert(RecycleViewHolder holder, final List l, final int position) {
                        Button btn = holder.getView(R.id.good_type_rv_button);
                        btn.setText(l.get(position).toString());
                        btn.setBackgroundResource(R.mipmap.good_type_item);
                        good_type_list.add(btn);//将所有按钮添加到list中
                        if (position == mGoodTypeClick) {//设置置顶按钮被点击
                            btn.setBackgroundResource(R.mipmap.good_type_item_pressed);
                        }
                        holder.setOnClickListener(R.id.good_type_rv_button, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refreshList(list[position]);
                                        good_type_list.get(mGoodTypeClick).setBackgroundResource(R.mipmap.good_type_item);
                                        good_type_list.get(position).setBackgroundResource(R.mipmap.good_type_item_pressed);
                                        mGoodTypeClick = position;
                                    }
                                }
                        );
                    }
                }
        );
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));
        refreshList(list[mGoodTypeClick]);
    }

    private void refreshList(final List list) {
        imageLoader = ImageLoaderFactory.create(mActivity);
        adapterBase = new DataAdapterBase<GoodTypeModel>(mActivity, R.layout.item_good_types, list) {
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
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                mActivity.mUtils.IntentPost(DetailListsActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        GoodTypeModel model = (GoodTypeModel) list.get(position);
                        GoodTypeModel first = (GoodTypeModel) list.get(0);
                        String link;
                        if (position > 0) {
                            link = "bid=" + first.getSid() + "&sid=" + model.getSid();
                        } else {
                            link = "bid=" + first.getSid();
                        }
                        intent.putExtra("desc", "spfl?" + link);
                    }
                });
            }
        });
        adapterBase.notifyDataSetChanged();
    }
}
