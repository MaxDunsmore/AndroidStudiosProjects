package com.example.daggercarexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.daggercarexample.car.Car;
import com.example.daggercarexample.dagger.ActivityComponent;
import com.example.daggercarexample.dagger.DaggerAppComponent;
import com.example.daggercarexample.dagger.DieselEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Car car1,car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityComponent component =((ExampleApp)getApplication())
                .getAppComponent()
                .getActivityComponentBuilder()
                .horsePower(348)
                .engineCapacity(600).build();
        component.inject(this);
        car1.drive();
        car2.drive();
    }
}
