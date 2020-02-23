package com.example.daggercarexample.dagger;

import com.example.daggercarexample.car.Rims;
import com.example.daggercarexample.car.Tires;
import com.example.daggercarexample.car.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public class WheelsModule {
    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
        Tires tires = new Tires();
        tires.inflate();
        return new Tires();
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(rims,tires);
    }
}
