package liuliu.he.community.control.typelist;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.TitleImagesModel;

/**
 * Created by Administrator on 2015/12/10.
 */
public class TypeListListener {
    ITypeListView mView;
    Context mContext;

    public TypeListListener(ITypeListView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        new Thread(new loadWodeNumber()).start();
    }

    class loadWodeNumber implements Runnable {

        @Override
        public void run() {
            new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {//获得头部图片集合
                    if (model.isReturnX()) {
                        JSONObject object = (JSONObject) model.getData();
                        try {
                            System.out.println("address::" + object.getInt("address"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/product/list.php?page=1&number=5&bid=14");
        }
    }
}
