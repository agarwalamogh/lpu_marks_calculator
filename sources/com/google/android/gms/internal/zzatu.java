package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.WorkerThread;
import android.support.p000v4.p002os.EnvironmentCompat;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;

public class zzatu extends zzauh {
    private String mAppId;
    private String zzVX;
    private String zzacL;
    private String zzacM;
    private long zzbqA;
    private String zzbqw;
    private int zzbsv;
    private long zzbsw;

    zzatu(zzaue zzaue) {
        super(zzaue);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: 0000 */
    public String getGmpAppId() {
        zzob();
        return this.zzVX;
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

    /* access modifiers changed from: 0000 */
    public String zzKu() {
        zzob();
        return this.zzbqw;
    }

    /* access modifiers changed from: 0000 */
    public long zzKv() {
        return zzKn().zzKv();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public long zzKw() {
        zzob();
        zzmR();
        if (this.zzbsw == 0) {
            this.zzbsw = this.zzbqc.zzKh().zzL(getContext(), getContext().getPackageName());
        }
        return this.zzbsw;
    }

    /* access modifiers changed from: 0000 */
    public int zzLX() {
        zzob();
        return this.zzbsv;
    }

    /* access modifiers changed from: protected */
    public void zzbw(Status status) {
        if (status == null) {
            zzKl().zzLY().log("GoogleService failed to initialize (no status)");
        } else {
            zzKl().zzLY().zze("GoogleService failed to initialize, status", Integer.valueOf(status.getStatusCode()), status.getStatusMessage());
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public zzatd zzfD(String str) {
        zzmR();
        return new zzatd(zzke(), getGmpAppId(), zzmZ(), (long) zzLX(), zzKu(), zzKv(), zzKw(), str, this.zzbqc.isEnabled(), !zzKm().zzbtq, zzKm().zzKq(), zzuW());
    }

    /* access modifiers changed from: 0000 */
    public String zzke() {
        zzob();
        return this.mAppId;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        boolean z;
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        String str2 = "Unknown";
        int i = Integer.MIN_VALUE;
        String str3 = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzKl().zzLY().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzatx.zzfE(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzKl().zzLY().zzj("Error retrieving app installer package name. appId", zzatx.zzfE(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e2) {
                zzKl().zzLY().zze("Error retrieving package info. appId, appName", zzatx.zzfE(packageName), str3);
            }
        }
        this.mAppId = packageName;
        this.zzbqw = str;
        this.zzacM = str2;
        this.zzbsv = i;
        this.zzacL = str3;
        this.zzbsw = 0;
        zzKn().zzLg();
        Status zzaQ = zzaba.zzaQ(getContext());
        boolean z2 = zzaQ != null && zzaQ.isSuccess();
        if (!z2) {
            zzbw(zzaQ);
        }
        if (z2) {
            Boolean zzLi = zzKn().zzLi();
            if (zzKn().zzLh()) {
                zzKl().zzMc().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (zzLi != null && !zzLi.booleanValue()) {
                zzKl().zzMc().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (zzLi != null || !zzKn().zzwR()) {
                zzKl().zzMe().log("Collection enabled");
                z = true;
            } else {
                zzKl().zzMc().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            }
        } else {
            z = false;
        }
        this.zzVX = "";
        this.zzbqA = 0;
        zzKn().zzLg();
        try {
            String zzwQ = zzaba.zzwQ();
            if (TextUtils.isEmpty(zzwQ)) {
                zzwQ = "";
            }
            this.zzVX = zzwQ;
            if (z) {
                zzKl().zzMe().zze("App package, google app id", this.mAppId, this.zzVX);
            }
        } catch (IllegalStateException e3) {
            zzKl().zzLY().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzatx.zzfE(packageName), e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public String zzmZ() {
        zzob();
        return this.zzacM;
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: 0000 */
    public long zzuW() {
        zzob();
        return 0;
    }
}
