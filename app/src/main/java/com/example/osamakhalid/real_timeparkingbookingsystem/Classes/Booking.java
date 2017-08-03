package com.example.osamakhalid.real_timeparkingbookingsystem.Classes;

import android.provider.ContactsContract;

/**
 * Created by Osama Khalid on 7/28/2017.
 */

public class Booking {
    String userid;
    String slotnum;
    String location;
    String startdate;
    String enddate;
    String bookingkey;
    public Booking(){}
    public Booking(String userid, String slotnum,String location,String startdate,String enddate,String bookingkey){
        this.userid=userid;
        this.slotnum=slotnum;
        this.location=location;
        this.startdate=startdate;
        this.enddate=enddate;
        this.bookingkey=bookingkey;
    }

    public String getBookingkey() {
        return bookingkey;
    }

    public String getEnddate() {
        return enddate;
    }
    public String getStartdate() {
        return startdate;
    }

    public String getLocation() {
        return location;
    }

    public String getSlotnum() {
        return slotnum;
    }

    public String getUserid() {
        return userid;
    }
}
