package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaza implements Creator<zzayz> {
    static void zza(zzayz zzayz, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzayz.name, false);
        zzc.zza(parcel, 3, zzayz.zzbBC);
        zzc.zza(parcel, 4, zzayz.zzbhn);
        zzc.zza(parcel, 5, zzayz.zzbhp);
        zzc.zza(parcel, 6, zzayz.zzaGV, false);
        zzc.zza(parcel, 7, zzayz.zzbBD, false);
        zzc.zzc(parcel, 8, zzayz.zzbBE);
        zzc.zzc(parcel, 9, zzayz.zzbBF);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjb */
    public zzayz createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        long j = 0;
        double d = 0.0d;
        int i2 = 0;
        String str = null;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    d = zzb.zzn(parcel, zzaX);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    bArr = zzb.zzt(parcel, zzaX);
                    break;
                case 8:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 9:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzayz(str2, j, z, d, str, bArr, i2, i);
        }
        throw new zza("Overread allowed size end=" + zzaY, parcel);
    }

    /* renamed from: zzmY */
    public zzayz[] newArray(int i) {
        return new zzayz[i];
    }
}
