package liuliu.he.community.control.main;

import android.content.Context;

import liuliu.he.community.base.VolloyTask;
import liuliu.he.community.model.TitleImagesModel;

/**
 * 主页面数据处理
 * Created by Administrator on 2015/11/16.
 */
public class MainListener {
    IMainView mView;
    Context mContext;

    public MainListener(IMainView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    //获得顶部轮动图片集合
    public void TitleImages() {
        mView.OnTitleImages(VolloyTask.GetJson("http://www.hesq.com.cn/fresh/fore/logic/app/home/focus.php", mContext));
    }
}

