package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.FeedbackAndReply;
import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.Reply;
import com.example.osamakhalid.real_timeparkingbookingsystem.R;
import com.example.osamakhalid.real_timeparkingbookingsystem.RepliesFromAdminAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SendFeedback extends Fragment {
    private Button sendButton;
    private EditText feedback;
    private ListView repliesfromAdmin_listview;
    private RepliesFromAdminAdapter myadapter;
    private FirebaseDatabase database;
    private DatabaseReference replyreference;
    private DatabaseReference feedbackreference;
    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private List<Reply> replies= new ArrayList<Reply>();
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           if (container != null) {
               container.removeAllViews();
           }
        View view=inflater.inflate(R.layout.fragment_send_feedback, container, false);
            database= FirebaseDatabase.getInstance();
            firebaseAuth= FirebaseAuth.getInstance();
            feedbackreference= database.getReference().child("Feedback");
            replyreference=  database.getReference().child("Reply");
            sendButton=(Button) view.findViewById(R.id.sendbutton_user);
            feedback=(EditText) view.findViewById(R.id.user_feedback);
            repliesfromAdmin_listview=(ListView) view.findViewById(R.id.replyfromadmin_listview);
            myadapter= new RepliesFromAdminAdapter(getActivity(),replies,"user");
            repliesfromAdmin_listview.setAdapter(myadapter);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String key =feedbackreference.push().getKey();
                    FeedbackAndReply feedback1= new FeedbackAndReply(key,feedback.getText().toString(),firebaseAuth.getCurrentUser().getUid());
                    feedbackreference.child(key).setValue(feedback1);
                    feedback.setText("");
                }
            });
            childEventListener= new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Reply reply=dataSnapshot.getValue(Reply.class);
                    if(reply.getUserid().equals(firebaseAuth.getCurrentUser().getUid())){
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
            replyreference.addChildEventListener(childEventListener);
            return view;
    }

}
