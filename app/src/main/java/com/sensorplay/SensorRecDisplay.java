package com.sensorplay;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jun on 2018-01-19.
 */

public class SensorRecDisplay extends Activity implements Observer {

    private TextView mXaxis;
    private TextView mYaxis;
    private TextView mZaxis;
    private TextView mNano;
    private long mXaxis1;
    private float mYaxis1;
    private float mZaxis1;
    private long mNano1;
    SensorManager mSensorManager;
    private SensorRec sensorRec;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorder_layout);

        mXaxis = (TextView)findViewById(R.id.rec01);
        mYaxis = (TextView)findViewById(R.id.rec02);
        mZaxis = (TextView)findViewById(R.id.rec03);
        mNano = (TextView)findViewById(R.id.nanosec);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorRec = new SensorRec(mSensorManager, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object data) {
        SensorRec sensorRec = (SensorRec) data;
        mNano1 = sensorRec.getTimestamp();
        mXaxis1 = sensorRec.getValueX();
        mYaxis1 = sensorRec.getValueY();
        mZaxis1 = sensorRec.getValueZ();

        mXaxis.setText(String.valueOf(mXaxis1));
        mYaxis.setText(String.valueOf(mYaxis1));
        mZaxis.setText(String.valueOf(mZaxis1));
        mNano.setText(String.valueOf(mNano1));
    }
}