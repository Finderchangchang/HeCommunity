package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.views.list.ListViewDataAdapter;
import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.ChangeItemModel;
import liuliu.he.community.model.ImageDemo;
import liuliu.he.community.model.ItemModel;
import liuliu.he.community.model.MyGridView;
import liuliu.he.community.model.TitleImagesModel;
import liuliu.he.community.model.TopImage;
import liuliu.he.community.test.DataAdapterBase;
import liuliu.he.community.test.ViewHolderBase;
import liuliu.he.community.type.ItemStyle;
import liuliu.he.community.ui.activity.GoodDetailActivity;
import liuliu.he.community.ui.activity.ListDemoActivity;

/**
 * 首页的Fragment
 * Created by Administrator on 2015/11/25.
 */
public class ShouyeFragment extends BaseFragment {
    //    @CodeNote(id = R.id.main_seal_hot)
//    RecyclerView seal_hot_rv;
    @CodeNote(id = R.id.good_list_grid_view)
    MyGridView good_list;
    @CodeNote(id = R.id.good_type_grid_view)
    MyGridView good_type_gv;
    @CodeNote(id = R.id.guang_gao_grid_view)
    GridView guang_gao_gv;
    ListViewDataAdapter adapter;
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
    //热门
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
    List<TopImage> mTopImgs;
    List<TopImage> mGuanggaoImgs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_shouye, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = ListDemoActivity.mIntails;
        imageLoader = ImageLoaderFactory.create(mContext);
        mDatas = new ArrayList<String>();
        VolloyTask task = new VolloyTask(mContext);
        task.getJson(new VolloyTask.OnReturn() {
            @Override
            public void onResult(TitleImagesModel model) {//获得头部图片集合
                if (model.isReturnX()) {
                    mTopImgs = new ArrayList<>();
                    JSONArray array = (JSONArray) model.getData();
                    for (int i = 0; i < array.length(); i++) {
                        TopImage image = new TopImage();
                        try {
                            image.setImg(array.getJSONObject(i).getString("img"));
                            image.setLink(array.getJSONObject(i).getString("link"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mTopImgs.add(image);
                    }
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/focus.php");

        new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {//八种分类的一种（热门消息）
            @Override
            public void onResult(TitleImagesModel model) {
                if (model.isReturnX()) {
                    JSONObject object = (JSONObject) model.getData();
                    try {
                        int type = object.getInt("type");
                        mGuanggaoImgs = new ArrayList<>();
                        JSONArray array = object.getJSONArray("content");
                        for (int i = 0; i < array.length(); i++) {
                            TopImage image = new TopImage();
                            try {
                                image.setImg(array.getJSONObject(i).getString("image"));
                                image.setLink(array.getJSONObject(i).getString("link"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mGuanggaoImgs.add(image);
                        }
                        guang_gao_adapter = new DataAdapterBase<TopImage>(mContext, R.layout.recycle_view_item_home, mGuanggaoImgs) {
                            @Override
                            public void convert(ViewHolderBase holder, TopImage model, int position) {
                                loadGG(mContext, 8, holder, mGuanggaoImgs, position);
                            }
                        };
                        guang_gao_gv.setNumColumns(1);
                        guang_gao_gv.setAdapter(guang_gao_adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/ad.php");
        for (int i = 'A'; i < 'H'; i++) {
            mDatas.add("" + (char) i);
        }


        initData();

        HotClick(0, ImageDemo.getSmallImages());
        hot_good_adapter = new DataAdapterBase<String>(mContext, R.layout.item_main_hot_good, ImageDemo.getSmallImages()) {
            @Override
            public void convert(ViewHolderBase holder, String url, int position) {
                holder.loadImage(R.id.good_iv, imageLoader, url);
            }
        };
        good_list.setNumColumns(2);
        good_list.setAdapter(hot_good_adapter);
        good_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ListDemoActivity.mIntails.mUtils.IntentPost(GoodDetailActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("goodid", position);//商品id
                    }
                });//跳转到商品详细页面
            }
        });
        hot_good_adapter.notifyDataSetChanged();

        good_type_adapter = new DataAdapterBase<String>(mContext, R.layout.item_main_fenlei, ImageDemo.getSmallImages()) {
            @Override
            public void convert(ViewHolderBase holder, String url, int position) {
                holder.loadImage(R.id.type_iv, imageLoader, url);
            }
        };
        good_type_gv.setNumColumns(2);
        good_type_gv.setAdapter(good_type_adapter);
        good_type_adapter.notifyDataSetChanged();
        return viewRoot;
    }
    //加载广告布局
    private void loadGG(Context context, int type, ViewHolderBase holder, List<TopImage> list, int position) {
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
                    holder.loadImage(R.id.total_left_one_iv, imageLoader, list.get(0).getImg());
                    holder.loadImage(R.id.total_right_two_iv, imageLoader, list.get(3).getImg());
                    holder.loadImage(R.id.total_right_three_iv, imageLoader, list.get(4).getImg());
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

    DataAdapterBase good_type_adapter;
    DataAdapterBase hot_good_adapter;
    DataAdapterBase guang_gao_adapter;
    List<ChangeItemModel> mItemList;
    List<ItemModel> mItems;

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
            case R.id.hot_tejia_rl:
                HotClick(0, ImageDemo.getSmallImages());
                break;
            case R.id.hot_jingpin_rl:
                HotClick(1, ImageDemo.getSmallImages());
                break;
            case R.id.hot_zuixin_rl:
                HotClick(2, ImageDemo.getSmallImages());
                break;
        }
    }

    //加载所需数据
    private void initData() {
        mItemList = new ArrayList<>();
        mItems = new ArrayList<>();
        mItemList.add(new ChangeItemModel(hot_tejia_rl, hot_tejia_ll, hot_tejia_tv, hot_tejia_iv));
        mItemList.add(new ChangeItemModel(hot_jingpin_rl, hot_jingpin_ll, hot_jingpin_tv, hot_jingpin_iv));
        mItemList.add(new ChangeItemModel(hot_zuixin_rl, hot_zuixin_ll, hot_zuixin_tv, hot_zuixin_iv));

        mItems.add(new ItemModel("首页", R.mipmap.shouye_normal, R.mipmap.shouye_normal_pressed));
        mItems.add(new ItemModel("分类", R.mipmap.fenlei_normal, R.mipmap.fenlei_normal_pressed));
        mItems.add(new ItemModel("我的", R.mipmap.wode_normal, R.mipmap.wode_normal_pressed));
    }

    private ChangeItemModel normalModel;
    private ChangeItemModel pressedModel;

    private void HotClick(int position, List<String> mList) {
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
        hot_good_adapter = new DataAdapterBase<String>(mContext, R.layout.item_main_hot_good, mList) {
            @Override
            public void convert(ViewHolderBase holder, String url, int position) {
                holder.loadImage(R.id.good_iv, imageLoader, url);
            }
        };
        good_list.setNumColumns(2);
        good_list.setAdapter(hot_good_adapter);
        good_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ListDemoActivity.mIntails.mUtils.IntentPost(GoodDetailActivity.class, new Utils.putListener() {
                    @Override
                    public void put(Intent intent) {
                        intent.putExtra("goodid", position);//商品id
                    }
                });//跳转到商品详细页面
            }
        });
        hot_good_adapter.notifyDataSetChanged();
    }

    public interface OnItemClick {
        void onItemClick(Object value);//value为传入的值

    }

    public void setOnItemClick(OnItemClick click) {
        mClick = click;
    }


}
