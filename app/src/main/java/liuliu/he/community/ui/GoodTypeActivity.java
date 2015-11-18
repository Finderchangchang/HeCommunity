package liuliu.he.community.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.type.ItemStyle;

/**
 * 商品类型Activity
 * Created by Administrator on 2015/11/18.
 */
public class GoodTypeActivity extends BaseActivity {
    @CodeNote(id = R.id.good_type_recycle_view)
    RecyclerView good_type_view;
    List mDatas;
    private HomeAdapter mAdapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activtiy_good_type);
    }

    @Override
    public void initEvents() {
//设置布局管理器
        good_type_view.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        good_type_view.setAdapter(mAdapter = new HomeAdapter());
        good_type_view.setLayoutManager(new GridLayoutManager(this, 4));
        initData();
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'H'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    GoodTypeActivity.this).inflate(R.layout.recycle_view_item_good_type, parent,
                    false), mDatas);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.btn.setText(mDatas.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            Button btn;//总体的

            public MyViewHolder(View view, List list) {
                super(view);
                btn = (Button) view.findViewById(R.id.good_type_rv_button);
            }
        }
    }
}
