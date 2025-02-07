package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import com.google.firebase.iid.zzg;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

class zza {
    static zza zzclG;
    private final Context mContext;
    private Bundle zzaDS;
    private final AtomicInteger zzclH = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private zza(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static boolean zzE(Bundle bundle) {
        return "1".equals(zzf(bundle, "gcm.n.e")) || zzf(bundle, "gcm.n.icon") != null;
    }

    private int zzGP() {
        return this.zzclH.incrementAndGet();
    }

    private Notification zzH(Bundle bundle) {
        String zzg = zzg(bundle, "gcm.n.title");
        String zzg2 = zzg(bundle, "gcm.n.body");
        int zzeB = zzeB(zzf(bundle, "gcm.n.icon"));
        Integer zzjD = zzjD(zzf(bundle, "gcm.n.color"));
        Uri zzeC = zzeC(zzU(bundle));
        PendingIntent zzI = zzI(bundle);
        PendingIntent pendingIntent = null;
        if (FirebaseMessagingService.zzX(bundle)) {
            zzI = zza(bundle, zzI);
            pendingIntent = zzW(bundle);
        }
        Builder smallIcon = new Builder(this.mContext).setAutoCancel(true).setSmallIcon(zzeB);
        if (!TextUtils.isEmpty(zzg)) {
            smallIcon.setContentTitle(zzg);
        } else {
            smallIcon.setContentTitle(this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()));
        }
        if (!TextUtils.isEmpty(zzg2)) {
            smallIcon.setContentText(zzg2);
            smallIcon.setStyle(new BigTextStyle().bigText(zzg2));
        }
        if (zzjD != null) {
            smallIcon.setColor(zzjD.intValue());
        }
        if (zzeC != null) {
            smallIcon.setSound(zzeC);
        }
        if (zzI != null) {
            smallIcon.setContentIntent(zzI);
        }
        if (pendingIntent != null) {
            smallIcon.setDeleteIntent(pendingIntent);
        }
        return smallIcon.build();
    }

