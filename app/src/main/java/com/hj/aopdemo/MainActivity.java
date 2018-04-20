package com.hj.aopdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hj.aoplib.annotation.DebugTrace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @DebugTrace
    public void test(View v) {
        Log.d("MainActivity", "test-weaveJoinPoint");
        try {
            Thread.sleep(100);
            throw new IllegalArgumentException("Exception");
        } catch (Exception e) {
        }
    }
}
