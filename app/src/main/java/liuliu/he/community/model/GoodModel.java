package liuliu.he.community.model;

/**
 * Created by liuliu on 2015/11/30   16:06
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class GoodModel {
    private int goodid;
    private String goodName;
    private String goodImgUrl;
    private String goodPrice;
    private String goodDesc;
    private int goodCount;

    public GoodModel(int id, String name, String url, String price, String desc, int count) {
        goodid = id;
        goodName = name;
        goodImgUrl = url;
        goodPrice = price;
        goodDesc = desc;
        goodCount = count;
    }

    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImgUrl() {
        return goodImgUrl;
    }

    public void setGoodImgUrl(String goodImgUrl) {
        this.goodImgUrl = goodImgUrl;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }
}
