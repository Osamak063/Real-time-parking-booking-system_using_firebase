package com.example.osamakhalid.real_timeparkingbookingsystem.Classes;

/**
 * Created by Osama Khalid on 7/24/2017.
 */

public class User {
    String name;
    String phonenum;
    String key;
    public User(){}
    public User(String name,String phonenum,String key){
        this.name=name;
        this.phonenum=phonenum;
        this.key=key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPhonenum() {
        return phonenum;
    }

}
