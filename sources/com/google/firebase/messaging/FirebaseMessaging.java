package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzf;
import java.util.regex.Pattern;

public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzclI = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzclJ;
    private PendingIntent zzclK;
    private final FirebaseInstanceId zzclg;

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zzclg = firebaseInstanceId;
    }

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            if (zzclJ == null) {
                zzclJ = new FirebaseMessaging(FirebaseInstanceId.getInstance());
            }
            firebaseMessaging = zzclJ;
        }
        return firebaseMessaging;
    }

    private synchronized void zzh(Context context, Intent intent) {
        if (this.zzclK == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzclK = PendingIntent.getBroadcast(context, 0, intent2, 0);
        }
        intent.putExtra("app", this.zzclK);
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        String zzbA = zzf.zzbA(applicationContext);
        if (zzbA == null) {
            Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        zzh(applicationContext, intent);
        intent.setPackage(zzbA);
        remoteMessage.zzK(intent);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public void subscribeToTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            str = str.substring("/topics/".length());
        }
        if (str == null || !zzclI.matcher(str).matches()) {
            String valueOf = String.valueOf("[a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 55 + String.valueOf(valueOf).length()).append("Invalid topic name: ").append(str).append(" does not match the allowed format ").append(valueOf).toString());
        }
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        String valueOf2 = String.valueOf("S!");
        String valueOf3 = String.valueOf(str);
        instance.zzjt(valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2));
    }

    public void unsubscribeFromTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            str = str.substring("/topics/".length());
        }
        if (str == null || !zzclI.matcher(str).matches()) {
            String valueOf = String.valueOf("[a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 55 + String.valueOf(valueOf).length()).append("Invalid topic name: ").append(str).append(" does not match the allowed format ").append(valueOf).toString());
        }
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        String valueOf2 = String.valueOf("U!");
        String valueOf3 = String.valueOf(str);
        instance.zzjt(valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2));
    }
}
