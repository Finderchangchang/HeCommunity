package liuliu.he.community.ui.first_frag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.CodeNote;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import liuliu.custom.control.view.GridLinearLayout;
import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.adapter.CommonAdapter;
import liuliu.he.community.adapter.CommonViewHolder;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.shouye.IShouyeView;
import liuliu.he.community.control.shouye.ShouyeListener;
import liuliu.he.community.model.ChangeItemModel;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.ItemModel;
import liuliu.he.community.model.MyGridView;
import liuliu.he.community.model.ProductModel;
import liuliu.he.community.model.TopImage;
import liuliu.he.community.adapter.DataAdapterBase;
import liuliu.he.community.adapter.ViewHolderBase;
import liuliu.he.community.ui.activity.DetailListsActivity;
import liuliu.he.community.ui.activity.DingDanActivity;
import liuliu.he.community.ui.activity.MainActivity;

/**
 * 首页的Fragment
 * Created by Administrator on 2015/11/25.
 */
public class ShouyeFragment extends BaseFragment implements IShouyeView {
    @CodeNote(id = R.id.good_list_grid_view)
    GridLinearLayout good_list;
    @CodeNote(id = R.id.good_type_grid_view)
    GridLinearLayout good_type_gv;
    @CodeNote(id = R.id.guang_gao_grid_view)
    GridLinearLayout guang_gao_gv;
    @CodeNote(id = R.id.fenlei_xiangqing_ll, click = "onClick")
    LinearLayout xiangqing_ll;
    Context mContext;
    OnItemClick mClick;
    @CodeNote(id = R.id.main_good_type_ll, click = "onClick")
    LinearLayout main_good_type_ll;
    @CodeNote(id = R.id.main_my_order_ll, click = "onClick")
    LinearLayout main_my_order_ll;
    @CodeNote(id = R.id.main_user_unit_ll, click = "onClick")
    LinearLayout main_user_unit_ll;
    @CodeNote(id = R.id.main_shoppingcar_ll, click = "onClick")
    LinearLayout main_shoppingcar_ll;
    @CodeNote(id = R.id.hot_tejia_rl, click = "onClick")
    RelativeLayout hot_tejia_rl;
    @CodeNote(id = R.id.hot_tejia_ll)
    LinearLayout hot_tejia_ll;
    @CodeNote(id = R.id.hot_tejia_iv)
    ImageView hot_tejia_iv;
    @CodeNote(id = R.id.hot_tejia_tv)
    TextView hot_tejia_tv;
    @CodeNote(id = R.id.hot_jingpin_rl, click = "onClick")
    RelativeLayout hot_jingpin_rl;
    @CodeNote(id = R.id.hot_jingpin_ll)
    LinearLayout hot_jingpin_ll;
    @CodeNote(id = R.id.hot_jingpin_iv)
    ImageView hot_jingpin_iv;
    @CodeNote(id = R.id.hot_jingpin_tv)
    TextView hot_jingpin_tv;
    @CodeNote(id = R.id.hot_zuixin_rl, click = "onClick")
    RelativeLayout hot_zuixin_rl;
    @CodeNote(id = R.id.hot_zuixin_ll)
    LinearLayout hot_zuixin_ll;
    @CodeNote(id = R.id.hot_zuixin_iv)
    ImageView hot_zuixin_iv;
    @CodeNote(id = R.id.hot_zuixin_tv)
    TextView hot_zuixin_tv;
    private int clickItem;//热门商品点击的项
    ImageLoader imageLoader = null;
    ShouyeListener mListener;

    DataAdapterBase good_type_adapter;
    DataAdapterBase hot_good_adapter;
    DataAdapterBase guang_gao_adapter;
    List<ChangeItemModel> mItemList;
    List<ItemModel> mItems;
    List list[];
    ChangeItemModel normalModel;
    ChangeItemModel pressedModel;
    int now_preaaed = -1;//当前点击的底部菜单
    MainActivity mIntails;
    @CodeNote(id = R.id.good_lists_ll)
    LinearLayout good_lists_ll;
    @CodeNote(id = R.id.main_good_classify_ll)
    LinearLayout good_classify_ll;
    @CodeNote(id = R.id.shouye_scroll)
    ScrollView shouye_scroll;

