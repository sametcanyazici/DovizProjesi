package com.example.dovizprojesi;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;



public class MyService extends Service {
    Convert convert=new Convert();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent intent1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);
        Notification notification=new NotificationCompat.Builder(this,"ChannelId1")
                .setContentTitle("Döviz Uygulaması")
                .setContentText("Doviz Projesi Arkaplanda çalışıyor")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();
            startForeground(1,notification);
        return START_STICKY;
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(
                    "ChannelId1","Arkaplan Servisi", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);


        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onDestroy() {
        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }
}

