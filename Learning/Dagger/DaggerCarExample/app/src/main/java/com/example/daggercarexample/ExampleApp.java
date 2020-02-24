package com.example.daggercarexample;

import android.app.Application;

import com.example.daggercarexample.dagger.ActivityComponent;
import com.example.daggercarexample.dagger.AppComponent;
//import com.example.daggercarexample.dagger.DaggerActivityComponent;
import com.example.daggercarexample.dagger.DaggerAppComponent;
import com.example.daggercarexample.dagger.DriverModule;

public class ExampleApp extends Application {
    private AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.factory().create(new DriverModule("Hans"));
    }
    public AppComponent getAppComponent(){
        return  component;
    }
}
