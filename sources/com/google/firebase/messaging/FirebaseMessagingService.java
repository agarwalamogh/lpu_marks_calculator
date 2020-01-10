package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzg;
import java.util.Iterator;

public class FirebaseMessagingService extends zzb {
    static void zzD(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    private void zzI(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzX(intent.getExtras())) {
            zzd.zzj(this, intent);
        }
    }

    private void zzJ(Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        char c = 65535;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals("deleted_messages")) {
                    c = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    c = 0;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals("send_error")) {
                    c = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals("send_event")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (zzX(intent.getExtras())) {
                    zzd.zzi(this, intent);
                }
                zzl(intent);
                return;
            case 1:
                onDeletedMessages();
                return;
            case 2:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            case 3:
                onSendError(zzm(intent), new SendException(intent.getStringExtra("error")));
                return;
            default:
                String str = "FirebaseMessaging";
                String str2 = "Received message with unknown type: ";
                String valueOf = String.valueOf(stringExtra);
                Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return;
        }
    }

    static boolean zzX(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString("google.c.a.e"));
    }

    private void zzl(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.remove("android.support.content.wakelockid");
        if (zza.zzE(extras)) {
            if (!zza.zzcx(this).zzG(extras)) {
                if (zzX(extras)) {
                    zzd.zzl(this, intent);
                }
            } else {
                return;
            }
        }
        onMessageReceived(new RemoteMessage(extras));
    }

    private String zzm(Intent intent) {
        String stringExtra = intent.getStringExtra("google.message_id");
        return stringExtra == null ? intent.getStringExtra("message_id") : stringExtra;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(android.content.Intent r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getAction()
            if (r0 != 0) goto L_0x0008
            java.lang.String r0 = ""
        L_0x0008:
            r1 = -1
            int r2 = r0.hashCode()
            switch(r2) {
                case 75300319: goto L_0x0038;
                case 366519424: goto L_0x002e;
                default: goto L_0x0010;
            }
        L_0x0010:
            r0 = r1
        L_0x0011:
            switch(r0) {
                case 0: goto L_0x0042;
                case 1: goto L_0x0046;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.String r1 = "FirebaseMessaging"
            java.lang.String r2 = "Unknown intent action: "
            java.lang.String r0 = r5.getAction()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r3 = r0.length()
            if (r3 == 0) goto L_0x0054
            java.lang.String r0 = r2.concat(r0)
        L_0x002a:
            android.util.Log.d(r1, r0)
        L_0x002d:
            return
        L_0x002e:
            java.lang.String r2 = "com.google.android.c2dm.intent.RECEIVE"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0010
            r0 = 0
            goto L_0x0011
        L_0x0038:
            java.lang.String r2 = "com.google.firebase.messaging.NOTIFICATION_DISMISS"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0042:
            r4.zzJ(r5)
            goto L_0x002d
        L_0x0046:
            android.os.Bundle r0 = r5.getExtras()
            boolean r0 = zzX(r0)
            if (r0 == 0) goto L_0x002d
            com.google.firebase.messaging.zzd.zzk(r4, r5)
            goto L_0x002d
        L_0x0054:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.handleIntent(android.content.Intent):void");
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return zzg.zzabU().zzabW();
    }

    public boolean zzE(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        zzI(intent);
        return true;
    }
}
