package com.example.sensordeposicion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText posicion;
    private SensorManager sm;
    private  Sensor sa;
    private SensorEventListener SEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posicion = findViewById(R.id.et_posicion);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sa = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sa == null) {
            Toast.makeText(this, "No hay sensor", Toast.LENGTH_SHORT).show();
        }else {
            SEL = new SensorEventListener() {
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float x = event.values[0];
                    posicion.setText(Float.toString(x));

                    if (x > -5 && latigo == 0) {

                    }
                }
            };
        }
    }
    public void iniciar() {
        sm.registerListener(SEL, sa, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onResume() {
        iniciar();
        super.onResume();
    }
    public void detener(){
        sm.unregisterListener(SEL);
    }
    protected void onStop() {
        detener();
        super.onStop();
    }
}