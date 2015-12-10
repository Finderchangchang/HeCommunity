package liuliu.he.community.model;

import java.io.Serializable;

/**
 * 代金券 by Administrator on 2015/12/10.
 */
public class DaiJinQuanModel implements Serializable{
    private int cid;//如果没有类别限制则为0, 注:cid为0时, 为全站通用代金券)
    private String cname;//类别名称
    private String color;//代金券背景色
    private String value;//代金券价值
    private double need;//最小订单额
    private String date;//到期日期
    private String time;//到期时间

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getNeed() {
        return need;
    }

    public void setNeed(double need) {
        this.need = need;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
