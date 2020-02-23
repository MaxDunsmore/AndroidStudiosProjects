package com.example.daggercarexample;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";

    @Inject Engine engine; // field Injection
    private Wheels wheels;

    @Inject // shows dagger what to inject
    public Car(Wheels wheels, Engine engine) {
        this.wheels = wheels;
    }
    @Inject
    public void enableRemote(Remote remote){
        remote.setListener(this);
    }

    public void drive() {
        //engine.start();
        Log.d(TAG, "driving ....");
    }

}
