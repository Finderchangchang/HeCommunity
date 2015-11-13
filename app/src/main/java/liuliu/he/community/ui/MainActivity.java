package liuliu.he.community.ui;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.custom.control.toolbar.TToolbar;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;


public class MainActivity extends BaseActivity {
    @CodeNote(id=R.id.main_toolbar)
    TToolbar mToolbar;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initEvents() {
        //������¼�
        mToolbar.setLeftOnClick(new TToolbar.LeftOnClickListener() {
            @Override
            public void leftclick() {

            }
        });
        //�м����¼�
        mToolbar.setCenterOnClick(new TToolbar.CenterOnClickListener() {
            @Override
            public void centerclick() {

            }
        });
        //�Ҳ����¼�
        mToolbar.setRightOnClick(new TToolbar.RightOnClickListener() {
            @Override
            public void rightclick() {

            }
        });
    }
}
