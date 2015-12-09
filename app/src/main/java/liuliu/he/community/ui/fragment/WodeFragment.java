package liuliu.he.community.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.wode.IWodeView;
import liuliu.he.community.control.wode.WodeListener;
import liuliu.he.community.model.WoDeModel;
import liuliu.he.community.ui.activity.ListDemoActivity;
import liuliu.he.community.view.MyItemView;

/**
 * Created by Administrator on 2015/11/25.
 */
public class WodeFragment extends BaseFragment implements IWodeView {
    Context mContext;
    @CodeNote(id = R.id.wode_item_libao)
    MyItemView itemlibao;
    @CodeNote(id = R.id.wode_item_dingdan)
    MyItemView itemdingdan;
    @CodeNote(id = R.id.wode_item_dizhi)
    MyItemView itemdizhi;
    @CodeNote(id = R.id.wode_item_daijinquan)
    MyItemView itemdaijinquan;
    @CodeNote(id = R.id.wode_item_xiaoxi)
    MyItemView itemxiaoxi;
    @CodeNote(id = R.id.wode_item_bangzhu)
    MyItemView itembangzhu;
    @CodeNote(id = R.id.wode_item_yijian)
    MyItemView itemyijian;
    @CodeNote(id = R.id.wode_item_women)
    MyItemView itemwomen;

    @CodeNote(id = R.id.person_center_balance_num)
    TextView person_center_balance_num;//我的余额
    @CodeNote(id = R.id.person_center_package_num)
    TextView person_center_package_num;//礼包
    @CodeNote(id = R.id.person_center_integral_num)
    TextView person_center_integral_num;//积分
    WodeListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.frag_wode, container, false);
        FinalActivity.initInjectedView(this, viewRoot);
        mContext = ListDemoActivity.mIntails;
        mListener = new WodeListener(mContext, this);
        return viewRoot;
    }

    private void setText(WoDeModel model) {
        person_center_balance_num.setText(model.getCash() + "");
        person_center_package_num.setText(model.getGift() + "");
        person_center_integral_num.setText(model.getPoint() + "");

        itemdingdan.setRightNumber("一个月内" + model.getOrder() + "条");
        itemdizhi.setRightNumber(model.getAddress() + "条");
        itemdaijinquan.setRightNumber(model.getCoupon() + "条");
        itemxiaoxi.setRightNumber("未读" + model.getMessage() + "条");
    }

    @Override
    public void returnResult(boolean isTrues, WoDeModel model) {
        if (isTrues) {
            if(model!=null) {
                setText(model);
            }
        } else {

        }
    }
}
