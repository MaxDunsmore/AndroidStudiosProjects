package com.example.kotlinsingletonexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kotlinsingletonexample.models.User
import com.example.kotlinsingletonexample.repository.Repository

class MainViewModel: ViewModel(){

    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations
        .switchMap(_userId){
            Repository.getUser(it)
    }

    fun setUserId(userID: String){
        val update = userID
        if(_userId.value == update){
            return
        }
        _userId.value = update
    }
    fun cancelJobs(){
        Repository.cancelJobs()
    }
}