package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class zzaud extends zzauh {
    /* access modifiers changed from: private */
    public static final AtomicLong zzbtS = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzbtI;
    /* access modifiers changed from: private */
    public zzd zzbtJ;
    /* access modifiers changed from: private */
    public zzd zzbtK;
    private final PriorityBlockingQueue<FutureTask<?>> zzbtL = new PriorityBlockingQueue<>();
    private final BlockingQueue<FutureTask<?>> zzbtM = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzbtN = new zzb("Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzbtO = new zzb("Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzbtP = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzbtQ = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzbtR;

    static class zza extends RuntimeException {
    }

    private final class zzb implements UncaughtExceptionHandler {
        private final String zzbtT;

        public zzb(String str) {
            zzac.zzw(str);
            this.zzbtT = str;
        }

        public synchronized void uncaughtException(Thread thread, Throwable th) {
            zzaud.this.zzKl().zzLY().zzj(this.zzbtT, th);
        }
    }

    private final class zzc<V> extends FutureTask<V> implements Comparable<zzc> {
        private final String zzbtT;
        private final long zzbtV = zzaud.zzbtS.getAndIncrement();
        private final boolean zzbtW;

        zzc(Runnable runnable, boolean z, String str) {
            super(runnable, null);
            zzac.zzw(str);
            this.zzbtT = str;
            this.zzbtW = z;
            if (this.zzbtV == Long.MAX_VALUE) {
                zzaud.this.zzKl().zzLY().log("Tasks index overflow");
            }
        }

        zzc(Callable<V> callable, boolean z, String str) {
            super(callable);
            zzac.zzw(str);
            this.zzbtT = str;
            this.zzbtW = z;
            if (this.zzbtV == Long.MAX_VALUE) {
                zzaud.this.zzKl().zzLY().log("Tasks index overflow");
            }
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            zzaud.this.zzKl().zzLY().zzj(this.zzbtT, th);
            if (th instanceof zza) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
            super.setException(th);
        }

        /* renamed from: zzb */
        public int compareTo(@NonNull zzc zzc) {
            if (this.zzbtW != zzc.zzbtW) {
                return this.zzbtW ? -1 : 1;
            }
            if (this.zzbtV < zzc.zzbtV) {
                return -1;
            }
            if (this.zzbtV > zzc.zzbtV) {
                return 1;
            }
            zzaud.this.zzKl().zzLZ().zzj("Two tasks share the same index. index", Long.valueOf(this.zzbtV));
            return 0;
        }
    }

    private final class zzd extends Thread {
        private final Object zzbtX = new Object();
        private final BlockingQueue<FutureTask<?>> zzbtY;

        public zzd(String str, BlockingQueue<FutureTask<?>> blockingQueue) {
            zzac.zzw(str);
            zzac.zzw(blockingQueue);
            this.zzbtY = blockingQueue;
            setName(str);
        }

        private void zza(InterruptedException interruptedException) {
            zzaud.this.zzKl().zzMa().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
            r1 = com.google.android.gms.internal.zzaud.zzc(r4.zzbtU);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x007e, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
            com.google.android.gms.internal.zzaud.zza(r4.zzbtU).release();
            com.google.android.gms.internal.zzaud.zzc(r4.zzbtU).notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0097, code lost:
            if (r4 != com.google.android.gms.internal.zzaud.zzd(r4.zzbtU)) goto L_0x00a9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0099, code lost:
            com.google.android.gms.internal.zzaud.zza(r4.zzbtU, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x009f, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a0, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00af, code lost:
            if (r4 != com.google.android.gms.internal.zzaud.zze(r4.zzbtU)) goto L_0x00bb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b1, code lost:
            com.google.android.gms.internal.zzaud.zzb(r4.zzbtU, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            r4.zzbtU.zzKl().zzLY().log("Current scheduler thread is neither worker nor network");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r4 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                if (r1 != 0) goto L_0x0015
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ InterruptedException -> 0x0010 }
                java.util.concurrent.Semaphore r0 = r0.zzbtQ     // Catch:{ InterruptedException -> 0x0010 }
                r0.acquire()     // Catch:{ InterruptedException -> 0x0010 }
                r0 = 1
                r1 = r0
                goto L_0x0002
            L_0x0010:
                r0 = move-exception
                r4.zza(r0)
                goto L_0x0002
            L_0x0015:
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r0 = r4.zzbtY     // Catch:{ all -> 0x0023 }
                java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0023 }
                java.util.concurrent.FutureTask r0 = (java.util.concurrent.FutureTask) r0     // Catch:{ all -> 0x0023 }
                if (r0 == 0) goto L_0x004d
                r0.run()     // Catch:{ all -> 0x0023 }
                goto L_0x0015
            L_0x0023:
                r0 = move-exception
                com.google.android.gms.internal.zzaud r1 = com.google.android.gms.internal.zzaud.this
                java.lang.Object r1 = r1.zzbtP
                monitor-enter(r1)
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                java.util.concurrent.Semaphore r2 = r2.zzbtQ     // Catch:{ all -> 0x00e1 }
                r2.release()     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                java.lang.Object r2 = r2.zzbtP     // Catch:{ all -> 0x00e1 }
                r2.notifyAll()     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzaud$zzd r2 = r2.zzbtJ     // Catch:{ all -> 0x00e1 }
                if (r4 != r2) goto L_0x00d1
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                r3 = 0
                r2.zzbtJ = r3     // Catch:{ all -> 0x00e1 }
            L_0x004b:
                monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
                throw r0
            L_0x004d:
                java.lang.Object r1 = r4.zzbtX     // Catch:{ all -> 0x0023 }
                monitor-enter(r1)     // Catch:{ all -> 0x0023 }
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r0 = r4.zzbtY     // Catch:{ all -> 0x00a6 }
                java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x00a6 }
                if (r0 != 0) goto L_0x0067
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00a6 }
                boolean r0 = r0.zzbtR     // Catch:{ all -> 0x00a6 }
                if (r0 != 0) goto L_0x0067
                java.lang.Object r0 = r4.zzbtX     // Catch:{ InterruptedException -> 0x00a1 }
                r2 = 30000(0x7530, double:1.4822E-319)
                r0.wait(r2)     // Catch:{ InterruptedException -> 0x00a1 }
            L_0x0067:
                monitor-exit(r1)     // Catch:{ all -> 0x00a6 }
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x0023 }
                java.lang.Object r1 = r0.zzbtP     // Catch:{ all -> 0x0023 }
                monitor-enter(r1)     // Catch:{ all -> 0x0023 }
                java.util.concurrent.BlockingQueue<java.util.concurrent.FutureTask<?>> r0 = r4.zzbtY     // Catch:{ all -> 0x00ce }
                java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x00ce }
                if (r0 != 0) goto L_0x00cb
                monitor-exit(r1)     // Catch:{ all -> 0x00ce }
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this
                java.lang.Object r1 = r0.zzbtP
                monitor-enter(r1)
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                java.util.concurrent.Semaphore r0 = r0.zzbtQ     // Catch:{ all -> 0x00b8 }
                r0.release()     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                java.lang.Object r0 = r0.zzbtP     // Catch:{ all -> 0x00b8 }
                r0.notifyAll()     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzaud$zzd r0 = r0.zzbtJ     // Catch:{ all -> 0x00b8 }
                if (r4 != r0) goto L_0x00a9
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                r2 = 0
                r0.zzbtJ = r2     // Catch:{ all -> 0x00b8 }
            L_0x009f:
                monitor-exit(r1)     // Catch:{ all -> 0x00b8 }
                return
            L_0x00a1:
                r0 = move-exception
                r4.zza(r0)     // Catch:{ all -> 0x00a6 }
                goto L_0x0067
            L_0x00a6:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00a6 }
                throw r0     // Catch:{ all -> 0x0023 }
            L_0x00a9:
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzaud$zzd r0 = r0.zzbtK     // Catch:{ all -> 0x00b8 }
                if (r4 != r0) goto L_0x00bb
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                r2 = 0
                r0.zzbtK = r2     // Catch:{ all -> 0x00b8 }
                goto L_0x009f
            L_0x00b8:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00b8 }
                throw r0
            L_0x00bb:
                com.google.android.gms.internal.zzaud r0 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ all -> 0x00b8 }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ all -> 0x00b8 }
                java.lang.String r2 = "Current scheduler thread is neither worker nor network"
                r0.log(r2)     // Catch:{ all -> 0x00b8 }
                goto L_0x009f
            L_0x00cb:
                monitor-exit(r1)     // Catch:{ all -> 0x00ce }
                goto L_0x0015
            L_0x00ce:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00ce }
                throw r0     // Catch:{ all -> 0x0023 }
            L_0x00d1:
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzaud$zzd r2 = r2.zzbtK     // Catch:{ all -> 0x00e1 }
                if (r4 != r2) goto L_0x00e4
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                r3 = 0
                r2.zzbtK = r3     // Catch:{ all -> 0x00e1 }
                goto L_0x004b
            L_0x00e1:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
                throw r0
            L_0x00e4:
                com.google.android.gms.internal.zzaud r2 = com.google.android.gms.internal.zzaud.this     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzatx r2 = r2.zzKl()     // Catch:{ all -> 0x00e1 }
                com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00e1 }
                java.lang.String r3 = "Current scheduler thread is neither worker nor network"
                r2.log(r3)     // Catch:{ all -> 0x00e1 }
                goto L_0x004b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaud.zzd.run():void");
        }

        public void zzhA() {
            synchronized (this.zzbtX) {
                this.zzbtX.notifyAll();
            }
        }
    }

    zzaud(zzaue zzaue) {
        super(zzaue);
    }

    private void zza(zzc<?> zzc2) {
        synchronized (this.zzbtP) {
            this.zzbtL.add(zzc2);
            if (this.zzbtJ == null) {
                this.zzbtJ = new zzd("Measurement Worker", this.zzbtL);
                this.zzbtJ.setUncaughtExceptionHandler(this.zzbtN);
                this.zzbtJ.start();
            } else {
                this.zzbtJ.zzhA();
            }
        }
    }

    private void zza(FutureTask<?> futureTask) {
        synchronized (this.zzbtP) {
            this.zzbtM.add(futureTask);
            if (this.zzbtK == null) {
                this.zzbtK = new zzd("Measurement Network", this.zzbtM);
                this.zzbtK.setUncaughtExceptionHandler(this.zzbtO);
                this.zzbtK.start();
            } else {
                this.zzbtK.zzhA();
            }
        }
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

    public void zzJX() {
        if (Thread.currentThread() != this.zzbtK) {
            throw new IllegalStateException("Call expected from network thread");
        }
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

    public boolean zzMq() {
        return Thread.currentThread() == this.zzbtJ;
    }

    /* access modifiers changed from: 0000 */
    public ExecutorService zzMr() {
        ExecutorService executorService;
        synchronized (this.zzbtP) {
            if (this.zzbtI == null) {
                this.zzbtI = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzbtI;
        }
        return executorService;
    }

    public boolean zzbc() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzob();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbtJ) {
            zzc2.run();
        } else {
            zza(zzc2);
        }
        return zzc2;
    }

    public <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzob();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbtJ) {
            zzc2.run();
        } else {
            zza(zzc2);
        }
        return zzc2;
    }

    public void zzm(Runnable runnable) throws IllegalStateException {
        zzob();
        zzac.zzw(runnable);
        zza(new zzc<>(runnable, false, "Task exception on worker thread"));
    }

    public void zzmR() {
        if (Thread.currentThread() != this.zzbtJ) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public void zzn(Runnable runnable) throws IllegalStateException {
        zzob();
        zzac.zzw(runnable);
        zza((FutureTask<?>) new zzc<Object>(runnable, false, "Task exception on network thread"));
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }
}
