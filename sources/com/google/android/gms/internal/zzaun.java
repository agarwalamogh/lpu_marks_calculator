package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement.zzf;

public class zzaun extends zzauh {
    private Handler mHandler;
    protected long zzbvW;
    private final zzatk zzbvX = new zzatk(this.zzbqc) {
        @WorkerThread
        public void run() {
            zzaun.this.zzNf();
        }
    };
    private final zzatk zzbvY = new zzatk(this.zzbqc) {
        @WorkerThread
        public void run() {
            zzaun.this.zzNg();
        }
    };

    zzaun(zzaue zzaue) {
        super(zzaue);
    }

    private void zzNd() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzNg() {
        zzmR();
        zzaO(false);
        zzJY().zzW(zznR().elapsedRealtime());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzas(long j) {
        zzmR();
        zzNd();
        this.zzbvX.cancel();
        this.zzbvY.cancel();
        zzKl().zzMe().zzj("Activity resumed, time", Long.valueOf(j));
        this.zzbvW = j;
        if (zznR().currentTimeMillis() - zzKm().zzbtm.get() > zzKm().zzbto.get()) {
            zzKm().zzbtn.set(true);
            zzKm().zzbtp.set(0);
        }
        if (zzKm().zzbtn.get()) {
            this.zzbvX.zzy(Math.max(0, zzKm().zzbtl.get() - zzKm().zzbtp.get()));
        } else {
            this.zzbvY.zzy(Math.max(0, 3600000 - zzKm().zzbtp.get()));
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzat(long j) {
        zzmR();
        zzNd();
        this.zzbvX.cancel();
        this.zzbvY.cancel();
        zzKl().zzMe().zzj("Activity paused, time", Long.valueOf(j));
        if (this.zzbvW != 0) {
            zzKm().zzbtp.set(zzKm().zzbtp.get() + (j - this.zzbvW));
        }
        zzKm().zzbto.set(zznR().currentTimeMillis());
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void zzNc() {
        final long elapsedRealtime = zznR().elapsedRealtime();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzaun.this.zzas(elapsedRealtime);
            }
        });
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void zzNe() {
        final long elapsedRealtime = zznR().elapsedRealtime();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzaun.this.zzat(elapsedRealtime);
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzNf() {
        zzmR();
        zzKl().zzMe().zzj("Session started, time", Long.valueOf(zznR().elapsedRealtime()));
        zzKm().zzbtn.set(false);
        zzKa().zze("auto", "_s", new Bundle());
    }

    @WorkerThread
    public boolean zzaO(boolean z) {
        zzmR();
        zzob();
        long elapsedRealtime = zznR().elapsedRealtime();
        if (this.zzbvW == 0) {
            this.zzbvW = elapsedRealtime - 3600000;
        }
        long j = elapsedRealtime - this.zzbvW;
        if (z || j >= 1000) {
            zzKm().zzbtp.set(j);
            zzKl().zzMe().zzj("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzauk.zza((zzf) zzKe().zzMU(), bundle);
            zzKa().zze("auto", "_e", bundle);
            this.zzbvW = elapsedRealtime;
            this.zzbvY.cancel();
            this.zzbvY.zzy(Math.max(0, 3600000 - zzKm().zzbtp.get()));
            return true;
        }
        zzKl().zzMe().zzj("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }
}
