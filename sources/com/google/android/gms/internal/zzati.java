package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzats.zza;
import java.lang.reflect.InvocationTargetException;

public class zzati extends zzaug {
    static final String zzbrf = String.valueOf(zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    private Boolean zzaeZ;

    zzati(zzaue zzaue) {
        super(zzaue);
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

    /* access modifiers changed from: 0000 */
    public String zzKK() {
        return (String) zzats.zzbrO.get();
    }

    public int zzKL() {
        return 25;
    }

    public int zzKM() {
        return 40;
    }

    public int zzKN() {
        return 24;
    }

    /* access modifiers changed from: 0000 */
    public int zzKO() {
        return 40;
    }

    /* access modifiers changed from: 0000 */
    public int zzKP() {
        return 100;
    }

    /* access modifiers changed from: 0000 */
    public int zzKQ() {
        return 256;
    }

    public int zzKR() {
        return 36;
    }

    public int zzKS() {
        return 2048;
    }

    /* access modifiers changed from: 0000 */
    public int zzKT() {
        return 500;
    }

    public long zzKU() {
        return (long) ((Integer) zzats.zzbrY.get()).intValue();
    }

    public long zzKV() {
        return (long) ((Integer) zzats.zzbsa.get()).intValue();
    }

    /* access modifiers changed from: 0000 */
    public int zzKW() {
        return 25;
    }

    /* access modifiers changed from: 0000 */
    public int zzKX() {
        return 1000;
    }

    /* access modifiers changed from: 0000 */
    public int zzKY() {
        return 25;
    }

    /* access modifiers changed from: 0000 */
    public int zzKZ() {
        return 1000;
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

    public long zzKv() {
        return 10240;
    }

    public long zzLA() {
        return Math.max(0, ((Long) zzats.zzbsp.get()).longValue());
    }

    public int zzLB() {
        return Math.min(20, Math.max(0, ((Integer) zzats.zzbsq.get()).intValue()));
    }

    public String zzLC() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzKl().zzLY().zzj("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            zzKl().zzLY().zzj("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            zzKl().zzLY().zzj("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            zzKl().zzLY().zzj("SystemProperties.get() threw an exception", e4);
        }
        return "";
    }

    /* access modifiers changed from: 0000 */
    public long zzLa() {
        return 15552000000L;
    }

    /* access modifiers changed from: 0000 */
    public long zzLb() {
        return 15552000000L;
    }

    /* access modifiers changed from: 0000 */
    public long zzLc() {
        return 3600000;
    }

    /* access modifiers changed from: 0000 */
    public long zzLd() {
        return 60000;
    }

    /* access modifiers changed from: 0000 */
    public long zzLe() {
        return 61000;
    }

    /* access modifiers changed from: 0000 */
    public String zzLf() {
        return "google_app_measurement_local.db";
    }

    public boolean zzLg() {
        return false;
    }

    public boolean zzLh() {
        Boolean zzfp = zzfp("firebase_analytics_collection_deactivated");
        return zzfp != null && zzfp.booleanValue();
    }

    public Boolean zzLi() {
        return zzfp("firebase_analytics_collection_enabled");
    }

    public long zzLj() {
        return ((Long) zzats.zzbsr.get()).longValue();
    }

    public long zzLk() {
        return ((Long) zzats.zzbsm.get()).longValue();
    }

    public long zzLl() {
        return ((Long) zzats.zzbsn.get()).longValue();
    }

    public long zzLm() {
        return 1000;
    }

    public int zzLn() {
        return Math.max(0, ((Integer) zzats.zzbrW.get()).intValue());
    }

    public int zzLo() {
        return Math.max(1, ((Integer) zzats.zzbrX.get()).intValue());
    }

    public int zzLp() {
        return 100000;
    }

    public String zzLq() {
        return (String) zzats.zzbse.get();
    }

    public long zzLr() {
        return ((Long) zzats.zzbrR.get()).longValue();
    }

    public long zzLs() {
        return Math.max(0, ((Long) zzats.zzbsf.get()).longValue());
    }

    public long zzLt() {
        return Math.max(0, ((Long) zzats.zzbsh.get()).longValue());
    }

    public long zzLu() {
        return Math.max(0, ((Long) zzats.zzbsi.get()).longValue());
    }

    public long zzLv() {
        return Math.max(0, ((Long) zzats.zzbsj.get()).longValue());
    }

    public long zzLw() {
        return Math.max(0, ((Long) zzats.zzbsk.get()).longValue());
    }

    public long zzLx() {
        return Math.max(0, ((Long) zzats.zzbsl.get()).longValue());
    }

    public long zzLy() {
        return ((Long) zzats.zzbsg.get()).longValue();
    }

    public long zzLz() {
        return Math.max(0, ((Long) zzats.zzbso.get()).longValue());
    }

    public String zzP(String str, String str2) {
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) zzats.zzbrS.get()).encodedAuthority((String) zzats.zzbrT.get());
        String str3 = "config/app/";
        String valueOf = String.valueOf(str);
        encodedAuthority.path(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).appendQueryParameter("app_instance_id", str2).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(10240));
        return builder.build().toString();
    }

