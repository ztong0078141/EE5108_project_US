package com.example.zhaotong.db410c_system;

/**
 * Created by ztong on 10/15/2016.
 */
import android.app.Activity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.zhaotong.db410c_system.UltrasonicSensor.UltraSonicInterface;

/**
 * Created by Ara on 8/10/15.
 */
public class DistanceCalculator implements UltraSonicInterface {
    private static final String TAG = "UltrasonicInterface";

    private Activity activity;

    private boolean state;
    private boolean ultrasonicOn;
    private TextView textView;


    public DistanceCalculator(Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_main);

        textView = (TextView) activity.findViewById(R.id.textView);
        ((ToggleButton) activity.findViewById(R.id.ultrasonicButton)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setUltrasonicOn(isChecked);
            }
        });
    }


    @Override
    public boolean getRunningState() {
        return state;
    }

    @Override
    public void setRunningState(boolean state) {
        this.state = state;
    }

    @Override
    public void execute(double distance) {
        if (isUltrasonicOn()) {
            checkDistance(distance);
        } else {
            turnOffDisplay();
        }
    }


    public void setUltrasonicOn(boolean ultrasonicOn) {
        this.ultrasonicOn = ultrasonicOn;
    }

    public boolean isUltrasonicOn() {
        return ultrasonicOn;
    }


    private void checkDistance(double distance) {
        if (distance < 0) {
            turnOffDisplay();
            return;
        }
        if (distance > 0 ) {
            updateDistance(distance);
           }
    }
    private void updateDistance(final double distance) {
        //Log.i(TAG, "Updating TextView");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("You are " + String.format("%.2f", distance) + "cm away from the nearest object");
                //textView.setTextColor(color);
                Log.i(TAG, String.format("%.2f", distance));
            }
        });


    }

    private void turnOffTextView(){

        Log.i(TAG, "Updating TextView - Off");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("The UltraSonic Sensor has been turned off");
            }
        });


    }

    public void turnOffDisplay(){
        turnOffTextView();
    }

    public void shutDown() {
        turnOffDisplay();
        setRunningState(false);

    }
}
