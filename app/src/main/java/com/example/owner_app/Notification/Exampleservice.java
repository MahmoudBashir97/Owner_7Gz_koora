package com.example.owner_app.Notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.owner_app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


@SuppressLint("Registered")
public class Exampleservice extends Service {

    private NotificationManagerCompat notificationManagerCompat;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    private CountDownTimer timer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String input = intent.getStringExtra("inputExtra");
        String username = intent.getStringExtra("username");
        String hagz_date = intent.getStringExtra("hagz_date");
        String hour_booking = intent.getStringExtra("booking_hour");
        String Ml3b_name = intent.getStringExtra("Ml3b_name");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, Notifications.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification1 = new NotificationCompat.Builder(Exampleservice.this,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.resv)
                .setContentTitle(Ml3b_name)
                .setContentText("[" + "لقد حجز " + username +" "+"الساعه"+ hour_booking + " "+"بتاريخ"+ " "+hagz_date + "]")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setLights(Color.YELLOW, 500, 500)
                .setSound(alarmSound)
                .build();
       // notificationManagerCompat.notify(2, notification1);
        startForeground(1, notification1);


/*

                            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                            Notification notification=new NotificationCompat.Builder(Exampleservice.this,App.CHANNEL_2_ID)
                                    .setSmallIcon(R.drawable.yellowcard)
                                    .setContentTitle("Yellow Card")
                                    .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                    .setContentIntent(pendingIntent)
                                    .setPriority(NotificationCompat.PRIORITY_LOW)
                                    .setLights(Color.BLUE, 500, 500)
                                    .setSound(alarmSound)
                                    .build();
                            notificationManagerCompat.notify(2,notification);
                            startForeground(1,notification);
                        }}
                   */
        //stopSelf();
        Timer();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    public void Timer(){
        timer=new CountDownTimer(10000,50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                try{
                    stopSelf();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();
    }
}
