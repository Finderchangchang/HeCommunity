package liuliu.he.community.control.typelist;

import java.util.List;

import liuliu.he.community.model.GoodListModel;
import liuliu.he.community.model.GoodModel;

/**
 * 商品列表接口
 * Created by Administrator on 2015/12/10.
 *
 * @param <T>
 */
public interface ITypeListView<T> {
    void loadGoodList(boolean result, List<T> list);//加载商品列表(加载出来的数据)
}
