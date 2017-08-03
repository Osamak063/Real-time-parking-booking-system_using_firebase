package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.osamakhalid.real_timeparkingbookingsystem.AllBookingsAdapter;
import com.example.osamakhalid.real_timeparkingbookingsystem.Classes.Booking;
import com.example.osamakhalid.real_timeparkingbookingsystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AllBookings extends Fragment {
    private ListView mylistview;
    private AllBookingsAdapter myadapter;
    private ChildEventListener childEventListener;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private List<Booking> allbookings = new ArrayList<Booking>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }
        View view=inflater.inflate(R.layout.fragment_all_bookings, container, false);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Bookings");
        mylistview=(ListView) view.findViewById(R.id.admin_allbookings);
        myadapter= new AllBookingsAdapter(getActivity(),allbookings,"admin");
        mylistview.setAdapter(myadapter);
        childEventListener= new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Booking addbooking = dataSnapshot.getValue(Booking.class);
                allbookings.add(addbooking);
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Booking deletebooking= dataSnapshot.getValue(Booking.class);
                allbookings.remove(deletebooking);
                for(int i=0;i<allbookings.size();i++){
                    if(allbookings.get(i).getBookingkey().equals(deletebooking.getBookingkey())){
                        allbookings.remove(i);
                    }
                }
                myadapter.notifyDataSetChanged();
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
