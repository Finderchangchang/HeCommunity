package liuliu.he.community.control.fenlei;

import android.content.Context;

import net.tsz.afinal.FinalDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.GoodTypeModel;
import liuliu.he.community.model.TitleImagesModel;

/**
 * Created by Administrator on 2015/12/9.
 */
public class FenLeiListener {
    private Context mContext;
    private IFenleiView mView;
    List<GoodTypeModel> mList;
    FinalDb mDB;

    public FenLeiListener(Context mContext, IFenleiView mView) {
        this.mContext = mContext;
        this.mView = mView;
        mDB = FinalDb.create(mContext);
        initData();
    }

    class loadFenLeiThread implements Runnable {
        @Override
        public void run() {
            new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {//获得头部图片集合
                    model.setType("fenleiye");
                    saveCache(model);
                    List lists[];
                    if (model.getReturnX().equals("OK")) {
                        JSONArray array = null;
                        try {
                            array = new JSONArray(model.getData());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        lists = new List[array.length()];
                        for (int i = 0; i < array.length(); i++) {
                            mList = new ArrayList<>();
                            try {
                                mList.add(getModel(array.getJSONObject(i), 1));
                                JSONArray a = array.getJSONObject(i).getJSONArray("small");
                                for (int j = 0; j < a.length(); j++) {
                                    mList.add(getModel(a.getJSONObject(j), 1));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            lists[i] = mList;
                        }
                        mView.loadFenLei(lists);
                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/product/category.php");
        }
    }

    public GoodTypeModel getModel(JSONObject object, int num) {
        GoodTypeModel image = new GoodTypeModel();
        try {
            image.setSid(object.getString("id"));
            image.setBid(object.getInt("bid"));
            image.setTitle(object.getString("name"));
            image.setImage(object.getString("image"));
            image.setIsPreferential(object.getBoolean("isPreferential"));
            image.setIsPresent(object.getBoolean("isPresent"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
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

    /**
     * 加载数据
     */
    private void initData() {
        List<TitleImagesModel> list = mDB.findAllByWhere(TitleImagesModel.class, "type='fenleiye'");
        if (list.size() > 0) {//缓存中有数据，直接加载
            List lists[];
            if (list.get(0).getReturnX().equals("OK")) {
                JSONArray array = null;
                try {
                    array = new JSONArray(list.get(0).getData());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lists = new List[array.length()];
                for (int i = 0; i < array.length(); i++) {
                    mList = new ArrayList<>();
                    try {
                        mList.add(getModel(array.getJSONObject(i), 1));
                        JSONArray a = array.getJSONObject(i).getJSONArray("small");
                        for (int j = 0; j < a.length(); j++) {
                            mList.add(getModel(a.getJSONObject(j), 1));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    lists[i] = mList;
                }
                mView.loadFenLei(lists);
            }
        } else {
            new Thread(new loadFenLeiThread()).start();
        }
    }
}
