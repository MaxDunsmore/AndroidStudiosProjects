package com.example.ecommerce;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.ecommerce.BR;

public class User extends BaseObservable {
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
        notifyPropertyChanged(BR.password);
    }
    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }
    public User() {

    }

    public User(String name, String password, String phoneNumber) {
        this.name = name;
        Password = password;
        this.phoneNumber = phoneNumber;
    }

    private String name, Password;
    private String phoneNumber;
}
