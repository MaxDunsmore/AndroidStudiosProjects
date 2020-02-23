package com.example.daggercarexample;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";

    private Engine engine;
    private Wheels wheels;

    @Inject // shows dagger what to inject
    public Car(Wheels wheels, Engine engine) {
        this.wheels = wheels;
        this.engine = engine;
    }

    public void drive() {
        //engine.start();
        Log.d(TAG, "driving ....");
    }

}
