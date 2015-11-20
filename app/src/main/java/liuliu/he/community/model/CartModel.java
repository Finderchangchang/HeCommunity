package liuliu.he.community.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/17.
 */
public class CartModel implements Serializable {
    private String name;
    private String num;
    private String price;
    private Drawable bitmap;

    public Drawable getBitmap() {
        return bitmap;
    }

    public void setBitmap(Drawable bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
