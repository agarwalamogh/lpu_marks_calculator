package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;

public final class zzaaj<O extends ApiOptions> extends zzc<O> {
    private final zze zzaAJ;
    private final zzaag zzaAK;
    private final zzg zzaAL;
    private final zza<? extends zzbai, zzbaj> zzazo;

    public zzaaj(@NonNull Context context, Api<O> api, Looper looper, @NonNull zze zze, @NonNull zzaag zzaag, zzg zzg, zza<? extends zzbai, zzbaj> zza) {
        super(context, api, looper);
        this.zzaAJ = zze;
        this.zzaAK = zzaag;
        this.zzaAL = zzg;
        this.zzazo = zza;
        this.zzayX.zzb((zzc<?>) this);
    }

    public zze buildApiClient(Looper looper, zzaax.zza<O> zza) {
        this.zzaAK.zza(zza);
        return this.zzaAJ;
    }

    public zzabr createSignInCoordinator(Context context, Handler handler) {
        return new zzabr(context, handler, this.zzaAL, this.zzazo);
    }

    public zze zzvU() {
        return this.zzaAJ;
    }
}
