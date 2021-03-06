package com.sensorplay;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jun on 2018-01-19.
 */

public class SensorRec extends Observable implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long mEventValue_0;
    private float mEventValue_1;
    private float mEventValue_2;
    private long mTime;
    private TextView mAccuracy;
    private String gsonStr;
    SharedPreferences sharedPreferences;

    public SensorRec(SensorManager sensorManager, Observer o) {
        this(sensorManager);
        addObserver(o);
    }

    public SensorRec(SensorManager mSensorManager) {
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ALL);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public SensorRec(long mTime, long mEventValue_0, float mEventValue_1, float mEventValue_2) {
        this.mTime = mTime;
        this.mEventValue_0 = mEventValue_0;
        this.mEventValue_1 = mEventValue_1;
        this.mEventValue_2 = mEventValue_2;
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {

        long mTime = event.timestamp;
        long mEventValue_0 = (long) event.values[0];
        float mEventValue_1 = event.values[1];
        float mEventValue_2 = event.values[2];

        SensorRec data = new SensorRec(mTime, mEventValue_0, mEventValue_1, mEventValue_2);
        this.setChanged();
        this.notifyObservers(data);

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("x", event.values[0]);
        params.put("y", event.values[1]);
        params.put("z", event.values[2]);
        gsonStr = JSON.toJSONString(params);
//        Log.d("aaa", String.valueOf(params));
    }

    public String getGsonStr(){
        return this.gsonStr;
    }

    public void Save1(Context context){
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("x", gsonStr);
        editor.commit();*/
        Toast.makeText(context, "토스트메시지입니다.", Toast.LENGTH_LONG).show();
    }

    public long getTimestamp() {
        return mTime;
    }

    public long getValueX() {
        return mEventValue_0;
    }

    public float getValueY() {
        return mEventValue_1;
    }

    public float getValueZ() {
        return mEventValue_2;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int Accuracy) {
    }


}
