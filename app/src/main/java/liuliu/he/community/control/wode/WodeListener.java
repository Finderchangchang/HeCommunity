package liuliu.he.community.control.wode;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.TitleImagesModel;
import liuliu.he.community.model.TopImage;
import liuliu.he.community.model.WoDeModel;

/**
 * Created by Administrator on 2015/12/9.
 */
public class WodeListener {
    IWodeView iWodeView;
    Context mContext;
    WoDeModel woDeModel;
    public WodeListener(Context context,IWodeView view){
        this.mContext=context;
        this.iWodeView=view;
        new Thread(new loadWodeNumber()).start();
    }

    class loadWodeNumber implements Runnable {

        @Override
        public void run() {
            new VolloyTask(mContext).getJson(new VolloyTask.OnReturn() {
                @Override
                public void onResult(TitleImagesModel model) {//获得头部图片集合
                    if (model.getReturnX().equals("OK")) {

                        JSONObject object = null;
                        try {
                            object = new JSONObject(model.getData());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        woDeModel=new WoDeModel();
                        try {
                            System.out.println("address::" + object.getInt("address"));
                            woDeModel.setAddress(object.getInt("address"));
                            woDeModel.setCash(object.getDouble("cash"));
                            woDeModel.setPoint(object.getInt("point"));
                            woDeModel.setGift(object.getInt("gift"));
                            woDeModel.setOrder(object.getInt("order"));
                            woDeModel.setCoupon(object.getInt("coupon"));
                            woDeModel.setMessage(object.getInt("message"));
                            iWodeView.returnResult(true,woDeModel);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            iWodeView.returnResult(false,null);
                        }
                    }else{
                        iWodeView.returnResult(false,null);
                    }
                }
            }, "http://www.hesq.com.cn/fresh/fore/logic/app/user/home.php");
        }
    }
}
