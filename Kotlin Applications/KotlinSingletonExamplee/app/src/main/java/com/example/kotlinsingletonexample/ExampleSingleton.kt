package com.example.kotlinsingletonexample

import com.example.kotlinsingletonexample.models.User

object ExampleSingleton{
    val singletonUser: User by lazy {
        User("maxdunsmore97@gmail.com","max","image.png")
    }
}