package com.example.daggercarexample;

import dagger.Component;

@Component
public interface CarComponent {
    Car getCar();
}