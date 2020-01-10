package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Executor zzbFM;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zzbNp;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbNq;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> zzh) {
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
                    Task task = (Task) zzb.this.zzbNp.then(task);
                    if (task == null) {
                        zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.zzbND, (OnSuccessListener<? super TResult>) zzb.this);
                    task.addOnFailureListener(TaskExecutors.zzbND, (OnFailureListener) zzb.this);
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zzb.this.zzbNq.setException((Exception) e.getCause());
                    } else {
                        zzb.this.zzbNq.setException(e);
                    }
                } catch (Exception e2) {
                    zzb.this.zzbNq.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exc) {
        this.zzbNq.setException(exc);
    }

    public void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzbNq.setResult(tcontinuationresult);
    }
}
