package com.gsls.speeddetection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GT_";

    private TextView tv_speed;

    @SuppressLint("HandlerLeak")
    private Handler mHnadler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    tv_speed.setText("当前网速： " + msg.obj.toString());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_speed = findViewById(R.id.tv_speed);
        int networkState = IntenetUtil.getNetworkState(this);
        Log.i(TAG, "onCreate: 网络状态:" + networkState);

        new NetWorkSpeedUtils(this,mHnadler).startShowNetSpeed();
    }
}
