package com.akhil.lovely.lpu.infinitum.verto;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class _MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification((String) remoteMessage.getData().get("title"), (String) remoteMessage.getData().get("message"), remoteMessage.getData());
    }

    private void sendNotification(String title, String messageBody, @Nullable Map<String, String> data) {
        Intent intent;
        if (((String) data.get(Param.DESTINATION)).contentEquals("2")) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse((String) data.get("url")));
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        intent.addFlags(67108864);
        ((NotificationManager) getSystemService("notification")).notify(124567854, new Builder(this).setContentTitle(title).setSmallIcon(C0373R.C0374drawable.notif_white).setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), C0373R.mipmap.ic_launcher)).setContentText(messageBody).setStyle(new BigTextStyle().bigText(messageBody)).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824)).build());
    }
}
