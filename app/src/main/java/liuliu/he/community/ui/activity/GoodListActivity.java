package liuliu.he.community.ui.activity;

import android.widget.LinearLayout;
import net.tsz.afinal.annotation.view.CodeNote;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;

/**
 * Created by liuliu on 2015/11/23   13:35
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class GoodListActivity extends BaseActivity {
    @CodeNote(id = R.id.item_good_list_type_ll)
    LinearLayout type_ll;

    @Override
    public void initViews() {
        setContentView(R.layout.item_good_desc);

    }

    @Override
    public void initEvents() {

    }


}
