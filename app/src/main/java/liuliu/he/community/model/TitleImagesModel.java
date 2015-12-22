package liuliu.he.community.model;


/**
 * 头部图片
 * Created by Administrator on 2015/12/7.
 */
public class TitleImagesModel {
    private int id;
    private String type;//存储的数据内容
    private String returnX;
    private String error;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnX() {
        return returnX;
    }

    public void setReturnX(String returnX) {
        this.returnX = returnX;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
