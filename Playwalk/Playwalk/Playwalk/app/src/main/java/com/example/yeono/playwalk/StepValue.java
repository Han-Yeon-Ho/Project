package com.example.yeono.playwalk;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Yeono on 2018-05-28.
 */

public class StepValue extends Service implements SensorEventListener {
    public  static  int Step =0 ;

    int count = Step;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;


    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 800;


    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;


    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.i("MyServiceIntent","Service is Create");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.i("MyServiceIntent","Service is started");


        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i("MyServiceIntent","Service is destroy");

        if (sensorManager != null)
            sensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);


            if (gabOfTime > 100) {
                lastTime = currentTime;


                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];


                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;



                if (speed > SHAKE_THRESHOLD) {

                    Log.e("Step!", "SHAKE");

                    Intent myFilteredResponse = new Intent("java.step");

                   Step = count++;

                    String msg = Step + "" ;
                    myFilteredResponse.putExtra("serviceData", msg);

                    sendBroadcast(myFilteredResponse);

                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }

    }

}
