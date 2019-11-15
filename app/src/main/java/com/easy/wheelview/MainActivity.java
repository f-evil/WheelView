package com.easy.wheelview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WheelView mWv;
    private TextView mTvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWv = (WheelView) findViewById(R.id.wv);
        mTvNum = (TextView) findViewById(R.id.tv_num);

        mWv.setOnRollListener(new WheelView.OnRollListener() {
            @Override
            public void onRollResult(float num) {
                mTvNum.setText(num + "");
            }
        });
    }
}
