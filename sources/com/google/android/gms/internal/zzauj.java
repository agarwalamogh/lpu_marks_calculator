package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.zzb;
import com.google.android.gms.measurement.AppMeasurement.zzc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class zzauj extends zzauh {
    protected zza zzbuV;
    private zzb zzbuW;
    private final Set<zzc> zzbuX = new CopyOnWriteArraySet();
    private boolean zzbuY;
    private String zzbuZ = null;
    private String zzbva = null;

    @MainThread
    @TargetApi(14)
    private class zza implements ActivityLifecycleCallbacks {
        private zza() {
        }

        private boolean zzfR(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            zzauj.this.zzd("auto", "_ldl", str);
            return true;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            try {
                zzauj.this.zzKl().zzMe().log("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Uri data = intent.getData();
                    if (data != null && data.isHierarchical()) {
                        if (bundle == null) {
                            Bundle zzu = zzauj.this.zzKh().zzu(data);
                            String str = zzauj.this.zzKh().zzA(intent) ? "gs" : "auto";
                            if (zzu != null) {
                                zzauj.this.zze(str, "_cmp", zzu);
                            }
                        }
                        String queryParameter = data.getQueryParameter("referrer");
                        if (!TextUtils.isEmpty(queryParameter)) {
                            if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                                zzauj.this.zzKl().zzMd().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                                return;
                            } else {
                                zzauj.this.zzKl().zzMd().zzj("Activity created with referrer", queryParameter);
                                zzfR(queryParameter);
                            }
                        } else {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                zzauj.this.zzKl().zzLY().zzj("Throwable caught in onActivityCreated", th);
            }
            zzauj.this.zzKe().onActivityCreated(activity, bundle);
        }

        public void onActivityDestroyed(Activity activity) {
            zzauj.this.zzKe().onActivityDestroyed(activity);
        }

        @MainThread
        public void onActivityPaused(Activity activity) {
            zzauj.this.zzKe().onActivityPaused(activity);
            zzauj.this.zzKj().zzNe();
        }

        @MainThread
        public void onActivityResumed(Activity activity) {
            zzauj.this.zzKe().onActivityResumed(activity);
            zzauj.this.zzKj().zzNc();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzauj.this.zzKe().onActivitySaveInstanceState(activity, bundle);
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    protected zzauj(zzaue zzaue) {
        super(zzaue);
    }

    @WorkerThread
    private void zzMS() {
        try {
            zzf(Class.forName(zzMT()));
        } catch (ClassNotFoundException e) {
            zzKl().zzMc().log("Tag Manager is not found and thus will not be used");
        }
    }

    private String zzMT() {
        return "com.google.android.gms.tagmanager.TagManagerService";
    }

    private void zza(final ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zznR().currentTimeMillis();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mName);
        zzac.zzdr(conditionalUserProperty.mOrigin);
        zzac.zzw(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzKh().zzfX(str) != 0) {
            zzKl().zzLY().zzj("Invalid conditional user property name", str);
        } else if (zzKh().zzm(str, obj) != 0) {
            zzKl().zzLY().zze("Invalid conditional user property value", str, obj);
        } else {
            Object zzn = zzKh().zzn(str, obj);
            if (zzn == null) {
                zzKl().zzLY().zze("Unable to normalize conditional user property value", str, obj);
                return;
            }
            conditionalUserProperty.mValue = zzn;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (j > zzKn().zzLa() || j < 1) {
                zzKl().zzLY().zze("Invalid conditional user property timeout", str, Long.valueOf(j));
                return;
            }
            long j2 = conditionalUserProperty.mTimeToLive;
            if (j2 > zzKn().zzLb() || j2 < 1) {
                zzKl().zzLY().zze("Invalid conditional user property time to live", str, Long.valueOf(j2));
            } else {
                zzKk().zzm(new Runnable() {
                    public void run() {
                        zzauj.this.zzb(conditionalUserProperty);
                    }
                });
            }
        }
    }

    private void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zznR().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zza(String str, String str2, Object obj, long j) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzJW();
        zzob();
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("User property not set since app measurement is disabled");
        } else if (this.zzbqc.zzMt()) {
            zzKl().zzMd().zze("Setting user property (FE)", str2, obj);
            zzKd().zzb(new zzauq(str2, j, obj, str));
        }
    }

    private void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zznR().currentTimeMillis();
        zzac.zzdr(str2);
        final ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzc(conditionalUserProperty);
            }
        });
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzaM(boolean z) {
        zzmR();
        zzJW();
        zzob();
        zzKl().zzMd().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzKm().setMeasurementEnabled(z);
        zzKd().zzMW();
    }

    private Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            final boolean z2 = z;
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzbqc.zzKd().zza(atomicReference, str4, str5, str6, z2);
                }
            });
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzKl().zzMa().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzauq> list = (List) atomicReference.get();
        if (list == null) {
            zzKl().zzMa().log("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzauq zzauq : list) {
            arrayMap.put(zzauq.name, zzauq.getValue());
        }
        return arrayMap;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzb(ConditionalUserProperty conditionalUserProperty) {
        zzmR();
        zzob();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mName);
        zzac.zzdr(conditionalUserProperty.mOrigin);
        zzac.zzw(conditionalUserProperty.mValue);
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzauq zzauq = new zzauq(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
        try {
            zzatq zza2 = zzKh().zza(conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0, true, false);
            zzKd().zzf(new zzatg(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzauq, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzKh().zza(conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0, true, false), conditionalUserProperty.mTriggerTimeout, zza2, conditionalUserProperty.mTimeToLive, zzKh().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzac.zzw(bundle);
        zzmR();
        zzob();
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Event not sent since app measurement is disabled");
            return;
        }
        if (!this.zzbuY) {
            this.zzbuY = true;
            zzMS();
        }
        boolean equals = "am".equals(str);
        boolean zzgd = zzaut.zzgd(str2);
        if (z && this.zzbuW != null && !zzgd && !equals) {
            zzKl().zzMd().zze("Passing event to registered event handler (FE)", str2, bundle);
            this.zzbuW.zzb(str, str2, bundle, j);
        } else if (this.zzbqc.zzMt()) {
            int zzfV = zzKh().zzfV(str2);
            if (zzfV != 0) {
                this.zzbqc.zzKh().zza(zzfV, "_ev", zzKh().zza(str2, zzKn().zzKM(), true), str2 != null ? str2.length() : 0);
                return;
            }
            bundle.putString("_o", str);
            Bundle zza2 = zzKh().zza(str2, bundle, zzf.zzx("_o"), z3);
            if (!bundle.containsKey("_sc")) {
                zzKn().zzLg();
                zza zzMU = zzKe().zzMU();
                if (zzMU != null) {
                    zzMU.zzbvz = true;
                }
                zzauk.zza((AppMeasurement.zzf) zzMU, zza2);
            }
            Bundle bundle2 = z2 ? zzKh().zzM(zza2) : zza2;
            zzKl().zzMd().zze("Logging event (FE)", str2, bundle2);
            zzKd().zzc(new zzatq(str2, new zzato(bundle2), str, j), str3);
            if (!equals) {
                for (zzc zzc : this.zzbuX) {
                    zzc.zzc(str, str2, new Bundle(bundle2), j);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzc(ConditionalUserProperty conditionalUserProperty) {
        zzmR();
        zzob();
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mName);
        if (!this.zzbqc.isEnabled()) {
            zzKl().zzMd().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzauq zzauq = new zzauq(conditionalUserProperty.mName, 0, null, null);
        try {
            zzKd().zzf(new zzatg(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzauq, conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, zzKh().zza(conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    private List<ConditionalUserProperty> zzo(String str, String str2, String str3) {
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzbqc.zzKd().zza(atomicReference, str4, str5, str6);
                }
            });
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzKl().zzMa().zze("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzatg> list = (List) atomicReference.get();
        if (list == null) {
            zzKl().zzMa().zzj("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (zzatg zzatg : list) {
            ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
            conditionalUserProperty.mAppId = str;
            conditionalUserProperty.mOrigin = str2;
            conditionalUserProperty.mCreationTimestamp = zzatg.zzbqX;
            conditionalUserProperty.mName = zzatg.zzbqW.name;
            conditionalUserProperty.mValue = zzatg.zzbqW.getValue();
            conditionalUserProperty.mActive = zzatg.zzbqY;
            conditionalUserProperty.mTriggerEventName = zzatg.zzbqZ;
            if (zzatg.zzbra != null) {
                conditionalUserProperty.mTimedOutEventName = zzatg.zzbra.name;
                if (zzatg.zzbra.zzbrG != null) {
                    conditionalUserProperty.mTimedOutEventParams = zzatg.zzbra.zzbrG.zzLW();
                }
            }
            conditionalUserProperty.mTriggerTimeout = zzatg.zzbrb;
            if (zzatg.zzbrc != null) {
                conditionalUserProperty.mTriggeredEventName = zzatg.zzbrc.name;
                if (zzatg.zzbrc.zzbrG != null) {
                    conditionalUserProperty.mTriggeredEventParams = zzatg.zzbrc.zzbrG.zzLW();
                }
            }
            conditionalUserProperty.mTriggeredTimestamp = zzatg.zzbqW.zzbwc;
            conditionalUserProperty.mTimeToLive = zzatg.zzbrd;
            if (zzatg.zzbre != null) {
                conditionalUserProperty.mExpiredEventName = zzatg.zzbre.name;
                if (zzatg.zzbre.zzbrG != null) {
                    conditionalUserProperty.mExpiredEventParams = zzatg.zzbre.zzbrG.zzLW();
                }
            }
            arrayList.add(conditionalUserProperty);
        }
        return arrayList;
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzJW();
        zza((String) null, str, str2, bundle);
    }

    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        zzac.zzdr(str);
        zzJV();
        zza(str, str2, str3, bundle);
    }

    public Task<String> getAppInstanceId() {
        try {
            String zzMl = zzKm().zzMl();
            return zzMl != null ? Tasks.forResult(zzMl) : Tasks.call(zzKk().zzMr(), new Callable<String>() {
                /* renamed from: zzbY */
                public String call() throws Exception {
                    String zzMl = zzauj.this.zzKm().zzMl();
                    if (zzMl == null) {
                        zzMl = zzauj.this.zzKa().zzar(120000);
                        if (zzMl == null) {
                            throw new TimeoutException();
                        }
                        zzauj.this.zzKm().zzfJ(zzMl);
                    }
                    return zzMl;
                }
            });
        } catch (Exception e) {
            zzKl().zzMa().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzJW();
        return zzo(null, str, str2);
    }

    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        zzac.zzdr(str);
        zzJV();
        return zzo(str, str2, str3);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public int getMaxUserProperties(String str) {
        zzac.zzdr(str);
        return zzKn().zzKY();
    }

    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzJW();
        return zzb(null, str, str2, z);
    }

    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        zzac.zzdr(str);
        zzJV();
        return zzb(str, str2, str3, z);
    }

    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        zzac.zzw(conditionalUserProperty);
        zzJW();
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzKl().zzMa().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        zzac.zzw(conditionalUserProperty);
        zzac.zzdr(conditionalUserProperty.mAppId);
        zzJV();
        zza(new ConditionalUserProperty(conditionalUserProperty));
    }

    public void setMeasurementEnabled(final boolean z) {
        zzob();
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzaM(z);
            }
        });
    }

    public void setMinimumSessionDuration(final long j) {
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzKm().zzbtl.set(j);
                zzauj.this.zzKl().zzMd().zzj("Minimum session duration set", Long.valueOf(j));
            }
        });
    }

    public void setSessionTimeoutDuration(final long j) {
        zzJW();
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzKm().zzbtm.set(j);
                zzauj.this.zzKl().zzMd().zzj("Session timeout duration set", Long.valueOf(j));
            }
        });
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

    @TargetApi(14)
    public void zzMQ() {
        if (getContext().getApplicationContext() instanceof Application) {
            Application application = (Application) getContext().getApplicationContext();
            if (this.zzbuV == null) {
                this.zzbuV = new zza();
            }
            application.unregisterActivityLifecycleCallbacks(this.zzbuV);
            application.registerActivityLifecycleCallbacks(this.zzbuV);
            zzKl().zzMe().log("Registered activity lifecycle callback");
        }
    }

    @WorkerThread
    public void zzMR() {
        zzmR();
        zzJW();
        zzob();
        if (this.zzbqc.zzMt()) {
            zzKd().zzMR();
            String zzMo = zzKm().zzMo();
            if (!TextUtils.isEmpty(zzMo) && !zzMo.equals(zzKc().zzLS())) {
                Bundle bundle = new Bundle();
                bundle.putString("_po", zzMo);
                zze("auto", "_ou", bundle);
            }
        }
    }

    @WorkerThread
    public void zza(zzb zzb) {
        zzmR();
        zzJW();
        zzob();
        if (!(zzb == null || zzb == this.zzbuW)) {
            zzac.zza(this.zzbuW == null, (Object) "EventInterceptor already set.");
        }
        this.zzbuW = zzb;
    }

    public void zza(zzc zzc) {
        zzJW();
        zzob();
        zzac.zzw(zzc);
        if (!this.zzbuX.add(zzc)) {
            zzKl().zzMa().log("OnEventListener already registered");
        }
    }

    /* access modifiers changed from: protected */
    public void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        final Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        final String str4 = str;
        final String str5 = str2;
        final long j2 = j;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        final String str6 = str3;
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zzb(str4, str5, j2, bundle2, z4, z5, z6, str6);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void zza(String str, String str2, long j, Object obj) {
        final String str3 = str;
        final String str4 = str2;
        final Object obj2 = obj;
        final long j2 = j;
        zzKk().zzm(new Runnable() {
            public void run() {
                zzauj.this.zza(str3, str4, obj2, j2);
            }
        });
    }

    public void zza(String str, String str2, Bundle bundle, boolean z) {
        zzJW();
        zza(str, str2, bundle, true, this.zzbuW == null || zzaut.zzgd(str2), z, null);
    }

    public List<zzauq> zzaN(final boolean z) {
        zzJW();
        zzob();
        zzKl().zzMd().log("Fetching user attributes (FE)");
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzbqc.zzKk().zzm(new Runnable() {
                public void run() {
                    zzauj.this.zzKd().zza(atomicReference, z);
                }
            });
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzKl().zzMa().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzauq> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzKl().zzMa().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    @Nullable
    public String zzar(long j) {
        if (zzKk().zzMq()) {
            zzKl().zzLY().log("Cannot retrieve app instance id from analytics worker thread");
            return null;
        } else if (zzKk().zzbc()) {
            zzKl().zzLY().log("Cannot retrieve app instance id from main thread");
            return null;
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                zzKk().zzm(new Runnable() {
                    public void run() {
                        zzauj.this.zzKd().zza(atomicReference);
                    }
                });
                try {
                    atomicReference.wait(j);
                } catch (InterruptedException e) {
                    zzKl().zzMa().log("Interrupted waiting for app instance id");
                    return null;
                }
            }
            return (String) atomicReference.get();
        }
    }

    public void zzd(String str, String str2, Bundle bundle, long j) {
        zzJW();
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public void zzd(String str, String str2, Object obj) {
        int i = 0;
        zzac.zzdr(str);
        long currentTimeMillis = zznR().currentTimeMillis();
        int zzfX = zzKh().zzfX(str2);
        if (zzfX != 0) {
            String zza2 = zzKh().zza(str2, zzKn().zzKN(), true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzbqc.zzKh().zza(zzfX, "_ev", zza2, i);
        } else if (obj != null) {
            int zzm = zzKh().zzm(str2, obj);
            if (zzm != 0) {
                String zza3 = zzKh().zza(str2, zzKn().zzKN(), true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzbqc.zzKh().zza(zzm, "_ev", zza3, i);
                return;
            }
            Object zzn = zzKh().zzn(str2, obj);
            if (zzn != null) {
                zza(str, str2, currentTimeMillis, zzn);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public void zze(String str, String str2, Bundle bundle) {
        zzJW();
        zza(str, str2, bundle, true, this.zzbuW == null || zzaut.zzgd(str2), false, null);
    }

    @WorkerThread
    public void zzf(Class<?> cls) {
        try {
            cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{getContext()});
        } catch (Exception e) {
            zzKl().zzMa().zzj("Failed to invoke Tag Manager's initialize() method", e);
        }
    }

    @Nullable
    @WorkerThread
    public synchronized String zzfQ(String str) {
        String str2;
        zzob();
        zzJW();
        if (str == null || !str.equals(this.zzbva)) {
            String zzar = zzar(30000);
            if (zzar == null) {
                str2 = null;
            } else {
                this.zzbva = str;
                this.zzbuZ = zzar;
                str2 = this.zzbuZ;
            }
        } else {
            str2 = this.zzbuZ;
        }
        return str2;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }
}
