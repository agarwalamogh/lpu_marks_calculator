package com.google.firebase.iid;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;

public class FirebaseInstanceIdService extends zzb {
    private static BroadcastReceiver zzcln;
    @VisibleForTesting
    static final Object zzclo = new Object();
    @VisibleForTesting
    static boolean zzclp = false;
    /* access modifiers changed from: private */
    public boolean zzclq = false;

    private String zzG(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private int zza(Intent intent, boolean z) {
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        if (intExtra < 10 && !z) {
            return 30;
        }
        if (intExtra < 10) {
            return 10;
        }
        if (intExtra > 28800) {
            return 28800;
        }
        return intExtra;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r0.zzjB(com.google.firebase.iid.zzd.zzbhN) != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r3.zzabP().zzabS() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        zzcs(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000a, code lost:
        r0 = r3.zzabN();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r0 == null) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(android.content.Context r2, com.google.firebase.iid.FirebaseInstanceId r3) {
        /*
            java.lang.Object r1 = zzclo
            monitor-enter(r1)
            boolean r0 = zzclp     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
        L_0x0008:
            return
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            com.google.firebase.iid.zzh$zza r0 = r3.zzabN()
            if (r0 == 0) goto L_0x0022
            java.lang.String r1 = com.google.firebase.iid.zzd.zzbhN
            boolean r0 = r0.zzjB(r1)
            if (r0 != 0) goto L_0x0022
            com.google.firebase.iid.zze r0 = r3.zzabP()
            java.lang.String r0 = r0.zzabS()
            if (r0 == 0) goto L_0x0008
        L_0x0022:
            zzcs(r2)
            goto L_0x0008
        L_0x0026:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Context, com.google.firebase.iid.FirebaseInstanceId):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a4 A[Catch:{ IOException -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c1 A[SYNTHETIC, Splitter:B:59:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0087 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(android.content.Intent r9, boolean r10, boolean r11) {
        /*
            r8 = this;
            r2 = 1
            r1 = 0
            java.lang.Object r3 = zzclo
            monitor-enter(r3)
            r0 = 0
            zzclp = r0     // Catch:{ all -> 0x0010 }
            monitor-exit(r3)     // Catch:{ all -> 0x0010 }
            java.lang.String r0 = com.google.firebase.iid.zzf.zzbA(r8)
            if (r0 != 0) goto L_0x0013
        L_0x000f:
            return
        L_0x0010:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0010 }
            throw r0
        L_0x0013:
            com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance()
            com.google.firebase.iid.zzh$zza r3 = r0.zzabN()
            if (r3 == 0) goto L_0x0025
            java.lang.String r4 = com.google.firebase.iid.zzd.zzbhN
            boolean r4 = r3.zzjB(r4)
            if (r4 == 0) goto L_0x0063
        L_0x0025:
            java.lang.String r1 = r0.zzabO()     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            if (r1 == 0) goto L_0x0054
            boolean r2 = r8.zzclq     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            if (r2 == 0) goto L_0x0036
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r4 = "get master token succeeded"
            android.util.Log.d(r2, r4)     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
        L_0x0036:
            zza(r8, r0)     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            if (r11 != 0) goto L_0x0047
            if (r3 == 0) goto L_0x0047
            if (r3 == 0) goto L_0x000f
            java.lang.String r0 = r3.zzbxT     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            boolean r0 = r1.equals(r0)     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            if (r0 != 0) goto L_0x000f
        L_0x0047:
            r8.onTokenRefresh()     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            goto L_0x000f
        L_0x004b:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            r8.zzd(r9, r0)
            goto L_0x000f
        L_0x0054:
            java.lang.String r0 = "returned token is null"
            r8.zzd(r9, r0)     // Catch:{ IOException -> 0x004b, SecurityException -> 0x005a }
            goto L_0x000f
        L_0x005a:
            r0 = move-exception
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Unable to get master token"
            android.util.Log.e(r1, r2, r0)
            goto L_0x000f
        L_0x0063:
            com.google.firebase.iid.zze r4 = r0.zzabP()
            java.lang.String r0 = r4.zzabS()
            r3 = r0
        L_0x006c:
            if (r3 == 0) goto L_0x00d4
            java.lang.String r0 = "!"
            java.lang.String[] r0 = r3.split(r0)
            int r5 = r0.length
            r6 = 2
            if (r5 != r6) goto L_0x0087
            r5 = r0[r1]
            r6 = r0[r2]
            r0 = -1
            int r7 = r5.hashCode()     // Catch:{ IOException -> 0x00b7 }
            switch(r7) {
                case 83: goto L_0x0090;
                case 84: goto L_0x0084;
                case 85: goto L_0x009a;
                default: goto L_0x0084;
            }
        L_0x0084:
            switch(r0) {
                case 0: goto L_0x00a4;
                case 1: goto L_0x00c1;
                default: goto L_0x0087;
            }
        L_0x0087:
            r4.zzjx(r3)
            java.lang.String r0 = r4.zzabS()
            r3 = r0
            goto L_0x006c
        L_0x0090:
            java.lang.String r7 = "S"
            boolean r5 = r5.equals(r7)     // Catch:{ IOException -> 0x00b7 }
            if (r5 == 0) goto L_0x0084
            r0 = r1
            goto L_0x0084
        L_0x009a:
            java.lang.String r7 = "U"
            boolean r5 = r5.equals(r7)     // Catch:{ IOException -> 0x00b7 }
            if (r5 == 0) goto L_0x0084
            r0 = r2
            goto L_0x0084
        L_0x00a4:
            com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IOException -> 0x00b7 }
            r0.zzju(r6)     // Catch:{ IOException -> 0x00b7 }
            boolean r0 = r8.zzclq     // Catch:{ IOException -> 0x00b7 }
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r5 = "subscribe operation succeeded"
            android.util.Log.d(r0, r5)     // Catch:{ IOException -> 0x00b7 }
            goto L_0x0087
        L_0x00b7:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            r8.zzd(r9, r0)
            goto L_0x000f
        L_0x00c1:
            com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IOException -> 0x00b7 }
            r0.zzjv(r6)     // Catch:{ IOException -> 0x00b7 }
            boolean r0 = r8.zzclq     // Catch:{ IOException -> 0x00b7 }
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r5 = "unsubscribe operation succeeded"
            android.util.Log.d(r0, r5)     // Catch:{ IOException -> 0x00b7 }
            goto L_0x0087
        L_0x00d4:
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Intent, boolean, boolean):void");
    }

    private void zza(zzf zzf, Bundle bundle) {
        String zzbA = zzf.zzbA(this);
        if (zzbA == null) {
            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.setPackage(zzbA);
        intent.putExtras(bundle);
        zzf.zzp(intent);
        intent.putExtra("google.to", "google.com/iid");
        intent.putExtra("google.message_id", zzf.zzHn());
        sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    static void zzcs(Context context) {
        if (zzf.zzbA(context) != null) {
            synchronized (zzclo) {
                if (!zzclp) {
                    zzg.zzabU().zzf(context, zzqF(0));
                    zzclp = true;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean zzct(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void zzd(Intent intent, String str) {
        boolean zzct = zzct(this);
        final int zza = zza(intent, zzct);
        Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 47).append("background sync failed: ").append(str).append(", retry in ").append(zza).append("s").toString());
        synchronized (zzclo) {
            zzqG(zza);
            zzclp = true;
        }
        if (!zzct) {
            if (this.zzclq) {
                Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
            }
            if (zzcln == null) {
                zzcln = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (FirebaseInstanceIdService.zzct(context)) {
                            if (FirebaseInstanceIdService.this.zzclq) {
                                Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                            }
                            FirebaseInstanceIdService.this.getApplicationContext().unregisterReceiver(this);
                            zzg.zzabU().zzf(context, FirebaseInstanceIdService.zzqF(zza));
                        }
                    }
                };
            }
            getApplicationContext().registerReceiver(zzcln, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private zzd zzjw(String str) {
        if (str == null) {
            return zzd.zzb(this, null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzd.zzb(this, bundle);
    }

    /* access modifiers changed from: private */
    public static Intent zzqF(int i) {
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return intent;
    }

    private void zzqG(int i) {
        ((AlarmManager) getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) (i * 1000)), zzg.zza(this, 0, zzqF(i * 2), 134217728));
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(android.content.Intent r5) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = r5.getAction()
            if (r0 != 0) goto L_0x0009
            java.lang.String r0 = ""
        L_0x0009:
            r2 = -1
            int r3 = r0.hashCode()
            switch(r3) {
                case -1737547627: goto L_0x0019;
                default: goto L_0x0011;
            }
        L_0x0011:
            r0 = r2
        L_0x0012:
            switch(r0) {
                case 0: goto L_0x0023;
                default: goto L_0x0015;
            }
        L_0x0015:
            r4.zzF(r5)
        L_0x0018:
            return
        L_0x0019:
            java.lang.String r3 = "ACTION_TOKEN_REFRESH_RETRY"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0011
            r0 = r1
            goto L_0x0012
        L_0x0023:
            r4.zza(r5, r1, r1)
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.handleIntent(android.content.Intent):void");
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return zzg.zzabU().zzabV();
    }

    public boolean zzE(Intent intent) {
        this.zzclq = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return false;
        }
        String zzG = zzG(intent);
        if (this.zzclq) {
            String str = "FirebaseInstanceId";
            String str2 = "Register result in service ";
            String valueOf = String.valueOf(zzG);
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzjw(zzG).zzabR().zzs(intent);
        return true;
    }

    public void zzF(Intent intent) {
        String zzG = zzG(intent);
        zzd zzjw = zzjw(zzG);
        String stringExtra = intent.getStringExtra("CMD");
        if (this.zzclq) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(zzG).length() + 18 + String.valueOf(stringExtra).length() + String.valueOf(valueOf).length()).append("Service command ").append(zzG).append(" ").append(stringExtra).append(" ").append(valueOf).toString());
        }
        if (intent.getStringExtra("unregistered") != null) {
            zzh zzabQ = zzjw.zzabQ();
            if (zzG == null) {
                zzG = "";
            }
            zzabQ.zzeK(zzG);
            zzjw.zzabR().zzs(intent);
        } else if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
            zzjw.zzabQ().zzeK(zzG);
            zza(intent, false, true);
        } else if ("RST".equals(stringExtra)) {
            zzjw.zzHi();
            zza(intent, true, true);
        } else if ("RST_FULL".equals(stringExtra)) {
            if (!zzjw.zzabQ().isEmpty()) {
                zzjw.zzHi();
                zzjw.zzabQ().zzHo();
                zza(intent, true, true);
            }
        } else if ("SYNC".equals(stringExtra)) {
            zzjw.zzabQ().zzeK(zzG);
            zza(intent, false, true);
        } else if ("PING".equals(stringExtra)) {
            zza(zzjw.zzabR(), intent.getExtras());
        }
    }
}
