package com.example.owner_app.Services.Fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.example.owner_app.ui.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "MyReceiver";
    private int count=0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive: " + intent.getAction());

                        String playerName = intent.getExtras().getString("playerName");
                        String playerNumber = intent.getExtras().getString("playerNumber");
                        String date = intent.getExtras().getString("date");
                        String hour = intent.getExtras().getString("hour");


                         if (intent.getAction().equals("date")) {
                             context.startActivity(new Intent(context, MainActivity.class)
                                     .putExtra("playerName", playerName)
                                     .putExtra("senderName", playerNumber)
                                     .putExtra("date", date)
                                     .putExtra("hour", hour)
                                     .addFlags(FLAG_ACTIVITY_NEW_TASK));
  }
    }
}
