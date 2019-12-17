package com.example.owner_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner_app.Adapters.adpt_ownerREcycler;
import com.example.owner_app.General.Firebase_keys;
import com.example.owner_app.General.Storage.SharedPrefranceManager;
import com.example.owner_app.Notification.Exampleservice;
import com.example.owner_app.R;
import com.example.owner_app.listitems.owner_listitem;
import com.example.owner_app.pojo.Save_Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView no_books,txt_ml3b_name;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mauth;
    String phone_no;
    String user_name;
    String Ml3b_name;
    private List<owner_listitem> listitems;
    owner_listitem list;
    String hagz_date,hour_booking;
    String Owner_city;
    FloatingActionButton floatingActionButton;
    SharedPreferences sharedpreferences;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    public static final String MyPREFERENCES = "MyPrefs" ;

    Intent intent_serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.Rec_owner_Act);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        no_books=(TextView)findViewById(R.id.no_books);
        txt_ml3b_name=(TextView)findViewById(R.id.ml3_name_txt);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        Ml3b_name=SharedPrefranceManager.getInastance(this).getMl3b_name();
        Owner_city=SharedPrefranceManager.getInastance(this).getOwner_city();
        String Owner_phone=SharedPrefranceManager.getInastance(this).getUserPhone();
        txt_ml3b_name.setText(SharedPrefranceManager.getInastance(this).getMl3b_name());

        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance();

        listitems = new ArrayList<>();

        reference=database.getReference().child(Firebase_keys.Booking_uniform.toString()).child(Owner_phone).child(Ml3b_name);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    String area_name=(String) snapshot.child("ml3b_name").getValue();
                    String area_city=(String) snapshot.child("area_city").getValue();


                    if ( Owner_city.equals(area_city)){
                        phone_no=(String) snapshot.child("user_phone").getValue();
                        hour_booking=(String) snapshot.child("l_hour").getValue();
                        hagz_date=(String) snapshot.child("date").getValue();
                        user_name=(String) snapshot.child("user_name").getValue();



                        SharedPreferences.Editor editor = getSharedPreferences("com.example.srushtee.dummy", MODE_PRIVATE).edit();
                        editor.putBoolean("active",true);
                        editor.commit();
                        editor.apply();

                        //startService(user_name,hagz_date,hour_booking,Ml3b_name);



                        //Save_Data save_data=new Save_Data(user_name,hagz_date,hour_booking,Ml3b_name);

/*
                        //MessageRecieved recieved=new MessageRecieved(new Message());
                        intent_serv=new Intent(MainActivity.this, timer_service.class);
                        intent_serv.putExtra("timer",1000);
                       // intent_serv.putExtra("reciever",recieved);

                        intent_serv.putExtra("inputExtra","checked");
                        intent_serv.putExtra("username",user_name);
                        intent_serv.putExtra("hagz_date",hagz_date);
                        intent_serv.putExtra("booking_hour",hour_booking);
                        intent_serv.putExtra("Ml3b_name",Ml3b_name);*/


                        no_books.setVisibility(View.GONE);
                        list = new owner_listitem(user_name,phone_no,hour_booking,hagz_date);
                        listitems.add(list);
                    }else {no_books.setVisibility(View.VISIBLE);}

                }
                       //startService(intent_serv);
                       adapter = new adpt_ownerREcycler( MainActivity.this,listitems);
                       recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        floatingActionButton.setOnClickListener(v ->{
            Intent i=new Intent(MainActivity.this,Add_information.class);
            startActivity(i);
        });
    }
    public void startService(String us_name,String hgz_date,String hour_bk,String ml3b_nme) {
        Intent serviceIntent = new Intent(this, Exampleservice.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        serviceIntent.putExtra("inputExtra","checked");
        serviceIntent.putExtra("username",us_name);
        serviceIntent.putExtra("hagz_date",hgz_date);
        serviceIntent.putExtra("booking_hour",hour_bk);
        serviceIntent.putExtra("Ml3b_name",Ml3b_name);


        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, Exampleservice.class);
        stopService(serviceIntent);
    }

    public class Message{
        public void displayMessage(int resultCode, Bundle resultData){
            String message=resultData.getString("message");
            Toast.makeText(MainActivity.this, resultCode+" "+message, Toast.LENGTH_SHORT).show();
        }
    }
}
