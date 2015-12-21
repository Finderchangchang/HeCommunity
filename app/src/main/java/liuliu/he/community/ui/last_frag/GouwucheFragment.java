package liuliu.he.community.ui.last_frag;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.adapter.DataAdapterBase;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.adapter.ViewHolderBase;
import liuliu.he.community.ui.activity.DetailListsActivity;
import liuliu.he.community.ui.activity.MainActivity;
import liuliu.he.community.model.MyGridView;

/**
 * 购物车
 * Created by Administrator on 2015/11/25.
 */
public class GouwucheFragment extends BaseFragment {
    @CodeNote(id = R.id.good_list_grid_view)
    MyGridView good_list;
    @CodeNote(id = R.id.jixugouwu, click = "onClick")
    LinearLayout jixugouwu;
    List<GoodModel> mDatas;
    DataAdapterBase adapter;
    DetailListsActivity mIntails;

    @Override
    public void initViews() {
        setContentView(R.layout.frag_gouwuche);
        mDatas = new ArrayList<GoodModel>();
//        mDatas.add(new GoodModel("中秋礼品", "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg"));
//        mDatas.add(new GoodModel("中秋礼品1", "http://img4.duitang.com/uploads/blog/201311/04/20131104193715_NCexN.thumb.jpeg"));
        mIntails = DetailListsActivity.mIntails;
    }

    @Override
    public void initEvents() {
        final ImageLoader imageLoader = ImageLoaderFactory.create(mIntails);
        adapter = new DataAdapterBase<GoodModel>(mIntails, R.layout.item_gouwuche, mDatas) {
            @Override
            public void convert(ViewHolderBase holder, final GoodModel model, int position) {
//                Button btn = (Button) holder.getView(R.id.count_jia_btn);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MainActivity.mIntails.mUtils.ToastShort("model:" + model.getName());
//                    }
//                });
//                holder.loadImage(R.id.good_img, imageLoader, model.getImage());
            }
        };
        good_list.setNumColumns(1);
        good_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jixugouwu:
                mIntails.mUtils.IntentPost(MainActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("BottomId", "1");
                        mIntails.finish();
                        MainActivity.mIntails.finish();
                    }
                });
                break;
        }
    }
}
