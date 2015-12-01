package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.views.list.ListViewDataAdapter;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.type.ItemStyle;
import liuliu.he.community.ui.demo.ImageDemo;
import liuliu.he.community.ui.demo.ListDemoActivity;
import liuliu.he.community.ui.demo.StringMiddleImageViewViewHolder;

/**
 * 首页的Fragment
 * Created by Administrator on 2015/11/25.
 */
public class ShouyeFragment extends BaseFragment {
    @CodeNote(id = R.id.main_seal_hot)
    RecyclerView seal_hot_rv;
    @CodeNote(id = R.id.good_list_grid_view)
    GridView good_list;
    ListViewDataAdapter adapter;
    //    CommonBaseAdapter adapter;
    Context mContext;
    List mDatas;
    OnItemClick mClick;
    @CodeNote(id = R.id.main_good_type_ll, click = "onClick")
    LinearLayout main_good_type_ll;
    @CodeNote(id = R.id.main_my_order_ll, click = "onClick")
    LinearLayout main_my_order_ll;
    @CodeNote(id = R.id.main_user_unit_ll, click = "onClick")
    LinearLayout main_user_unit_ll;
    @CodeNote(id = R.id.main_shoppingcar_ll, click = "onClick")
    LinearLayout main_shoppingcar_ll;
    @CodeNote(id = R.id.cub_img)
    CubeImageView mImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_shouye, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mDatas = new ArrayList<String>();
        String[] s = {"中秋礼品", "米面粮油", "生鲜蔬菜", "新鲜水果", "干果炒货",
                "鱼肉蛋禽", "豆制品", "乳品饮料", "日化美护", "休闲食品"};
        for (int i = 'A'; i < 'H'; i++) {
            mDatas.add("" + (char) i);
        }
        //设置布局管理器
        seal_hot_rv.setLayoutManager(new LinearLayoutManager(ListDemoActivity.mIntails));
        //设置adapter
        seal_hot_rv.setAdapter(new HomeAdapter());

        FinalActivity.initInjectedView(this, viewRoot);
        mContext = ListDemoActivity.mIntails;

        ImageLoader imageLoader = ImageLoaderFactory.create(mContext);
        adapter = new ListViewDataAdapter<String>();
        adapter.setViewHolderClass(this, StringMiddleImageViewViewHolder.class, imageLoader);
        adapter.getDataList().addAll(ImageDemo.getSmallImages());
        good_list.setNumColumns(2);
        good_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return viewRoot;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_good_type_ll:
                mClick.onItemClick(1);
                break;
            case R.id.main_my_order_ll:
                break;
            case R.id.main_user_unit_ll:
                mClick.onItemClick(2);
                break;
            case R.id.main_shoppingcar_ll:
                mClick.onItemClick(3);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnItemClick {
        void onItemClick(Object value);//value为传入的值
    }

    public void setOnItemClick(OnItemClick click) {
        mClick = click;
    }

    /*加载各大促销的Adapter*/
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    ListDemoActivity.mIntails).inflate(R.layout.recycle_view_item_home, parent,
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
