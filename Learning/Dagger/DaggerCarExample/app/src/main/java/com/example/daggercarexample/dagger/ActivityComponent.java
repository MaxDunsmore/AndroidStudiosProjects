package com.example.daggercarexample.dagger;

import android.app.AlertDialog.Builder;

import androidx.core.app.ActivityCompat;

import com.example.daggercarexample.MainActivity;
import com.example.daggercarexample.car.Car;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {WheelsModule.class, PetrolEngineModule.class})
public interface ActivityComponent {
    Car getCar();
    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower(@Named("horsePower") int horsePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);

        ActivityComponent build();
    }
}
