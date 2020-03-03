package com.example.rxjavarange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final Task task = new Task("walk decoda", false, 1);
        //final List<Task> tasks = DataSource.createTasksList();

        Observable<Integer> observable = Observable
                .range(0,3)
                .subscribeOn(Schedulers.io())
                .repeat(3)
                .observeOn(AndroidSchedulers.mainThread());

               /* .map(new Function<Integer, Task>() {
                    @Override
                    public Task apply(Integer integer) throws Exception {
                        Log.d(TAG,"apply: " + Thread.currentThread().getName());
                        return new Task("this is a task with priority: " + String.valueOf(integer),false,2);
                    }
                })
                .takeWhile(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        return task.getPriority() < 9;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());*/
           observable.subscribe(new Observer<Integer>() {
               @Override
               public void onSubscribe(Disposable d) {

               }

               @Override
               public void onNext(Integer integer) {
                    Log.d(TAG,"onNext: " + integer);
               }

               @Override
               public void onError(Throwable e) {

               }

               @Override
               public void onComplete() {

               }
           });
    }
}
