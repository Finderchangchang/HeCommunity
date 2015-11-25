package liuliu.he.community.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import liuliu.custom.control.toolbar.TToolbar;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.control.main.IMainView;
import liuliu.he.community.control.main.MainListener;
import liuliu.he.community.type.ItemStyle;


public class MainActivity extends BaseActivity implements IMainView {
    @CodeNote(id = R.id.main_toolbar)
    TToolbar mToolbar;
    @CodeNote(id = R.id.main_seal_hot)
    RecyclerView seal_hot_rv;
    @CodeNote(id = R.id.main_seal_detail_ll)
    RecyclerView seal_detail_ll;
    @CodeNote(id = R.id.main_good_type_lv)
    RecyclerView good_type_lv;
    List mDatas;
    List mXinDatas;//新品推荐
    List mFenDatas;//商品分类
    private HomeAdapter mAdapter;
    private XinAdapter mXinAdapter;
    private FenLeiAdapter mFenAdapter;
    private MainListener mListener;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        initData();
        //设置布局管理器
        seal_hot_rv.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        seal_hot_rv.setAdapter(mAdapter = new HomeAdapter());
        /*新品推荐Adapter*/
        //设置布局管理器
        seal_detail_ll.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        seal_detail_ll.setAdapter(mXinAdapter = new XinAdapter());
        seal_detail_ll.setLayoutManager(new GridLayoutManager(this, 2));
        good_type_lv.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        good_type_lv.setAdapter(mFenAdapter = new FenLeiAdapter());
        good_type_lv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void initEvents() {
        //左侧点击事件
        mToolbar.setLeftOnClick(new TToolbar.LeftOnClickListener() {
            @Override
            public void leftclick() {

            }
        });
        //中间点击事件
        mToolbar.setCenterOnClick(new TToolbar.CenterOnClickListener() {
            @Override
            public void centerclick() {

            }
        });
        //右侧点击事件
        mToolbar.setRightOnClick(new TToolbar.RightOnClickListener() {
            @Override
            public void rightclick() {

            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        mXinDatas = new ArrayList();
        mFenDatas = new ArrayList();
        String[] s = {"中秋礼品", "米面粮油", "生鲜蔬菜", "新鲜水果", "干果炒货",
                "鱼肉蛋禽", "豆制品", "乳品饮料", "日化美护", "休闲食品"};
        for (int i = 'A'; i < 'H'; i++) {
            mDatas.add("" + (char) i);
            mXinDatas.add((char) i);
        }
        for (int i = 0; i < s.length; i++) {
            mFenDatas.add(s[i]);
        }
    }

    class XinAdapter extends RecyclerView.Adapter<XinAdapter.XinViewHolder> {
        @Override
        public XinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            XinViewHolder holder = new XinViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.recycle_view_item_hot_good, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(XinViewHolder holder, int position) {
            holder.tv.setText(mXinDatas.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mXinDatas.size();
        }

        class XinViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public XinViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.xin_good_tv);
            }
        }
    }

    /*加载各大促销的Adapter*/
    class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.FenLeiViewHolder> {
        @Override
        public FenLeiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FenLeiViewHolder holder = new FenLeiViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.recycle_view_item_hot_good, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(FenLeiViewHolder holder, int position) {
            holder.tv.setText(mFenDatas.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mFenDatas.size();
        }

        class FenLeiViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public FenLeiViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.xin_good_tv);
            }
        }
    }

    /*加载各大促销的Adapter*/
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.recycle_view_item_home, parent,
                    false), mDatas);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ViewGroup.LayoutParams lp = holder.mTotalItem.getLayoutParams();
            switch (mDatas.size()) {
                case 4://版本一
                    if (position == 0) {
                        lp.height = 420;
                        holder.mTotalItem.setLayoutParams(lp);
                        holder.left_one_tv.setText(mDatas.get(position).toString());
                        holder.right_two_tv.setText(mDatas.get(position + 1).toString());
                        holder.right_three_tv.setText(mDatas.get(position + 2).toString());
                        holder.right_four_tv.setText(mDatas.get(position + 3).toString());
                    } else {
                        holder.mTotalItem.setVisibility(View.GONE);
                    }
                    break;
                case 5://版本二
                    if (position == 0) {
                        lp.height = 420;
                        holder.mTotalItem.setLayoutParams(lp);
                        holder.left_one_tv.setText(mDatas.get(position).toString());
                        holder.right_two_tv.setText(mDatas.get(position + 1).toString());
                        holder.right_three_tv.setText(mDatas.get(position + 2).toString());
                        holder.mRightLLFour.setVisibility(View.GONE);
                    } else if (position == 1) {
                        lp.height = 200;
                        setItemTwo(holder, lp);
                        holder.left_one_tv.setText(mDatas.get(position + 2).toString());
                        holder.right_one_tv.setText(mDatas.get(position + 3).toString());
                    } else {
                        holder.mTotalItem.setVisibility(View.GONE);
                    }
                    break;
                case 6:
                    if (ItemStyle.SixFour == ItemStyle.SixFour) {//版本八
                        if (position == 0) {
                            lp.height = 420;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 1).toString());
                        } else if (position == 1) {
                            lp.height = 200;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position + 1).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 2).toString());
                        } else if (position == 2) {
                            lp.height = 200;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position + 2).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 3).toString());
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    } else if (ItemStyle.SixThree == ItemStyle.SixThree) {//�汾��
                        if (position == 0) {
                            lp.height = 420;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 1).toString());
                        } else if (position == 1) {
                            lp.height = 200;
                            setItemFour(holder, lp, position);
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    } else if (ItemStyle.SixOne == ItemStyle.SixOne) {//�汾��
                        setWeight(holder);
                        if (position == 0 || position == 1 || position == 2) {
                            lp.height = 200;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 1).toString());
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    } else if (ItemStyle.SixTwo == ItemStyle.SixTwo) {//�汾��
                        if (position == 0) {
                            lp.height = 420;
                            holder.mTotalItem.setLayoutParams(lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_two_tv.setText(mDatas.get(position + 1).toString());
                            holder.right_three_tv.setText(mDatas.get(position + 2).toString());
                            holder.right_four_tv.setText(mDatas.get(position + 3).toString());
                        } else if (position == 1) {
                            lp.height = 200;
                            holder.left_one_tv.setText(mDatas.get(position + 3).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 4).toString());
                            setItemTwo(holder, lp);
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 7:
                    if (ItemStyle.SevenOne == ItemStyle.SevenOne) {//�汾��
                        if (position == 0) {
                            lp.height = 350;
                            holder.mTotalItem.setLayoutParams(lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_two_tv.setText(mDatas.get(position + 1).toString());
                            holder.right_three_tv.setText(mDatas.get(position + 2).toString());
                            holder.mRightLLFour.setVisibility(View.GONE);
                        } else if (position == 1) {
                            lp.height = 170;
                            setItemFour(holder, lp, position);
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    } else if (ItemStyle.SevenTwo == ItemStyle.SevenTwo) {//�汾��
                        setWeight(holder);
                        if (position == 0) {
                            lp.height = 300;
                            holder.mTotalItem.setLayoutParams(lp);
                            holder.left_one_tv.setText(mDatas.get(position).toString());
                            holder.right_two_tv.setText(mDatas.get(position + 1).toString());
                            holder.right_three_tv.setText(mDatas.get(position + 2).toString());
                            holder.mRightLLFour.setVisibility(View.GONE);
                        } else if (position == 1) {
                            lp.height = 150;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position + 2).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 3).toString());
                        } else if (position == 2) {
                            lp.height = 150;
                            setItemTwo(holder, lp);
                            holder.left_one_tv.setText(mDatas.get(position + 3).toString());
                            holder.right_one_tv.setText(mDatas.get(position + 4).toString());
                        } else {
                            holder.mTotalItem.setVisibility(View.GONE);
                        }
                    }
                    break;
            }
        }

        private void setItemTwo(MyViewHolder holder, ViewGroup.LayoutParams lp) {
            holder.mTotalItem.setLayoutParams(lp);
            holder.left_two_tv.setVisibility(View.GONE);
            holder.mRightLLOne.setVisibility(View.VISIBLE);
            holder.mRightRightLL.setVisibility(View.GONE);
        }

        //设置Item有四个
        private void setItemFour(MyViewHolder holder, ViewGroup.LayoutParams lp, int position) {
            holder.mTotalItem.setLayoutParams(lp);
            holder.left_one_tv.setText(mDatas.get(position + 1).toString());
            holder.left_two_tv.setText(mDatas.get(position + 2).toString());
            holder.right_one_tv.setText(mDatas.get(position + 3).toString());
            holder.right_two_tv.setText(mDatas.get(position + 4).toString());
            holder.mLeftLLTwo.setVisibility(View.VISIBLE);
            holder.mRightLLOne.setVisibility(View.VISIBLE);
            holder.mRightRightLL.setVisibility(View.VISIBLE);
            holder.mRightLLThree.setVisibility(View.GONE);
            holder.mRightLLFour.setVisibility(View.GONE);
        }

        //设置weight 2:1
        private void setWeight(MyViewHolder holder) {
            LinearLayout.LayoutParams param;
            param = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);
            param.setMargins(0, 0, 5, 0);
            holder.mLeftLL.setLayoutParams(param);
            param = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            param.setMargins(5, 0, 0, 0);
            holder.mRightLL.setLayoutParams(param);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout mTotalItem;//总体的
            LinearLayout mLeftLL;//左侧布局
            LinearLayout mLeftLLOne;//左侧布局
            LinearLayout mLeftLLTwo;//左侧布局
            LinearLayout mRightLL;//右侧布局
            LinearLayout mRightLLOne;//右侧布局
            LinearLayout mRightRightLL;//右侧布局
            LinearLayout mRightLLTwo;//右侧布局
            LinearLayout mRightLLThree;//右侧布局
            LinearLayout mRightLLFour;//右侧布局
            TextView left_one_tv;
            TextView left_two_tv;
            TextView right_one_tv;
            TextView right_two_tv;
            TextView right_three_tv;
            TextView right_four_tv;
            List mList;

            public MyViewHolder(View view, List list) {
                super(view);
                mList = list;
                mTotalItem = (LinearLayout) view.findViewById(R.id.totalItem_ll);
                mLeftLL = (LinearLayout) view.findViewById(R.id.total_left_ll);
                mLeftLLOne = (LinearLayout) view.findViewById(R.id.total_left_ll_one);
                mLeftLLTwo = (LinearLayout) view.findViewById(R.id.total_left_ll_two);
                mRightLL = (LinearLayout) view.findViewById(R.id.total_right_ll);
                mRightRightLL = (LinearLayout) view.findViewById(R.id.total_right_ll_right);
                mRightLLOne = (LinearLayout) view.findViewById(R.id.total_right_ll_one);
                mRightLLTwo = (LinearLayout) view.findViewById(R.id.total_right_ll_two);
                mRightLLThree = (LinearLayout) view.findViewById(R.id.total_right_ll_three);
                mRightLLFour = (LinearLayout) view.findViewById(R.id.total_right_ll_four);
                left_one_tv = (TextView) view.findViewById(R.id.total_left_one);
                left_two_tv = (TextView) view.findViewById(R.id.total_left_two);
                right_one_tv = (TextView) view.findViewById(R.id.total_right_one);
                right_two_tv = (TextView) view.findViewById(R.id.total_right_two);
                right_three_tv = (TextView) view.findViewById(R.id.total_right_three);
                right_four_tv = (TextView) view.findViewById(R.id.total_right_four);
            }
        }
    }

}
