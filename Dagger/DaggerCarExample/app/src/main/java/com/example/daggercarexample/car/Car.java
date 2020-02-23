package com.example.daggercarexample.car;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";

    private Engine engine; // field Injection
    private Wheels wheels;

    @Inject // shows dagger what to inject
    public Car(Wheels wheels, Engine engine) {
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
        Log.d(TAG, "driving ....");
    }

}
