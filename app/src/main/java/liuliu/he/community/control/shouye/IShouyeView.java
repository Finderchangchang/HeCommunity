package liuliu.he.community.control.shouye;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public interface IShouyeView {
    void OnTitleImages(List list);//获得头部的图片集合

    void OnGuanggao(int type, List list);//加载广告

    void OnGoodList(List list[]);//加载商品列表

    void OnGoodType(List list);//加载商品类型
}
