package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzr;
import java.util.HashSet;
import java.util.Set;

public class zzabr extends zzbam implements ConnectionCallbacks, OnConnectionFailedListener {
    private static com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zzaDg = zzbah.zzaie;
    private final Context mContext;
    private final Handler mHandler;
    private zzg zzaAL;
    private zzbai zzaBs;
    private final boolean zzaDh;
    private zza zzaDi;
    private Set<Scope> zzakq;
    private final com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zzayH;

    @WorkerThread
    public interface zza {
        void zzb(zzr zzr, Set<Scope> set);

        void zzi(ConnectionResult connectionResult);
    }

    @WorkerThread
    public zzabr(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzayH = zzaDg;
        this.zzaDh = true;
    }

    @WorkerThread
    public zzabr(Context context, Handler handler, zzg zzg, com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zza2) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzaAL = zzg;
        this.zzakq = zzg.zzxL();
        this.zzayH = zza2;
        this.zzaDh = false;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzc(zzbaw zzbaw) {
        ConnectionResult zzyh = zzbaw.zzyh();
        if (zzyh.isSuccess()) {
            zzaf zzPU = zzbaw.zzPU();
            ConnectionResult zzyh2 = zzPU.zzyh();
            if (!zzyh2.isSuccess()) {
                String valueOf = String.valueOf(zzyh2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                this.zzaDi.zzi(zzyh2);
                this.zzaBs.disconnect();
                return;
            }
            this.zzaDi.zzb(zzPU.zzyg(), this.zzakq);
        } else {
            this.zzaDi.zzi(zzyh);
        }
        this.zzaBs.disconnect();
    }

    @WorkerThread
    public void onConnected(@Nullable Bundle bundle) {
        this.zzaBs.zza(this);
    }

    @WorkerThread
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzaDi.zzi(connectionResult);
    }

    @WorkerThread
    public void onConnectionSuspended(int i) {
        this.zzaBs.disconnect();
    }

    @WorkerThread
    public void zza(zza zza2) {
        if (this.zzaBs != null) {
            this.zzaBs.disconnect();
        }
        if (this.zzaDh) {
            GoogleSignInOptions zzrC = zzn.zzas(this.mContext).zzrC();
            this.zzakq = zzrC == null ? new HashSet() : new HashSet(zzrC.zzrj());
            this.zzaAL = new zzg(null, this.zzakq, null, 0, null, null, null, zzbaj.zzbEi);
        }
        this.zzaBs = (zzbai) this.zzayH.zza(this.mContext, this.mHandler.getLooper(), this.zzaAL, this.zzaAL.zzxR(), this, this);
        this.zzaDi = zza2;
        this.zzaBs.connect();
    }

    @BinderThread
    public void zzb(final zzbaw zzbaw) {
        this.mHandler.post(new Runnable() {
            public void run() {
                zzabr.this.zzc(zzbaw);
            }
        });
    }

    public zzbai zzwO() {
        return this.zzaBs;
    }

    public void zzwY() {
        this.zzaBs.disconnect();
    }
}
