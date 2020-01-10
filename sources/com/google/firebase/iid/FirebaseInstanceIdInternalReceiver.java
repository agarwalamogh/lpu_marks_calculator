package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import com.google.firebase.iid.zzb.C1015zzb;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    private static boolean zzbgs = false;
    private C1015zzb zzcll;
    private C1015zzb zzclm;

    private C1015zzb zzK(Context context, String str) {
        if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
            if (this.zzclm == null) {
                this.zzclm = new C1015zzb(context, str);
            }
            return this.zzclm;
        }
        if (this.zzcll == null) {
            this.zzcll = new C1015zzb(context, str);
        }
        return this.zzcll;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (!(parcelableExtra instanceof Intent)) {
                Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
                return;
            }
            Intent intent2 = (Intent) parcelableExtra;
            if (zzt.zzzq()) {
                zzK(context, intent.getAction()).zzb(intent2, goAsync());
            } else {
                zzg.zzabU().zzb(context, intent.getAction(), intent2);
            }
        }
    }
}
