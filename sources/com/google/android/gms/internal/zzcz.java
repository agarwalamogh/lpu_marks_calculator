package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

class zzcz implements ActivityLifecycleCallbacks {
    private final Application zzwS;
    private final WeakReference<ActivityLifecycleCallbacks> zzxm;
    private boolean zzxn = false;

    public interface zza {
        void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks);
    }

    public zzcz(Application application, ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzxm = new WeakReference<>(activityLifecycleCallbacks);
        this.zzwS = application;
    }

    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityCreated(activity, bundle);
            }
        });
    }

    public void onActivityDestroyed(final Activity activity) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityDestroyed(activity);
            }
        });
    }

    public void onActivityPaused(final Activity activity) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityPaused(activity);
            }
        });
    }

    public void onActivityResumed(final Activity activity) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityResumed(activity);
            }
        });
    }

    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivitySaveInstanceState(activity, bundle);
            }
        });
    }

    public void onActivityStarted(final Activity activity) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityStarted(activity);
            }
        });
    }

    public void onActivityStopped(final Activity activity) {
        zza(new zza(this) {
            public void zza(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
                activityLifecycleCallbacks.onActivityStopped(activity);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(zza zza2) {
        try {
            ActivityLifecycleCallbacks activityLifecycleCallbacks = (ActivityLifecycleCallbacks) this.zzxm.get();
            if (activityLifecycleCallbacks != null) {
                zza2.zza(activityLifecycleCallbacks);
            } else if (!this.zzxn) {
                this.zzwS.unregisterActivityLifecycleCallbacks(this);
                this.zzxn = true;
            }
        } catch (Exception e) {
            zzpk.zzb("Error while dispatching lifecycle callback.", e);
        }
    }
}
