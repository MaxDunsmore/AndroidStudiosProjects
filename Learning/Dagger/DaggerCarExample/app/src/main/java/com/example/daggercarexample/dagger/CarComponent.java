package com.example.daggercarexample.dagger;

import com.example.daggercarexample.MainActivity;

import dagger.Component;

@Component (modules = {WheelsModule.class, DieselEngineModule.class})
public interface CarComponent {
    //Car getCar();
    void inject(MainActivity mainActivity);
}
