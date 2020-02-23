package com.example.daggercarexample.dagger;

import com.example.daggercarexample.car.DieselEngine;
import com.example.daggercarexample.car.Engine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DieselEngineModule {
    @Binds
    abstract Engine bindEngine(DieselEngine engine);
}
