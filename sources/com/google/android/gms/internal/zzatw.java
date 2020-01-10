package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;
import com.google.android.gms.internal.zzatt.zza;

public class zzatw extends zzf<zzatt> {
    public zzatw(Context context, Looper looper, zzb zzb, zzc zzc) {
        super(context, looper, 93, zzb, zzc, null);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzeA() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }

    /* renamed from: zzet */
    public zzatt zzh(IBinder iBinder) {
        return zza.zzes(iBinder);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String zzez() {
        return "com.google.android.gms.measurement.START";
    }
}
