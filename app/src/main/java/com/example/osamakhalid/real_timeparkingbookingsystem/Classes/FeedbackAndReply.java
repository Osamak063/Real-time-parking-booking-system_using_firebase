package com.example.osamakhalid.real_timeparkingbookingsystem.Classes;

/**
 * Created by Osama Khalid on 8/4/2017.
 */

public class FeedbackAndReply {
    String feedbackkey;
    String userkey;
    String feedback;
    public FeedbackAndReply(){}
    public FeedbackAndReply(String feedbackkey,String feedback,String userkey){
        this.feedbackkey=feedbackkey;
        this.userkey=userkey;
        this.feedback=feedback;
    }

    public String getFeedbackkey() {
        return feedbackkey;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getUserkey() {
        return userkey;
    }
}
