package liuliu.he.community.ui.first_frag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.custom.method.Utils;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseFragment;
import liuliu.he.community.control.wode.IWodeView;
import liuliu.he.community.control.wode.WodeListener;
import liuliu.he.community.model.WoDeModel;
import liuliu.he.community.ui.activity.BalanceDetailActivity;
import liuliu.he.community.ui.activity.GuanYuWoMenActivity;
import liuliu.he.community.ui.activity.JiFenDetailActivity;
import liuliu.he.community.ui.activity.MainActivity;
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
    Utils mUtils;
    @CodeNote(id = R.id.wode_ll_jifen, click = "onClick")
    LinearLayout ll_jifen;
    @CodeNote(id = R.id.wode_ll_yue, click = "onClick")
    LinearLayout ll_yue;
    @CodeNote(id = R.id.wode_ll_libao, click = "onClick")
    LinearLayout ll_libao;

    @Override
    public void initViews() {
        setContentView(R.layout.frag_wode);
        mContext = MainActivity.mIntails;
        mListener = new WodeListener(mContext, this);
        mUtils = new Utils(mContext);
    }

    @Override
    public void initEvents() {

    }

    private void setText(WoDeModel model) {
        person_center_balance_num.setText(model.getCash() + "");
        person_center_package_num.setText(model.getGift() + "");
        person_center_integral_num.setText(model.getPoint() + "");

        itemdingdan.setRightNumber("一个月内" + model.getOrder() + "条");
        itemdizhi.setRightNumber(model.getAddress() + "条");
        itemdaijinquan.setRightNumber(model.getCoupon() + "条");
        itemxiaoxi.setRightNumber("未读" + model.getMessage() + "条");

        itemwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.IntentPost(GuanYuWoMenActivity.class);
            }
        });
    }

    @Override
    public void returnResult(boolean isTrues, WoDeModel model) {
        if (isTrues) {
            if (model != null) {
                setText(model);
            }
        } else {

        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wode_ll_jifen:
                mUtils.IntentPost(JiFenDetailActivity.class);
                break;
            case R.id.wode_ll_yue:
                System.out.println("yue;..");
                mUtils.IntentPost(BalanceDetailActivity.class);
                break;
            case R.id.wode_ll_libao:

                break;
        }
    }
}