    @Override
    public void initViews() {
        setContentView(R.layout.frag_shouye);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initEvents() {
        mContext = MainActivity.mIntails;
        mIntails = MainActivity.mIntails;
        imageLoader = ImageLoaderFactory.create(mContext);
        mListener = new ShouyeListener(this, mContext);
        mItemList = new ArrayList<>();
        mItems = new ArrayList<>();
        mItemList.add(new ChangeItemModel(hot_tejia_rl, hot_tejia_ll, hot_tejia_tv, hot_tejia_iv));
        mItemList.add(new ChangeItemModel(hot_jingpin_rl, hot_jingpin_ll, hot_jingpin_tv, hot_jingpin_iv));
        mItemList.add(new ChangeItemModel(hot_zuixin_rl, hot_zuixin_ll, hot_zuixin_tv, hot_zuixin_iv));

        mItems.add(new ItemModel("首页", R.mipmap.shouye_normal, R.mipmap.shouye_normal_pressed));
        mItems.add(new ItemModel("分类", R.mipmap.fenlei_normal, R.mipmap.fenlei_normal_pressed));
        mItems.add(new ItemModel("我的", R.mipmap.wode_normal, R.mipmap.wode_normal_pressed));
        mListener.loadTitle();//加载缓存(顶部图片集合)
        mListener.loadGuangGao();//加载缓存（广告）
        mListener.loadGoodLists();//加载缓存(商品列表)
        mListener.loadTypes();//加载缓存(商品分类)
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_good_type_ll:
                mClick.onItemClick(1);
                break;
            case R.id.main_my_order_ll://跳转到订单列表
                MainActivity.mIntails.mUtils.IntentPost(DingDanActivity.class);
                break;
            case R.id.main_user_unit_ll:
                mClick.onItemClick(2);
                break;
            case R.id.main_shoppingcar_ll:
                mClick.onItemClick(3);
                break;
            case R.id.hot_tejia_rl:
                HotClick(0, list[0]);
                break;
            case R.id.hot_jingpin_rl:
                HotClick(1, list[1]);
                break;
            case R.id.hot_zuixin_rl:
                HotClick(2, list[2]);
                break;
            case R.id.fenlei_xiangqing_ll://点击跳转到商品类型
                mClick.onItemClick(1);
                break;
        }
    }

    /**
     * 热门商品选择
     *
     * @param position 选中的位置
     * @param mList    商品信息集合
     */
    private void HotClick(final int position, final List mList) {
        normalModel = mItemList.get(clickItem);
        pressedModel = mItemList.get(position);
        //恢复成未点击状态
        normalModel.getTv().setTextColor(mContext.getResources().getColor(R.color.main_item_normal));
        Bitmap bitmap = Utils.readBitMap(mContext, mItems.get(clickItem).getNormal_img());
        normalModel.getIv().setImageBitmap(bitmap);
        normalModel.getRl().setBackgroundColor(mContext.getResources().getColor(R.color.total_bg));
        normalModel.getLl().setVisibility(View.VISIBLE);
        //设置为点击状态
        pressedModel.getTv().setTextColor(mContext.getResources().getColor(R.color.main_item_pressed));
        pressedModel.getIv().setImageBitmap(Utils.readBitMap(mContext, mItems.get(position).getPressed_img()));
        pressedModel.getRl().setBackgroundColor(mContext.getResources().getColor(R.color.white));
        pressedModel.getLl().setVisibility(View.GONE);
        clickItem = position;
        hot_good_adapter = new DataAdapterBase<ProductModel>(mContext, R.layout.item_main_hot_good, mList) {
            @Override
            public void convert(ViewHolderBase holder, ProductModel model, int position) {
                TopImage m = new TopImage();
                m.setImg(model.getImage());
                m.setLink(model.getGid());
                holder.loadImageByUrl(R.id.good_iv, mIntails.finalBitmap, m);
//                holder.loadImage(R.id.good_iv, imageLoader, model.getImage());
                if (model.getName().length() > 8) {
                    holder.setText(R.id.good_name_tv, model.getName().substring(0, 8) + "..");
                } else {
                    holder.setText(R.id.good_name_tv, model.getName());
                }
                holder.setText(R.id.good_price_tv, model.getPrice());
            }
        };
//        good_list.setNumColumns(2);
        good_list.setAdapter(hot_good_adapter);
        good_list.setOnCellClickListener(new GridLinearLayout.OnCellClickListener() {
            @Override
            public void onCellClick(final int index) {
                MainActivity.mIntails.mUtils.IntentPost(DetailListsActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        ProductModel model = (ProductModel) mList.get(index);
                        intent.putExtra("desc", "xq?" + model.getGid());//商品id
                    }
                });//跳转到商品详细页面
            }
        });
        good_list.setColumns(2);
        good_list.bindLinearLayout();
