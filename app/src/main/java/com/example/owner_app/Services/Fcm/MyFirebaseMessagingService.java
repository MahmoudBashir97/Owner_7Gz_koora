package com.example.owner_app.Services.Fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.owner_app.R;
import com.example.owner_app.ui.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static androidx.core.app.NotificationCompat.PRIORITY_MAX;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String LOG_TAG = "MyFirebaseMessaging";
    public static final String MESSAGE = "message";

    public ImageView imageView;
    private DatabaseReference getbadgeCount;
    int count=0;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(LOG_TAG, remoteMessage.getData().toString() + "");
        String playerName = remoteMessage.getData().get("playerName");
        String playerNumber = remoteMessage.getData().get("playerNumber");
        String date=remoteMessage.getData().get("date");
        String hour = remoteMessage.getData().get("hour");



        Log.d(LOG_TAG, "onMessageReceived: ");

            handleInviteIntent(playerName, playerNumber,date,hour);


    }

    private void handleInviteIntent(String playerName, String playerNumber,String date, String hour) {


        Intent reciveMessage = new Intent(getApplicationContext(), MyReceiver.class)
                .setAction("date")
                .putExtra("playerName", playerName)
                .putExtra("playerNumber", playerNumber)
                .putExtra("date",date)
                .putExtra("message", hour);
        PendingIntent pendingIntentAccept = PendingIntent.getBroadcast(this, 2, reciveMessage, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(reciveMessage);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        2,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.app.Notification build = new NotificationCompat.Builder(this, MESSAGE)
                .setContentTitle("هناك حجز جديد!")
                .setContentText("[" + "لقد حجز " + " "+playerName +" "+"الساعه "+ hour + " "+"بتاريخ"+ " "+date + "]")
                .setSmallIcon(R.drawable.resv)
                .setPriority(PRIORITY_MAX)
                .addAction(R.drawable.goal, "عرض الحجز", pendingIntentAccept)
                .setVibrate(new long[3000])
                .setChannelId(MESSAGE)
                .setContentIntent(resultPendingIntent)
                .setLights(Color.YELLOW, 500, 500)
                .setSound(alarmSound)
                .build();




        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(MESSAGE, MESSAGE, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(1, build);

    }
}
