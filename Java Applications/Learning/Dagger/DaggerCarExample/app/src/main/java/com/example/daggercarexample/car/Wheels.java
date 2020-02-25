package com.example.daggercarexample.car;

import javax.inject.Inject;

public class Wheels {
    // we dont own this class so we can't annotate it with @inject
    private Rims rims;
    private Tires tires;

    @Inject
    public Wheels(Rims rims, Tires tires){
        this.rims = rims;
        this.tires = tires;

    }
}
