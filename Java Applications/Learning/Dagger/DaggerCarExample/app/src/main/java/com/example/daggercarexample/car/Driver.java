package com.example.daggercarexample.car;

import javax.inject.Inject;
import javax.inject.Singleton;


public class Driver {
    // we don't own this class so we can't annotate it with @Inject
    String name;
    public Driver(String name) {
        this.name = name;
    }



}
