package com.example.osamakhalid.real_timeparkingbookingsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.FeedbackAndReply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Khalid on 8/4/2017.
 */

public class ViewFeedbacksAdapter extends ArrayAdapter<FeedbackAndReply> {
    private TextView feedback_text;
    private Button replyshowbutton;
    private Context context;
    List<FeedbackAndReply> feedback= new ArrayList<FeedbackAndReply>();
    public ViewFeedbacksAdapter(@NonNull Context context, List<FeedbackAndReply> feedback) {
        super(context, R.layout.viewfeedbacks_admin,feedback);;
        this.feedback=feedback;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            customView = LayoutInflater.from(getContext()).inflate(R.layout.viewfeedbacks_admin, parent, false);
        }
        final FeedbackAndReply Feedback= getItem(position);
        feedback_text=(TextView) customView.findViewById(R.id.feedback);
        replyshowbutton=(Button) customView.findViewById(R.id.reply_show_button);
        feedback_text.setText(Feedback.getFeedback());
        replyshowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackAndReply Feedback1= getItem(position);
                Intent i = new Intent(context,Reply_admin.class);
                i.putExtra("feedback",Feedback1.getFeedback());
                i.putExtra("feedbackkey",Feedback1.getFeedbackkey());
                i.putExtra("userid",Feedback1.getUserkey());
                context.startActivity(i);
            }
        });
        return customView;
    }
}
