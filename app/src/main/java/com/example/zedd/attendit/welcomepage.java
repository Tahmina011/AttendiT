package com.example.zedd.attendit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class welcomepage extends AppCompatActivity {
    private Handler mHandler;

    private long delay = 2000;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcomepage);
        Timer timer = new Timer();
        timer.schedule(task, delay);
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            Intent in = new Intent().setClass(welcomepage.this,
                    LoginActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();

        }
    };
}
