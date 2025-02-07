package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzacw extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zzacw> CREATOR = new zzacx();
    private final HashMap<String, Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>>> zzaHh;
    private final ArrayList<zza> zzaHi = null;
    private final String zzaHj;
    final int zzaiI;

    public static class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Creator<zza> CREATOR = new zzacy();
        final String className;
        final int versionCode;
        final ArrayList<zzb> zzaHk;

        zza(int i, String str, ArrayList<zzb> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.zzaHk = arrayList;
        }

        zza(String str, Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.zzaHk = zzX(map);
        }

        private static ArrayList<zzb> zzX(Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (String str : map.keySet()) {
                arrayList.add(new zzb(str, (com.google.android.gms.internal.zzacs.zza) map.get(str)));
            }
            return arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacy.zza(this, parcel, i);
        }

        /* access modifiers changed from: 0000 */
        public HashMap<String, com.google.android.gms.internal.zzacs.zza<?, ?>> zzyG() {
            HashMap<String, com.google.android.gms.internal.zzacs.zza<?, ?>> hashMap = new HashMap<>();
            int size = this.zzaHk.size();
            for (int i = 0; i < size; i++) {
                zzb zzb = (zzb) this.zzaHk.get(i);
                hashMap.put(zzb.zzaB, zzb.zzaHl);
            }
            return hashMap;
        }
    }

    public static class zzb extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Creator<zzb> CREATOR = new zzacv();
        final int versionCode;
        final String zzaB;
        final com.google.android.gms.internal.zzacs.zza<?, ?> zzaHl;

        zzb(int i, String str, com.google.android.gms.internal.zzacs.zza<?, ?> zza) {
            this.versionCode = i;
            this.zzaB = str;
            this.zzaHl = zza;
        }

        zzb(String str, com.google.android.gms.internal.zzacs.zza<?, ?> zza) {
            this.versionCode = 1;
            this.zzaB = str;
            this.zzaHl = zza;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacv.zza(this, parcel, i);
        }
    }

    zzacw(int i, ArrayList<zza> arrayList, String str) {
        this.zzaiI = i;
        this.zzaHh = zzi(arrayList);
        this.zzaHj = (String) zzac.zzw(str);
        zzyD();
    }

    private static HashMap<String, Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>>> zzi(ArrayList<zza> arrayList) {
        HashMap<String, Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zza zza2 = (zza) arrayList.get(i);
            hashMap.put(zza2.className, zza2.zzyG());
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zzaHh.keySet()) {
            sb.append(str).append(":\n");
            Map map = (Map) this.zzaHh.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ").append(str2).append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzacx.zza(this, parcel, i);
    }

    public Map<String, com.google.android.gms.internal.zzacs.zza<?, ?>> zzdw(String str) {
        return (Map) this.zzaHh.get(str);
    }

    public void zzyD() {
        for (String str : this.zzaHh.keySet()) {
            Map map = (Map) this.zzaHh.get(str);
            for (String str2 : map.keySet()) {
                ((com.google.android.gms.internal.zzacs.zza) map.get(str2)).zza(this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<zza> zzyE() {
        ArrayList<zza> arrayList = new ArrayList<>();
        for (String str : this.zzaHh.keySet()) {
            arrayList.add(new zza(str, (Map) this.zzaHh.get(str)));
        }
        return arrayList;
    }

    public String zzyF() {
        return this.zzaHj;
    }
}
