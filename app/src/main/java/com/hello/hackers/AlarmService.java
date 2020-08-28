package com.hello.hackers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.media.app.NotificationCompat;

public class AlarmService extends Service {
    NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
//        startForeground(0,new Notification());

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);



        int a=intent.getExtras().getInt("AlarmActivityid");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            String id="id_notifiaction";

            CharSequence name="Scanchai chhau";
            int priority=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mchannel=new NotificationChannel(id,name,priority);
              mchannel.setSound(null,null);
              mchannel.enableLights(true);
            manager.createNotificationChannel(mchannel);

            Intent intent1=new Intent(getApplicationContext(),AlarmActivity.class);
            intent1.putExtra("AlarmActivityid",a);

            TaskStackBuilder stackBuilder=TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addNextIntentWithParentStack(intent1);
            PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


            Notification.Builder builder=new Notification.Builder(getApplicationContext()).setContentTitle("Alarm").
                    setSmallIcon(R.drawable.notification).setContentText("Alarm is trigerred").setChannelId(id).setContentIntent(pendingIntent).setAutoCancel(true).setOngoing(false);

            startForeground(1,builder.build());

            

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
