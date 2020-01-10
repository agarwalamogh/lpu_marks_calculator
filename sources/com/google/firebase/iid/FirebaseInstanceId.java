package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzbhH = new ArrayMap();
    private static zze zzclh;
    private final FirebaseApp zzcli;
    private final zzd zzclj;
    private final String zzclk = zzabM();

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzd zzd) {
        this.zzcli = firebaseApp;
        this.zzclj = zzd;
        if (this.zzclk == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zzcli.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) zzbhH.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzd zzb = zzd.zzb(firebaseApp.getApplicationContext(), null);
                if (zzclh == null) {
                    zzclh = new zze(zzb.zzabQ());
                }
                firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zzb);
                zzbhH.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    static int zzR(Context context, String str) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf).toString());
            return z;
        }
    }

    private void zzS(Bundle bundle) {
        bundle.putString("gmp_app_id", this.zzcli.getOptions().getApplicationId());
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzh zzh) {
        zzh.zzHo();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        zzg.zzabU().zzf(context, intent);
    }

    static String zzbx(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static void zzby(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        zzg.zzabU().zzf(context, intent);
    }

    static int zzcr(Context context) {
        return zzR(context, context.getPackageName());
    }

    static String zzv(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceId() throws IOException {
        this.zzclj.zzb("*", "*", null);
        this.zzclj.zzHi();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzS(bundle);
        this.zzclj.zzb(str, str2, bundle);
    }

    public long getCreationTime() {
        return this.zzclj.getCreationTime();
    }

    public String getId() {
        return zza(this.zzclj.zzHh());
    }

    @Nullable
    public String getToken() {
        zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            FirebaseInstanceIdService.zzcs(this.zzcli.getApplicationContext());
        }
        if (zzabN != null) {
            return zzabN.zzbxT;
        }
        return null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzS(bundle);
        return this.zzclj.getToken(str, str2, bundle);
    }

    /* access modifiers changed from: 0000 */
    public String zzabM() {
        String gcmSenderId = this.zzcli.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = this.zzcli.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public zza zzabN() {
        return this.zzclj.zzabQ().zzu("", this.zzclk, "*");
    }

    /* access modifiers changed from: 0000 */
    public String zzabO() throws IOException {
        return getToken(this.zzclk, "*");
    }

    /* access modifiers changed from: 0000 */
    public zze zzabP() {
        return zzclh;
    }

    public String zzc(String str, String str2, Bundle bundle) throws IOException {
        zzS(bundle);
        return this.zzclj.zzc(str, str2, bundle);
    }

    public void zzjt(String str) {
        zzclh.zzjt(str);
        FirebaseInstanceIdService.zzcs(this.zzcli.getApplicationContext());
    }

    /* access modifiers changed from: 0000 */
    public void zzju(String str) throws IOException {
        zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String str3 = zzabN.zzbxT;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzc(str3, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }

    /* access modifiers changed from: 0000 */
    public void zzjv(String str) throws IOException {
        zza zzabN = zzabN();
        if (zzabN == null || zzabN.zzjB(zzd.zzbhN)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzclj;
        String str3 = zzabN.zzbxT;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzd.zzb(str3, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }
}