//        setListViewHeightBasedOnChildren(good_list, 2);
    }

    @Override
    public void OnTitleImages(List list) {

    }

    CommonAdapter<GoodTypeModel> mAdapters;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.obj.toString()) {
                case "guanggao":
                    guang_gao_adapter = new DataAdapterBase<TopImage>(mContext, R.layout.recycle_view_item_home, mList) {
                        @Override
                        public void convert(ViewHolderBase holder, TopImage model, int position) {
                            loadGG(mContext, mType, holder, mList, position);
                        }
                    };
                    if (guang_gao_gv != null) {
//                        guang_gao_gv.setNumColumns(1);
                        guang_gao_gv.setAdapter(guang_gao_adapter);
                        shouye_scroll.smoothScrollTo(0, 0);
                        guang_gao_gv.setColumns(1);
//                        guang_gao_gv.bindLinearLayout();
                    }
                    break;
                case "goodlist":
                    good_lists_ll.setVisibility(View.VISIBLE);
                    now_preaaed = 0;
                    HotClick(0, list[0]);
                    break;
                case "goodtype":
                    if (mAdapters != null) {
                        good_classify_ll.setVisibility(View.VISIBLE);
//                        good_type_gv.setNumColumns(2);
                        good_type_gv.setAdapter(mAdapters);
                        good_type_gv.setColumns(2);
                        good_type_gv.bindLinearLayout();
//                        setListViewHeightBasedOnChildren(good_type_gv, 2);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private int mType;
    private List mList;

    @Override
    public void OnGuanggao(final int type, final List list) {
        mType = type;
        mList = list;
        Message message = Message.obtain();
        message.obj = "guanggao";
        mHandler.sendMessage(message);
    }

    @Override
    public void OnGoodList(final List list[]) {
        this.list = list;
        Message message = Message.obtain();
        message.obj = "goodlist";
        mHandler.sendMessage(message);
    }

    //商品分类
    @Override
    public void OnGoodType(List list) {
        good_type_adapter = new DataAdapterBase<GoodTypeModel>(mContext, R.layout.item_main_fenlei, list) {
            @Override
            public void convert(ViewHolderBase holder, final GoodTypeModel model, int position) {
                TopImage m = new TopImage();
                m.setImg(model.getImage());
                m.setLink(model.getLink());
                holder.loadImageByUrl(R.id.type_iv, mIntails.finalBitmap, m);
                holder.setText(R.id.type_title_tv, model.getTitle());
                holder.setText(R.id.type_desc1_tv, model.getT1());
                holder.setText(R.id.type_desc2_tv, model.getT2());
                holder.setOnClick(R.id.item_fenlei_ll, new ViewHolderBase.OnImageClick() {
                    @Override
                    public void click() {
                        MainActivity.mIntails.mUtils.IntentPost(DetailListsActivity.class, new Utils.putListener() {
                            @Override
                            public void put(Intent intent) {
                                intent.putExtra("desc", "spfl?" + model.getLink().split("\\?")[1]);
                            }
                        });
                    }
                });
            }
        };
        mAdapters = new CommonAdapter<GoodTypeModel>(mIntails, list, R.layout.item_main_fenlei) {
            @Override
            public void convert(CommonViewHolder holder, GoodTypeModel model, int position) {
                TopImage m = new TopImage();
                m.setImg(model.getImage());
                m.setLink(model.getLink());
                holder.loadImageByUrl(R.id.type_iv, mIntails.finalBitmap, m);
                holder.setText(R.id.type_title_tv, model.getTitle());
                holder.setText(R.id.type_desc1_tv, model.getT1());
                holder.setText(R.id.type_desc2_tv, model.getT2());
//                holder.setOnClick(R.id.item_fenlei_ll, new ViewHolderBase.OnImageClick() {
//                    @Override
//                    public void click() {
//                        MainActivity.mIntails.mUtils.IntentPost(DetailListsActivity.class, new Utils.putListener() {
//                            @Override
//                            public void put(Intent intent) {
//                                intent.putExtra("desc", "spfl?" + model.getLink().split("\\?")[1]);
//                            }
//                        });
//                    }
//                });
            }
        };
        Message message = Message.obtain();
        message.obj = "goodtype";
        mHandler.sendMessage(message);
    }

    public interface OnItemClick {
        void onItemClick(Object value);//value为传入的值
    }

    public void setOnItemClick(OnItemClick click) {
        mClick = click;
    }

    /*------广告布局管理---------*/

    //加载广告布局
    private void loadGG(Context context, int type, ViewHolderBase holder, final List<TopImage> list, int position) {
        int width = Utils.getScannerWidth(context);//获得屏幕宽度
        switch (type) {
            case 1:
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, (width - 15) / 2);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(1).getImg());
                    holder.loadImage(R.id.total_right_three_iv, imageLoader, list.get(2).getImg());
                    holder.loadImage(R.id.total_right_four_iv, imageLoader, list.get(3).getImg());
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                } else {
                    setOtherGone(holder);
                }
                break;
            case 2:
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, (width - 15) / 2);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_three_iv, imageLoader, list.get(4).getImg());
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                } else if (position == 1) {
                    holder.setHeight(R.id.total_left_ll, 170);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_one_iv, imageLoader, list.get(5).getImg());
                } else {
                    setOtherGone(holder);
                }
                break;
            case 3:
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, (width - 15) / 2);
                    holder.loadImageByUrl(R.id.total_left_one_iv, mIntails.finalBitmap, list.get(0));
                    holder.loadImageByUrl(R.id.total_right_two_iv, mIntails.finalBitmap, list.get(3));
                    holder.loadImageByUrl(R.id.total_right_three_iv, mIntails.finalBitmap, list.get(4));
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                } else if (position == 1) {
                    holder.setHeight(R.id.total_left_ll, 170);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImageByUrl(R.id.total_left_one_iv, mIntails.finalBitmap, list.get(1));
                    holder.loadImageByUrl(R.id.total_left_two_iv, mIntails.finalBitmap, list.get(2));
                    holder.loadImageByUrl(R.id.total_right_one_iv, mIntails.finalBitmap, list.get(5));
                    holder.loadImageByUrl(R.id.total_right_two_iv, mIntails.finalBitmap, list.get(6));
                } else {
                    setOtherGone(holder);
                }
                break;
            case 4:
                if (position == 0 || position == 1 || position == 2) {
                    holder.setHeight(R.id.total_left_ll, 230);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_one_iv, imageLoader, list.get(4).getImg());
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                    setWeight(holder);
                } else {
                    setOtherGone(holder);
                }
                break;
            case 5:
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, (width - 15) / 2);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_three_iv, imageLoader, list.get(4).getImg());
                    holder.loadImage(R.id.total_right_four_iv, imageLoader, list.get(5).getImg());
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                } else if (position == 1) {
                    holder.setHeight(R.id.total_left_ll, 170);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_one_iv, imageLoader, list.get(5).getImg());
                    holder.setVisible(R.id.total_right_ll_right, View.GONE);
                } else {
                    setOtherGone(holder);
                }
                break;
            case 6:
                setWeight(holder);
                holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, 230);
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_three_iv, imageLoader, list.get(3).getImg());
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                } else if (position == 1 || position == 2) {
                    holder.setHeight(R.id.total_left_ll, 150);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                } else {
                    setOtherGone(holder);
                }
                break;
            case 7:
                if (position == 0) {
                    holder.setHeight(R.id.total_left_ll, (width - 15) / 2);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                } else if (position == 1) {
                    holder.setHeight(R.id.total_left_ll, 170);
                    holder.setVisible(R.id.total_right_ll_right, View.VISIBLE);
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_left_two_iv, imageLoader, list.get(2).getImg());
                    holder.loadImage(R.id.total_right_one_iv, imageLoader, list.get(5).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(6).getImg());
                } else {
                    setOtherGone(holder);
                }
                break;
            case 8:
                if (position == 0 || position == 1 || position == 2) {
                    if (position == 0) {
                        holder.setHeight(R.id.total_left_ll, 230);
                    } else {
                        holder.setHeight(R.id.total_left_ll, 150);
                    }
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_one_iv, imageLoader, list.get(4).getImg());
                    holder.setMargin(R.id.total_right_ll_right, 0, 0, 0, 0);
                } else {
                    setOtherGone(holder);
                }
                break;
        }
    }

    //设置weight 2:1
    private void setWeight(ViewHolderBase holder) {
        LinearLayout.LayoutParams param;
        param = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);
        param.setMargins(5, 5, 5, 5);
        holder.getView(R.id.total_left_ll).setLayoutParams(param);
        param = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        param.setMargins(5, 5, 5, 5);
        holder.getView(R.id.total_right_ll).setLayoutParams(param);
    }

    //设置其他内容隐藏
    private void setOtherGone(ViewHolderBase holder) {
        holder.setVisible(R.id.totalItem_ll, View.GONE);
        holder.setVisible(R.id.total_left_ll, View.GONE);
        holder.setVisible(R.id.total_right_ll, View.GONE);
    }

    public static void setListViewHeightBasedOnChildren(GridView listView, int col) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
//        int col = 2;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }
}
