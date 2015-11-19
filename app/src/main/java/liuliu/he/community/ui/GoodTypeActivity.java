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

import liuliu.he.community.ListModel;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.type.ItemStyle;

/**
 * 商品类型Activity
 * Created by Administrator on 2015/11/18.
 */
public class GoodTypeActivity extends BaseActivity {
    public static GoodTypeActivity mIntails;
    @CodeNote(id = R.id.good_type_recycle_view)
    RecyclerView good_type_view;
    List<ListModel> mDatas;
    private HomeAdapter mAdapter;
    Button btns;

    @Override
    public void initViews() {
        setContentView(R.layout.activtiy_good_type);
        mIntails = this;
    }

    @Override
    public void initEvents() {
        //设置布局管理器
        good_type_view.setLayoutManager(new LinearLayoutManager(this));
        initData();
        //设置adapter
        good_type_view.setAdapter(mAdapter = new HomeAdapter(mDatas));
        good_type_view.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(Button btn, int position) {
                if (btns != null) {
                    btns.setBackgroundColor(R.color.b2b2b2);
                }
                btns = btn;
                btns.setBackgroundColor(R.color.red);
            }
        });

    }

    protected void initData() {
        mDatas = new ArrayList<ListModel>();
        String[] s = {"馈赠礼包", "米面粮油", "生鲜蔬菜", "新鲜水果", "干果炒货", "鱼肉蛋禽", "豆制品"
                , "乳品饮料", "日化美护", "休闲食品", "贵宾卡"};
        for (int i = 0; i < s.length; i++) {
            ListModel model = new ListModel();
            model.setVal(s[i]);
            model.setResult(false);
            mDatas.add(model);
        }
    }

    static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        List<ListModel> mList;

        public HomeAdapter(List list) {
            mList = list;
        }

        public interface OnItemClickLitener {
            void onItemClick(Button btn, int position);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    GoodTypeActivity.mIntails).inflate(R.layout.recycle_view_item_good_type, parent,
                    false), mList);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.btn.setText(mList.get(position).getVal());
            holder.btn.setBackgroundColor(R.color.b2b2b2);

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.btn, position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
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
