package com.mrerror.parachut.FCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.mrerror.parachut.Models.BaseResponce;
import com.mrerror.parachut.NetWork.RetroWeb;
import com.mrerror.parachut.NetWork.ServiceApi;
import com.mrerror.parachut.R;
import com.mrerror.parachut.ui.home.MainActivity;
import com.mrerror.parachut.utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    GlobalPrefrencies globalPrefrencies;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(TAG, "From: 0000");
        Log.e(TAG, "From: " + new Gson().toJson(remoteMessage.getData()));

        globalPrefrencies = new GlobalPrefrencies(this);

        if (globalPrefrencies.getLoginStatus()) {
            if (globalPrefrencies.getAllowNotification()) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id", Integer.parseInt(remoteMessage.getData().get("id")));
                showNotification(remoteMessage, intent);
//
            }
        }
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.e(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        getApiToken(token);
    }
    // [END on_new_token]


    private void showNotification(RemoteMessage message, Intent intent) {

//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("notification", "not");
        myIntent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                myIntent,
                0);
*/
        Bitmap myLogo = ((BitmapDrawable) ResourcesCompat.getDrawable(this.getResources(), R.mipmap.ic_logo, null)).getBitmap();

        Notification myNotification = new NotificationCompat.Builder(this)
                .setContentTitle(message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody())
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, myNotification);

        Intent gcm_rec = new Intent("your_action");
        LocalBroadcastManager.getInstance(this).sendBroadcast(gcm_rec);
        notificationManager.notify(0, myNotification);
    }


    public void getApiToken(String token) {

        RetroWeb.getClient().create(ServiceApi.class).getDeviceToken(token, globalPrefrencies.getLanguage(), "Bearer " + MainActivity.user_token).enqueue(new Callback<BaseResponce>() {
            @Override
            public void onResponse(Call<BaseResponce> call, Response<BaseResponce> response) {

                Log.e("tokenResponse", response.body().getStatus());

            }

            @Override
            public void onFailure(Call<BaseResponce> call, Throwable t) {
                Log.e("tokenResponse", t.getMessage());
            }
        });

    }

}
