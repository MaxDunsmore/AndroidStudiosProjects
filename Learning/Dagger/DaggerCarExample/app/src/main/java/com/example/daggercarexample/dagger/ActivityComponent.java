package com.example.daggercarexample.dagger;

import android.app.AlertDialog.Builder;

import com.example.daggercarexample.MainActivity;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
@PerActivity
@Component (dependencies = AppComponent.class,modules = {WheelsModule.class, PetrolEngineModule.class})
public interface ActivityComponent {
    //Car getCar();
    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder horsePower(@Named("horsePower") int horsePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);

        Builder appComponent(AppComponent component);

        ActivityComponent build();
    }
}
