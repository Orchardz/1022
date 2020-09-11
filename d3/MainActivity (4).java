//Noah Chiang
// youtube link:https://youtu.be/U2uLnhrkND4
//ID:216612285
package com.example.mcalcproo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.roumani.i2c.MPro;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener , SensorEventListener {
    private TextToSpeech tts;
    private SensorManager sensorManager;
    Sensor accelerometer;
    public void onInit(int initStatus){
        this.tts.setLanguage(Locale.US);
    }
    public void onAccuracyChanged(Sensor arg0, int arg1){

    }
    public void onSensorChanged(SensorEvent event){
        double ax= event.values[0];
        double ay = event.values[1];
        double az= event.values[2];
        double a= Math.sqrt(ax*ax+ay*ay+ az*az);
        if (a>10 )
        {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tts = new TextToSpeech(this,this);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }


    public void buttonClicked(View v) {




        try {
            MPro mp = new MPro();
            EditText pBoxView = (EditText) findViewById(R.id.pBox);
            String principle = pBoxView.getText().toString();

            EditText aBoxView = (EditText) findViewById(R.id.aBox);
            String amortization = aBoxView.getText().toString();

            EditText iBoxView = (EditText) findViewById(R.id.iBox);
            String interest = iBoxView.getText().toString();

            System.out.println(mp.computePayment("%,.2f"));
            System.out.println(mp.outstandingAfter(2, "%,16.0f"));

            mp.setPrinciple(principle);
            mp.setAmortization(amortization);
            mp.setInterest(interest);
            String s = "Monthly Payment= " + mp.computePayment("%,.2f");
            s += "\n\n";
            s+= "By making this payments monthly for 20 years, the mortgage will be paid in full. but if you terminate the mortgage on its nth anniversary, the balance still owing depends on n as shown below ";
            s += "\n\n";
            s+= "       n" + "          balance";
            s += "\n\n";

            s += String.format("%8d", 0) + mp.outstandingAfter(0, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 1) + mp.outstandingAfter(1, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 2) + mp.outstandingAfter(2, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 3) + mp.outstandingAfter(3, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 4) + mp.outstandingAfter(4, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 5) + mp.outstandingAfter(5, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 10) + mp.outstandingAfter(10, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 15) + mp.outstandingAfter(15, "%16.0f");
            s += "\n\n";
            s += String.format("%8d", 20) + mp.outstandingAfter(20, "%16.0f");

            ((TextView) findViewById(R.id.output)).setText(s);
            tts.speak(s, TextToSpeech.QUEUE_FLUSH,null);



        } catch (Exception e) { Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }





    }



}
