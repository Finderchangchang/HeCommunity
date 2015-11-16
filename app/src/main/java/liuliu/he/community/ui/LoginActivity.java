package liuliu.he.community.ui;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import net.tsz.afinal.annotation.view.CodeNote;

import liuliu.custom.control.edittext.ImageEditText;
import liuliu.he.community.R;
import liuliu.he.community.base.BaseActivity;
import liuliu.he.community.control.login.ILoginView;
import liuliu.he.community.control.login.LoginListener;

/**
 * ��¼
 * Created by Administrator on 2015/11/16.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @CodeNote(id = R.id.login_user_id_et)
    ImageEditText user_id;//�û�
    @CodeNote(id = R.id.login_password_et)
    ImageEditText pwd;//����
    @CodeNote(id = R.id.login_button, click = "onClick")
    Button btn;
    @CodeNote(id = R.id.login_remember_pwd)
    CheckBox remember_pwd;
    @CodeNote(id = R.id.login_reg_user_ll, click = "onClick")
    LinearLayout reg_user_ll;//�û�ע��
    @CodeNote(id = R.id.login_get_pwd_ll, click = "onClick")
    LinearLayout get_pwd_ll;//��������
    LoginListener mListener;

    @Override
    public void initViews() {
        setContentView(R.layout.activtiy_login);
        mListener = new LoginListener(this, this, finalDb);
    }

    @Override
    public void initEvents() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (user_id.getText().isEmpty() || pwd.getText().isEmpty()) {
                    mUtils.ToastShort("�˺Ż����벻��Ϊ�գ�");
                } else {//ִ�е�¼����
                    mListener.UserLogin(user_id.getText(), pwd.getText());
                }
                break;
            case R.id.login_reg_user_ll://�û�ע��
                mUtils.IntentPost(RegUserActivity.class);
                break;
            case R.id.login_get_pwd_ll://��������
                break;
        }
    }

    @Override
    public void OnLoginResult(boolean result, String message) {
        if (result) {//��֤�ɹ���ִ�е�¼
            mUtils.IntentPost(MainActivity.class);
            if (remember_pwd.isChecked()) {//�洢���ڴ��е��Ǹ�

            }
        } else {
            mUtils.ToastShort("�˺Ż�����������������룡");
        }
    }
}
