package liuliu.he.community.control.typelist;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.GoodModel;
import liuliu.he.community.model.TitleImagesModel;

/**
 * 商品列表信息
 * Created by Administrator on 2015/12/10.
 */
public class TypeListListener {
    ITypeListView mView;
    Context mContext;

    public TypeListListener(ITypeListView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    public void loadList(String link) {
        new Thread(new loadWodeNumber(link)).start();
    }

    class loadWodeNumber implements Runnable {
        String mResult;

        public loadWodeNumber(String mResult) {
            this.mResult = mResult;
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
                            List<GoodModel> goods = new ArrayList<GoodModel>();
                            for (int i = 0; i < array.length(); i++) {
                                GoodModel good = new GoodModel();
                                JSONObject obj = (JSONObject) array.getJSONObject(i);
                                good.setId(obj.getString("id"));
                                good.setName(obj.getString("name"));
                                good.setImage(obj.getString("image"));
                                good.setFeature(obj.getString("feature"));
                                good.setSales(obj.getString("sales"));
                                good.setStock(obj.getString("stock"));
                                good.setPrice(obj.getString("price"));
                                good.setPriceSales(obj.getString("priceSales"));
                                good.setIsSales(obj.getBoolean("isSales"));
                                good.setIsRecom(obj.getBoolean("isRecom"));
                                good.setIsNew(obj.getBoolean("isNew"));
                                good.setIsLimit(obj.getBoolean("isLimit"));
                                good.setIsRush(obj.getBoolean("isRush"));
                                good.setIsArea(obj.getBoolean("isArea"));
                                good.setIsPresent(obj.getBoolean("isPresent"));
                                good.setIsDrive(obj.getBoolean("isDrive"));
                                goods.add(good);
                            }
                            mView.loadGoodList(object.getBoolean("tail"), goods);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/product/list.php" + mResult);
        }
    }
}
