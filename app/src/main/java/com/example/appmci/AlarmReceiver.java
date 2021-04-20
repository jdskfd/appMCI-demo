package com.example.appmci;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.example.appmci.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;
    private Notification testNotification;
    private final static int NOTIFICATION_ID=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notifyIntent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,notifyIntent,0);
        broadcastNotify(context, pendingIntent);
    }
    private void broadcastNotify(Context context, PendingIntent pendingIntent) {
        notificationManager=
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        testNotification = new Notification.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.danger)
                .setContentTitle("系統通知")
                .setContentText("通知測試！")
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{0,100,200,300,400,500})
                .build();
        notificationManager.notify(NOTIFICATION_ID, testNotification);
    }
}
