package liuliu.he.community.control.shouye;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.ProductModel;
import liuliu.he.community.model.TitleImagesModel;
import liuliu.he.community.model.TopImage;

/**
 * 主页面数据处理
 * Created by Administrator on 2015/11/16.
 */
public class ShouyeListener {
    IShouyeView mView;
    Context mContext;
    List<TopImage> mTopImgs;
    List<TopImage> mGuanggaoImgs;
    List<ProductModel> mGoodLists;
    List<GoodTypeModel> mGoodTypes;

    public ShouyeListener(IShouyeView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        loadGuanggao();
        loadTitleImgs();
        loadGoodLists();
        loadGoodTypes();
    }

    //加载顶部图片集合
    public void loadTitleImgs() {
        new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
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
                    mView.OnTitleImages(mTopImgs);
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/focus.php");
    }

    //加载广告及其类型
    public void loadGuanggao() {
        new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {//八种分类的一种（热门消息）
            @Override
            public void onResult(TitleImagesModel model) {
                if (model.isReturnX()) {
                    JSONObject object = (JSONObject) model.getData();
                    try {
                        final int type = object.getInt("type");
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
                        mView.OnGuanggao(type, mGuanggaoImgs);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/ad.php");
    }

    //加载商品列表
    public void loadGoodLists() {
        new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
            @Override
            public void onResult(TitleImagesModel model) {//获得头部图片集合
                List mList[] = new List[3];
                if (model.isReturnX()) {
                    mGoodLists = new ArrayList<>();
                    JSONArray array = (JSONArray) model.getData();

                    for (int i = 0; i < array.length(); i++) {
                        List<ProductModel> list = new ArrayList<ProductModel>();
                        try {
                            JSONArray arr = array.getJSONArray(i);
                            for (int j = 0; j < arr.length(); j++) {
                                ProductModel m = new ProductModel();
                                JSONObject object = arr.getJSONObject(j);
                                m.setGid(object.getString("id"));
                                m.setName(object.getString("name"));
                                m.setImage(object.getString("image"));
                                m.setPrice(object.getString("price"));
                                m.setPriceSales(object.getString("priceSales"));
                                list.add(m);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mList[i] = list;
                    }
                    mView.OnGoodList(mList);
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/product.php");
    }

    //加载商品分类
    public void loadGoodTypes() {
        new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
            @Override
            public void onResult(TitleImagesModel model) {//获得头部图片集合
                if (model.isReturnX()) {
                    mGoodTypes = new ArrayList<>();
                    JSONArray array = (JSONArray) model.getData();
                    for (int i = 0; i < array.length(); i++) {
                        GoodTypeModel typeModel = new GoodTypeModel();
                        try {
                            typeModel.setImage(array.getJSONObject(i).getString("image"));
                            typeModel.setTitle(array.getJSONObject(i).getString("title"));
                            typeModel.setT1(array.getJSONObject(i).getString("t1"));
                            typeModel.setT2(array.getJSONObject(i).getString("t2"));
                            typeModel.setLink(array.getJSONObject(i).getString("link"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mGoodTypes.add(typeModel);
                    }
                    mView.OnGoodType(mGoodTypes);
                }
            }
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/category.php");
    }
}

