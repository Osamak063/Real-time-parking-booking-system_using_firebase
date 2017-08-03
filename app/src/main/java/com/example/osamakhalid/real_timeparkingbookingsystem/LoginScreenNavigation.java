package com.example.osamakhalid.real_timeparkingbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import layout.AllBookings;
import layout.Gulshan;
import layout.MyBookings;
import layout.UsersListAdmin;

public class LoginScreenNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_navigation);
        Intent i = getIntent();
        String type=i.getStringExtra("type");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth=FirebaseAuth.getInstance();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(type.equals("admin")){
            getSupportActionBar().setTitle("All Bookings");
            fragment = new AllBookings();
            FragmentManager fm99 = getSupportFragmentManager();
            FragmentTransaction ft99 = fm99.beginTransaction();
            ft99.replace(R.id.fragment_g, fragment);
            ft99.commit();
            final Menu menu = navigationView.getMenu();
            if(menu!=null){
                MenuItem menuItem= (MenuItem) menu.findItem(R.id.gulshan);
                MenuItem menuItem1= (MenuItem) menu.findItem(R.id.northkhi);
                MenuItem menuItem2= (MenuItem) menu.findItem(R.id.johar);
                MenuItem menuItem4=(MenuItem) menu.findItem(R.id.my_bookings);
                MenuItem menuItem3=(MenuItem) menu.findItem(R.id.allbookings);
                MenuItem menuItem5= (MenuItem) menu.findItem(R.id.users_list);
                menuItem3.setVisible(true);
                menuItem.setVisible(false);
                menuItem1.setVisible(false);
                menuItem2.setVisible(false);
                menuItem4.setVisible(false);
                menuItem5.setVisible(true);
            }
        }
        else if(type.equals("user")){
            getSupportActionBar().setTitle("Gulshan");
            final Menu menu = navigationView.getMenu();
            if(menu!=null){
                MenuItem menuItem= (MenuItem) menu.findItem(R.id.gulshan);
                MenuItem menuItem1= (MenuItem) menu.findItem(R.id.northkhi);
                MenuItem menuItem2= (MenuItem) menu.findItem(R.id.johar);
                MenuItem menuItem3=(MenuItem) menu.findItem(R.id.allbookings);
                MenuItem menuItem4=(MenuItem) menu.findItem(R.id.my_bookings);
                MenuItem menuItem5= (MenuItem) menu.findItem(R.id.users_list);
                menuItem3.setVisible(false);
                menuItem.setVisible(true);
                menuItem1.setVisible(true);
                menuItem2.setVisible(true);
                menuItem4.setVisible(true);
                menuItem5.setVisible(false);

            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id==R.id.logout){
            firebaseAuth.signOut();
            Intent i= new Intent(LoginScreenNavigation.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       int id = item.getItemId();
//
        if (id == R.id.gulshan) {
            fragment = new Gulshan();
            Bundle bundle= new Bundle();
            bundle.putString("location","Gulshan");
            fragment.setArguments(bundle);
            FragmentManager fm1 = getSupportFragmentManager();
            FragmentTransaction ft1 = fm1.beginTransaction();
            ft1.replace(R.id.fragment_g, fragment);
            ft1.commit();
            getSupportActionBar().setTitle("Gulshan");
        } else if (id == R.id.johar) {
            fragment = new Gulshan();
            Bundle bundle= new Bundle();
            bundle.putString("location","Johar");
            fragment.setArguments(bundle);
            FragmentManager fm2 = getSupportFragmentManager();
            FragmentTransaction ft2 = fm2.beginTransaction();
            ft2.replace(R.id.fragment_g, fragment);
            ft2.commit();
            getSupportActionBar().setTitle("Gulistan-e-Johar");

        } else if (id == R.id.northkhi) {
            fragment = new Gulshan();
            Bundle bundle= new Bundle();
            bundle.putString("location","North");
            fragment.setArguments(bundle);
            FragmentManager fm3 = getSupportFragmentManager();
            FragmentTransaction ft3 = fm3.beginTransaction();
            ft3.replace(R.id.fragment_g, fragment);
            ft3.commit();
            getSupportActionBar().setTitle("North Karachi");
        }
        else if(id==R.id.my_bookings){
            fragment = new MyBookings();
            FragmentManager fm4 = getSupportFragmentManager();
            FragmentTransaction ft4 = fm4.beginTransaction();
            ft4.replace(R.id.fragment_g, fragment);
            ft4.commit();
            getSupportActionBar().setTitle("My Bookings");
        }
        else if(id==R.id.allbookings){
            getSupportActionBar().setTitle("All Bookings");
            fragment = new AllBookings();
            FragmentManager fm5 = getSupportFragmentManager();
            FragmentTransaction ft5 = fm5.beginTransaction();
            ft5.replace(R.id.fragment_g, fragment);
            ft5.commit();
        }
        else if(id==R.id.users_list){
            getSupportActionBar().setTitle("Users");
            fragment = new UsersListAdmin();
            FragmentManager fm6 = getSupportFragmentManager();
            FragmentTransaction ft6 = fm6.beginTransaction();
            ft6.replace(R.id.fragment_g, fragment);
            ft6.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
