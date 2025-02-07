package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Executor zzbFM;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zzbNp;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbNq;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.zzbFM = executor;
        this.zzbNp = continuation;
        this.zzbNq = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.zzbFM.execute(new Runnable() {
            public void run() {
                try {
                    zza.this.zzbNq.setResult(zza.this.zzbNp.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zza.this.zzbNq.setException((Exception) e.getCause());
                    } else {
                        zza.this.zzbNq.setException(e);
                    }
                } catch (Exception e2) {
                    zza.this.zzbNq.setException(e2);
                }
            }
        });
    }
}
