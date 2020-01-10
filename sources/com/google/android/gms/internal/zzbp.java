package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.util.concurrent.Callable;

public class zzbp implements Callable {
    private final zzbd zzpz;
    private final zza zzqV;

    public zzbp(zzbd zzbd, zza zza) {
        this.zzpz = zzbd;
        this.zzqV = zza;
    }

    /* renamed from: zzbk */
    public Void call() throws Exception {
        if (this.zzpz.zzaS() != null) {
            this.zzpz.zzaS().get();
        }
        zza zzaR = this.zzpz.zzaR();
        if (zzaR != null) {
            try {
                synchronized (this.zzqV) {
                    zzbxt.zza(this.zzqV, zzbxt.zzf(zzaR));
                }
            } catch (zzbxs e) {
            }
        }
        return null;
    }
}
