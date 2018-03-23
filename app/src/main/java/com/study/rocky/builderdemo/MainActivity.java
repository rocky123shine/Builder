package com.study.rocky.builderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        A.Builder builder = new A.Builder();
        A a = builder.setName("Apple").setNumber("IphoneX").setPrice("6800").build();


        setContentView(R.layout.activity_main);
        final TextView tv_content = findViewById(R.id.tv_content);
        Map<String, Object> map = new HashMap<>();
        map.put("cashier_name", "wangwu");
        map.put("merchant_id", "13");
        map.put("cashier_password", "111111");

        new HttpTask.Builder()
                .method("POST")
                .url("http://xlm.showxin.net/Api/Log/log_user")
                .paramMap(map)
                .onHttpResultListener(new HttpUtils.OnHttpResultListener() {
                    @Override
                    public void onResult(String result) {
                        Log.i("main", "返回内容：" + result);
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                        tv_content.setText(result);
                    }
                }).build().execute();
    }



}
