package com.example.mvvmrecyclerviewjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmrecyclerviewjava.models.NicePlace;
import com.example.mvvmrecyclerviewjava.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;

    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();

    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }
}
