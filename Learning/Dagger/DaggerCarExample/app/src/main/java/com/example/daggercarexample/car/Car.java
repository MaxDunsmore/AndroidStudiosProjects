package com.example.daggercarexample.car;

import android.util.Log;

import com.example.daggercarexample.dagger.PerActivity;

import javax.inject.Inject;

@PerActivity
public class Car {
    private static final String TAG = "Car";

    private Engine engine; // field Injection
    private Wheels wheels;
    private Driver driver;

    @Inject // shows dagger what to inject
    public Car(Driver driver,Wheels wheels, Engine engine) {
        this.driver = driver;
        this.wheels = wheels;
        this.engine = engine;
    }
    @Inject
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }

    public void drive() {
        //engine.start();
        engine.start();
        Log.d(TAG, driver +" "+ driver.name + " drives " + this);
    }

}
