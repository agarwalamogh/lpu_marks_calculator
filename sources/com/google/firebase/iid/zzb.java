package com.google.firebase.iid;

import android.app.Service;
import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.util.Pair;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    @VisibleForTesting
    final ExecutorService zzbtI = Executors.newSingleThreadExecutor();
    private Binder zzckU;
    private int zzckV;
    private int zzckW = 0;
    private final Object zzrJ = new Object();

    public static class zza extends Binder {
        /* access modifiers changed from: private */
        public final zzb zzckZ;

        zza(zzb zzb) {
            this.zzckZ = zzb;
        }

        /* access modifiers changed from: private */
        public static void zza(@Nullable PendingResult pendingResult) {
            if (pendingResult != null) {
                pendingResult.finish();
            }
        }

        public void zza(final Intent intent, @Nullable final PendingResult pendingResult) {
            if (Binder.getCallingUid() != Process.myUid()) {
                throw new SecurityException("Binding only allowed within app");
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "service received new intent via bind strategy");
            }
            if (this.zzckZ.zzE(intent)) {
                zza(pendingResult);
                return;
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }
            this.zzckZ.zzbtI.execute(new Runnable() {
                public void run() {
                    if (Log.isLoggable("EnhancedIntentService", 3)) {
                        Log.d("EnhancedIntentService", "bg processing of the intent starting now");
                    }
                    zza.this.zzckZ.handleIntent(intent);
                    zza.zza(pendingResult);
                }
            });
        }
    }

    /* renamed from: com.google.firebase.iid.zzb$zzb reason: collision with other inner class name */
    public static class C1015zzb implements ServiceConnection {
        private final Intent zzclc;
        private final Queue<Pair<Intent, PendingResult>> zzcld = new LinkedList();
        private zza zzcle;
        private boolean zzclf = false;
        private final Context zzqn;

        public C1015zzb(Context context, String str) {
            this.zzqn = context.getApplicationContext();
            this.zzclc = new Intent(str).setPackage(this.zzqn.getPackageName());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0066, code lost:
            if (android.util.Log.isLoggable("EnhancedIntentService", 3) == false) goto L_0x0087;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
            r1 = "EnhancedIntentService";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
            if (r4.zzclf != false) goto L_0x00a0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
            r0 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
            android.util.Log.d(r1, "binder is dead. start connection? " + r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
            if (r4.zzclf != false) goto L_0x009e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
            r4.zzclf = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x009c, code lost:
            if (com.google.android.gms.common.stats.zza.zzyJ().zza(r4.zzqn, r4.zzclc, (android.content.ServiceConnection) r4, 65) == false) goto L_0x00a2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a0, code lost:
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
            android.util.Log.e("EnhancedIntentService", "binding to the service failed");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c1, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c2, code lost:
            android.util.Log.e("EnhancedIntentService", "Exception while binding the service", r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized void zzwH() {
            /*
                r4 = this;
                r2 = 1
                monitor-enter(r4)
                java.lang.String r0 = "EnhancedIntentService"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x0012
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r1 = "flush queue called"
                android.util.Log.d(r0, r1)     // Catch:{ all -> 0x005c }
            L_0x0012:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r4.zzcld     // Catch:{ all -> 0x005c }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x009e
                java.lang.String r0 = "EnhancedIntentService"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x002a
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r1 = "found intent to be delivered"
                android.util.Log.d(r0, r1)     // Catch:{ all -> 0x005c }
            L_0x002a:
                com.google.firebase.iid.zzb$zza r0 = r4.zzcle     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x005f
                com.google.firebase.iid.zzb$zza r0 = r4.zzcle     // Catch:{ all -> 0x005c }
                boolean r0 = r0.isBinderAlive()     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x005f
                java.lang.String r0 = "EnhancedIntentService"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x0046
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r1 = "binder is alive, sending the intent."
                android.util.Log.d(r0, r1)     // Catch:{ all -> 0x005c }
            L_0x0046:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r4.zzcld     // Catch:{ all -> 0x005c }
                java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x005c }
                android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x005c }
                com.google.firebase.iid.zzb$zza r3 = r4.zzcle     // Catch:{ all -> 0x005c }
                java.lang.Object r1 = r0.first     // Catch:{ all -> 0x005c }
                android.content.Intent r1 = (android.content.Intent) r1     // Catch:{ all -> 0x005c }
                java.lang.Object r0 = r0.second     // Catch:{ all -> 0x005c }
                android.content.BroadcastReceiver$PendingResult r0 = (android.content.BroadcastReceiver.PendingResult) r0     // Catch:{ all -> 0x005c }
                r3.zza(r1, r0)     // Catch:{ all -> 0x005c }
                goto L_0x0012
            L_0x005c:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            L_0x005f:
                java.lang.String r0 = "EnhancedIntentService"
                r1 = 3
                boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x005c }
                if (r0 == 0) goto L_0x0087
                java.lang.String r1 = "EnhancedIntentService"
                boolean r0 = r4.zzclf     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x00a0
                r0 = r2
            L_0x006f:
                r2 = 39
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
                r3.<init>(r2)     // Catch:{ all -> 0x005c }
                java.lang.String r2 = "binder is dead. start connection? "
                java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x005c }
                java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x005c }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005c }
                android.util.Log.d(r1, r0)     // Catch:{ all -> 0x005c }
            L_0x0087:
                boolean r0 = r4.zzclf     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x009e
                r0 = 1
                r4.zzclf = r0     // Catch:{ all -> 0x005c }
                com.google.android.gms.common.stats.zza r0 = com.google.android.gms.common.stats.zza.zzyJ()     // Catch:{ SecurityException -> 0x00c1 }
                android.content.Context r1 = r4.zzqn     // Catch:{ SecurityException -> 0x00c1 }
                android.content.Intent r2 = r4.zzclc     // Catch:{ SecurityException -> 0x00c1 }
                r3 = 65
                boolean r0 = r0.zza(r1, r2, r4, r3)     // Catch:{ SecurityException -> 0x00c1 }
                if (r0 == 0) goto L_0x00a2
            L_0x009e:
                monitor-exit(r4)
                return
            L_0x00a0:
                r0 = 0
                goto L_0x006f
            L_0x00a2:
                java.lang.String r0 = "EnhancedIntentService"
                java.lang.String r1 = "binding to the service failed"
                android.util.Log.e(r0, r1)     // Catch:{ SecurityException -> 0x00c1 }
            L_0x00a9:
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r4.zzcld     // Catch:{ all -> 0x005c }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x009e
                java.util.Queue<android.util.Pair<android.content.Intent, android.content.BroadcastReceiver$PendingResult>> r0 = r4.zzcld     // Catch:{ all -> 0x005c }
                java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x005c }
                android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x005c }
                java.lang.Object r0 = r0.second     // Catch:{ all -> 0x005c }
                android.content.BroadcastReceiver$PendingResult r0 = (android.content.BroadcastReceiver.PendingResult) r0     // Catch:{ all -> 0x005c }
                r0.finish()     // Catch:{ all -> 0x005c }
                goto L_0x00a9
            L_0x00c1:
                r0 = move-exception
                java.lang.String r1 = "EnhancedIntentService"
                java.lang.String r2 = "Exception while binding the service"
                android.util.Log.e(r1, r2, r0)     // Catch:{ all -> 0x005c }
                goto L_0x00a9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzb.C1015zzb.zzwH():void");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (this) {
                this.zzclf = false;
                this.zzcle = (zza) iBinder;
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    String valueOf = String.valueOf(componentName);
                    Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 20).append("onServiceConnected: ").append(valueOf).toString());
                }
                zzwH();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(valueOf).length() + 23).append("onServiceDisconnected: ").append(valueOf).toString());
            }
            zzwH();
        }

        public synchronized void zzb(Intent intent, PendingResult pendingResult) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }
            this.zzcld.add(new Pair(intent, pendingResult));
            zzwH();
        }
    }

    /* access modifiers changed from: private */
    public void zzC(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.zzrJ) {
            this.zzckW--;
            if (this.zzckW == 0) {
                zzqE(this.zzckV);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzckU == null) {
            this.zzckU = new zza(this);
        }
        return this.zzckU;
    }

    public final int onStartCommand(final Intent intent, int i, int i2) {
        synchronized (this.zzrJ) {
            this.zzckV = i2;
            this.zzckW++;
        }
        final Intent zzD = zzD(intent);
        if (zzD == null) {
            zzC(intent);
            return 2;
        } else if (zzE(zzD)) {
            zzC(intent);
            return 2;
        } else {
            this.zzbtI.execute(new Runnable() {
                public void run() {
                    zzb.this.handleIntent(zzD);
                    zzb.this.zzC(intent);
                }
            });
            return 3;
        }
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return intent;
    }

    public boolean zzE(Intent intent) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzqE(int i) {
        return stopSelfResult(i);
    }
}
