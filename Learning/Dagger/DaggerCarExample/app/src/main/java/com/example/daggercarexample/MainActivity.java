package com.example.daggercarexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.daggercarexample.car.Car;
import com.example.daggercarexample.dagger.CarComponent;
import com.example.daggercarexample.dagger.DaggerCarComponent;
import com.example.daggercarexample.dagger.DieselEngineModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarComponent component = DaggerCarComponent.builder()
                .dieselEngineModule(new DieselEngineModule(100)).build();
        component.inject(this);
        car.drive();
    }
}
