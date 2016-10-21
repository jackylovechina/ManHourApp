package com.shg.manhourapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.handle;

public class WelcomeActivity extends AppCompatActivity {

    private TextView WelcomeNum_TV;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            WelcomeNum_TV.setText(msg.what + "s");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        WelcomeNum_TV = (TextView) findViewById(R.id.tv_welcome_num);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 2; i >= 0; i--) {

                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = i;

                        handler.sendEmptyMessage(msg.what);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();

            }
        };

        timer.schedule(timerTask, 3000);
    }
}
