package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzauw.zzb;
import com.google.android.gms.internal.zzauw.zzc;
import com.google.android.gms.internal.zzauw.zzd;
import com.google.android.gms.internal.zzauw.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzaue {
    private static volatile zzaue zzbtZ;
    private final Context mContext;
    private final boolean zzadP;
    private List<Long> zzbuA;
    private int zzbuB;
    private int zzbuC;
    private long zzbuD = -1;
    protected long zzbuE;
    private final zzati zzbua;
    private final zzaua zzbub;
    private final zzatx zzbuc;
    private final zzaud zzbud;
    private final zzaun zzbue;
    private final zzauc zzbuf;
    private final AppMeasurement zzbug;
    private final FirebaseAnalytics zzbuh;
    private final zzaut zzbui;
    private final zzatj zzbuj;
    private final zzatv zzbuk;
    private final zzaty zzbul;
    private final zzauk zzbum;
    private final zzaul zzbun;
    private final zzatl zzbuo;
    private final zzauj zzbup;
    private final zzatu zzbuq;
    private final zzatz zzbur;
    private final zzaup zzbus;
    private final zzatf zzbut;
    private final zzatb zzbuu;
    private boolean zzbuv;
    private Boolean zzbuw;
    private long zzbux;
    private FileLock zzbuy;
    private FileChannel zzbuz;
    private final zze zzuP;

    private class zza implements zzb {
        zzauw.zze zzbuG;
        List<Long> zzbuH;
        long zzbuI;
        List<zzb> zzth;

        private zza() {
        }

        private long zza(zzb zzb) {
            return ((zzb.zzbwZ.longValue() / 1000) / 60) / 60;
        }

        /* access modifiers changed from: 0000 */
        public boolean isEmpty() {
            return this.zzth == null || this.zzth.isEmpty();
        }

        public boolean zza(long j, zzb zzb) {
            zzac.zzw(zzb);
            if (this.zzth == null) {
                this.zzth = new ArrayList();
            }
            if (this.zzbuH == null) {
                this.zzbuH = new ArrayList();
            }
            if (this.zzth.size() > 0 && zza((zzb) this.zzth.get(0)) != zza(zzb)) {
                return false;
            }
            long zzaeT = this.zzbuI + ((long) zzb.zzaeT());
            if (zzaeT >= ((long) zzaue.this.zzKn().zzLn())) {
                return false;
            }
            this.zzbuI = zzaeT;
            this.zzth.add(zzb);
            this.zzbuH.add(Long.valueOf(j));
            return this.zzth.size() < zzaue.this.zzKn().zzLo();
        }

        public void zzb(zzauw.zze zze) {
            zzac.zzw(zze);
            this.zzbuG = zze;
        }
    }

    zzaue(zzaui zzaui) {
        zzac.zzw(zzaui);
        this.mContext = zzaui.mContext;
        this.zzuP = zzaui.zzn(this);
        this.zzbua = zzaui.zza(this);
        zzaua zzb = zzaui.zzb(this);
        zzb.initialize();
        this.zzbub = zzb;
        zzatx zzc = zzaui.zzc(this);
        zzc.initialize();
        this.zzbuc = zzc;
        zzKl().zzMc().zzj("App measurement is starting up, version", Long.valueOf(zzKn().zzKv()));
        zzKn().zzLg();
        zzKl().zzMc().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzaut zzj = zzaui.zzj(this);
        zzj.initialize();
        this.zzbui = zzj;
        zzatl zzq = zzaui.zzq(this);
        zzq.initialize();
        this.zzbuo = zzq;
        zzatu zzr = zzaui.zzr(this);
        zzr.initialize();
        this.zzbuq = zzr;
        zzKn().zzLg();
        String zzke = zzr.zzke();
        if (zzKh().zzge(zzke)) {
            zzKl().zzMc().log("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
        } else {
            com.google.android.gms.internal.zzatx.zza zzMc = zzKl().zzMc();
            String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            String valueOf = String.valueOf(zzke);
            zzMc.log(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        zzKl().zzMd().log("Debug-level message logging enabled");
        zzatj zzk = zzaui.zzk(this);
        zzk.initialize();
        this.zzbuj = zzk;
        zzatv zzl = zzaui.zzl(this);
        zzl.initialize();
        this.zzbuk = zzl;
        zzatf zzu = zzaui.zzu(this);
        zzu.initialize();
        this.zzbut = zzu;
        this.zzbuu = zzaui.zzv(this);
        zzaty zzm = zzaui.zzm(this);
        zzm.initialize();
        this.zzbul = zzm;
        zzauk zzo = zzaui.zzo(this);
        zzo.initialize();
        this.zzbum = zzo;
        zzaul zzp = zzaui.zzp(this);
        zzp.initialize();
        this.zzbun = zzp;
        zzauj zzi = zzaui.zzi(this);
        zzi.initialize();
        this.zzbup = zzi;
        zzaup zzt = zzaui.zzt(this);
        zzt.initialize();
        this.zzbus = zzt;
        this.zzbur = zzaui.zzs(this);
        this.zzbug = zzaui.zzh(this);
        this.zzbuh = zzaui.zzg(this);
        zzaun zze = zzaui.zze(this);
        zze.initialize();
        this.zzbue = zze;
        zzauc zzf = zzaui.zzf(this);
        zzf.initialize();
        this.zzbuf = zzf;
        zzaud zzd = zzaui.zzd(this);
        zzd.initialize();
        this.zzbud = zzd;
        if (this.zzbuB != this.zzbuC) {
            zzKl().zzLY().zze("Not all components initialized", Integer.valueOf(this.zzbuB), Integer.valueOf(this.zzbuC));
        }
        this.zzadP = true;
        this.zzbua.zzLg();
        if (this.mContext.getApplicationContext() instanceof Application) {
            int i = VERSION.SDK_INT;
            zzKa().zzMQ();
        } else {
            zzKl().zzMa().log("Application context is not an Application");
        }
        this.zzbud.zzm(new Runnable() {
            public void run() {
                zzaue.this.start();
            }
        });
    }

    private boolean zzMH() {
        zzmR();
        zzob();
        return zzKg().zzLJ() || !TextUtils.isEmpty(zzKg().zzLD());
    }

    @WorkerThread
    private void zzMI() {
        zzmR();
        zzob();
        if (zzMM()) {
            if (this.zzbuE > 0) {
                long abs = 3600000 - Math.abs(zznR().elapsedRealtime() - this.zzbuE);
                if (abs > 0) {
                    zzKl().zzMe().zzj("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzMz().unregister();
                    zzMA().cancel();
                    return;
                }
                this.zzbuE = 0;
            }
            if (!zzMt() || !zzMH()) {
                zzMz().unregister();
                zzMA().cancel();
                return;
            }
            long zzMJ = zzMJ();
            if (zzMJ == 0) {
                zzMz().unregister();
                zzMA().cancel();
            } else if (!zzMy().zzqa()) {
                zzMz().zzpX();
                zzMA().cancel();
            } else {
                long j = zzKm().zzbtb.get();
                long zzLs = zzKn().zzLs();
                if (!zzKh().zzh(j, zzLs)) {
                    zzMJ = Math.max(zzMJ, j + zzLs);
                }
                zzMz().unregister();
                long currentTimeMillis = zzMJ - zznR().currentTimeMillis();
                if (currentTimeMillis <= 0) {
                    currentTimeMillis = zzKn().zzLw();
                    zzKm().zzbsZ.set(zznR().currentTimeMillis());
                }
                zzKl().zzMe().zzj("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis));
                zzMA().zzy(currentTimeMillis);
            }
        }
    }

    private long zzMJ() {
        long zzLt;
        long currentTimeMillis = zznR().currentTimeMillis();
        long zzLz = zzKn().zzLz();
        boolean z = zzKg().zzLK() || zzKg().zzLE();
        if (z) {
            String zzLC = zzKn().zzLC();
            zzLt = (TextUtils.isEmpty(zzLC) || ".none.".equals(zzLC)) ? zzKn().zzLu() : zzKn().zzLv();
        } else {
            zzLt = zzKn().zzLt();
        }
        long j = zzKm().zzbsZ.get();
        long j2 = zzKm().zzbta.get();
        long max = Math.max(zzKg().zzLH(), zzKg().zzLI());
        if (max == 0) {
            return 0;
        }
        long abs = currentTimeMillis - Math.abs(max - currentTimeMillis);
        long abs2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        long max2 = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), abs2);
        long j3 = abs + zzLz;
        if (z && max2 > 0) {
            j3 = Math.min(abs, max2) + zzLt;
        }
        if (!zzKh().zzh(max2, zzLt)) {
            j3 = max2 + zzLt;
        }
        if (abs2 == 0 || abs2 < abs) {
            return j3;
        }
        for (int i = 0; i < zzKn().zzLB(); i++) {
            j3 += ((long) (1 << i)) * zzKn().zzLA();
            if (j3 > abs2) {
                return j3;
            }
        }
        return 0;
    }

    private void zza(zzaug zzaug) {
        if (zzaug == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private void zza(zzauh zzauh) {
        if (zzauh == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzauh.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private boolean zza(zzatm zzatm) {
        if (zzatm.zzbrz == null) {
            return false;
        }
        Iterator it = zzatm.zzbrz.iterator();
        while (it.hasNext()) {
            if ("_r".equals((String) it.next())) {
                return true;
            }
        }
        return zzKi().zzab(zzatm.mAppId, zzatm.mName) && zzKg().zza(zzME(), zzatm.mAppId, false, false, false, false, false).zzbrr < ((long) zzKn().zzfl(zzatm.mAppId));
    }

    private com.google.android.gms.internal.zzauw.zza[] zza(String str, zzg[] zzgArr, zzb[] zzbArr) {
        zzac.zzdr(str);
        return zzJZ().zza(str, zzbArr, zzgArr);
    }

    public static zzaue zzbM(Context context) {
        zzac.zzw(context);
        zzac.zzw(context.getApplicationContext());
        if (zzbtZ == null) {
            synchronized (zzaue.class) {
                if (zzbtZ == null) {
                    zzbtZ = new zzaui(context).zzMP();
                }
            }
        }
        return zzbtZ;
    }

    @WorkerThread
    private void zzf(zzatd zzatd) {
        boolean z = true;
        zzmR();
        zzob();
        zzac.zzw(zzatd);
        zzac.zzdr(zzatd.packageName);
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        String zzfH = zzKm().zzfH(zzatd.packageName);
        boolean z2 = false;
        if (zzfu == null) {
            zzatc zzatc = new zzatc(this, zzatd.packageName);
            zzatc.zzfd(zzKm().zzMh());
            zzatc.zzff(zzfH);
            zzfu = zzatc;
            z2 = true;
        } else if (!zzfH.equals(zzfu.zzKp())) {
            zzfu.zzff(zzfH);
            zzfu.zzfd(zzKm().zzMh());
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzatd.zzbqL) && !zzatd.zzbqL.equals(zzfu.getGmpAppId())) {
            zzfu.zzfe(zzatd.zzbqL);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzatd.zzbqT) && !zzatd.zzbqT.equals(zzfu.zzKq())) {
            zzfu.zzfg(zzatd.zzbqT);
            z2 = true;
        }
        if (!(zzatd.zzbqN == 0 || zzatd.zzbqN == zzfu.zzKv())) {
            zzfu.zzab(zzatd.zzbqN);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzatd.zzbhN) && !zzatd.zzbhN.equals(zzfu.zzmZ())) {
            zzfu.setAppVersion(zzatd.zzbhN);
            z2 = true;
        }
        if (zzatd.zzbqS != zzfu.zzKt()) {
            zzfu.zzaa(zzatd.zzbqS);
            z2 = true;
        }
        if (zzatd.zzbqM != null && !zzatd.zzbqM.equals(zzfu.zzKu())) {
            zzfu.zzfh(zzatd.zzbqM);
            z2 = true;
        }
        if (zzatd.zzbqO != zzfu.zzKw()) {
            zzfu.zzac(zzatd.zzbqO);
            z2 = true;
        }
        if (zzatd.zzbqQ != zzfu.zzKx()) {
            zzfu.setMeasurementEnabled(zzatd.zzbqQ);
            z2 = true;
        }
        if (!TextUtils.isEmpty(zzatd.zzbqP) && !zzatd.zzbqP.equals(zzfu.zzKI())) {
            zzfu.zzfi(zzatd.zzbqP);
            z2 = true;
        }
        if (zzatd.zzbqU != zzfu.zzuW()) {
            zzfu.zzam(zzatd.zzbqU);
        } else {
            z = z2;
        }
        if (z) {
            zzKg().zza(zzfu);
        }
    }

    private boolean zzl(String str, long j) {
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        boolean z4;
        zzKg().beginTransaction();
        try {
            zza zza2 = new zza();
            zzKg().zza(str, j, this.zzbuD, zza2);
            if (!zza2.isEmpty()) {
                boolean z5 = false;
                zzauw.zze zze = zza2.zzbuG;
                zze.zzbxg = new zzb[zza2.zzth.size()];
                int i3 = 0;
                int i4 = 0;
                while (i4 < zza2.zzth.size()) {
                    if (zzKi().zzaa(zza2.zzbuG.zzaS, ((zzb) zza2.zzth.get(i4)).name)) {
                        zzKl().zzMa().zze("Dropping blacklisted raw event. appId", zzatx.zzfE(zza2.zzbuG.zzaS), ((zzb) zza2.zzth.get(i4)).name);
                        if ((zzKh().zzgg(zza2.zzbuG.zzaS) || zzKh().zzgh(zza2.zzbuG.zzaS)) || "_err".equals(((zzb) zza2.zzth.get(i4)).name)) {
                            i = i3;
                            z2 = z5;
                        } else {
                            zzKh().zza(11, "_ev", ((zzb) zza2.zzth.get(i4)).name, 0);
                            i = i3;
                            z2 = z5;
                        }
                    } else {
                        boolean zzab = zzKi().zzab(zza2.zzbuG.zzaS, ((zzb) zza2.zzth.get(i4)).name);
                        if (zzab || zzKh().zzgi(((zzb) zza2.zzth.get(i4)).name)) {
                            boolean z6 = false;
                            boolean z7 = false;
                            if (((zzb) zza2.zzth.get(i4)).zzbwY == null) {
                                ((zzb) zza2.zzth.get(i4)).zzbwY = new zzc[0];
                            }
                            zzc[] zzcArr = ((zzb) zza2.zzth.get(i4)).zzbwY;
                            int length = zzcArr.length;
                            int i5 = 0;
                            while (i5 < length) {
                                zzc zzc = zzcArr[i5];
                                if ("_c".equals(zzc.name)) {
                                    zzc.zzbxc = Long.valueOf(1);
                                    z6 = true;
                                    z4 = z7;
                                } else if ("_r".equals(zzc.name)) {
                                    zzc.zzbxc = Long.valueOf(1);
                                    z4 = true;
                                } else {
                                    z4 = z7;
                                }
                                i5++;
                                z7 = z4;
                            }
                            if (!z6 && zzab) {
                                zzKl().zzMe().zzj("Marking event as conversion", ((zzb) zza2.zzth.get(i4)).name);
                                zzc[] zzcArr2 = (zzc[]) Arrays.copyOf(((zzb) zza2.zzth.get(i4)).zzbwY, ((zzb) zza2.zzth.get(i4)).zzbwY.length + 1);
                                zzc zzc2 = new zzc();
                                zzc2.name = "_c";
                                zzc2.zzbxc = Long.valueOf(1);
                                zzcArr2[zzcArr2.length - 1] = zzc2;
                                ((zzb) zza2.zzth.get(i4)).zzbwY = zzcArr2;
                            }
                            if (!z7) {
                                zzKl().zzMe().zzj("Marking event as real-time", ((zzb) zza2.zzth.get(i4)).name);
                                zzc[] zzcArr3 = (zzc[]) Arrays.copyOf(((zzb) zza2.zzth.get(i4)).zzbwY, ((zzb) zza2.zzth.get(i4)).zzbwY.length + 1);
                                zzc zzc3 = new zzc();
                                zzc3.name = "_r";
                                zzc3.zzbxc = Long.valueOf(1);
                                zzcArr3[zzcArr3.length - 1] = zzc3;
                                ((zzb) zza2.zzth.get(i4)).zzbwY = zzcArr3;
                            }
                            boolean z8 = true;
                            if (zzKg().zza(zzME(), zza2.zzbuG.zzaS, false, false, false, false, true).zzbrr > ((long) zzKn().zzfl(zza2.zzbuG.zzaS))) {
                                zzb zzb = (zzb) zza2.zzth.get(i4);
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= zzb.zzbwY.length) {
                                        break;
                                    } else if ("_r".equals(zzb.zzbwY[i6].name)) {
                                        zzc[] zzcArr4 = new zzc[(zzb.zzbwY.length - 1)];
                                        if (i6 > 0) {
                                            System.arraycopy(zzb.zzbwY, 0, zzcArr4, 0, i6);
                                        }
                                        if (i6 < zzcArr4.length) {
                                            System.arraycopy(zzb.zzbwY, i6 + 1, zzcArr4, i6, zzcArr4.length - i6);
                                        }
                                        zzb.zzbwY = zzcArr4;
                                    } else {
                                        i6++;
                                    }
                                }
                                z8 = z5;
                            }
                            if (zzaut.zzfT(((zzb) zza2.zzth.get(i4)).name) && zzab && zzKg().zza(zzME(), zza2.zzbuG.zzaS, false, false, true, false, false).zzbrp > ((long) zzKn().zzfk(zza2.zzbuG.zzaS))) {
                                zzKl().zzMa().zzj("Too many conversions. Not logging as conversion. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                                zzb zzb2 = (zzb) zza2.zzth.get(i4);
                                boolean z9 = false;
                                zzc zzc4 = null;
                                zzc[] zzcArr5 = zzb2.zzbwY;
                                int length2 = zzcArr5.length;
                                int i7 = 0;
                                while (i7 < length2) {
                                    zzc zzc5 = zzcArr5[i7];
                                    if ("_c".equals(zzc5.name)) {
                                        z3 = z9;
                                    } else if ("_err".equals(zzc5.name)) {
                                        zzc zzc6 = zzc4;
                                        z3 = true;
                                        zzc5 = zzc6;
                                    } else {
                                        zzc5 = zzc4;
                                        z3 = z9;
                                    }
                                    i7++;
                                    z9 = z3;
                                    zzc4 = zzc5;
                                }
                                if (z9 && zzc4 != null) {
                                    zzc[] zzcArr6 = new zzc[(zzb2.zzbwY.length - 1)];
                                    int i8 = 0;
                                    zzc[] zzcArr7 = zzb2.zzbwY;
                                    int length3 = zzcArr7.length;
                                    int i9 = 0;
                                    while (i9 < length3) {
                                        zzc zzc7 = zzcArr7[i9];
                                        if (zzc7 != zzc4) {
                                            i2 = i8 + 1;
                                            zzcArr6[i8] = zzc7;
                                        } else {
                                            i2 = i8;
                                        }
                                        i9++;
                                        i8 = i2;
                                    }
                                    zzb2.zzbwY = zzcArr6;
                                    z = z8;
                                } else if (zzc4 != null) {
                                    zzc4.name = "_err";
                                    zzc4.zzbxc = Long.valueOf(10);
                                    z = z8;
                                } else {
                                    zzKl().zzLY().zzj("Did not find conversion parameter. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                                }
                            }
                            z = z8;
                        } else {
                            z = z5;
                        }
                        int i10 = i3 + 1;
                        zze.zzbxg[i3] = (zzb) zza2.zzth.get(i4);
                        i = i10;
                        z2 = z;
                    }
                    i4++;
                    i3 = i;
                    z5 = z2;
                }
                if (i3 < zza2.zzth.size()) {
                    zze.zzbxg = (zzb[]) Arrays.copyOf(zze.zzbxg, i3);
                }
                zze.zzbxz = zza(zza2.zzbuG.zzaS, zza2.zzbuG.zzbxh, zze.zzbxg);
                zze.zzbxj = Long.valueOf(Long.MAX_VALUE);
                zze.zzbxk = Long.valueOf(Long.MIN_VALUE);
                for (zzb zzb3 : zze.zzbxg) {
                    if (zzb3.zzbwZ.longValue() < zze.zzbxj.longValue()) {
                        zze.zzbxj = zzb3.zzbwZ;
                    }
                    if (zzb3.zzbwZ.longValue() > zze.zzbxk.longValue()) {
                        zze.zzbxk = zzb3.zzbwZ;
                    }
                }
                String str2 = zza2.zzbuG.zzaS;
                zzatc zzfu = zzKg().zzfu(str2);
                if (zzfu == null) {
                    zzKl().zzLY().zzj("Bundling raw events w/o app info. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                } else if (zze.zzbxg.length > 0) {
                    long zzKs = zzfu.zzKs();
                    zze.zzbxm = zzKs != 0 ? Long.valueOf(zzKs) : null;
                    long zzKr = zzfu.zzKr();
                    if (zzKr != 0) {
                        zzKs = zzKr;
                    }
                    zze.zzbxl = zzKs != 0 ? Long.valueOf(zzKs) : null;
                    zzfu.zzKB();
                    zze.zzbxx = Integer.valueOf((int) zzfu.zzKy());
                    zzfu.zzY(zze.zzbxj.longValue());
                    zzfu.zzZ(zze.zzbxk.longValue());
                    zze.zzbqP = zzfu.zzKJ();
                    zzKg().zza(zzfu);
                }
                if (zze.zzbxg.length > 0) {
                    zzKn().zzLg();
                    zzauv.zzb zzfL = zzKi().zzfL(zza2.zzbuG.zzaS);
                    if (zzfL != null && zzfL.zzbwN != null) {
                        zze.zzbxE = zzfL.zzbwN;
                    } else if (TextUtils.isEmpty(zza2.zzbuG.zzbqL)) {
                        zze.zzbxE = Long.valueOf(-1);
                    } else {
                        zzKl().zzMa().zzj("Did not find measurement config or missing version info. appId", zzatx.zzfE(zza2.zzbuG.zzaS));
                    }
                    zzKg().zza(zze, z5);
                }
                zzKg().zzJ(zza2.zzbuH);
                zzKg().zzfB(str2);
                zzKg().setTransactionSuccessful();
                return zze.zzbxg.length > 0;
            }
            zzKg().setTransactionSuccessful();
            zzKg().endTransaction();
            return false;
        } finally {
            zzKg().endTransaction();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public boolean isEnabled() {
        boolean z = false;
        zzmR();
        zzob();
        if (zzKn().zzLh()) {
            return false;
        }
        Boolean zzLi = zzKn().zzLi();
        if (zzLi != null) {
            z = zzLi.booleanValue();
        } else if (!zzKn().zzwR()) {
            z = true;
        }
        return zzKm().zzaL(z);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void start() {
        zzmR();
        zzKg().zzLF();
        if (zzKm().zzbsZ.get() == 0) {
            zzKm().zzbsZ.set(zznR().currentTimeMillis());
        }
        if (zzMt()) {
            zzKn().zzLg();
            if (!TextUtils.isEmpty(zzKb().getGmpAppId())) {
                String zzMk = zzKm().zzMk();
                if (zzMk == null) {
                    zzKm().zzfI(zzKb().getGmpAppId());
                } else if (!zzMk.equals(zzKb().getGmpAppId())) {
                    zzKl().zzMc().log("Rechecking which service to use due to a GMP App Id change");
                    zzKm().zzMn();
                    this.zzbun.disconnect();
                    this.zzbun.zzoD();
                    zzKm().zzfI(zzKb().getGmpAppId());
                }
            }
            zzKn().zzLg();
            if (!TextUtils.isEmpty(zzKb().getGmpAppId())) {
                zzKa().zzMR();
            }
        } else if (isEnabled()) {
            if (!zzKh().zzbW("android.permission.INTERNET")) {
                zzKl().zzLY().log("App is missing INTERNET permission");
            }
            if (!zzKh().zzbW("android.permission.ACCESS_NETWORK_STATE")) {
                zzKl().zzLY().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzKn().zzLg();
            if (!zzadg.zzbi(getContext()).zzzx()) {
                if (!zzaub.zzi(getContext(), false)) {
                    zzKl().zzLY().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzaum.zzj(getContext(), false)) {
                    zzKl().zzLY().log("AppMeasurementService not registered/enabled");
                }
            }
            zzKl().zzLY().log("Uploading is not possible. App measurement disabled");
        }
        zzMI();
    }

    /* access modifiers changed from: 0000 */
    public void zzJV() {
        zzKn().zzLg();
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: 0000 */
    public void zzJW() {
        zzKn().zzLg();
    }

    public zzatb zzJY() {
        zza((zzaug) this.zzbuu);
        return this.zzbuu;
    }

    public zzatf zzJZ() {
        zza((zzauh) this.zzbut);
        return this.zzbut;
    }

    /* access modifiers changed from: protected */
    public void zzK(List<Long> list) {
        zzac.zzax(!list.isEmpty());
        if (this.zzbuA != null) {
            zzKl().zzLY().log("Set uploading progress before finishing the previous upload");
        } else {
            this.zzbuA = new ArrayList(list);
        }
    }

    public zzauj zzKa() {
        zza((zzauh) this.zzbup);
        return this.zzbup;
    }

    public zzatu zzKb() {
        zza((zzauh) this.zzbuq);
        return this.zzbuq;
    }

    public zzatl zzKc() {
        zza((zzauh) this.zzbuo);
        return this.zzbuo;
    }

    public zzaul zzKd() {
        zza((zzauh) this.zzbun);
        return this.zzbun;
    }

    public zzauk zzKe() {
        zza((zzauh) this.zzbum);
        return this.zzbum;
    }

    public zzatv zzKf() {
        zza((zzauh) this.zzbuk);
        return this.zzbuk;
    }

    public zzatj zzKg() {
        zza((zzauh) this.zzbuj);
        return this.zzbuj;
    }

    public zzaut zzKh() {
        zza((zzaug) this.zzbui);
        return this.zzbui;
    }

    public zzauc zzKi() {
        zza((zzauh) this.zzbuf);
        return this.zzbuf;
    }

    public zzaun zzKj() {
        zza((zzauh) this.zzbue);
        return this.zzbue;
    }

    public zzaud zzKk() {
        zza((zzauh) this.zzbud);
        return this.zzbud;
    }

    public zzatx zzKl() {
        zza((zzauh) this.zzbuc);
        return this.zzbuc;
    }

    public zzaua zzKm() {
        zza((zzaug) this.zzbub);
        return this.zzbub;
    }

    public zzati zzKn() {
        return this.zzbua;
    }

    public zzaup zzMA() {
        zza((zzauh) this.zzbus);
        return this.zzbus;
    }

    /* access modifiers changed from: 0000 */
    public FileChannel zzMB() {
        return this.zzbuz;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzMC() {
        zzmR();
        zzob();
        if (zzMM() && zzMD()) {
            zzy(zza(zzMB()), zzKb().zzLX());
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zzMD() {
        zzmR();
        try {
            this.zzbuz = new RandomAccessFile(new File(getContext().getFilesDir(), this.zzbuj.zzow()), "rw").getChannel();
            this.zzbuy = this.zzbuz.tryLock();
            if (this.zzbuy != null) {
                zzKl().zzMe().log("Storage concurrent access okay");
                return true;
            }
            zzKl().zzLY().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzKl().zzLY().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzKl().zzLY().zzj("Failed to access storage lock file", e2);
        }
    }

    /* access modifiers changed from: 0000 */
    public long zzME() {
        return ((((zznR().currentTimeMillis() + zzKm().zzMi()) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMF() {
        zzmR();
        return this.zzbuA != null;
    }

    @WorkerThread
    public void zzMG() {
        Object obj;
        List list;
        zzmR();
        zzob();
        zzKn().zzLg();
        Boolean zzMm = zzKm().zzMm();
        if (zzMm == null) {
            zzKl().zzMa().log("Upload data called on the client side before use of service was decided");
        } else if (zzMm.booleanValue()) {
            zzKl().zzLY().log("Upload called in the client side when service should be used");
        } else if (this.zzbuE > 0) {
            zzMI();
        } else if (zzMF()) {
            zzKl().zzMa().log("Uploading requested multiple times");
        } else if (!zzMy().zzqa()) {
            zzKl().zzMa().log("Network not connected, ignoring upload request");
            zzMI();
        } else {
            long currentTimeMillis = zznR().currentTimeMillis();
            zzaq(currentTimeMillis - zzKn().zzLr());
            long j = zzKm().zzbsZ.get();
            if (j != 0) {
                zzKl().zzMd().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzLD = zzKg().zzLD();
            if (!TextUtils.isEmpty(zzLD)) {
                if (this.zzbuD == -1) {
                    this.zzbuD = zzKg().zzLL();
                }
                List zzn = zzKg().zzn(zzLD, zzKn().zzfq(zzLD), zzKn().zzfr(zzLD));
                if (!zzn.isEmpty()) {
                    Iterator it = zzn.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        zzauw.zze zze = (zzauw.zze) ((Pair) it.next()).first;
                        if (!TextUtils.isEmpty(zze.zzbxt)) {
                            obj = zze.zzbxt;
                            break;
                        }
                    }
                    if (obj != null) {
                        int i = 0;
                        while (true) {
                            if (i >= zzn.size()) {
                                break;
                            }
                            zzauw.zze zze2 = (zzauw.zze) ((Pair) zzn.get(i)).first;
                            if (!TextUtils.isEmpty(zze2.zzbxt) && !zze2.zzbxt.equals(obj)) {
                                list = zzn.subList(0, i);
                                break;
                            }
                            i++;
                        }
                    }
                    list = zzn;
                    zzd zzd = new zzd();
                    zzd.zzbxd = new zzauw.zze[list.size()];
                    ArrayList arrayList = new ArrayList(list.size());
                    for (int i2 = 0; i2 < zzd.zzbxd.length; i2++) {
                        zzd.zzbxd[i2] = (zzauw.zze) ((Pair) list.get(i2)).first;
                        arrayList.add((Long) ((Pair) list.get(i2)).second);
                        zzd.zzbxd[i2].zzbxs = Long.valueOf(zzKn().zzKv());
                        zzd.zzbxd[i2].zzbxi = Long.valueOf(currentTimeMillis);
                        zzd.zzbxd[i2].zzbxy = Boolean.valueOf(zzKn().zzLg());
                    }
                    String str = zzKl().zzak(2) ? zzaut.zzb(zzd) : null;
                    byte[] zza2 = zzKh().zza(zzd);
                    String zzLq = zzKn().zzLq();
                    try {
                        URL url = new URL(zzLq);
                        zzK(arrayList);
                        zzKm().zzbta.set(currentTimeMillis);
                        String str2 = "?";
                        if (zzd.zzbxd.length > 0) {
                            str2 = zzd.zzbxd[0].zzaS;
                        }
                        zzKl().zzMe().zzd("Uploading data. app, uncompressed size, data", str2, Integer.valueOf(zza2.length), str);
                        zzMy().zza(zzLD, url, zza2, null, new zza() {
                            public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                                zzaue.this.zza(i, th, bArr);
                            }
                        });
                    } catch (MalformedURLException e) {
                        zzKl().zzLY().zze("Failed to parse upload URL. Not uploading. appId", zzatx.zzfE(zzLD), zzLq);
                    }
                }
            } else {
                this.zzbuD = -1;
                String zzao = zzKg().zzao(currentTimeMillis - zzKn().zzLr());
                if (!TextUtils.isEmpty(zzao)) {
                    zzatc zzfu = zzKg().zzfu(zzao);
                    if (zzfu != null) {
                        zzb(zzfu);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzMK() {
        this.zzbuC++;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzML() {
        zzmR();
        zzob();
        if (!this.zzbuv) {
            zzKl().zzMc().log("This instance being marked as an uploader");
            zzMC();
        }
        this.zzbuv = true;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zzMM() {
        zzmR();
        zzob();
        return this.zzbuv;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMt() {
        boolean z = false;
        zzob();
        zzmR();
        if (this.zzbuw == null || this.zzbux == 0 || (this.zzbuw != null && !this.zzbuw.booleanValue() && Math.abs(zznR().elapsedRealtime() - this.zzbux) > 1000)) {
            this.zzbux = zznR().elapsedRealtime();
            zzKn().zzLg();
            if (zzKh().zzbW("android.permission.INTERNET") && zzKh().zzbW("android.permission.ACCESS_NETWORK_STATE") && (zzadg.zzbi(getContext()).zzzx() || (zzaub.zzi(getContext(), false) && zzaum.zzj(getContext(), false)))) {
                z = true;
            }
            this.zzbuw = Boolean.valueOf(z);
            if (this.zzbuw.booleanValue()) {
                this.zzbuw = Boolean.valueOf(zzKh().zzga(zzKb().getGmpAppId()));
            }
        }
        return this.zzbuw.booleanValue();
    }

    public zzatx zzMu() {
        if (this.zzbuc == null || !this.zzbuc.isInitialized()) {
            return null;
        }
        return this.zzbuc;
    }

    /* access modifiers changed from: 0000 */
    public zzaud zzMv() {
        return this.zzbud;
    }

    public AppMeasurement zzMw() {
        return this.zzbug;
    }

    public FirebaseAnalytics zzMx() {
        return this.zzbuh;
    }

    public zzaty zzMy() {
        zza((zzauh) this.zzbul);
        return this.zzbul;
    }

    public zzatz zzMz() {
        if (this.zzbur != null) {
            return this.zzbur;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public void zzW(boolean z) {
        zzMI();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public int zza(FileChannel fileChannel) {
        int i = 0;
        zzmR();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzKl().zzLY().log("Bad chanel to read from");
            return i;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            } else if (read == -1) {
                return i;
            } else {
                zzKl().zzMa().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                return i;
            }
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to read from channel", e);
            return i;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(int i, Throwable th, byte[] bArr) {
        boolean z = false;
        zzmR();
        zzob();
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.zzbuA;
        this.zzbuA = null;
        if ((i == 200 || i == 204) && th == null) {
            try {
                zzKm().zzbsZ.set(zznR().currentTimeMillis());
                zzKm().zzbta.set(0);
                zzMI();
                zzKl().zzMe().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzKg().beginTransaction();
                for (Long longValue : list) {
                    zzKg().zzan(longValue.longValue());
                }
                zzKg().setTransactionSuccessful();
                zzKg().endTransaction();
                if (!zzMy().zzqa() || !zzMH()) {
                    this.zzbuD = -1;
                    zzMI();
                } else {
                    zzMG();
                }
                this.zzbuE = 0;
            } catch (SQLiteException e) {
                zzKl().zzLY().zzj("Database error while trying to delete uploaded bundles", e);
                this.zzbuE = zznR().elapsedRealtime();
                zzKl().zzMe().zzj("Disable upload, time", Long.valueOf(this.zzbuE));
            } catch (Throwable th2) {
                zzKg().endTransaction();
                throw th2;
            }
        } else {
            zzKl().zzMe().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzKm().zzbta.set(zznR().currentTimeMillis());
            if (i == 503 || i == 429) {
                z = true;
            }
            if (z) {
                zzKm().zzbtb.set(zznR().currentTimeMillis());
            }
            zzMI();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zza(zzatd zzatd, long j) {
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (!(zzfu == null || zzfu.getGmpAppId() == null || zzfu.getGmpAppId().equals(zzatd.zzbqL))) {
            zzKl().zzMa().zzj("New GMP App Id passed in. Removing cached database data. appId", zzatx.zzfE(zzfu.zzke()));
            zzKg().zzfz(zzfu.zzke());
            zzfu = null;
        }
        if (zzfu != null && zzfu.zzmZ() != null && !zzfu.zzmZ().equals(zzatd.zzbhN)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzfu.zzmZ());
            zzb(new zzatq("_au", new zzato(bundle), "auto", j), zzatd);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzatm zzatm, zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzw(zzatm);
        zzac.zzw(zzatd);
        zzac.zzdr(zzatm.mAppId);
        zzac.zzax(zzatm.mAppId.equals(zzatd.packageName));
        zzauw.zze zze = new zzauw.zze();
        zze.zzbxf = Integer.valueOf(1);
        zze.zzbxn = "android";
        zze.zzaS = zzatd.packageName;
        zze.zzbqM = zzatd.zzbqM;
        zze.zzbhN = zzatd.zzbhN;
        zze.zzbxA = Integer.valueOf((int) zzatd.zzbqS);
        zze.zzbxr = Long.valueOf(zzatd.zzbqN);
        zze.zzbqL = zzatd.zzbqL;
        zze.zzbxw = zzatd.zzbqO == 0 ? null : Long.valueOf(zzatd.zzbqO);
        Pair zzfG = zzKm().zzfG(zzatd.packageName);
        if (!TextUtils.isEmpty((CharSequence) zzfG.first)) {
            zze.zzbxt = (String) zzfG.first;
            zze.zzbxu = (Boolean) zzfG.second;
        } else if (!zzKc().zzbL(this.mContext)) {
            String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzKl().zzMa().zzj("null secure ID. appId", zzatx.zzfE(zze.zzaS));
                string = "null";
            } else if (string.isEmpty()) {
                zzKl().zzMa().zzj("empty secure ID. appId", zzatx.zzfE(zze.zzaS));
            }
            zze.zzbxD = string;
        }
        zze.zzbxo = zzKc().zzkN();
        zze.zzbb = zzKc().zzLS();
        zze.zzbxq = Integer.valueOf((int) zzKc().zzLT());
        zze.zzbxp = zzKc().zzLU();
        zze.zzbxs = null;
        zze.zzbxi = null;
        zze.zzbxj = null;
        zze.zzbxk = null;
        zze.zzbxF = Long.valueOf(zzatd.zzbqU);
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (zzfu == null) {
            zzfu = new zzatc(this, zzatd.packageName);
            zzfu.zzfd(zzKm().zzMh());
            zzfu.zzfg(zzatd.zzbqT);
            zzfu.zzfe(zzatd.zzbqL);
            zzfu.zzff(zzKm().zzfH(zzatd.packageName));
            zzfu.zzad(0);
            zzfu.zzY(0);
            zzfu.zzZ(0);
            zzfu.setAppVersion(zzatd.zzbhN);
            zzfu.zzaa(zzatd.zzbqS);
            zzfu.zzfh(zzatd.zzbqM);
            zzfu.zzab(zzatd.zzbqN);
            zzfu.zzac(zzatd.zzbqO);
            zzfu.setMeasurementEnabled(zzatd.zzbqQ);
            zzfu.zzam(zzatd.zzbqU);
            zzKg().zza(zzfu);
        }
        zze.zzbxv = zzfu.getAppInstanceId();
        zze.zzbqT = zzfu.zzKq();
        List zzft = zzKg().zzft(zzatd.packageName);
        zze.zzbxh = new zzg[zzft.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < zzft.size()) {
                zzg zzg = new zzg();
                zze.zzbxh[i2] = zzg;
                zzg.name = ((zzaus) zzft.get(i2)).mName;
                zzg.zzbxJ = Long.valueOf(((zzaus) zzft.get(i2)).zzbwg);
                zzKh().zza(zzg, ((zzaus) zzft.get(i2)).mValue);
                i = i2 + 1;
            } else {
                try {
                    break;
                } catch (IOException e) {
                    zzKl().zzLY().zze("Data loss. Failed to insert raw event metadata. appId", zzatx.zzfE(zze.zzaS), e);
                    return;
                }
            }
        }
        if (zzKg().zza(zzatm, zzKg().zza(zze), zza(zzatm))) {
            this.zzbuE = 0;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zza(int i, FileChannel fileChannel) {
        zzmR();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzKl().zzLY().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzKl().zzLY().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull zzatq zzatq, @Size(min = 1) String str) {
        long j;
        zzob();
        zzmR();
        zzJV();
        zzac.zzw(zzatq);
        zzac.zzdr(str);
        zzd zzd = new zzd();
        zzKg().beginTransaction();
        try {
            zzatc zzfu = zzKg().zzfu(str);
            if (zzfu == null) {
                zzKl().zzMd().zzj("Log and bundle not available. package_name", str);
                return new byte[0];
            } else if (!zzfu.zzKx()) {
                zzKl().zzMd().zzj("Log and bundle disabled. package_name", str);
                byte[] bArr = new byte[0];
                zzKg().endTransaction();
                return bArr;
            } else {
                zzauw.zze zze = new zzauw.zze();
                zzd.zzbxd = new zzauw.zze[]{zze};
                zze.zzbxf = Integer.valueOf(1);
                zze.zzbxn = "android";
                zze.zzaS = zzfu.zzke();
                zze.zzbqM = zzfu.zzKu();
                zze.zzbhN = zzfu.zzmZ();
                zze.zzbxA = Integer.valueOf((int) zzfu.zzKt());
                zze.zzbxr = Long.valueOf(zzfu.zzKv());
                zze.zzbqL = zzfu.getGmpAppId();
                zze.zzbxw = Long.valueOf(zzfu.zzKw());
                Pair zzfG = zzKm().zzfG(zzfu.zzke());
                if (!TextUtils.isEmpty((CharSequence) zzfG.first)) {
                    zze.zzbxt = (String) zzfG.first;
                    zze.zzbxu = (Boolean) zzfG.second;
                }
                zze.zzbxo = zzKc().zzkN();
                zze.zzbb = zzKc().zzLS();
                zze.zzbxq = Integer.valueOf((int) zzKc().zzLT());
                zze.zzbxp = zzKc().zzLU();
                zze.zzbxv = zzfu.getAppInstanceId();
                zze.zzbqT = zzfu.zzKq();
                List zzft = zzKg().zzft(zzfu.zzke());
                zze.zzbxh = new zzg[zzft.size()];
                for (int i = 0; i < zzft.size(); i++) {
                    zzg zzg = new zzg();
                    zze.zzbxh[i] = zzg;
                    zzg.name = ((zzaus) zzft.get(i)).mName;
                    zzg.zzbxJ = Long.valueOf(((zzaus) zzft.get(i)).zzbwg);
                    zzKh().zza(zzg, ((zzaus) zzft.get(i)).mValue);
                }
                Bundle zzLW = zzatq.zzbrG.zzLW();
                if ("_iap".equals(zzatq.name)) {
                    zzLW.putLong("_c", 1);
                    zzKl().zzMd().log("Marking in-app purchase as real-time");
                    zzLW.putLong("_r", 1);
                }
                zzLW.putString("_o", zzatq.zzbqV);
                if (zzKh().zzge(zze.zzaS)) {
                    zzKh().zza(zzLW, "_dbg", (Object) Long.valueOf(1));
                    zzKh().zza(zzLW, "_r", (Object) Long.valueOf(1));
                }
                zzatn zzQ = zzKg().zzQ(str, zzatq.name);
                if (zzQ == null) {
                    zzKg().zza(new zzatn(str, zzatq.name, 1, 0, zzatq.zzbrH));
                    j = 0;
                } else {
                    j = zzQ.zzbrC;
                    zzKg().zza(zzQ.zzap(zzatq.zzbrH).zzLV());
                }
                zzatm zzatm = new zzatm(this, zzatq.zzbqV, str, zzatq.name, zzatq.zzbrH, j, zzLW);
                zzb zzb = new zzb();
                zze.zzbxg = new zzb[]{zzb};
                zzb.zzbwZ = Long.valueOf(zzatm.zzaxb);
                zzb.name = zzatm.mName;
                zzb.zzbxa = Long.valueOf(zzatm.zzbry);
                zzb.zzbwY = new zzc[zzatm.zzbrz.size()];
                Iterator it = zzatm.zzbrz.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzc zzc = new zzc();
                    int i3 = i2 + 1;
                    zzb.zzbwY[i2] = zzc;
                    zzc.name = str2;
                    zzKh().zza(zzc, zzatm.zzbrz.get(str2));
                    i2 = i3;
                }
                zze.zzbxz = zza(zzfu.zzke(), zze.zzbxh, zze.zzbxg);
                zze.zzbxj = zzb.zzbwZ;
                zze.zzbxk = zzb.zzbwZ;
                long zzKs = zzfu.zzKs();
                zze.zzbxm = zzKs != 0 ? Long.valueOf(zzKs) : null;
                long zzKr = zzfu.zzKr();
                if (zzKr != 0) {
                    zzKs = zzKr;
                }
                zze.zzbxl = zzKs != 0 ? Long.valueOf(zzKs) : null;
                zzfu.zzKB();
                zze.zzbxx = Integer.valueOf((int) zzfu.zzKy());
                zze.zzbxs = Long.valueOf(zzKn().zzKv());
                zze.zzbxi = Long.valueOf(zznR().currentTimeMillis());
                zze.zzbxy = Boolean.TRUE;
                zzfu.zzY(zze.zzbxj.longValue());
                zzfu.zzZ(zze.zzbxk.longValue());
                zzKg().zza(zzfu);
                zzKg().setTransactionSuccessful();
                zzKg().endTransaction();
                try {
                    byte[] bArr2 = new byte[zzd.zzaeT()];
                    zzbxm zzag = zzbxm.zzag(bArr2);
                    zzd.zza(zzag);
                    zzag.zzaeG();
                    return zzKh().zzk(bArr2);
                } catch (IOException e) {
                    zzKl().zzLY().zze("Data loss. Failed to bundle and serialize. appId", zzatx.zzfE(str), e);
                    return null;
                }
            }
        } finally {
            zzKg().endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzaq(long j) {
        return zzl(null, j);
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zzatc zzatc) {
        ArrayMap arrayMap = null;
        if (TextUtils.isEmpty(zzatc.getGmpAppId())) {
            zzb(zzatc.zzke(), 204, null, null, null);
            return;
        }
        String zzP = zzKn().zzP(zzatc.getGmpAppId(), zzatc.getAppInstanceId());
        try {
            URL url = new URL(zzP);
            zzKl().zzMe().zzj("Fetching remote configuration", zzatc.zzke());
            zzauv.zzb zzfL = zzKi().zzfL(zzatc.zzke());
            String zzfM = zzKi().zzfM(zzatc.zzke());
            if (zzfL != null && !TextUtils.isEmpty(zzfM)) {
                arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzfM);
            }
            zzMy().zza(zzatc.zzke(), url, arrayMap, new zza() {
                public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                    zzaue.this.zzb(str, i, th, bArr, map);
                }
            });
        } catch (MalformedURLException e) {
            zzKl().zzLY().zze("Failed to parse config URL. Not fetching. appId", zzatx.zzfE(zzatc.zzke()), zzP);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(zzatd zzatd, long j) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        zzmR();
        zzob();
        zzatc zzfu = zzKg().zzfu(zzatd.packageName);
        if (zzfu != null && TextUtils.isEmpty(zzfu.getGmpAppId()) && zzatd != null && !TextUtils.isEmpty(zzatd.zzbqL)) {
            zzfu.zzae(0);
            zzKg().zza(zzfu);
        }
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        bundle.putLong("_r", 1);
        bundle.putLong("_uwa", 0);
        bundle.putLong("_pfo", 0);
        bundle.putLong("_sys", 0);
        bundle.putLong("_sysu", 0);
        if (getContext().getPackageManager() == null) {
            zzKl().zzLY().zzj("PackageManager is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName));
        } else {
            try {
                packageInfo = zzadg.zzbi(getContext()).getPackageInfo(zzatd.packageName, 0);
            } catch (NameNotFoundException e) {
                zzKl().zzLY().zze("Package info is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName), e);
                packageInfo = null;
            }
            if (!(packageInfo == null || packageInfo.firstInstallTime == 0 || packageInfo.firstInstallTime == packageInfo.lastUpdateTime)) {
                bundle.putLong("_uwa", 1);
            }
            try {
                applicationInfo = zzadg.zzbi(getContext()).getApplicationInfo(zzatd.packageName, 0);
            } catch (NameNotFoundException e2) {
                zzKl().zzLY().zze("Application info is null, first open report might be inaccurate. appId", zzatx.zzfE(zzatd.packageName), e2);
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    bundle.putLong("_sys", 1);
                }
                if ((applicationInfo.flags & 128) != 0) {
                    bundle.putLong("_sysu", 1);
                }
            }
        }
        long zzfA = zzKg().zzfA(zzatd.packageName);
        if (zzfA >= 0) {
            bundle.putLong("_pfo", zzfA);
        }
        zzb(new zzatq("_f", new zzato(bundle), "auto", j), zzatd);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(zzatg zzatg, zzatd zzatd) {
        boolean z;
        zzac.zzw(zzatg);
        zzac.zzdr(zzatg.packageName);
        zzac.zzw(zzatg.zzbqV);
        zzac.zzw(zzatg.zzbqW);
        zzac.zzdr(zzatg.zzbqW.name);
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzatg zzatg2 = new zzatg(zzatg);
            zzKg().beginTransaction();
            try {
                zzatg zzT = zzKg().zzT(zzatg2.packageName, zzatg2.zzbqW.name);
                if (zzT != null && zzT.zzbqY) {
                    zzatg2.zzbqV = zzT.zzbqV;
                    zzatg2.zzbqX = zzT.zzbqX;
                    zzatg2.zzbqZ = zzT.zzbqZ;
                    zzatg2.zzbrc = zzT.zzbrc;
                    z = false;
                } else if (TextUtils.isEmpty(zzatg2.zzbqZ)) {
                    zzauq zzauq = zzatg2.zzbqW;
                    zzatg2.zzbqW = new zzauq(zzauq.name, zzatg2.zzbqX, zzauq.getValue(), zzauq.zzbqV);
                    zzatg2.zzbqY = true;
                    z = true;
                } else {
                    z = false;
                }
                if (zzatg2.zzbqY) {
                    zzauq zzauq2 = zzatg2.zzbqW;
                    zzaus zzaus = new zzaus(zzatg2.packageName, zzatg2.zzbqV, zzauq2.name, zzauq2.zzbwc, zzauq2.getValue());
                    if (zzKg().zza(zzaus)) {
                        zzKl().zzMd().zzd("User property updated immediately", zzatg2.packageName, zzaus.mName, zzaus.mValue);
                    } else {
                        zzKl().zzLY().zzd("(2)Too many active user properties, ignoring", zzatx.zzfE(zzatg2.packageName), zzaus.mName, zzaus.mValue);
                    }
                    if (z && zzatg2.zzbrc != null) {
                        zzc(new zzatq(zzatg2.zzbrc, zzatg2.zzbqX), zzatd);
                    }
                }
                if (zzKg().zza(zzatg2)) {
                    zzKl().zzMd().zzd("Conditional property added", zzatg2.packageName, zzatg2.zzbqW.name, zzatg2.zzbqW.getValue());
                } else {
                    zzKl().zzLY().zzd("Too many conditional properties, ignoring", zzatx.zzfE(zzatg2.packageName), zzatg2.zzbqW.name, zzatg2.zzbqW.getValue());
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(zzatq zzatq, zzatd zzatd) {
        zzac.zzw(zzatd);
        zzac.zzdr(zzatd.packageName);
        zzmR();
        zzob();
        String str = zzatd.packageName;
        long j = zzatq.zzbrH;
        if (zzKh().zzd(zzatq, zzatd)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKg().beginTransaction();
            try {
                for (zzatg zzatg : zzKg().zzh(str, j)) {
                    if (zzatg != null) {
                        zzKl().zzMd().zzd("User property timed out", zzatg.packageName, zzatg.zzbqW.name, zzatg.zzbqW.getValue());
                        if (zzatg.zzbra != null) {
                            zzc(new zzatq(zzatg.zzbra, j), zzatd);
                        }
                        zzKg().zzU(str, zzatg.zzbqW.name);
                    }
                }
                List<zzatg> zzi = zzKg().zzi(str, j);
                ArrayList<zzatq> arrayList = new ArrayList<>(zzi.size());
                for (zzatg zzatg2 : zzi) {
                    if (zzatg2 != null) {
                        zzKl().zzMd().zzd("User property expired", zzatg2.packageName, zzatg2.zzbqW.name, zzatg2.zzbqW.getValue());
                        zzKg().zzR(str, zzatg2.zzbqW.name);
                        if (zzatg2.zzbre != null) {
                            arrayList.add(zzatg2.zzbre);
                        }
                        zzKg().zzU(str, zzatg2.zzbqW.name);
                    }
                }
                for (zzatq zzatq2 : arrayList) {
                    zzc(new zzatq(zzatq2, j), zzatd);
                }
                List<zzatg> zzc = zzKg().zzc(str, zzatq.name, j);
                ArrayList<zzatq> arrayList2 = new ArrayList<>(zzc.size());
                for (zzatg zzatg3 : zzc) {
                    if (zzatg3 != null) {
                        zzauq zzauq = zzatg3.zzbqW;
                        zzaus zzaus = new zzaus(zzatg3.packageName, zzatg3.zzbqV, zzauq.name, j, zzauq.getValue());
                        if (zzKg().zza(zzaus)) {
                            zzKl().zzMd().zzd("User property triggered", zzatg3.packageName, zzaus.mName, zzaus.mValue);
                        } else {
                            zzKl().zzLY().zzd("Too many active user properties, ignoring", zzatx.zzfE(zzatg3.packageName), zzaus.mName, zzaus.mValue);
                        }
                        if (zzatg3.zzbrc != null) {
                            arrayList2.add(zzatg3.zzbrc);
                        }
                        zzatg3.zzbqW = new zzauq(zzaus);
                        zzatg3.zzbqY = true;
                        zzKg().zza(zzatg3);
                    }
                }
                zzc(zzatq, zzatd);
                for (zzatq zzatq3 : arrayList2) {
                    zzc(new zzatq(zzatq3, j), zzatd);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(zzatq zzatq, String str) {
        zzatc zzfu = zzKg().zzfu(str);
        if (zzfu == null || TextUtils.isEmpty(zzfu.zzmZ())) {
            zzKl().zzMd().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzadg.zzbi(getContext()).getPackageInfo(str, 0).versionName;
            if (zzfu.zzmZ() != null && !zzfu.zzmZ().equals(str2)) {
                zzKl().zzMa().zzj("App version does not match; dropping event. appId", zzatx.zzfE(str));
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(zzatq.name)) {
                zzKl().zzMa().zzj("Could not find package. appId", zzatx.zzfE(str));
            }
        }
        zzatq zzatq2 = zzatq;
        zzb(zzatq2, new zzatd(str, zzfu.getGmpAppId(), zzfu.zzmZ(), zzfu.zzKt(), zzfu.zzKu(), zzfu.zzKv(), zzfu.zzKw(), (String) null, zzfu.zzKx(), false, zzfu.zzKq(), zzfu.zzuW()));
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zzauh zzauh) {
        this.zzbuB++;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(zzauq zzauq, zzatd zzatd) {
        int i = 0;
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            int zzfX = zzKh().zzfX(zzauq.name);
            if (zzfX != 0) {
                String zza2 = zzKh().zza(zzauq.name, zzKn().zzKN(), true);
                if (zzauq.name != null) {
                    i = zzauq.name.length();
                }
                zzKh().zza(zzfX, "_ev", zza2, i);
                return;
            }
            int zzm = zzKh().zzm(zzauq.name, zzauq.getValue());
            if (zzm != 0) {
                String zza3 = zzKh().zza(zzauq.name, zzKn().zzKN(), true);
                Object value = zzauq.getValue();
                if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                    i = String.valueOf(value).length();
                }
                zzKh().zza(zzm, "_ev", zza3, i);
                return;
            }
            Object zzn = zzKh().zzn(zzauq.name, zzauq.getValue());
            if (zzn != null) {
                zzaus zzaus = new zzaus(zzatd.packageName, zzauq.zzbqV, zzauq.name, zzauq.zzbwc, zzn);
                zzKl().zzMd().zze("Setting user property", zzaus.mName, zzn);
                zzKg().beginTransaction();
                try {
                    zzf(zzatd);
                    boolean zza4 = zzKg().zza(zzaus);
                    zzKg().setTransactionSuccessful();
                    if (zza4) {
                        zzKl().zzMd().zze("User property set", zzaus.mName, zzaus.mValue);
                    } else {
                        zzKl().zzLY().zze("Too many unique user properties are set. Ignoring user property", zzaus.mName, zzaus.mValue);
                        zzKh().zza(9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzKg().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = false;
        zzmR();
        zzob();
        zzac.zzdr(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        zzKg().beginTransaction();
        try {
            zzatc zzfu = zzKg().zzfu(str);
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzfu == null) {
                zzKl().zzMa().zzj("App does not exist in onConfigFetched. appId", zzatx.zzfE(str));
            } else if (z2 || i == 404) {
                List list = map != null ? (List) map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
                if (i == 404 || i == 304) {
                    if (zzKi().zzfL(str) == null && !zzKi().zzb(str, null, null)) {
                        zzKg().endTransaction();
                        return;
                    }
                } else if (!zzKi().zzb(str, bArr, str2)) {
                    zzKg().endTransaction();
                    return;
                }
                zzfu.zzae(zznR().currentTimeMillis());
                zzKg().zza(zzfu);
                if (i == 404) {
                    zzKl().zzMb().zzj("Config not found. Using empty config. appId", str);
                } else {
                    zzKl().zzMe().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (!zzMy().zzqa() || !zzMH()) {
                    zzMI();
                } else {
                    zzMG();
                }
            } else {
                zzfu.zzaf(zznR().currentTimeMillis());
                zzKg().zza(zzfu);
                zzKl().zzMe().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzKi().zzfN(str);
                zzKm().zzbta.set(zznR().currentTimeMillis());
                if (i == 503 || i == 429) {
                    z = true;
                }
                if (z) {
                    zzKm().zzbtb.set(zznR().currentTimeMillis());
                }
                zzMI();
            }
            zzKg().setTransactionSuccessful();
        } finally {
            zzKg().endTransaction();
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzc(zzatd zzatd, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new zzatq("_e", new zzato(bundle), "auto", j), zzatd);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzc(zzatg zzatg, zzatd zzatd) {
        zzac.zzw(zzatg);
        zzac.zzdr(zzatg.packageName);
        zzac.zzw(zzatg.zzbqW);
        zzac.zzdr(zzatg.zzbqW.name);
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKg().beginTransaction();
            try {
                zzf(zzatd);
                zzatg zzT = zzKg().zzT(zzatg.packageName, zzatg.zzbqW.name);
                if (zzT != null) {
                    zzKl().zzMd().zze("Removing conditional user property", zzatg.packageName, zzatg.zzbqW.name);
                    zzKg().zzU(zzatg.packageName, zzatg.zzbqW.name);
                    if (zzT.zzbqY) {
                        zzKg().zzR(zzatg.packageName, zzatg.zzbqW.name);
                    }
                    if (zzatg.zzbre != null) {
                        Bundle bundle = null;
                        if (zzatg.zzbre.zzbrG != null) {
                            bundle = zzatg.zzbre.zzbrG.zzLW();
                        }
                        zzc(zzKh().zza(zzatg.zzbre.name, bundle, zzT.zzbqV, zzatg.zzbre.zzbrH, true, false), zzatd);
                    }
                } else {
                    zzKl().zzMa().zze("Conditional user property doesn't exist", zzatx.zzfE(zzatg.packageName), zzatg.zzbqW.name);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:64:0x0259=Splitter:B:64:0x0259, B:93:0x034f=Splitter:B:93:0x034f} */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzc(com.google.android.gms.internal.zzatq r19, com.google.android.gms.internal.zzatd r20) {
        /*
            r18 = this;
            com.google.android.gms.common.internal.zzac.zzw(r20)
            r0 = r20
            java.lang.String r2 = r0.packageName
            com.google.android.gms.common.internal.zzac.zzdr(r2)
            long r16 = java.lang.System.nanoTime()
            r18.zzmR()
            r18.zzob()
            r0 = r20
            java.lang.String r3 = r0.packageName
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()
            r0 = r19
            r1 = r20
            boolean r2 = r2.zzd(r0, r1)
            if (r2 != 0) goto L_0x0027
        L_0x0026:
            return
        L_0x0027:
            r0 = r20
            boolean r2 = r0.zzbqQ
            if (r2 != 0) goto L_0x0035
            r0 = r18
            r1 = r20
            r0.zzf(r1)
            goto L_0x0026
        L_0x0035:
            com.google.android.gms.internal.zzauc r2 = r18.zzKi()
            r0 = r19
            java.lang.String r4 = r0.name
            boolean r2 = r2.zzaa(r3, r4)
            if (r2 == 0) goto L_0x00d3
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r3)
            r0 = r19
            java.lang.String r6 = r0.name
            r2.zze(r4, r5, r6)
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()
            boolean r2 = r2.zzgg(r3)
            if (r2 != 0) goto L_0x006c
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()
            boolean r2 = r2.zzgh(r3)
            if (r2 == 0) goto L_0x00d1
        L_0x006c:
            r2 = 1
        L_0x006d:
            if (r2 != 0) goto L_0x008b
            java.lang.String r4 = "_err"
            r0 = r19
            java.lang.String r5 = r0.name
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x008b
            com.google.android.gms.internal.zzaut r4 = r18.zzKh()
            r5 = 11
            java.lang.String r6 = "_ev"
            r0 = r19
            java.lang.String r7 = r0.name
            r8 = 0
            r4.zza(r5, r6, r7, r8)
        L_0x008b:
            if (r2 == 0) goto L_0x0026
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            com.google.android.gms.internal.zzatc r2 = r2.zzfu(r3)
            if (r2 == 0) goto L_0x0026
            long r4 = r2.zzKA()
            long r6 = r2.zzKz()
            long r4 = java.lang.Math.max(r4, r6)
            com.google.android.gms.common.util.zze r3 = r18.zznR()
            long r6 = r3.currentTimeMillis()
            long r4 = r6 - r4
            long r4 = java.lang.Math.abs(r4)
            com.google.android.gms.internal.zzati r3 = r18.zzKn()
            long r6 = r3.zzLl()
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x0026
            com.google.android.gms.internal.zzatx r3 = r18.zzKl()
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzMd()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.log(r4)
            r0 = r18
            r0.zzb(r2)
            goto L_0x0026
        L_0x00d1:
            r2 = 0
            goto L_0x006d
        L_0x00d3:
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()
            r4 = 2
            boolean r2 = r2.zzak(r4)
            if (r2 == 0) goto L_0x00ed
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMe()
            java.lang.String r4 = "Logging event"
            r0 = r19
            r2.zzj(r4, r0)
        L_0x00ed:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.beginTransaction()
            r0 = r19
            com.google.android.gms.internal.zzato r2 = r0.zzbrG     // Catch:{ all -> 0x028e }
            android.os.Bundle r14 = r2.zzLW()     // Catch:{ all -> 0x028e }
            r0 = r18
            r1 = r20
            r0.zzf(r1)     // Catch:{ all -> 0x028e }
            java.lang.String r2 = "_iap"
            r0 = r19
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x028e }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x028e }
            if (r2 != 0) goto L_0x011b
            java.lang.String r2 = "ecommerce_purchase"
            r0 = r19
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x028e }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x028e }
            if (r2 == 0) goto L_0x01e6
        L_0x011b:
            java.lang.String r2 = "currency"
            java.lang.String r2 = r14.getString(r2)     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "ecommerce_purchase"
            r0 = r19
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x028e }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x028e }
            if (r4 == 0) goto L_0x027e
            java.lang.String r4 = "value"
            double r4 = r14.getDouble(r4)     // Catch:{ all -> 0x028e }
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r4 = r4 * r6
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x014c
            java.lang.String r4 = "value"
            long r4 = r14.getLong(r4)     // Catch:{ all -> 0x028e }
            double r4 = (double) r4     // Catch:{ all -> 0x028e }
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r4 = r4 * r6
        L_0x014c:
            r6 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x0259
            r6 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x0259
            long r4 = java.lang.Math.round(r4)     // Catch:{ all -> 0x028e }
            r8 = r4
        L_0x015d:
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x028e }
            if (r4 != 0) goto L_0x01e6
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ all -> 0x028e }
            java.lang.String r2 = r2.toUpperCase(r4)     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "[A-Z]{3}"
            boolean r4 = r2.matches(r4)     // Catch:{ all -> 0x028e }
            if (r4 == 0) goto L_0x01e6
            java.lang.String r4 = "_ltv_"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x028e }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x028e }
            int r5 = r2.length()     // Catch:{ all -> 0x028e }
            if (r5 == 0) goto L_0x0287
            java.lang.String r5 = r4.concat(r2)     // Catch:{ all -> 0x028e }
        L_0x0185:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaus r2 = r2.zzS(r3, r5)     // Catch:{ all -> 0x028e }
            if (r2 == 0) goto L_0x0195
            java.lang.Object r4 = r2.mValue     // Catch:{ all -> 0x028e }
            boolean r4 = r4 instanceof java.lang.Long     // Catch:{ all -> 0x028e }
            if (r4 != 0) goto L_0x0297
        L_0x0195:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r4 = r18.zzKn()     // Catch:{ all -> 0x028e }
            int r4 = r4.zzfn(r3)     // Catch:{ all -> 0x028e }
            int r4 = r4 + -1
            r2.zzz(r3, r4)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaus r2 = new com.google.android.gms.internal.zzaus     // Catch:{ all -> 0x028e }
            r0 = r19
            java.lang.String r4 = r0.zzbqV     // Catch:{ all -> 0x028e }
            com.google.android.gms.common.util.zze r6 = r18.zznR()     // Catch:{ all -> 0x028e }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x028e }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x028e }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x028e }
        L_0x01bb:
            com.google.android.gms.internal.zzatj r4 = r18.zzKg()     // Catch:{ all -> 0x028e }
            boolean r4 = r4.zza(r2)     // Catch:{ all -> 0x028e }
            if (r4 != 0) goto L_0x01e6
            com.google.android.gms.internal.zzatx r4 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x028e }
            java.lang.String r5 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            java.lang.String r7 = r2.mName     // Catch:{ all -> 0x028e }
            java.lang.Object r2 = r2.mValue     // Catch:{ all -> 0x028e }
            r4.zzd(r5, r6, r7, r2)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            r4 = 9
            r5 = 0
            r6 = 0
            r7 = 0
            r2.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x028e }
        L_0x01e6:
            r0 = r19
            java.lang.String r2 = r0.name     // Catch:{ all -> 0x028e }
            boolean r10 = com.google.android.gms.internal.zzaut.zzfT(r2)     // Catch:{ all -> 0x028e }
            java.lang.String r2 = "_err"
            r0 = r19
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x028e }
            boolean r12 = r2.equals(r4)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r5 = r18.zzKg()     // Catch:{ all -> 0x028e }
            long r6 = r18.zzME()     // Catch:{ all -> 0x028e }
            r9 = 1
            r11 = 0
            r13 = 0
            r8 = r3
            com.google.android.gms.internal.zzatj$zza r2 = r5.zza(r6, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x028e }
            long r4 = r2.zzbro     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r6 = r18.zzKn()     // Catch:{ all -> 0x028e }
            long r6 = r6.zzKU()     // Catch:{ all -> 0x028e }
            long r4 = r4 - r6
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x02b7
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 % r6
            r6 = 1
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0239
            com.google.android.gms.internal.zzatx r4 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x028e }
            java.lang.String r5 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            long r6 = r2.zzbro     // Catch:{ all -> 0x028e }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x028e }
            r4.zze(r5, r3, r2)     // Catch:{ all -> 0x028e }
        L_0x0239:
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            r3 = 16
            java.lang.String r4 = "_ev"
            r0 = r19
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x028e }
            r6 = 0
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            goto L_0x0026
        L_0x0259:
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()     // Catch:{ all -> 0x028e }
            java.lang.String r6 = "Data lost. Currency value is too big. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ all -> 0x028e }
            r2.zze(r6, r3, r4)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            goto L_0x0026
        L_0x027e:
            java.lang.String r4 = "value"
            long r4 = r14.getLong(r4)     // Catch:{ all -> 0x028e }
            r8 = r4
            goto L_0x015d
        L_0x0287:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x028e }
            r5.<init>(r4)     // Catch:{ all -> 0x028e }
            goto L_0x0185
        L_0x028e:
            r2 = move-exception
            com.google.android.gms.internal.zzatj r3 = r18.zzKg()
            r3.endTransaction()
            throw r2
        L_0x0297:
            java.lang.Object r2 = r2.mValue     // Catch:{ all -> 0x028e }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x028e }
            long r10 = r2.longValue()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaus r2 = new com.google.android.gms.internal.zzaus     // Catch:{ all -> 0x028e }
            r0 = r19
            java.lang.String r4 = r0.zzbqV     // Catch:{ all -> 0x028e }
            com.google.android.gms.common.util.zze r6 = r18.zznR()     // Catch:{ all -> 0x028e }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x028e }
            long r8 = r8 + r10
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x028e }
            r2.<init>(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x028e }
            goto L_0x01bb
        L_0x02b7:
            if (r10 == 0) goto L_0x030a
            long r4 = r2.zzbrn     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r6 = r18.zzKn()     // Catch:{ all -> 0x028e }
            long r6 = r6.zzKV()     // Catch:{ all -> 0x028e }
            long r4 = r4 - r6
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x030a
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 % r6
            r6 = 1
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x02ea
            com.google.android.gms.internal.zzatx r4 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x028e }
            java.lang.String r5 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            long r6 = r2.zzbrn     // Catch:{ all -> 0x028e }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x028e }
            r4.zze(r5, r3, r2)     // Catch:{ all -> 0x028e }
        L_0x02ea:
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            r3 = 16
            java.lang.String r4 = "_ev"
            r0 = r19
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x028e }
            r6 = 0
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            goto L_0x0026
        L_0x030a:
            if (r12 == 0) goto L_0x034f
            long r4 = r2.zzbrq     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r6 = r18.zzKn()     // Catch:{ all -> 0x028e }
            r0 = r20
            java.lang.String r7 = r0.packageName     // Catch:{ all -> 0x028e }
            int r6 = r6.zzfj(r7)     // Catch:{ all -> 0x028e }
            long r6 = (long) r6     // Catch:{ all -> 0x028e }
            long r4 = r4 - r6
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x034f
            r6 = 1
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x033f
            com.google.android.gms.internal.zzatx r4 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x028e }
            java.lang.String r5 = "Too many error events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            long r6 = r2.zzbrq     // Catch:{ all -> 0x028e }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x028e }
            r4.zze(r5, r3, r2)     // Catch:{ all -> 0x028e }
        L_0x033f:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            goto L_0x0026
        L_0x034f:
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "_o"
            r0 = r19
            java.lang.String r5 = r0.zzbqV     // Catch:{ all -> 0x028e }
            r2.zza(r14, r4, r5)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            boolean r2 = r2.zzge(r3)     // Catch:{ all -> 0x028e }
            if (r2 == 0) goto L_0x0384
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "_dbg"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x028e }
            r2.zza(r14, r4, r5)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "_r"
            r6 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x028e }
            r2.zza(r14, r4, r5)     // Catch:{ all -> 0x028e }
        L_0x0384:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            long r4 = r2.zzfv(r3)     // Catch:{ all -> 0x028e }
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x03a7
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()     // Catch:{ all -> 0x028e }
            java.lang.String r6 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x028e }
            r2.zze(r6, r7, r4)     // Catch:{ all -> 0x028e }
        L_0x03a7:
            com.google.android.gms.internal.zzatm r5 = new com.google.android.gms.internal.zzatm     // Catch:{ all -> 0x028e }
            r0 = r19
            java.lang.String r7 = r0.zzbqV     // Catch:{ all -> 0x028e }
            r0 = r19
            java.lang.String r9 = r0.name     // Catch:{ all -> 0x028e }
            r0 = r19
            long r10 = r0.zzbrH     // Catch:{ all -> 0x028e }
            r12 = 0
            r6 = r18
            r8 = r3
            r5.<init>(r6, r7, r8, r9, r10, r12, r14)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            java.lang.String r4 = r5.mName     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatn r2 = r2.zzQ(r3, r4)     // Catch:{ all -> 0x028e }
            if (r2 != 0) goto L_0x0478
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            long r6 = r2.zzfC(r3)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r2 = r18.zzKn()     // Catch:{ all -> 0x028e }
            r2.zzKT()     // Catch:{ all -> 0x028e }
            r8 = 500(0x1f4, double:2.47E-321)
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0412
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x028e }
            java.lang.String r4 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r3)     // Catch:{ all -> 0x028e }
            java.lang.String r5 = r5.mName     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzati r6 = r18.zzKn()     // Catch:{ all -> 0x028e }
            int r6 = r6.zzKT()     // Catch:{ all -> 0x028e }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x028e }
            r2.zzd(r4, r3, r5, r6)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzaut r2 = r18.zzKh()     // Catch:{ all -> 0x028e }
            r3 = 8
            r4 = 0
            r5 = 0
            r6 = 0
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            goto L_0x0026
        L_0x0412:
            com.google.android.gms.internal.zzatn r7 = new com.google.android.gms.internal.zzatn     // Catch:{ all -> 0x028e }
            java.lang.String r9 = r5.mName     // Catch:{ all -> 0x028e }
            r10 = 0
            r12 = 0
            long r14 = r5.zzaxb     // Catch:{ all -> 0x028e }
            r8 = r3
            r7.<init>(r8, r9, r10, r12, r14)     // Catch:{ all -> 0x028e }
        L_0x0420:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.zza(r7)     // Catch:{ all -> 0x028e }
            r0 = r18
            r1 = r20
            r0.zza(r5, r1)     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()     // Catch:{ all -> 0x028e }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()     // Catch:{ all -> 0x028e }
            r3 = 2
            boolean r2 = r2.zzak(r3)     // Catch:{ all -> 0x028e }
            if (r2 == 0) goto L_0x044d
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMe()     // Catch:{ all -> 0x028e }
            java.lang.String r3 = "Event recorded"
            r2.zzj(r3, r5)     // Catch:{ all -> 0x028e }
        L_0x044d:
            com.google.android.gms.internal.zzatj r2 = r18.zzKg()
            r2.endTransaction()
            r18.zzMI()
            com.google.android.gms.internal.zzatx r2 = r18.zzKl()
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMe()
            java.lang.String r3 = "Background event processing time, ms"
            long r4 = java.lang.System.nanoTime()
            long r4 = r4 - r16
            r6 = 500000(0x7a120, double:2.47033E-318)
            long r4 = r4 + r6
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = r4 / r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r2.zzj(r3, r4)
            goto L_0x0026
        L_0x0478:
            long r6 = r2.zzbrC     // Catch:{ all -> 0x028e }
            r0 = r18
            com.google.android.gms.internal.zzatm r5 = r5.zza(r0, r6)     // Catch:{ all -> 0x028e }
            long r6 = r5.zzaxb     // Catch:{ all -> 0x028e }
            com.google.android.gms.internal.zzatn r7 = r2.zzap(r6)     // Catch:{ all -> 0x028e }
            goto L_0x0420
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaue.zzc(com.google.android.gms.internal.zzatq, com.google.android.gms.internal.zzatd):void");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzc(zzauq zzauq, zzatd zzatd) {
        zzmR();
        zzob();
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            zzKl().zzMd().zzj("Removing user property", zzauq.name);
            zzKg().beginTransaction();
            try {
                zzf(zzatd);
                zzKg().zzR(zzatd.packageName, zzauq.name);
                zzKg().setTransactionSuccessful();
                zzKl().zzMd().zzj("User property removed", zzauq.name);
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzd(zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzdr(zzatd.packageName);
        zzf(zzatd);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzd(zzatd zzatd, long j) {
        zzb(new zzatq("_cd", new zzato(new Bundle()), "auto", j), zzatd);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzd(zzatg zzatg) {
        zzatd zzfO = zzfO(zzatg.packageName);
        if (zzfO != null) {
            zzb(zzatg, zzfO);
        }
    }

    @WorkerThread
    public void zze(zzatd zzatd) {
        zzmR();
        zzob();
        zzac.zzw(zzatd);
        zzac.zzdr(zzatd.packageName);
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            if (!zzatd.zzbqQ) {
                zzf(zzatd);
                return;
            }
            long currentTimeMillis = zznR().currentTimeMillis();
            zzKg().beginTransaction();
            try {
                zza(zzatd, currentTimeMillis);
                zzf(zzatd);
                if (zzKg().zzQ(zzatd.packageName, "_f") == null) {
                    zzb(new zzauq("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), zzatd);
                    zzb(zzatd, currentTimeMillis);
                    zzc(zzatd, currentTimeMillis);
                } else if (zzatd.zzbqR) {
                    zzd(zzatd, currentTimeMillis);
                }
                zzKg().setTransactionSuccessful();
            } finally {
                zzKg().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zze(zzatg zzatg) {
        zzatd zzfO = zzfO(zzatg.packageName);
        if (zzfO != null) {
            zzc(zzatg, zzfO);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public zzatd zzfO(String str) {
        zzatc zzfu = zzKg().zzfu(str);
        if (zzfu == null || TextUtils.isEmpty(zzfu.zzmZ())) {
            zzKl().zzMd().zzj("No app data available; dropping", str);
            return null;
        }
        try {
            String str2 = zzadg.zzbi(getContext()).getPackageInfo(str, 0).versionName;
            if (zzfu.zzmZ() != null && !zzfu.zzmZ().equals(str2)) {
                zzKl().zzMa().zzj("App version does not match; dropping. appId", zzatx.zzfE(str));
                return null;
            }
        } catch (NameNotFoundException e) {
        }
        return new zzatd(str, zzfu.getGmpAppId(), zzfu.zzmZ(), zzfu.zzKt(), zzfu.zzKu(), zzfu.zzKv(), zzfu.zzKw(), (String) null, zzfu.zzKx(), false, zzfu.zzKq(), zzfu.zzuW());
    }

    public String zzfP(final String str) {
        try {
            return (String) zzKk().zzd((Callable<V>) new Callable<String>() {
                /* renamed from: zzbY */
                public String call() throws Exception {
                    zzatc zzfu = zzaue.this.zzKg().zzfu(str);
                    if (zzfu == null) {
                        return null;
                    }
                    return zzfu.getAppInstanceId();
                }
            }).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzKl().zzLY().zze("Failed to get app instance id. appId", zzatx.zzfE(str), e);
            return null;
        }
    }

    @WorkerThread
    public void zzmR() {
        zzKk().zzmR();
    }

    public zze zznR() {
        return this.zzuP;
    }

    /* access modifiers changed from: 0000 */
    public void zzob() {
        if (!this.zzadP) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zzy(int i, int i2) {
        zzmR();
        if (i > i2) {
            zzKl().zzLY().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            return false;
        }
        if (i < i2) {
            if (zza(i2, zzMB())) {
                zzKl().zzMe().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                zzKl().zzLY().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return false;
            }
        }
        return true;
    }
}
