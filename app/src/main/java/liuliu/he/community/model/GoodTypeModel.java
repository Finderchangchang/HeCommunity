package liuliu.he.community.model;

import java.util.List;

/**
 * Created by Administrator on 2015/12/8.
 */
public class GoodTypeModel {

    /**
     * image : 字符串(图片地址), http://img.hesq.com.cn/fresh/upload/ad/20151130/1448836122892.jpg
     * title : 字符串(标题) 精品主食
     * t1 : 字符串(第一行灰文字) 速冻水饺
     * t2 : 字符串(第二行灰文字) 馄饨 方便面
     * link :字符串(链接地址)  ../product/list.php?bid=69
     * isPreferential: bool(是否满就优惠)
     * isPresent: bool(是否满就送)
     */
    private int id;
    private int bid;
    private String image;
    private String title;
    private String t1;
    private String t2;
    private String link;
    private boolean isPreferential;
    private boolean isPresent;

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public boolean isPreferential() {
        return isPreferential;
    }

    public void setIsPreferential(boolean isPreferential) {
        this.isPreferential = isPreferential;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}
