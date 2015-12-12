package liuliu.he.community.control.typelist;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liuliu.custom.method.Utils;
import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.model.TitleImagesModel;

/**
 * 商品列表信息
 * Created by Administrator on 2015/12/10.
 */
public class TypeListListener<T> {
    ITypeListView mView;
    Context mContext;

    public TypeListListener(ITypeListView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    public void loadList(String link, String tClass) {
        new Thread(new loadWodeNumber(link, tClass)).start();
    }

    class loadWodeNumber implements Runnable {
        String mResult;
        String mClass;

        public loadWodeNumber(String mResult, String mClass) {
            this.mResult = mResult;
            this.mClass = mClass;
        }

        @Override
        public void run() {
            new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {//获得头部图片集合
                    if (model.isReturnX()) {
                        JSONObject object = (JSONObject) model.getData();
                        try {
                            JSONArray array = object.getJSONArray("data");
                            List<T> goods = new ArrayList<T>();
                            for (int i = 0; i < array.length(); i++) {
                                goods.add((T) Utils.getObject(mClass, array.getJSONObject(i)));
                            }
                            mView.loadGoodList(object.getBoolean("tail"), goods);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/product/list.php" + mResult);
        }
    }


}
