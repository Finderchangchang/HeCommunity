package liuliu.he.community.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 头部图片
 * Created by Administrator on 2015/12/7.
 */
public class TitleImagesModel<T> {

    /**
     * return : OK
     * error :
     * data : [{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151130/1448835776487.jpg","link":"#"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151130/1448835796703.jpg","link":"#"},{"img":"http://img.hesq.com.cn/fresh/upload/ad/20151130/1448835823156.jpg","link":"#"}]
     */
    private boolean returnX;
    private String error;
    /**
     * img : http://img.hesq.com.cn/fresh/upload/ad/20151130/1448835776487.jpg
     * link : #
     */

    private Object data;

    public boolean isReturnX() {
        return returnX;
    }

    public void setReturnX(String returnX) {
        if (returnX.equals("OK")) {
            this.returnX = true;
        } else {
            this.returnX = false;
        }

    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
