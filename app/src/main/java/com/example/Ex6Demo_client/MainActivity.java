package com.example.Ex6Demo_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et_main_content)
    EditText et_main_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new Thread(new ClientThread()).start();
    }
    @OnClick(R.id.btn_main_send)
    void send(){
        Message msg=new Message();
        msg.what=0x1234;
        Bundle bundle =new Bundle();
        bundle.putString("sendContent",et_main_content.getText().toString());
        msg.setData(bundle);
        ClientThread.sendHandler.sendMessage(msg);
    }
}