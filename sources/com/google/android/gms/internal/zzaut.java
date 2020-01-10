package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauu.zzb;
import com.google.android.gms.internal.zzauu.zzc;
import com.google.android.gms.internal.zzauu.zzd;
import com.google.android.gms.internal.zzauu.zze;
import com.google.android.gms.internal.zzauu.zzf;
import com.google.android.gms.internal.zzauw.zza;
import com.google.android.gms.internal.zzauw.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public class zzaut extends zzauh {
    private final AtomicLong zzbwh = new AtomicLong(0);
    private int zzbwi;

    zzaut(zzaue zzaue) {
        super(zzaue);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[Catch:{ IOException | ClassNotFoundException -> 0x003c }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[Catch:{ IOException | ClassNotFoundException -> 0x003c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zzH(java.lang.Object r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x002e }
            r1.<init>()     // Catch:{ all -> 0x002e }
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x002e }
            r3.<init>(r1)     // Catch:{ all -> 0x002e }
            r3.writeObject(r5)     // Catch:{ all -> 0x0040 }
            r3.flush()     // Catch:{ all -> 0x0040 }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ all -> 0x0040 }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0040 }
            byte[] r1 = r1.toByteArray()     // Catch:{ all -> 0x0040 }
            r4.<init>(r1)     // Catch:{ all -> 0x0040 }
            r2.<init>(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Object r1 = r2.readObject()     // Catch:{ all -> 0x0043 }
            r3.close()     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x003e }
            r2.close()     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x003e }
            r0 = r1
            goto L_0x0003
        L_0x002e:
            r1 = move-exception
            r2 = r0
            r3 = r0
        L_0x0031:
            if (r3 == 0) goto L_0x0036
            r3.close()     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x003e }
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x003e }
        L_0x003b:
            throw r1     // Catch:{ IOException -> 0x003c, ClassNotFoundException -> 0x003e }
        L_0x003c:
            r1 = move-exception
            goto L_0x0003
        L_0x003e:
            r1 = move-exception
            goto L_0x0003
        L_0x0040:
            r1 = move-exception
            r2 = r0
            goto L_0x0031
        L_0x0043:
            r1 = move-exception
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zzH(java.lang.Object):java.lang.Object");
    }

    private Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzb.zzbwo);
        zza(sb, 0, "event_name", (Object) zzb.zzbwp);
        zza(sb, 1, "event_count_filter", zzb.zzbws);
        sb.append("  filters {\n");
        for (zzc zza : zzb.zzbwq) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    public static String zza(zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zze.zzbwo);
        zza(sb, 0, "property_name", (Object) zze.zzbwE);
        zza(sb, 1, zze.zzbwF);
        sb.append("}\n");
        return sb.toString();
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzc.zzbww);
            zza(sb, i, "param_name", (Object) zzc.zzbwx);
            zza(sb, i + 1, "string_filter", zzc.zzbwu);
            zza(sb, i + 1, "number_filter", zzc.zzbwv);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zze zze) {
        if (zze != null) {
            zza(sb, i);
            sb.append("bundle {\n");
            zza(sb, i, "protocol_version", (Object) zze.zzbxf);
            zza(sb, i, "platform", (Object) zze.zzbxn);
            zza(sb, i, "gmp_version", (Object) zze.zzbxr);
            zza(sb, i, "uploading_gmp_version", (Object) zze.zzbxs);
            zza(sb, i, "config_version", (Object) zze.zzbxE);
            zza(sb, i, "gmp_app_id", (Object) zze.zzbqL);
            zza(sb, i, "app_id", (Object) zze.zzaS);
            zza(sb, i, "app_version", (Object) zze.zzbhN);
            zza(sb, i, "app_version_major", (Object) zze.zzbxA);
            zza(sb, i, "app_version_minor", (Object) zze.zzbxB);
            zza(sb, i, "app_version_release", (Object) zze.zzbxC);
            zza(sb, i, "firebase_instance_id", (Object) zze.zzbqT);
            zza(sb, i, "dev_cert_hash", (Object) zze.zzbxw);
            zza(sb, i, "app_store", (Object) zze.zzbqM);
            zza(sb, i, "upload_timestamp_millis", (Object) zze.zzbxi);
            zza(sb, i, "start_timestamp_millis", (Object) zze.zzbxj);
            zza(sb, i, "end_timestamp_millis", (Object) zze.zzbxk);
            zza(sb, i, "previous_bundle_start_timestamp_millis", (Object) zze.zzbxl);
            zza(sb, i, "previous_bundle_end_timestamp_millis", (Object) zze.zzbxm);
            zza(sb, i, "app_instance_id", (Object) zze.zzbxv);
            zza(sb, i, "resettable_device_id", (Object) zze.zzbxt);
            zza(sb, i, "device_id", (Object) zze.zzbxD);
            zza(sb, i, "limited_ad_tracking", (Object) zze.zzbxu);
            zza(sb, i, "os_version", (Object) zze.zzbb);
            zza(sb, i, "device_model", (Object) zze.zzbxo);
            zza(sb, i, "user_default_language", (Object) zze.zzbxp);
            zza(sb, i, "time_zone_offset_minutes", (Object) zze.zzbxq);
            zza(sb, i, "bundle_sequential_index", (Object) zze.zzbxx);
            zza(sb, i, "service_upload", (Object) zze.zzbxy);
            zza(sb, i, "health_monitor", (Object) zze.zzbqP);
            if (zze.zzbxF.longValue() != 0) {
                zza(sb, i, "android_id", (Object) zze.zzbxF);
            }
            zza(sb, i, zze.zzbxh);
            zza(sb, i, zze.zzbxz);
            zza(sb, i, zze.zzbxg);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zzbwy != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzd.zzbwy.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzd.zzbwz);
            zza(sb, i, "comparison_value", (Object) zzd.zzbwA);
            zza(sb, i, "min_comparison_value", (Object) zzd.zzbwB);
            zza(sb, i, "max_comparison_value", (Object) zzd.zzbwC);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzf zzf) {
        String[] strArr;
        if (zzf != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzf.zzbwG != null) {
                String str2 = "UNKNOWN_MATCH_TYPE";
                switch (zzf.zzbwG.intValue()) {
                    case 1:
                        str2 = "REGEXP";
                        break;
                    case 2:
                        str2 = "BEGINS_WITH";
                        break;
                    case 3:
                        str2 = "ENDS_WITH";
                        break;
                    case 4:
                        str2 = "PARTIAL";
                        break;
                    case 5:
                        str2 = "EXACT";
                        break;
                    case 6:
                        str2 = "IN_LIST";
                        break;
                }
                zza(sb, i, "match_type", (Object) str2);
            }
            zza(sb, i, "expression", (Object) zzf.zzbwH);
            zza(sb, i, "case_sensitive", (Object) zzf.zzbwI);
            if (zzf.zzbwJ.length > 0) {
                zza(sb, i + 1);
                sb.append("expression_list {\n");
                for (String str3 : zzf.zzbwJ) {
                    zza(sb, i + 2);
                    sb.append(str3);
                    sb.append("\n");
                }
                sb.append("}\n");
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauw.zzf zzf) {
        int i2 = 0;
        if (zzf != null) {
            int i3 = i + 1;
            zza(sb, i3);
            sb.append(str);
            sb.append(" {\n");
            if (zzf.zzbxH != null) {
                zza(sb, i3 + 1);
                sb.append("results: ");
                long[] jArr = zzf.zzbxH;
                int length = jArr.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    Long valueOf = Long.valueOf(jArr[i4]);
                    int i6 = i5 + 1;
                    if (i5 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i4++;
                    i5 = i6;
                }
                sb.append(10);
            }
            if (zzf.zzbxG != null) {
                zza(sb, i3 + 1);
                sb.append("status: ");
                long[] jArr2 = zzf.zzbxG;
                int length2 = jArr2.length;
                int i7 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i8 = i7 + 1;
                    if (i7 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i2++;
                    i7 = i8;
                }
                sb.append(10);
            }
            zza(sb, i3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static void zza(StringBuilder sb, int i, zza[] zzaArr) {
        if (zzaArr != null) {
            int i2 = i + 1;
            for (zza zza : zzaArr) {
                if (zza != null) {
                    zza(sb, i2);
                    sb.append("audience_membership {\n");
                    zza(sb, i2, "audience_id", (Object) zza.zzbwk);
                    zza(sb, i2, "new_audience", (Object) zza.zzbwW);
                    zza(sb, i2, "current_data", zza.zzbwU);
                    zza(sb, i2, "previous_data", zza.zzbwV);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zzb[] zzbArr) {
        if (zzbArr != null) {
            int i2 = i + 1;
            for (zzauw.zzb zzb : zzbArr) {
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("event {\n");
                    zza(sb, i2, "name", (Object) zzb.name);
                    zza(sb, i2, "timestamp_millis", (Object) zzb.zzbwZ);
                    zza(sb, i2, "previous_timestamp_millis", (Object) zzb.zzbxa);
                    zza(sb, i2, "count", (Object) zzb.count);
                    zza(sb, i2, zzb.zzbwY);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zzc[] zzcArr) {
        if (zzcArr != null) {
            int i2 = i + 1;
            for (zzauw.zzc zzc : zzcArr) {
                if (zzc != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    zza(sb, i2, "name", (Object) zzc.name);
                    zza(sb, i2, "string_value", (Object) zzc.zzaGV);
                    zza(sb, i2, "int_value", (Object) zzc.zzbxc);
                    zza(sb, i2, "double_value", (Object) zzc.zzbwf);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzg[] zzgArr) {
        if (zzgArr != null) {
            int i2 = i + 1;
            for (zzg zzg : zzgArr) {
                if (zzg != null) {
                    zza(sb, i2);
                    sb.append("user_property {\n");
                    zza(sb, i2, "set_timestamp_millis", (Object) zzg.zzbxJ);
                    zza(sb, i2, "name", (Object) zzg.name);
                    zza(sb, i2, "string_value", (Object) zzg.zzaGV);
                    zza(sb, i2, "int_value", (Object) zzg.zzbxc);
                    zza(sb, i2, "double_value", (Object) zzg.zzbwf);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    public static boolean zza(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, str), 2);
            if (receiverInfo == null || !receiverInfo.enabled) {
                return false;
            }
            return !z || receiverInfo.exported;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean zza(long[] jArr, int i) {
        return i < jArr.length * 64 && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i * 64) + i2 < bitSet.length()) {
                if (bitSet.get((i * 64) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static boolean zzae(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static String zzb(zzauw.zzd zzd) {
        zzauw.zze[] zzeArr;
        if (zzd == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzd.zzbxd != null) {
            for (zzauw.zze zze : zzd.zzbxd) {
                if (zze != null) {
                    zza(sb, 1, zze);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    static MessageDigest zzch(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i = i2 + 1;
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    static boolean zzfT(String str) {
        zzac.zzdr(str);
        return str.charAt(0) != '_';
    }

    private int zzgc(String str) {
        return "_ldl".equals(str) ? zzKn().zzKS() : zzKn().zzKR();
    }

    public static boolean zzgd(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzgf(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static long zzy(byte[] bArr) {
        int i = 0;
        zzac.zzw(bArr);
        zzac.zzaw(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    public static boolean zzy(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 4);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public boolean zzA(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
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
    @WorkerThread
    public long zzL(Context context, String str) {
        zzmR();
        zzac.zzw(context);
        zzac.zzdr(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzch = zzch("MD5");
        if (zzch == null) {
            zzKl().zzLY().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzM(context, str)) {
                    PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzy(zzch.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzKl().zzMa().log("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e) {
                zzKl().zzLY().zzj("Package name not found", e);
            }
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public Bundle zzM(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzl = zzKh().zzl(str, bundle.get(str));
                if (zzl == null) {
                    zzKl().zzMa().zzj("Param value can't be null", str);
                } else {
                    zzKh().zza(bundle2, str, zzl);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzM(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzKl().zzLY().zzj("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzKl().zzLY().zzj("Package name not found", e2);
        }
        return true;
    }

    public long zzNi() {
        long andIncrement;
        if (this.zzbwh.get() == 0) {
            synchronized (this.zzbwh) {
                long nextLong = new Random(System.nanoTime() ^ zznR().currentTimeMillis()).nextLong();
                int i = this.zzbwi + 1;
                this.zzbwi = i;
                andIncrement = nextLong + ((long) i);
            }
        } else {
            synchronized (this.zzbwh) {
                this.zzbwh.compareAndSet(-1, 1);
                andIncrement = this.zzbwh.getAndIncrement();
            }
        }
        return andIncrement;
    }

    public Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z) {
        int i;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        zzKn().zzKL();
        int i2 = 0;
        for (String str2 : bundle.keySet()) {
            if (list == null || !list.contains(str2)) {
                i = z ? zzfY(str2) : 0;
                if (i == 0) {
                    i = zzfZ(str2);
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                if (zzd(bundle2, i)) {
                    bundle2.putString("_ev", zza(str2, zzKn().zzKO(), true));
                    if (i == 3) {
                        zzb(bundle2, (Object) str2);
                    }
                }
                bundle2.remove(str2);
            } else if (zzk(str2, bundle.get(str2)) || "_ev".equals(str2)) {
                if (zzfT(str2)) {
                    i2++;
                    if (i2 > 25) {
                        zzKl().zzLY().zze("Event can't contain more then " + 25 + " params", str, bundle);
                        zzd(bundle2, 5);
                        bundle2.remove(str2);
                    }
                }
                i2 = i2;
            } else {
                if (zzd(bundle2, 4)) {
                    bundle2.putString("_ev", zza(str2, zzKn().zzKO(), true));
                    zzb(bundle2, bundle.get(str2));
                }
                bundle2.remove(str2);
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: 0000 */
    public zzatq zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzKh().zzfV(str) != 0) {
            zzKl().zzLY().zzj("Invalid conditional property event name", str);
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        Bundle zza = zzKh().zza(str, bundle2, com.google.android.gms.common.util.zzf.zzx("_o"), z2);
        return new zzatq(str, new zzato(z ? zzM(zza) : zza), str2, j);
    }

    public String zza(String str, int i, boolean z) {
        if (str.length() <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, i)).concat("...");
        }
        return null;
    }

    public void zza(int i, String str, String str2, int i2) {
        zza(null, i, str, str2, i2);
    }

    public void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzKl().zzMb().zze("Not putting event parameter. Invalid value type. name, type", str, obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zza(zzauw.zzc zzc, Object obj) {
        zzac.zzw(obj);
        zzc.zzaGV = null;
        zzc.zzbxc = null;
        zzc.zzbwf = null;
        if (obj instanceof String) {
            zzc.zzaGV = (String) obj;
        } else if (obj instanceof Long) {
            zzc.zzbxc = (Long) obj;
        } else if (obj instanceof Double) {
            zzc.zzbwf = (Double) obj;
        } else {
            zzKl().zzLY().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public void zza(zzg zzg, Object obj) {
        zzac.zzw(obj);
        zzg.zzaGV = null;
        zzg.zzbxc = null;
        zzg.zzbwf = null;
        if (obj instanceof String) {
            zzg.zzaGV = (String) obj;
        } else if (obj instanceof Long) {
            zzg.zzbxc = (Long) obj;
        } else if (obj instanceof Double) {
            zzg.zzbwf = (Double) obj;
        } else {
            zzKl().zzLY().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzbqc.zzKn().zzLg();
        this.zzbqc.zzKa().zze("auto", "_err", bundle);
    }

    /* access modifiers changed from: 0000 */
    public boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() <= i) {
            return true;
        }
        zzKl().zzMa().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    /* access modifiers changed from: 0000 */
    public byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public byte[] zza(zzauw.zzd zzd) {
        try {
            byte[] bArr = new byte[zzd.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzd.zza(zzag);
            zzag.zzaeG();
            return bArr;
        } catch (IOException e) {
            zzKl().zzLY().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzac(String str, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzKl().zzLY().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzKl().zzLY().zze("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzKl().zzLY().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzad(String str, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzKl().zzLY().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzKl().zzLY().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzKl().zzLY().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public <T extends Parcelable> T zzb(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            T t = (Parcelable) creator.createFromParcel(obtain);
            obtain.recycle();
            return t;
        } catch (com.google.android.gms.common.internal.safeparcel.zzb.zza e) {
            zzKl().zzLY().log("Failed to load parcelable from buffer");
            obtain.recycle();
            return null;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public void zzb(Bundle bundle, Object obj) {
        zzac.zzw(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzb(String str, int i, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() <= i) {
            return true;
        } else {
            zzKl().zzLY().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    @WorkerThread
    public boolean zzbW(String str) {
        zzmR();
        if (zzadg.zzbi(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzKl().zzMd().zzj("Permission not granted", str);
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzc(String str, Map<String, String> map, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.startsWith("firebase_")) {
            zzKl().zzLY().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (map == null || !map.containsKey(str2)) {
            return true;
        } else {
            zzKl().zzLY().zze("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    public boolean zzd(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zzd(zzatq zzatq, zzatd zzatd) {
        zzac.zzw(zzatq);
        zzac.zzw(zzatd);
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            return true;
        }
        zzKn().zzLg();
        return false;
    }

    public int zzfU(String str) {
        if (!zzac("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbqd, str)) {
            return 13;
        }
        return zzb("event", zzKn().zzKM(), str) ? 0 : 2;
    }

    public int zzfV(String str) {
        if (!zzad("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbqd, str)) {
            return 13;
        }
        return zzb("event", zzKn().zzKM(), str) ? 0 : 2;
    }

    public int zzfW(String str) {
        if (!zzac("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbqi, str)) {
            return 15;
        }
        return zzb("user property", zzKn().zzKN(), str) ? 0 : 6;
    }

    public int zzfX(String str) {
        if (!zzad("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbqi, str)) {
            return 15;
        }
        return zzb("user property", zzKn().zzKN(), str) ? 0 : 6;
    }

    public int zzfY(String str) {
        if (!zzac("event param", str)) {
            return 3;
        }
        if (!zzc("event param", null, str)) {
            return 14;
        }
        return zzb("event param", zzKn().zzKO(), str) ? 0 : 3;
    }

    public int zzfZ(String str) {
        if (!zzad("event param", str)) {
            return 3;
        }
        if (!zzc("event param", null, str)) {
            return 14;
        }
        return zzb("event param", zzKn().zzKO(), str) ? 0 : 3;
    }

    public boolean zzga(String str) {
        if (TextUtils.isEmpty(str)) {
            zzKl().zzLY().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        } else if (zzgb(str)) {
            return true;
        } else {
            zzKl().zzLY().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzgb(String str) {
        zzac.zzw(str);
        return str.matches("^1:\\d+:android:[a-f0-9]+$");
    }

    public boolean zzge(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzLC = zzKn().zzLC();
        zzKn().zzLg();
        return zzLC.equals(str);
    }

    /* access modifiers changed from: 0000 */
    public boolean zzgg(String str) {
        return "1".equals(zzKi().zzZ(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: 0000 */
    public boolean zzgh(String str) {
        return "1".equals(zzKi().zzZ(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public boolean zzgi(String str) {
        zzac.zzdr(str);
        char c = 65535;
        switch (str.hashCode()) {
            case 94660:
                if (str.equals("_in")) {
                    c = 0;
                    break;
                }
                break;
            case 95025:
                if (str.equals("_ug")) {
                    c = 2;
                    break;
                }
                break;
            case 95027:
                if (str.equals("_ui")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public boolean zzh(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zznR().currentTimeMillis() - j) > j2;
    }

    public boolean zzk(String str, Object obj) {
        return zzgd(str) ? zza("param", str, zzKn().zzKQ(), obj) : zza("param", str, zzKn().zzKP(), obj);
    }

    public byte[] zzk(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public Object zzl(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzKn().zzKQ(), obj, true);
        }
        return zza(zzgd(str) ? zzKn().zzKQ() : zzKn().zzKP(), obj, false);
    }

    public int zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzgc(str), obj) : zza("user property", str, zzgc(str), obj) ? 0 : 7;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzKl().zzMa().log("Utils falling back to Random for random id");
            }
        }
        this.zzbwh.set(nextLong);
    }

    public Object zzn(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzgc(str), obj, true) : zza(zzgc(str), obj, false);
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zznR() {
        return super.zznR();
    }

    public Bundle zzu(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        Bundle bundle = null;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    str4 = uri.getQueryParameter("utm_campaign");
                    str3 = uri.getQueryParameter("utm_source");
                    str2 = uri.getQueryParameter("utm_medium");
                    str = uri.getQueryParameter("gclid");
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                }
                if (!TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(str4)) {
                        bundle.putString(Param.CAMPAIGN, str4);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        bundle.putString(Param.SOURCE, str3);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        bundle.putString(Param.MEDIUM, str2);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        bundle.putString("gclid", str);
                    }
                    String queryParameter = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString(Param.TERM, queryParameter);
                    }
                    String queryParameter2 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString(Param.CONTENT, queryParameter2);
                    }
                    String queryParameter3 = uri.getQueryParameter(Param.ACLID);
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString(Param.ACLID, queryParameter3);
                    }
                    String queryParameter4 = uri.getQueryParameter(Param.CP1);
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(Param.CP1, queryParameter4);
                    }
                    String queryParameter5 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter5)) {
                        bundle.putString("anid", queryParameter5);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzKl().zzMa().zzj("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    public byte[] zzx(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    @WorkerThread
    public long zzz(byte[] bArr) {
        zzac.zzw(bArr);
        zzmR();
        MessageDigest zzch = zzch("MD5");
        if (zzch != null) {
            return zzy(zzch.digest(bArr));
        }
        zzKl().zzLY().log("Failed to get MD5");
        return 0;
    }
}
