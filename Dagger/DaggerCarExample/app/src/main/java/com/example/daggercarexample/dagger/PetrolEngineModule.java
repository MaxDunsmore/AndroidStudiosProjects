package com.example.daggercarexample.dagger;
import com.example.daggercarexample.car.Engine;
import com.example.daggercarexample.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class PetrolEngineModule {
    @Binds
    abstract Engine bindEngine(PetrolEngine engine);
}

