package com.example.zhaotong.db410c_system.UltrasonicSensor;

/**
 * Created by ztong on 10/15/2016.
 */

public interface UltraSonicInterface {
    boolean getRunningState();
    void setRunningState(boolean state);

    void execute(double process);
}

