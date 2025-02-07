package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zza;

public class zzaam extends zzaae {
    private final zza<zzzz<?>> zzaBh = new zza<>();
    private zzaax zzayX;

    private zzaam(zzabf zzabf) {
        super(zzabf);
        this.zzaCR.zza("ConnectionlessLifecycleHelper", (zzabe) this);
    }

    public static void zza(Activity activity, zzaax zzaax, zzzz<?> zzzz) {
        zzabf zzs = zzs(activity);
        zzaam zzaam = (zzaam) zzs.zza("ConnectionlessLifecycleHelper", zzaam.class);
        if (zzaam == null) {
            zzaam = new zzaam(zzs);
        }
        zzaam.zzayX = zzaax;
        zzaam.zza(zzzz);
        zzaax.zza(zzaam);
    }

    private void zza(zzzz<?> zzzz) {
        zzac.zzb(zzzz, (Object) "ApiKey cannot be null");
        this.zzaBh.add(zzzz);
    }

    public void onStart() {
        super.onStart();
        if (!this.zzaBh.isEmpty()) {
            this.zzayX.zza(this);
        }
    }

    public void onStop() {
        super.onStop();
        this.zzayX.zzb(this);
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.zzayX.zza(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public void zzvx() {
        this.zzayX.zzvx();
    }

    /* access modifiers changed from: 0000 */
    public zza<zzzz<?>> zzwb() {
        return this.zzaBh;
    }
}
