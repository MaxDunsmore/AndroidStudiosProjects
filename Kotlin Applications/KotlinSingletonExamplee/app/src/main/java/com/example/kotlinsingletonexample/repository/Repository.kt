package com.example.kotlinsingletonexample.repository

import androidx.lifecycle.LiveData
import com.example.kotlinsingletonexample.api.MyRetrofitBuilder
import com.example.kotlinsingletonexample.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { jobA ->
                    CoroutineScope(IO + jobA).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            jobA.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}