package liuliu.he.community.model;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by liuliu on 2015/11/25   15:24
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class ChangeItemModel {
    private TextView tv;
    private ImageView iv;
    private RelativeLayout rl;
    private LinearLayout ll;

    public ChangeItemModel(TextView mtv, ImageView miv) {
        tv = mtv;
        iv = miv;
    }

    public ChangeItemModel(RelativeLayout mrl, LinearLayout mll, TextView mtv, ImageView miv) {
        tv = mtv;
        iv = miv;
        rl = mrl;
        ll = mll;
    }


    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public RelativeLayout getRl() {
        return rl;
    }

    public void setRl(RelativeLayout rl) {
        this.rl = rl;
    }

    public LinearLayout getLl() {
        return ll;
    }

    public void setLl(LinearLayout ll) {
        this.ll = ll;
    }
}