    public long zza(String str, zza<Long> zza) {
        if (str == null) {
            return ((Long) zza.get()).longValue();
        }
        String zzZ = zzKi().zzZ(str, zza.getKey());
        if (TextUtils.isEmpty(zzZ)) {
            return ((Long) zza.get()).longValue();
        }
        try {
            return ((Long) zza.get(Long.valueOf(Long.valueOf(zzZ).longValue()))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) zza.get()).longValue();
        }
    }

    public int zzb(String str, zza<Integer> zza) {
        if (str == null) {
            return ((Integer) zza.get()).intValue();
        }
        String zzZ = zzKi().zzZ(str, zza.getKey());
        if (TextUtils.isEmpty(zzZ)) {
            return ((Integer) zza.get()).intValue();
        }
        try {
            return ((Integer) zza.get(Integer.valueOf(Integer.valueOf(zzZ).intValue()))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) zza.get()).intValue();
        }
    }

    public int zzfj(@Size(min = 1) String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzats.zzbrZ)));
    }

    public int zzfk(@Size(min = 1) String str) {
        return zzb(str, zzats.zzbsb);
    }

    public int zzfl(@Size(min = 1) String str) {
        return zzb(str, zzats.zzbsc);
    }

    /* access modifiers changed from: 0000 */
    public long zzfm(String str) {
        return zza(str, zzats.zzbrP);
    }

    /* access modifiers changed from: 0000 */
    public int zzfn(String str) {
        return zzb(str, zzats.zzbss);
    }

    /* access modifiers changed from: 0000 */
    public int zzfo(String str) {
        return Math.max(0, Math.min(2000, zzb(str, zzats.zzbst)));
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Boolean zzfp(@Size(min = 1) String str) {
        zzac.zzdr(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzKl().zzLY().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = zzadg.zzbi(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzKl().zzLY().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzKl().zzLY().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (applicationInfo.metaData.containsKey(str)) {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            } else {
                return null;
            }
        } catch (NameNotFoundException e) {
            zzKl().zzLY().zzj("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public int zzfq(String str) {
        return zzb(str, zzats.zzbrU);
    }

    public int zzfr(String str) {
        return Math.max(0, zzb(str, zzats.zzbrV));
    }

    public int zzfs(String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzats.zzbsd)));
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zznR() {
        return super.zznR();
    }

    public boolean zzoW() {
        if (this.zzaeZ == null) {
            synchronized (this) {
                if (this.zzaeZ == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzzr = zzu.zzzr();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzaeZ = Boolean.valueOf(str != null && str.equals(zzzr));
                    }
                    if (this.zzaeZ == null) {
                        this.zzaeZ = Boolean.TRUE;
                        zzKl().zzLY().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzaeZ.booleanValue();
    }

    /* access modifiers changed from: 0000 */
    public long zzpq() {
        return ((Long) zzats.zzbsu.get()).longValue();
    }

    public String zzpv() {
        return "google_app_measurement.db";
    }

    public long zzpz() {
        return Math.max(0, ((Long) zzats.zzbrQ.get()).longValue());
    }

    public boolean zzwR() {
        return zzaba.zzwR();
    }
}
