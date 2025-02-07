package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzath implements Creator<zzatg> {
    static void zza(zzatg zzatg, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzatg.versionCode);
        zzc.zza(parcel, 2, zzatg.packageName, false);
        zzc.zza(parcel, 3, zzatg.zzbqV, false);
        zzc.zza(parcel, 4, (Parcelable) zzatg.zzbqW, i, false);
        zzc.zza(parcel, 5, zzatg.zzbqX);
        zzc.zza(parcel, 6, zzatg.zzbqY);
        zzc.zza(parcel, 7, zzatg.zzbqZ, false);
        zzc.zza(parcel, 8, (Parcelable) zzatg.zzbra, i, false);
        zzc.zza(parcel, 9, zzatg.zzbrb);
        zzc.zza(parcel, 10, (Parcelable) zzatg.zzbrc, i, false);
        zzc.zza(parcel, 11, zzatg.zzbrd);
        zzc.zza(parcel, 12, (Parcelable) zzatg.zzbre, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhR */
    public zzatg createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        zzauq zzauq = null;
        long j = 0;
        boolean z = false;
        String str3 = null;
        zzatq zzatq = null;
        long j2 = 0;
        zzatq zzatq2 = null;
        long j3 = 0;
        zzatq zzatq3 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    zzauq = (zzauq) zzb.zza(parcel, zzaX, zzauq.CREATOR);
                    break;
                case 5:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 7:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 8:
                    zzatq = (zzatq) zzb.zza(parcel, zzaX, zzatq.CREATOR);
                    break;
                case 9:
                    j2 = zzb.zzi(parcel, zzaX);
                    break;
                case 10:
                    zzatq2 = (zzatq) zzb.zza(parcel, zzaX, zzatq.CREATOR);
                    break;
                case 11:
                    j3 = zzb.zzi(parcel, zzaX);
                    break;
                case 12:
                    zzatq3 = (zzatq) zzb.zza(parcel, zzaX, zzatq.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzatg(i, str, str2, zzauq, j, z, str3, zzatq, j2, zzatq2, j3, zzatq3);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzlA */
    public zzatg[] newArray(int i) {
        return new zzatg[i];
    }
}
