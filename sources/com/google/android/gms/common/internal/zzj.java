package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import java.util.Collection;

public class zzj extends zza {
    public static final Creator<zzj> CREATOR = new zzk();
    final int version;
    final int zzaFK;
    int zzaFL;
    String zzaFM;
    IBinder zzaFN;
    Scope[] zzaFO;
    Bundle zzaFP;
    Account zzaFQ;
    zzc[] zzaFR;

    public zzj(int i) {
        this.version = 3;
        this.zzaFL = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzaFK = i;
    }

    zzj(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, zzc[] zzcArr) {
        this.version = i;
        this.zzaFK = i2;
        this.zzaFL = i3;
        if ("com.google.android.gms".equals(str)) {
            this.zzaFM = "com.google.android.gms";
        } else {
            this.zzaFM = str;
        }
        if (i < 2) {
            this.zzaFQ = zzbq(iBinder);
        } else {
            this.zzaFN = iBinder;
            this.zzaFQ = account;
        }
        this.zzaFO = scopeArr;
        this.zzaFP = bundle;
        this.zzaFR = zzcArr;
    }

    private Account zzbq(IBinder iBinder) {
        if (iBinder != null) {
            return zza.zza(zzr.zza.zzbr(iBinder));
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }

    public zzj zza(zzc[] zzcArr) {
        this.zzaFR = zzcArr;
        return this;
    }

    public zzj zzb(zzr zzr) {
        if (zzr != null) {
            this.zzaFN = zzr.asBinder();
        }
        return this;
    }

    public zzj zzdm(String str) {
        this.zzaFM = str;
        return this;
    }

    public zzj zzf(Account account) {
        this.zzaFQ = account;
        return this;
    }

    public zzj zzf(Collection<Scope> collection) {
        this.zzaFO = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public zzj zzp(Bundle bundle) {
        this.zzaFP = bundle;
        return this;
    }
}
