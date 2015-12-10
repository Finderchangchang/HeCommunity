package liuliu.he.community.control.fenlei;

import android.content.Context;

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
    private IFenLeiView mView;
    List<GoodTypeModel> mList;

    public FenLeiListener(Context mContext, IFenLeiView mView) {
        this.mContext = mContext;
        this.mView = mView;
        new Thread(new loadFenLeiThread()).start();
    }

    class loadFenLeiThread implements Runnable {
        @Override
        public void run() {
            new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {//获得头部图片集合
                    List lists[];
                    if (model.isReturnX()) {
                        JSONArray array = (JSONArray) model.getData();
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
            image.setId(object.getInt("id"));
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
}
