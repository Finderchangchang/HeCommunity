package liuliu.he.community.base;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import liuliu.he.community.model.TitleImagesModel;


/**
 * 和社区解析json格式
 * Created by Administrator on 2015/12/7.
 */
public class VolloyTask {
    Context mContext;

    public VolloyTask(Context mContext) {
        this.mContext = mContext;
    }

    public interface OnReturn {
        void onResult(TitleImagesModel model);
    }

    public void getJson(final OnReturn on, String url) {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        final TitleImagesModel model = new TitleImagesModel();
        JsonRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            model.setError(response.getString("error"));//错误信息
                            model.setReturnX(response.getString("return"));
                            model.setData(response.get("data"));//结果为true，返回结果
                            on.onResult(model);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                model.setReturnX("");
                model.setError(error.toString());
                model.setData(null);
                on.onResult(model);
            }
        }

        );
        mQueue.add(jsonObjectRequest);
    }
}