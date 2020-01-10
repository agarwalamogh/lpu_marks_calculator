package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbr extends zzca {
    private long zzqX = -1;

    public zzbr(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbl = Long.valueOf(-1);
        if (this.zzqX == -1) {
            this.zzqX = (long) ((Integer) this.zzre.invoke(null, new Object[]{this.zzpz.getContext()})).intValue();
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbl = Long.valueOf(this.zzqX);
        }
    }
}
