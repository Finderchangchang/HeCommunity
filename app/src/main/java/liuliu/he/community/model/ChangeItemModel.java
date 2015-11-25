package liuliu.he.community.model;

import android.widget.ImageView;
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

    public ChangeItemModel(TextView mtv, ImageView miv) {
        tv = mtv;
        iv = miv;
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
}
