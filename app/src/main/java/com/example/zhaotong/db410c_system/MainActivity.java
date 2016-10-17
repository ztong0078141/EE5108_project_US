package com.example.zhaotong.db410c_system;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import net.calit2.mooc.iot_db410c.db410c_gpiolib.GpioProcessor;
import net.calit2.mooc.iot_db410c.db410c_gpiolib.GpioProcessor.Gpio;

import com.example.zhaotong.db410c_system.Bluetooth.BluetoothRemoteFragment;
import com.example.zhaotong.db410c_system.UltrasonicSensor.UltrasonicSensorProcessor;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";

    private GpioProcessor processor;
    private Gpio triggerPin;
    private Gpio echoPin;
    private Gpio red;
    private DistanceCalculator distanceCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init();

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();


            /********************************************************************************
             * Make sure you comment out one of the two following fragments
             * depending on what kind of device you are deploying to
             */
            //BluetoothReceiverFragment fragment = BluetoothReceiverFragment.newInstance();
            BluetoothRemoteFragment fragment = BluetoothRemoteFragment.newInstance();
            /********************************************************************************/
            transaction.replace(R.id.content_fragment, fragment);
            transaction.commit();
        }
    }
    private void init(){
        Log.i(TAG, "Initializing Variables");
        processor = new GpioProcessor();

        echoPin = processor.getPin32();
        triggerPin = processor.getPin33();
        red = processor.getPin34();

        distanceCalculator = new DistanceCalculator(this);

    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        distanceCalculator.setRunningState(true);
        new UltrasonicSensorProcessor(echoPin,triggerPin, distanceCalculator).start();

    }



    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        distanceCalculator.shutDown();

    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }


}