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
     */
    private int id;
    private String image;
    private String title;
    private String t1;
    private String t2;
    private String link;

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

}
