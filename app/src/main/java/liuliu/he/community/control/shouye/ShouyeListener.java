package liuliu.he.community.control.shouye;

import android.content.Context;

import net.tsz.afinal.FinalDb;

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
    FinalDb mDB;

    public ShouyeListener(IShouyeView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mDB = FinalDb.create(mContext);
    }

    /**
     * 加载顶部图片集合
     */
    class loadThread implements Runnable {
        String mType;
        String mUrl;
        OnLoad mLoad;

        public loadThread(String mType, OnLoad load, String mUrl) {
            this.mType = mType;
            this.mUrl = mUrl;
            this.mLoad = load;
        }

        @Override
        public void run() {
            List<TitleImagesModel> list = mDB.findAllByWhere(TitleImagesModel.class, "type='" + mType + "'");
            if (list.size() > 0) {//加载缓存，且有缓存
                mLoad.load(list.get(0));
            } else {
                new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                    @Override
                    public void onResult(TitleImagesModel model) {//获得头部图片集合
                        mLoad.load(model);
                    }
                }, mUrl);
            }
        }
    }

    private interface OnLoad {
        void load(TitleImagesModel model);
    }

    /**
     * 加载顶部图片集合
     *
     * @param cache 加载缓存（true,加载缓存。false,访问网络）
     */
    public void loadTitle(final boolean cache) {
        new Thread(new loadThread("titleimg", new OnLoad() {
            @Override
            public void load(TitleImagesModel model) {
                if (model.getReturnX().equals("OK")) {
                    if (!cache) {//加载网络数据
                        model.setType("titleimg");//设置缓存的内容为广告
                        saveCache(model);
                    }
                    mTopImgs = new ArrayList<>();
                    JSONArray array = null;
                    try {
                        array = new JSONArray(model.getData());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/focus.php")).start();
    }

    /**
     * 加载广告信息集合
     *
     * @param cache 加载缓存（true,加载缓存。false,访问网络）
     */
    public void loadGuangGao(final boolean cache) {
        new Thread(new loadThread("guanggao", new OnLoad() {
            @Override
            public void load(TitleImagesModel model) {
                if (model.getReturnX().equals("OK")) {
                    if (!cache) {
                        model.setType("guanggao");//设置缓存的内容为广告
                        saveCache(model);
                    }
                    JSONObject object = null;
                    try {
                        object = new JSONObject(model.getData());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/ad.php")).start();
    }

    /**
     * 加载商品分类集合信息
     *
     * @param cache 加载缓存（true,加载缓存。false,访问网络）
     */
    public void loadGoodLists(final boolean cache) {
        new Thread(new loadThread("goodlists", new OnLoad() {
            @Override
            public void load(TitleImagesModel model) {
                List mList[] = new List[3];
                if (model.getReturnX().equals("OK")) {
                    if (!cache) {
                        model.setType("goodlists");//设置缓存的内容为广告
                        saveCache(model);
                    }
                    mGoodLists = new ArrayList<>();
                    JSONArray array = null;
                    try {
                        array = new JSONArray(model.getData());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/product.php")).start();
    }

    /**
     * 加载商品分类集合信息
     *
     * @param cache 加载缓存（true,加载缓存。false,访问网络）
     */
    public void loadTypes(final boolean cache) {
        new Thread(new loadThread("goodtypes", new OnLoad() {
            @Override
            public void load(TitleImagesModel model) {
                if (model.getReturnX().equals("OK")) {
                    if (!cache) {
                        model.setType("goodtypes");//设置缓存的内容为广告
                        saveCache(model);
                    }
                    mGoodTypes = new ArrayList<>();
                    JSONArray array = null;
                    try {
                        array = new JSONArray(model.getData());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        }, "http://www.hesq.com.cn/fresh/fore/logic/app/home/category.php")).start();
    }

    private void saveCache(TitleImagesModel model) {
        List<TitleImagesModel> list = mDB.findAllByWhere(TitleImagesModel.class, "type='" + model.getType() + "'");
        if (list.size() > 0) {
            model.setId(list.get(0).getId());
            mDB.update(model);
        } else {
            mDB.save(model);
        }
    }
}

