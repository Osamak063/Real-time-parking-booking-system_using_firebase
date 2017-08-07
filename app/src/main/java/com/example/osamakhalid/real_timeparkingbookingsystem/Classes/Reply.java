package com.example.osamakhalid.real_timeparkingbookingsystem.Classes;

import com.example.osamakhalid.real_timeparkingbookingsystem.RepliesFromAdminAdapter;

/**
 * Created by Osama Khalid on 8/4/2017.
 */

public class Reply {
    String reply;
    String feedback;
    String userid;
    String feedbackkey;
    public Reply(){}
    public Reply(String reply , String feedback,String userid,String feedbackkey){
        this.reply=reply;
        this.feedback=feedback;
        this.userid=userid;
        this.feedbackkey=feedbackkey;
    }

    public String getFeedbackkey() {
        return feedbackkey;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getReply() {
        return reply;
    }

    public String getUserid() {
        return userid;
    }
}
