package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;

public class zzatx extends zzauh {
    private final String zzaGr = zzKn().zzKK();
    private final long zzbqx = zzKn().zzKv();
    private final char zzbsA;
    private final zza zzbsB;
    private final zza zzbsC;
    private final zza zzbsD;
    private final zza zzbsE;
    private final zza zzbsF;
    private final zza zzbsG;
    private final zza zzbsH;
    private final zza zzbsI;
    private final zza zzbsJ;

    public class zza {
        private final int mPriority;
        private final boolean zzbsM;
        private final boolean zzbsN;

        zza(int i, boolean z, boolean z2) {
            this.mPriority = i;
            this.zzbsM = z;
            this.zzbsN = z2;
        }

        public void log(String str) {
            zzatx.this.zza(this.mPriority, this.zzbsM, this.zzbsN, str, null, null, null);
        }

        public void zzd(String str, Object obj, Object obj2, Object obj3) {
            zzatx.this.zza(this.mPriority, this.zzbsM, this.zzbsN, str, obj, obj2, obj3);
        }

        public void zze(String str, Object obj, Object obj2) {
            zzatx.this.zza(this.mPriority, this.zzbsM, this.zzbsN, str, obj, obj2, null);
        }

        public void zzj(String str, Object obj) {
            zzatx.this.zza(this.mPriority, this.zzbsM, this.zzbsN, str, obj, null, null);
        }
    }

    private static class zzb {
        /* access modifiers changed from: private */
        public final String zzbsO;

        public zzb(@NonNull String str) {
            this.zzbsO = str;
        }
    }

    zzatx(zzaue zzaue) {
        super(zzaue);
        if (zzKn().zzoW()) {
            zzKn().zzLg();
            this.zzbsA = 'C';
        } else {
            zzKn().zzLg();
            this.zzbsA = 'c';
        }
        this.zzbsB = new zza(6, false, false);
        this.zzbsC = new zza(6, true, false);
        this.zzbsD = new zza(6, false, true);
        this.zzbsE = new zza(5, false, false);
        this.zzbsF = new zza(5, true, false);
        this.zzbsG = new zza(5, false, true);
        this.zzbsH = new zza(4, false, false);
        this.zzbsI = new zza(3, false, false);
        this.zzbsJ = new zza(2, false, false);
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zzc = zzc(z, obj);
        String zzc2 = zzc(z, obj2);
        String zzc3 = zzc(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzc)) {
            sb.append(str2);
            sb.append(zzc);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc2)) {
            sb.append(str2);
            sb.append(zzc2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc3)) {
            sb.append(str2);
            sb.append(zzc3);
        }
        return sb.toString();
    }

    static String zzc(boolean z, Object obj) {
        StackTraceElement stackTraceElement;
        if (obj == null) {
            return "";
        }
        Object obj2 = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (obj2 instanceof Long) {
            if (!z) {
                return String.valueOf(obj2);
            }
            if (Math.abs(((Long) obj2).longValue()) < 100) {
                return String.valueOf(obj2);
            }
            String str = String.valueOf(obj2).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(((Long) obj2).longValue()));
            return new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d)).toString();
        } else if (obj2 instanceof Boolean) {
            return String.valueOf(obj2);
        } else {
            if (!(obj2 instanceof Throwable)) {
                return obj2 instanceof zzb ? ((zzb) obj2).zzbsO : z ? "-" : String.valueOf(obj2);
            }
            Throwable th = (Throwable) obj2;
            StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzfF = zzfF(AppMeasurement.class.getCanonicalName());
            String zzfF2 = zzfF(zzaue.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod()) {
                    String className = stackTraceElement.getClassName();
                    if (className != null) {
                        String zzfF3 = zzfF(className);
                        if (zzfF3.equals(zzfF) || zzfF3.equals(zzfF2)) {
                            sb.append(": ");
                            sb.append(stackTraceElement);
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            sb.append(": ");
            sb.append(stackTraceElement);
            return sb.toString();
        }
    }

    protected static Object zzfE(String str) {
        if (str == null) {
            return null;
        }
        return new zzb(str);
    }

    private static String zzfF(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
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

    public zza zzLY() {
        return this.zzbsB;
    }

    public zza zzLZ() {
        return this.zzbsC;
    }

    public zza zzMa() {
        return this.zzbsE;
    }

    public zza zzMb() {
        return this.zzbsG;
    }

    public zza zzMc() {
        return this.zzbsH;
    }

    public zza zzMd() {
        return this.zzbsI;
    }

    public zza zzMe() {
        return this.zzbsJ;
    }

    public String zzMf() {
        Pair<String, Long> zzqm = zzKm().zzbsY.zzqm();
        if (zzqm == null || zzqm == zzaua.zzbsX) {
            return null;
        }
        String valueOf = String.valueOf(String.valueOf(zzqm.second));
        String str = (String) zzqm.first;
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zzak(i)) {
            zzn(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzak(int i) {
        return Log.isLoggable(this.zzaGr, i);
    }

    public void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        zzac.zzw(str);
        zzaud zzMv = this.zzbqc.zzMv();
        if (zzMv == null) {
            zzn(6, "Scheduler not set. Not logging error/warn");
        } else if (!zzMv.isInitialized()) {
            zzn(6, "Scheduler not initialized. Not logging error/warn");
        } else {
            if (i < 0) {
                i = 0;
            }
            if (i >= "01VDIWEA?".length()) {
                i = "01VDIWEA?".length() - 1;
            }
            String valueOf = String.valueOf("2");
            char charAt = "01VDIWEA?".charAt(i);
            char c = this.zzbsA;
            long j = this.zzbqx;
            String valueOf2 = String.valueOf(zza(true, str, obj, obj2, obj3));
            final String sb = new StringBuilder(String.valueOf(valueOf).length() + 23 + String.valueOf(valueOf2).length()).append(valueOf).append(charAt).append(c).append(j).append(":").append(valueOf2).toString();
            if (sb.length() > 1024) {
                sb = str.substring(0, 1024);
            }
            zzMv.zzm(new Runnable() {
                public void run() {
                    zzaua zzKm = zzatx.this.zzbqc.zzKm();
                    if (zzKm.isInitialized()) {
                        zzKm.zzbsY.zzcc(sb);
                    } else {
                        zzatx.this.zzn(6, "Persisted config not initialized. Not logging error/warn");
                    }
                }
            });
        }
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    /* access modifiers changed from: protected */
    public void zzn(int i, String str) {
        Log.println(i, this.zzaGr, str);
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }
}
