package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzmx extends zzpj {
    /* access modifiers changed from: private */
    public static zzji zzQn = null;
    static final long zzSV = TimeUnit.SECONDS.toMillis(10);
    static boolean zzSW = false;
    private static zzie zzSX = null;
    /* access modifiers changed from: private */
    public static zzii zzSY = null;
    private static zzid zzSZ = null;
    private static final Object zztX = new Object();
    private final Context mContext;
    private final Object zzPU = new Object();
    /* access modifiers changed from: private */
    public final com.google.android.gms.internal.zzmf.zza zzRl;
    private final com.google.android.gms.internal.zzmk.zza zzRm;
    /* access modifiers changed from: private */
    public com.google.android.gms.internal.zzji.zzc zzTa;

    public static class zza implements zzpt<zzjf> {
        /* renamed from: zza */
        public void zzd(zzjf zzjf) {
            zzmx.zzc(zzjf);
        }
    }

    public static class zzb implements zzpt<zzjf> {
        /* renamed from: zza */
        public void zzd(zzjf zzjf) {
            zzmx.zzb(zzjf);
        }
    }

    public static class zzc implements zzid {
        public void zza(zzqw zzqw, Map<String, String> map) {
            String str = (String) map.get("request_id");
            String str2 = "Invalid request: ";
            String valueOf = String.valueOf((String) map.get("errors"));
            zzpk.zzbh(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            zzmx.zzSY.zzac(str);
        }
    }

    public zzmx(Context context, com.google.android.gms.internal.zzmk.zza zza2, com.google.android.gms.internal.zzmf.zza zza3) {
        super(true);
        this.zzRl = zza3;
        this.mContext = context;
        this.zzRm = zza2;
        synchronized (zztX) {
            if (!zzSW) {
                zzSY = new zzii();
                zzSX = new zzie(context.getApplicationContext(), zza2.zzvn);
                zzSZ = new zzc();
                zzQn = new zzji(this.mContext.getApplicationContext(), this.zzRm.zzvn, (String) zzgd.zzBh.get(), new zzb(), new zza());
                zzSW = true;
            }
        }
    }

    private JSONObject zza(zzmk zzmk, String str) {
        zzni zzni;
        Info info;
        JSONObject jSONObject = 0;
        Bundle bundle = zzmk.zzRy.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return jSONObject;
        }
        try {
            zzni = (zzni) zzw.zzcV().zzA(this.mContext).get();
        } catch (Exception e) {
            zzpk.zzc("Error grabbing device info: ", e);
            zzni = jSONObject;
        }
        JSONObject zza2 = zznd.zza(this.mContext, new zzna().zzf(zzmk).zza(zzni));
        if (zza2 == null) {
            return jSONObject;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e2) {
            zzpk.zzc("Cannot get advertising id info", e2);
            info = jSONObject;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", zza2);
        hashMap.put("data", bundle);
        if (info != 0) {
            hashMap.put("adid", info.getId());
            hashMap.put("lat", Integer.valueOf(info.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return zzw.zzcM().zzQ((Map<String, ?>) hashMap);
        } catch (JSONException e3) {
            return jSONObject;
        }
    }

    protected static void zzb(zzjf zzjf) {
        zzjf.zza("/loadAd", (zzid) zzSY);
        zzjf.zza("/fetchHttpRequest", (zzid) zzSX);
        zzjf.zza("/invalidRequest", zzSZ);
    }

    protected static void zzc(zzjf zzjf) {
        zzjf.zzb("/loadAd", (zzid) zzSY);
        zzjf.zzb("/fetchHttpRequest", (zzid) zzSX);
        zzjf.zzb("/invalidRequest", zzSZ);
    }

    private zzmn zze(zzmk zzmk) {
        final String zzkL = zzw.zzcM().zzkL();
        final JSONObject zza2 = zza(zzmk, zzkL);
        if (zza2 == null) {
            return new zzmn(0);
        }
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        Future zzab = zzSY.zzab(zzkL);
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                zzmx.this.zzTa = zzmx.zzQn.zzgO();
                zzmx.this.zzTa.zza(new com.google.android.gms.internal.zzqp.zzc<zzjj>() {
                    /* renamed from: zzb */
                    public void zzd(zzjj zzjj) {
                        try {
                            zzjj.zza("AFMA_getAdapterLessMediationAd", zza2);
                        } catch (Exception e) {
                            zzpk.zzb("Error requesting an ad url", e);
                            zzmx.zzSY.zzac(zzkL);
                        }
                    }
                }, new com.google.android.gms.internal.zzqp.zza() {
                    public void run() {
                        zzmx.zzSY.zzac(zzkL);
                    }
                });
            }
        });
        try {
            JSONObject jSONObject = (JSONObject) zzab.get(zzSV - (zzw.zzcS().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzmn(-1);
            }
            zzmn zza3 = zznd.zza(this.mContext, zzmk, jSONObject.toString());
            return (zza3.errorCode == -3 || !TextUtils.isEmpty(zza3.body)) ? zza3 : new zzmn(3);
        } catch (InterruptedException | CancellationException e) {
            return new zzmn(-1);
        } catch (TimeoutException e2) {
            return new zzmn(2);
        } catch (ExecutionException e3) {
            return new zzmn(0);
        }
    }

    public void onStop() {
        synchronized (this.zzPU) {
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    if (zzmx.this.zzTa != null) {
                        zzmx.this.zzTa.release();
                        zzmx.this.zzTa = null;
                    }
                }
            });
        }
    }

    public void zzco() {
        zzpk.zzbf("SdkLessAdLoaderBackgroundTask started.");
        String zzF = zzw.zzdl().zzF(this.mContext);
        zzmk zzmk = new zzmk(this.zzRm, -1, zzw.zzdl().zzD(this.mContext), zzw.zzdl().zzE(this.mContext), zzF);
        zzw.zzdl().zzh(this.mContext, zzF);
        zzmn zze = zze(zzmk);
        zzmk zzmk2 = zzmk;
        final com.google.android.gms.internal.zzpb.zza zza2 = new com.google.android.gms.internal.zzpb.zza(zzmk2, zze, null, null, zze.errorCode, zzw.zzcS().elapsedRealtime(), zze.zzSr, null);
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                zzmx.this.zzRl.zza(zza2);
                if (zzmx.this.zzTa != null) {
                    zzmx.this.zzTa.release();
                    zzmx.this.zzTa = null;
                }
            }
        });
    }
}
