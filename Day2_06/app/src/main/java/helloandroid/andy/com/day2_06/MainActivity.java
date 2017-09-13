package helloandroid.andy.com.day2_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import helloandroid.andy.com.utils.Utils;
import helloandroid.andy.com.utils.UtilsOfSDCard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private  String qqNum;
    private  String qqPwd;

    private EditText numText;
    private EditText pwdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);

        numText = (EditText) findViewById(R.id.et_num);
        pwdText = (EditText) findViewById(R.id.et_pwd);

        //回显数据
        Map<String, String> userInfoMap = UtilsOfSDCard.getUserInfo(this);
        if(userInfoMap != null) {
            numText.setText(userInfoMap.get("number"));
            pwdText.setText(userInfoMap.get("password"));
        }
    }

    @Override
    public void onClick(View v) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.rember_pwd);

        //获取QQ号
        qqNum = ((EditText) findViewById(R.id.et_num)).getText().toString();

        //获取密码
        qqPwd = ((EditText) findViewById(R.id.et_pwd)).getText().toString();

        if (TextUtils.isEmpty(qqNum) || TextUtils.isEmpty(qqPwd)) {
            Toast.makeText(this,"请正确输入",Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkBox.isChecked()) {
            Log.i(TAG,"QQ号：" + qqNum + "密码：" + qqPwd);
            boolean isSaved = UtilsOfSDCard.saveUserInfo(this, qqNum, qqPwd);
            if (isSaved) {
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
            }

        }

        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }
}
