package com.example.daggercarexample.dagger;

import com.example.daggercarexample.MainActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Component (modules = {WheelsModule.class, PetrolEngineModule.class})
public interface CarComponent {
    //Car getCar();
    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower(@Named("horsePower") int horsePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);


        CarComponent build();
    }
}
