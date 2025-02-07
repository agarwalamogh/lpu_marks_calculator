package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzacf {
    public static final Api<NoOptions> API = new Api<>("Common.API", zzaie, zzaid);
    public static final zzacg zzaGM = new zzach();
    public static final zzf<zzacj> zzaid = new zzf<>();
    private static final zza<zzacj, NoOptions> zzaie = new zza<zzacj, NoOptions>() {
        /* renamed from: zzf */
        public zzacj zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzacj(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
}
