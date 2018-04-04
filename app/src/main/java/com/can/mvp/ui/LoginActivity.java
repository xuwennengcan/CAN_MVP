package com.can.mvp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.mvps.interfaces.LoginInterface;
import com.can.mvp.mvps.models.LoginModel;
import com.can.mvp.mvps.presenters.LoginPresenter;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/4/3.
 */

public class LoginActivity extends BaseActivity implements LoginInterface.View{

    @BindView(id = R.id.et_name)
    private EditText et_name;
    @BindView(id = R.id.et_password)
    private EditText et_password;
    @BindView(id = R.id.btn_submit,click = true)
    private Button btn_submit;

    LoginInterface.Presenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new LoginPresenter(this,new LoginModel());
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        switch (view.getId()){
            case R.id.btn_submit:
                presenter.login(et_name.getText().toString().trim(),et_password.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {
        Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
