package com.example.tema_3.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.tema_3.Alarm.NotificationHelper;

import static com.example.tema_3.Activities.ToDosActivity.EXTRA_TITLE_TASK;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationHelper mNotificationHelper;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {

        mContext = context;

        mNotificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification("TODO: " + EXTRA_TITLE_TASK);
        mNotificationHelper.getManager().notify(1, nb.build());
    }
}