    private PendingIntent zzI(Bundle bundle) {
        Intent zzV = zzV(bundle);
        if (zzV == null) {
            return null;
        }
        zzV.addFlags(67108864);
        Bundle bundle2 = new Bundle(bundle);
        FirebaseMessagingService.zzD(bundle2);
        zzV.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                zzV.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.mContext, zzGP(), zzV, 1073741824);
    }

    @Nullable
    static Uri zzT(@NonNull Bundle bundle) {
        String zzf = zzf(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zzf)) {
            zzf = zzf(bundle, "gcm.n.link");
        }
        if (!TextUtils.isEmpty(zzf)) {
            return Uri.parse(zzf);
        }
        return null;
    }

    static String zzU(Bundle bundle) {
        String zzf = zzf(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zzf) ? zzf(bundle, "gcm.n.sound") : zzf;
    }

    private Intent zzV(Bundle bundle) {
        String zzf = zzf(bundle, "gcm.n.click_action");
        if (!TextUtils.isEmpty(zzf)) {
            Intent intent = new Intent(zzf);
            intent.setPackage(this.mContext.getPackageName());
            intent.setFlags(268435456);
            return intent;
        }
        Uri zzT = zzT(bundle);
        if (zzT != null) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setPackage(this.mContext.getPackageName());
            intent2.setData(zzT);
            return intent2;
        }
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
        if (launchIntentForPackage != null) {
            return launchIntentForPackage;
        }
        Log.w("FirebaseMessaging", "No activity found to launch app");
        return launchIntentForPackage;
    }

    private PendingIntent zzW(Bundle bundle) {
        Intent intent = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
        zza(intent, bundle);
        return zzg.zzb(this.mContext, zzGP(), intent, 1073741824);
    }

    private PendingIntent zza(Bundle bundle, PendingIntent pendingIntent) {
        Intent intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
        zza(intent, bundle);
        intent.putExtra("pending_intent", pendingIntent);
        return zzg.zzb(this.mContext, zzGP(), intent, 1073741824);
    }

    private void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private void zza(String str, Notification notification) {
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (TextUtils.isEmpty(str)) {
            str = "GCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(str, 0, notification);
    }

    private boolean zzaca() {
        if (((KeyguardManager) this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        if (!zzt.zzzn()) {
            SystemClock.sleep(10);
        }
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.importance == 100;
            }
        }
        return false;
    }

    private Bundle zzacb() {
        if (this.zzaDS != null) {
            return this.zzaDS;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
        } catch (NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzaDS = applicationInfo.metaData;
        return this.zzaDS;
    }

    static synchronized zza zzcx(Context context) {
        zza zza;
        synchronized (zza.class) {
            if (zzclG == null) {
                zzclG = new zza(context);
            }
            zza = zzclG;
        }
        return zza;
    }

    private static String zzeA(String str) {
        return str.substring("gcm.n.".length());
    }

    private int zzeB(String str) {
        if (!TextUtils.isEmpty(str)) {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier(str, "drawable", this.mContext.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            int identifier2 = resources.getIdentifier(str, "mipmap", this.mContext.getPackageName());
            if (identifier2 != 0) {
                return identifier2;
            }
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 61).append("Icon resource ").append(str).append(" not found. Notification will use default icon.").toString());
        }
        int i = zzacb().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if (i == 0) {
            i = this.mContext.getApplicationInfo().icon;
        }
        if (i == 0) {
            return 17301651;
        }
        return i;
    }

    private Uri zzeC(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("default".equals(str) || this.mContext.getResources().getIdentifier(str, "raw", this.mContext.getPackageName()) == 0) {
            return RingtoneManager.getDefaultUri(2);
        }
        String valueOf = String.valueOf("android.resource://");
        String valueOf2 = String.valueOf(this.mContext.getPackageName());
        return Uri.parse(new StringBuilder(String.valueOf(valueOf).length() + 5 + String.valueOf(valueOf2).length() + String.valueOf(str).length()).append(valueOf).append(valueOf2).append("/raw/").append(str).toString());
    }

    static String zzf(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private String zzg(Bundle bundle, String str) {
        String zzf = zzf(bundle, str);
        if (!TextUtils.isEmpty(zzf)) {
            return zzf;
        }
        String zzi = zzi(bundle, str);
        if (TextUtils.isEmpty(zzi)) {
            return null;
        }
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier(zzi, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            String str2 = "FirebaseMessaging";
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            String valueOf3 = String.valueOf(zzeA(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
            Log.w(str2, new StringBuilder(String.valueOf(valueOf3).length() + 49 + String.valueOf(zzi).length()).append(valueOf3).append(" resource not found: ").append(zzi).append(" Default value will be used.").toString());
            return null;
        }
        Object[] zzj = zzj(bundle, str);
        if (zzj == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzj);
        } catch (MissingFormatArgumentException e) {
            String valueOf4 = String.valueOf(Arrays.toString(zzj));
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zzi).length() + 58 + String.valueOf(valueOf4).length()).append("Missing format argument for ").append(zzi).append(": ").append(valueOf4).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    static String zzi(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zzf(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    static Object[] zzj(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zzf = zzf(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zzf)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zzf);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return objArr;
        } catch (JSONException e) {
            String str2 = "FirebaseMessaging";
            String valueOf3 = String.valueOf(str);
            String valueOf4 = String.valueOf("_loc_args");
            String valueOf5 = String.valueOf(zzeA(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)));
            Log.w(str2, new StringBuilder(String.valueOf(valueOf5).length() + 41 + String.valueOf(zzf).length()).append("Malformed ").append(valueOf5).append(": ").append(zzf).append("  Default value will be used.").toString());
            return null;
        }
    }

    private Integer zzjD(String str) {
        Integer num = null;
        if (VERSION.SDK_INT < 21) {
            return num;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
            }
        }
        int i = zzacb().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i == 0) {
            return num;
        }
        try {
            return Integer.valueOf(ContextCompat.getColor(this.mContext, i));
        } catch (NotFoundException e2) {
            Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            return num;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzG(Bundle bundle) {
        if ("1".equals(zzf(bundle, "gcm.n.noui"))) {
            return true;
        }
        if (zzaca()) {
            return false;
        }
        zza(zzf(bundle, "gcm.n.tag"), zzH(bundle));
        return true;
    }
}
