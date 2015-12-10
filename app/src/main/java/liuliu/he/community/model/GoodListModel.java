package liuliu.he.community.model;

import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 */
public class GoodListModel {

    /**
     * tail : false
     * data : [{"id":"785","name":"小蒙香-火锅底料",
     * "image":"http://img.hesq.com.cn/fresh/upload/product/20151030/1446187383029.jpg",
     * "feature":"火锅底料","sales":"46","stock":"100","price":3.5,
     * "priceSales":3.5,"isSales":false,"isRecom":false,"isNew":false,
     * "isLimit":false,"isRush":false,"isArea":false,"isPresent":false,
     * "isDrive":false}]
     */
    private boolean tail;

    public void setTail(boolean tail) {
        this.tail = tail;
    }


    public boolean isTail() {
        return tail;
    }

    private List<GoodModel> goods;

    public List<GoodModel> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodModel> goods) {
        this.goods = goods;
    }
}
