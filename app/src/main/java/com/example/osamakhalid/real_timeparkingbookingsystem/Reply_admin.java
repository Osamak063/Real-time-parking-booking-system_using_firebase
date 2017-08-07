package com.example.osamakhalid.real_timeparkingbookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.Reply;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Reply_admin extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String feedback;
    private String feedbackkey;
    private ChildEventListener childEventListener;
    private Button sendreply;
    private EditText editTextreply;
    private ListView mylistview;
    private RepliesFromAdminAdapter myadapter;
    private List<Reply> replies= new ArrayList<Reply>();
    private String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_admin);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Reply");
        sendreply=(Button) findViewById(R.id.replybutton_admin);
        editTextreply=(EditText) findViewById(R.id.replytext_admin);
        mylistview=(ListView) findViewById(R.id.replies_admin_listview);
        myadapter= new RepliesFromAdminAdapter(Reply_admin.this,replies,"admin");
        mylistview.setAdapter(myadapter);
        Intent i=getIntent();
        feedback=i.getStringExtra("feedback");
        userid=i.getStringExtra("userid");
        feedbackkey=i.getStringExtra("feedbackkey");
        sendreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reply sendreply= new Reply(editTextreply.getText().toString(),feedback,userid,feedbackkey);
                reference.push().setValue(sendreply);
                editTextreply.setText("");
            }
        });
        childEventListener= new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reply reply=dataSnapshot.getValue(Reply.class);
                if(reply.getFeedbackkey().equals(feedbackkey)){
                    replies.add(reply);
                    myadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addChildEventListener(childEventListener);
    }

}
