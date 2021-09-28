package com.example.yeono.playwalk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView steps, time, dist;
    Button reset, play, reQ, report;
    SensorManager sm;
    BroadcastReceiver receiver;
    String serviceData;
    Intent Service;
    private  int count=StepValue.Step;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;





    private static final int SHAKE_THRESHOLD = 1000;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Service = new Intent(this,StepValue.class);

        sm= (SensorManager)getSystemService(SENSOR_SERVICE);

        receiver = new MyMainLocalRecever();
        steps=findViewById(R.id.steps);
        time = findViewById(R.id.time);
        dist =findViewById(R.id.dist);
        reset = findViewById(R.id.reset);
        play= findViewById(R.id.play);
        reQ= findViewById(R.id.reQ);
        report = findViewById(R.id.report);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    IntentFilter mainFilter = new IntentFilter("java.step");

                    registerReceiver(receiver, mainFilter);

                    startService(Service);
                    //txtMsg.setText("After stoping Service:\n"+service.getClassName());

                }
                catch (Exception e) {
                    // TODO: handle exception
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        try{

            IntentFilter mainFilter = new IntentFilter("java.step");

            registerReceiver(receiver, mainFilter);

            startService(Service);
            //txtMsg.setText("After stoping Service:\n"+service.getClassName());

        }
        catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();

    }
//    @Override
//    protected void onStart() {
//        Sensor s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        boolean check = sm.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
//        if(check == false){
//            Toast.makeText(this, "가속도 센서를 지원하지 않음", Toast.LENGTH_SHORT).show();
//        }
//
//        super.onStart();
//
//    }

 //   @Override
//    protected void onStop() {
//        sm.unregisterListener(this);
//        super.onStop();
//
//    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//
//
//            double x = event.values[0];
//            double y = event.values[1];
//            double z = event.values[2];
//            steps.setText("x : " + x + "\n");
//            steps.append("y : " + y + "\n");
//            steps.append("z : " + z + "\n");
//            long currentTime = System.currentTimeMillis();
//            long gabOfTime = (currentTime - lastTime);
//            if (gabOfTime > 2000) {
//                lastTime = currentTime;
//                x = event.values[SensorManager.DATA_X];
//                y = event.values[SensorManager.DATA_Y];
//                z = event.values[SensorManager.DATA_Z];
//
//                speed = (float)Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;
//
//                if (speed > SHAKE_THRESHOLD) {
//                StepValue.Step= count++;
//                String msg = StepValue.Step / 2 + "";
//
//                    try{
//
//                        IntentFilter mainFilter = new IntentFilter("com.example.yeono.playwalk");
//
//                        registerReceiver(receiver, mainFilter);
//
//                        startService(Service);
//                        //txtMsg.setText("After stoping Service:\n"+service.getClassName());
//                        Toast.makeText(getApplicationContext(), "���� ����",Toast.LENGTH_SHORT).show();
//                    }
//                    catch (Exception e) {
//                        // TODO: handle exception
//                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                dist.setText(""+count);
//                }
//
//                lastX = event.values[DATA_X];
//                lastY = event.values[DATA_Y];
//                lastZ = event.values[DATA_Z];
//            }
//
//
//
//
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (sm != null) {
//            sm.unregisterListener(this);
//            StepValue.Step = 0;
//        }
//
//
//
//        super.onDestroy();
//    }
    class MyMainLocalRecever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            serviceData = intent.getStringExtra("serviceData");

           steps.setText(serviceData);

            Toast.makeText(getApplicationContext(), "Walking . . . ", Toast.LENGTH_SHORT).show();

        }
    }

}
