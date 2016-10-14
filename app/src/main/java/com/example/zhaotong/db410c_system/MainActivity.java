package com.example.zhaotong.db410c_system;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.calit2.mooc.iot_db410c.db410c_gpiolib.GpioProcessor;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                GpioProcessor gpioProcessor = new GpioProcessor();

                GpioProcessor.Gpio led = gpioProcessor.getPin34();
                led.out();
                led.low();

                for(int i = 0; i<10;i++){
                    led.high();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    led.low();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }


}
