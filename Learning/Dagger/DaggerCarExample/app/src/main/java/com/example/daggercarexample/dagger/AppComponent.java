package com.example.daggercarexample.dagger;

import com.example.daggercarexample.car.Driver;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DriverModule.class)
public interface AppComponent {
    Driver getDriver();
}
