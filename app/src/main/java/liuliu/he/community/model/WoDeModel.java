package liuliu.he.community.model;

import java.io.Serializable;

/**
 * 我的 by Administrator on 2015/12/9.
 */
public class WoDeModel implements Serializable {
    private double cash;//余额
    private int point;//积分
    private int gift;//礼包个数
    private int order;//订单个数
    private int address;//收获地址个数
    private int coupon;//代金券个数
    private int message;//未读消息

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
