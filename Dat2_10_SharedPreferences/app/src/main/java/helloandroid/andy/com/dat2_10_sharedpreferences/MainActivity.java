package helloandroid.andy.com.dat2_10_sharedpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private EditText etNumber;
    private EditText etPassword;
    private CheckBox cbRemerberPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        cbRemerberPWD = (CheckBox) findViewById(R.id.cb_remerber_pwd);
        Button btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);

        //取出值  回显数据
        Map<String, String> userInfoMap = UtilsOfSharedPreferences.getUserInfo(this);
        if(userInfoMap != null) {
            etNumber.setText(userInfoMap.get("number"));
            etPassword.setText(userInfoMap.get("password"));
        }
    }

    @Override
    public void onClick(View view) {
        String number = etNumber.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            // 验证不为空
            Toast.makeText(this, "请正确填写", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2.保存验证
        if(cbRemerberPWD.isChecked()) {

            boolean isSuccess = UtilsOfSharedPreferences.saveUserInfo(this, number, password);

            if(isSuccess) {
                Toast.makeText(this, "保存成功", 0).show();
            } else {
                Toast.makeText(this, "保存失败", 0).show();
            }
        }

        // 3. 登录成功
        Toast.makeText(this, "登录成功", 0).show();
    }
}
