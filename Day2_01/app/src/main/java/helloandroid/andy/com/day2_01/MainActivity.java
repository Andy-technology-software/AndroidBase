package helloandroid.andy.com.day2_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText telTex;
    private EditText infoTex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        telTex = (EditText) findViewById(R.id.telEdit);

        infoTex = (EditText) findViewById(R.id.dxEdit);

        Button sendBtn = (Button) findViewById(R.id.info_sendBtn);
        sendBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        System.out.println("发送");
        String info = infoTex.getText().toString();
        String tel = telTex.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(
                tel,
                null,
                info,
                null,
                null
        );
    }
}
