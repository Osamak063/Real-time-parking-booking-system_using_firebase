package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.FeedbackAndReply;
import com.example.osamakhalid.real_timeparkingbookingsystem.R;
import com.example.osamakhalid.real_timeparkingbookingsystem.ViewFeedbacksAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ViewFeedbacks extends Fragment {
    private ListView listView;
    private ViewFeedbacksAdapter myadapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private List<FeedbackAndReply> feedbacks=new ArrayList<FeedbackAndReply>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_feedbacks, container, false);
        database=FirebaseDatabase.getInstance();
        reference= database.getReference().child("Feedback");
        listView=(ListView) view.findViewById(R.id.viewfeedbacksadmin_listview);
        myadapter= new ViewFeedbacksAdapter(getActivity(),feedbacks);
        listView.setAdapter(myadapter);


        childEventListener= new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FeedbackAndReply feedback= dataSnapshot.getValue(FeedbackAndReply.class);
                feedbacks.add(feedback);
                myadapter.notifyDataSetChanged();
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
        return view;
    }


}
