package com.example.daggercarexample;

import android.app.Application;

import com.example.daggercarexample.dagger.ActivityComponent;
import com.example.daggercarexample.dagger.AppComponent;
//import com.example.daggercarexample.dagger.DaggerActivityComponent;
import com.example.daggercarexample.dagger.DaggerAppComponent;

public class ExampleApp extends Application {
    private AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.create();
    }
    public AppComponent getAppComponent(){
        return  component;
    }
}
