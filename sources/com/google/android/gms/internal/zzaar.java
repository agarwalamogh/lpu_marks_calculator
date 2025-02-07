package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzf.C0934zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzaar implements zzaau {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Lock zzaAG;
    private final zzg zzaAL;
    private final Map<Api<?>, Boolean> zzaAO;
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.zze zzaAQ;
    private ConnectionResult zzaAZ;
    /* access modifiers changed from: private */
    public final zzaav zzaBk;
    private int zzaBn;
    private int zzaBo = 0;
    private int zzaBp;
    private final Bundle zzaBq = new Bundle();
    private final Set<com.google.android.gms.common.api.Api.zzc> zzaBr = new HashSet();
    /* access modifiers changed from: private */
    public zzbai zzaBs;
    private boolean zzaBt;
    /* access modifiers changed from: private */
    public boolean zzaBu;
    private boolean zzaBv;
    /* access modifiers changed from: private */
    public zzr zzaBw;
    private boolean zzaBx;
    private boolean zzaBy;
    private ArrayList<Future<?>> zzaBz = new ArrayList<>();
    private final com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zzazo;

    private static class zza implements C0934zzf {
        /* access modifiers changed from: private */
        public final boolean zzaAu;
        private final WeakReference<zzaar> zzaBB;
        private final Api<?> zzaxf;

        public zza(zzaar zzaar, Api<?> api, boolean z) {
            this.zzaBB = new WeakReference<>(zzaar);
            this.zzaxf = api;
            this.zzaAu = z;
        }

        public void zzg(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzaar zzaar = (zzaar) this.zzaBB.get();
            if (zzaar != null) {
                if (Looper.myLooper() == zzaar.zzaBk.zzaAw.getLooper()) {
                    z = true;
                }
                zzac.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzaar.zzaAG.lock();
                try {
                    if (zzaar.zzcB(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzaar.zzb(connectionResult, this.zzaxf, this.zzaAu);
                        }
                        if (zzaar.zzwf()) {
                            zzaar.zzwg();
                        }
                        zzaar.zzaAG.unlock();
                    }
                } finally {
                    zzaar.zzaAG.unlock();
                }
            }
        }
    }

    private class zzb extends zzf {
        private final Map<com.google.android.gms.common.api.Api.zze, zza> zzaBC;

        public zzb(Map<com.google.android.gms.common.api.Api.zze, zza> map) {
            super();
            this.zzaBC = map;
        }

        @WorkerThread
        public void zzwe() {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            int i = 0;
            Iterator it = this.zzaBC.keySet().iterator();
            boolean z5 = true;
            boolean z6 = false;
            while (true) {
                if (!it.hasNext()) {
                    z4 = z6;
                    z = false;
                    break;
                }
                com.google.android.gms.common.api.Api.zze zze = (com.google.android.gms.common.api.Api.zze) it.next();
                if (!zze.zzvh()) {
                    z3 = false;
                    z2 = z6;
                } else if (!((zza) this.zzaBC.get(zze)).zzaAu) {
                    z = true;
                    break;
                } else {
                    z3 = z5;
                    z2 = true;
                }
                z6 = z2;
                z5 = z3;
            }
            if (z4) {
                i = zzaar.this.zzaAQ.isGooglePlayServicesAvailable(zzaar.this.mContext);
            }
            if (i == 0 || (!z && !z5)) {
                if (zzaar.this.zzaBu) {
                    zzaar.this.zzaBs.connect();
                }
                for (com.google.android.gms.common.api.Api.zze zze2 : this.zzaBC.keySet()) {
                    final C0934zzf zzf = (C0934zzf) this.zzaBC.get(zze2);
                    if (!zze2.zzvh() || i == 0) {
                        zze2.zza(zzf);
                    } else {
                        zzaar.this.zzaBk.zza((zza) new zza(this, zzaar.this) {
                            public void zzwe() {
                                zzf.zzg(new ConnectionResult(16, null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i, null);
            zzaar.this.zzaBk.zza((zza) new zza(zzaar.this) {
                public void zzwe() {
                    zzaar.this.zzf(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {
        private final ArrayList<com.google.android.gms.common.api.Api.zze> zzaBG;

        public zzc(ArrayList<com.google.android.gms.common.api.Api.zze> arrayList) {
            super();
            this.zzaBG = arrayList;
        }

        @WorkerThread
        public void zzwe() {
            zzaar.this.zzaBk.zzaAw.zzaBR = zzaar.this.zzwl();
            Iterator it = this.zzaBG.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zze) it.next()).zza(zzaar.this.zzaBw, zzaar.this.zzaBk.zzaAw.zzaBR);
            }
        }
    }

    private static class zzd extends zzbam {
        private final WeakReference<zzaar> zzaBB;

        zzd(zzaar zzaar) {
            this.zzaBB = new WeakReference<>(zzaar);
        }

        @BinderThread
        public void zzb(final zzbaw zzbaw) {
            final zzaar zzaar = (zzaar) this.zzaBB.get();
            if (zzaar != null) {
                zzaar.zzaBk.zza((zza) new zza(this, zzaar) {
                    public void zzwe() {
                        zzaar.zza(zzbaw);
                    }
                });
            }
        }
    }

    private class zze implements ConnectionCallbacks, OnConnectionFailedListener {
        private zze() {
        }

        public void onConnected(Bundle bundle) {
            zzaar.this.zzaBs.zza(new zzd(zzaar.this));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzaar.this.zzaAG.lock();
            try {
                if (zzaar.this.zze(connectionResult)) {
                    zzaar.this.zzwj();
                    zzaar.this.zzwg();
                } else {
                    zzaar.this.zzf(connectionResult);
                }
            } finally {
                zzaar.this.zzaAG.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    private abstract class zzf implements Runnable {
        private zzf() {
        }

        @WorkerThread
        public void run() {
            zzaar.this.zzaAG.lock();
            try {
                if (!Thread.interrupted()) {
                    zzwe();
                    zzaar.this.zzaAG.unlock();
                }
            } catch (RuntimeException e) {
                zzaar.this.zzaBk.zza(e);
            } finally {
                zzaar.this.zzaAG.unlock();
            }
        }

        /* access modifiers changed from: protected */
        @WorkerThread
        public abstract void zzwe();
    }

    public zzaar(zzaav zzaav, zzg zzg, Map<Api<?>, Boolean> map, com.google.android.gms.common.zze zze2, com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zza2, Lock lock, Context context) {
        this.zzaBk = zzaav;
        this.zzaAL = zzg;
        this.zzaAO = map;
        this.zzaAQ = zze2;
        this.zzazo = zza2;
        this.zzaAG = lock;
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public void zza(zzbaw zzbaw) {
        if (zzcB(0)) {
            ConnectionResult zzyh = zzbaw.zzyh();
            if (zzyh.isSuccess()) {
                zzaf zzPU = zzbaw.zzPU();
                ConnectionResult zzyh2 = zzPU.zzyh();
                if (!zzyh2.isSuccess()) {
                    String valueOf = String.valueOf(zzyh2);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zzf(zzyh2);
                    return;
                }
                this.zzaBv = true;
                this.zzaBw = zzPU.zzyg();
                this.zzaBx = zzPU.zzyi();
                this.zzaBy = zzPU.zzyj();
                zzwg();
            } else if (zze(zzyh)) {
                zzwj();
                zzwg();
            } else {
                zzf(zzyh);
            }
        }
    }

    private boolean zza(int i, boolean z, ConnectionResult connectionResult) {
        if (!z || zzd(connectionResult)) {
            return this.zzaAZ == null || i < this.zzaBn;
        }
        return false;
    }

    private void zzau(boolean z) {
        if (this.zzaBs != null) {
            if (this.zzaBs.isConnected() && z) {
                this.zzaBs.zzPJ();
            }
            this.zzaBs.disconnect();
            this.zzaBw = null;
        }
    }

    /* access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, boolean z) {
        int priority = api.zzve().getPriority();
        if (zza(priority, z, connectionResult)) {
            this.zzaAZ = connectionResult;
            this.zzaBn = priority;
        }
        this.zzaBk.zzaCf.put(api.zzvg(), connectionResult);
    }

    /* access modifiers changed from: private */
    public boolean zzcB(int i) {
        if (this.zzaBo == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzaBk.zzaAw.zzwr());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.zzaBp);
        String valueOf2 = String.valueOf(zzcC(this.zzaBo));
        String valueOf3 = String.valueOf(zzcC(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf2).length() + 70 + String.valueOf(valueOf3).length()).append("GoogleApiClient connecting is in step ").append(valueOf2).append(" but received callback for step ").append(valueOf3).toString(), new Exception());
        zzf(new ConnectionResult(8, null));
        return false;
    }

    private String zzcC(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private boolean zzd(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zzaAQ.zzcw(connectionResult.getErrorCode()) != null;
    }

    /* access modifiers changed from: private */
    public boolean zze(ConnectionResult connectionResult) {
        return this.zzaBt && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    public void zzf(ConnectionResult connectionResult) {
        zzwk();
        zzau(!connectionResult.hasResolution());
        this.zzaBk.zzh(connectionResult);
        this.zzaBk.zzaCj.zzc(connectionResult);
    }

    /* access modifiers changed from: private */
    public boolean zzwf() {
        this.zzaBp--;
        if (this.zzaBp > 0) {
            return false;
        }
        if (this.zzaBp < 0) {
            Log.w("GoogleApiClientConnecting", this.zzaBk.zzaAw.zzwr());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzf(new ConnectionResult(8, null));
            return false;
        } else if (this.zzaAZ == null) {
            return true;
        } else {
            this.zzaBk.zzaCi = this.zzaBn;
            zzf(this.zzaAZ);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void zzwg() {
        if (this.zzaBp == 0) {
            if (!this.zzaBu || this.zzaBv) {
                zzwh();
            }
        }
    }

    private void zzwh() {
        ArrayList arrayList = new ArrayList();
        this.zzaBo = 1;
        this.zzaBp = this.zzaBk.zzaBQ.size();
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.zzaBk.zzaBQ.keySet()) {
            if (!this.zzaBk.zzaCf.containsKey(zzc2)) {
                arrayList.add((com.google.android.gms.common.api.Api.zze) this.zzaBk.zzaBQ.get(zzc2));
            } else if (zzwf()) {
                zzwi();
            }
        }
        if (!arrayList.isEmpty()) {
            this.zzaBz.add(zzaaw.zzwv().submit(new zzc(arrayList)));
        }
    }

    private void zzwi() {
        this.zzaBk.zzwt();
        zzaaw.zzwv().execute(new Runnable() {
            public void run() {
                zzaar.this.zzaAQ.zzaF(zzaar.this.mContext);
            }
        });
        if (this.zzaBs != null) {
            if (this.zzaBx) {
                this.zzaBs.zza(this.zzaBw, this.zzaBy);
            }
            zzau(false);
        }
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.zzaBk.zzaCf.keySet()) {
            ((com.google.android.gms.common.api.Api.zze) this.zzaBk.zzaBQ.get(zzc2)).disconnect();
        }
        this.zzaBk.zzaCj.zzo(this.zzaBq.isEmpty() ? null : this.zzaBq);
    }

    /* access modifiers changed from: private */
    public void zzwj() {
        this.zzaBu = false;
        this.zzaBk.zzaAw.zzaBR = Collections.emptySet();
        for (com.google.android.gms.common.api.Api.zzc zzc2 : this.zzaBr) {
            if (!this.zzaBk.zzaCf.containsKey(zzc2)) {
                this.zzaBk.zzaCf.put(zzc2, new ConnectionResult(17, null));
            }
        }
    }

    private void zzwk() {
        Iterator it = this.zzaBz.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.zzaBz.clear();
    }

    /* access modifiers changed from: private */
    public Set<Scope> zzwl() {
        if (this.zzaAL == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzaAL.zzxL());
        Map zzxN = this.zzaAL.zzxN();
        for (Api api : zzxN.keySet()) {
            if (!this.zzaBk.zzaCf.containsKey(api.zzvg())) {
                hashSet.addAll(((com.google.android.gms.common.internal.zzg.zza) zzxN.get(api)).zzakq);
            }
        }
        return hashSet;
    }

    public void begin() {
        this.zzaBk.zzaCf.clear();
        this.zzaBu = false;
        this.zzaAZ = null;
        this.zzaBo = 0;
        this.zzaBt = true;
        this.zzaBv = false;
        this.zzaBx = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.zzaAO.keySet()) {
            com.google.android.gms.common.api.Api.zze zze2 = (com.google.android.gms.common.api.Api.zze) this.zzaBk.zzaBQ.get(api.zzvg());
            boolean z2 = (api.zzve().getPriority() == 1) | z;
            boolean booleanValue = ((Boolean) this.zzaAO.get(api)).booleanValue();
            if (zze2.zzrd()) {
                this.zzaBu = true;
                if (booleanValue) {
                    this.zzaBr.add(api.zzvg());
                } else {
                    this.zzaBt = false;
                }
            }
            hashMap.put(zze2, new zza(this, api, booleanValue));
            z = z2;
        }
        if (z) {
            this.zzaBu = false;
        }
        if (this.zzaBu) {
            this.zzaAL.zzc(Integer.valueOf(this.zzaBk.zzaAw.getSessionId()));
            zze zze3 = new zze();
            this.zzaBs = (zzbai) this.zzazo.zza(this.mContext, this.zzaBk.zzaAw.getLooper(), this.zzaAL, this.zzaAL.zzxR(), zze3, zze3);
        }
        this.zzaBp = this.zzaBk.zzaBQ.size();
        this.zzaBz.add(zzaaw.zzwv().submit(new zzb(hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzwk();
        zzau(true);
        this.zzaBk.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzcB(1)) {
            if (bundle != null) {
                this.zzaBq.putAll(bundle);
            }
            if (zzwf()) {
                zzwi();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzf(new ConnectionResult(8, null));
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzaad.zza<R, A>> T zza(T t) {
        this.zzaBk.zzaAw.zzaAU.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzcB(1)) {
            zzb(connectionResult, api, z);
            if (zzwf()) {
                zzwi();
            }
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzaad.zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
