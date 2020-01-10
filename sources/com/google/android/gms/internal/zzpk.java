package com.google.android.gms.internal;

import android.util.Log;
import com.google.ads.AdRequest;

@zzme
public final class zzpk extends zzqf {
    /* renamed from: v */
    public static void m30v(String str) {
        if (zzkI()) {
            Log.v(AdRequest.LOGTAG, str);
        }
    }

    public static boolean zzkH() {
        return ((Boolean) zzgd.zzDr.get()).booleanValue();
    }

    public static boolean zzkI() {
        return zzak(2) && zzkH();
    }
}
