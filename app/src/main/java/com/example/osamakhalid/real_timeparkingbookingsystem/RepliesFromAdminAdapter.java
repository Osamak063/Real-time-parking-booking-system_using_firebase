package com.example.osamakhalid.real_timeparkingbookingsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.Booking;
import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.FeedbackAndReply;
import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.Reply;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 8/4/2017.
 */

public class RepliesFromAdminAdapter extends ArrayAdapter<Reply> {
    private TextView reply;
    private TextView yourfeedback;
    private String type;
    private FirebaseDatabase database;
    private DatabaseReference feedbackreference;
    List<Reply> replies= new ArrayList<Reply>();
    public RepliesFromAdminAdapter(@NonNull Context context, List<Reply> replies,String type) {
        super(context, R.layout.reply,replies);;
        this.replies=replies;
        this.type=type;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.reply, parent, false);
        }
        final Reply reply1= getItem(position);
        reply=(TextView) customView.findViewById(R.id.reply);
        yourfeedback=(TextView) customView.findViewById(R.id.yourfeedback);
        reply.setText(reply1.getReply());
        if(type.equals("user")){
            yourfeedback.setVisibility(View.VISIBLE);
            yourfeedback.setText("Your feedback: "+reply1.getFeedback());
        }
        else if(type.equals("admin")){
            yourfeedback.setVisibility(View.INVISIBLE);
        }

        return customView;
    }
}
