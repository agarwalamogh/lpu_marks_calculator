package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;
import com.google.android.gms.common.zze;
import com.google.android.gms.measurement.AppMeasurement.zzf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzaul extends zzauh {
    /* access modifiers changed from: private */
    public final zza zzbvA;
    /* access modifiers changed from: private */
    public zzatt zzbvB;
    private Boolean zzbvC;
    private final zzatk zzbvD;
    private final zzauo zzbvE;
    private final List<Runnable> zzbvF = new ArrayList();
    private final zzatk zzbvG;

    protected class zza implements ServiceConnection, zzb, zzc {
        /* access modifiers changed from: private */
        public volatile boolean zzbvO;
        private volatile zzatw zzbvP;

        protected zza() {
        }

        @MainThread
        public void onConnected(@Nullable Bundle bundle) {
            zzac.zzdj("MeasurementServiceConnection.onConnected");
            synchronized (this) {
                try {
                    final zzatt zzatt = (zzatt) this.zzbvP.zzxD();
                    this.zzbvP = null;
                    zzaul.this.zzKk().zzm(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                zza.this.zzbvO = false;
                                if (!zzaul.this.isConnected()) {
                                    zzaul.this.zzKl().zzMd().log("Connected to remote service");
                                    zzaul.this.zza(zzatt);
                                }
                            }
                        }
                    });
                } catch (DeadObjectException | IllegalStateException e) {
                    this.zzbvP = null;
                    this.zzbvO = false;
                }
            }
        }

        @MainThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzac.zzdj("MeasurementServiceConnection.onConnectionFailed");
            zzatx zzMu = zzaul.this.zzbqc.zzMu();
            if (zzMu != null) {
                zzMu.zzMa().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.zzbvO = false;
                this.zzbvP = null;
            }
        }

        @MainThread
        public void onConnectionSuspended(int i) {
            zzac.zzdj("MeasurementServiceConnection.onConnectionSuspended");
            zzaul.this.zzKl().zzMd().log("Service connection suspended");
            zzaul.this.zzKk().zzm(new Runnable() {
                public void run() {
                    zzaul zzaul = zzaul.this;
                    Context context = zzaul.this.getContext();
                    zzaul.this.zzKn().zzLg();
                    zzaul.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                }
            });
        }

        @MainThread
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzac.zzdj("MeasurementServiceConnection.onServiceConnected");
            synchronized (this) {
                if (iBinder == null) {
                    this.zzbvO = false;
                    zzaul.this.zzKl().zzLY().log("Service connected with null binder");
                    return;
                }
                final zzatt zzatt = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                        zzatt = com.google.android.gms.internal.zzatt.zza.zzes(iBinder);
                        zzaul.this.zzKl().zzMe().log("Bound to IMeasurementService interface");
                    } else {
                        zzaul.this.zzKl().zzLY().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().log("Service connect failed to get IMeasurementService");
                }
                if (zzatt == null) {
                    this.zzbvO = false;
                    try {
                        com.google.android.gms.common.stats.zza.zzyJ().zza(zzaul.this.getContext(), zzaul.this.zzbvA);
                    } catch (IllegalArgumentException e2) {
                    }
                } else {
                    zzaul.this.zzKk().zzm(new Runnable() {
                        public void run() {
                            synchronized (zza.this) {
                                zza.this.zzbvO = false;
                                if (!zzaul.this.isConnected()) {
                                    zzaul.this.zzKl().zzMe().log("Connected to service");
                                    zzaul.this.zza(zzatt);
                                }
                            }
                        }
                    });
                }
            }
        }

        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzac.zzdj("MeasurementServiceConnection.onServiceDisconnected");
            zzaul.this.zzKl().zzMd().log("Service disconnected");
            zzaul.this.zzKk().zzm(new Runnable() {
                public void run() {
                    zzaul.this.onServiceDisconnected(componentName);
                }
            });
        }

        @WorkerThread
        public void zzNb() {
            zzaul.this.zzmR();
            Context context = zzaul.this.getContext();
            synchronized (this) {
                if (this.zzbvO) {
                    zzaul.this.zzKl().zzMe().log("Connection attempt already in progress");
                } else if (this.zzbvP != null) {
                    zzaul.this.zzKl().zzMe().log("Already awaiting connection attempt");
                } else {
                    this.zzbvP = new zzatw(context, Looper.getMainLooper(), this, this);
                    zzaul.this.zzKl().zzMe().log("Connecting to remote service");
                    this.zzbvO = true;
                    this.zzbvP.zzxz();
                }
            }
        }

        @WorkerThread
        public void zzz(Intent intent) {
            zzaul.this.zzmR();
            Context context = zzaul.this.getContext();
            com.google.android.gms.common.stats.zza zzyJ = com.google.android.gms.common.stats.zza.zzyJ();
            synchronized (this) {
                if (this.zzbvO) {
                    zzaul.this.zzKl().zzMe().log("Connection attempt already in progress");
                    return;
                }
                this.zzbvO = true;
                zzyJ.zza(context, intent, (ServiceConnection) zzaul.this.zzbvA, 129);
            }
        }
    }

    protected zzaul(zzaue zzaue) {
        super(zzaue);
        this.zzbvE = new zzauo(zzaue.zznR());
        this.zzbvA = new zza();
        this.zzbvD = new zzatk(zzaue) {
            public void run() {
                zzaul.this.zzop();
            }
        };
        this.zzbvG = new zzatk(zzaue) {
            public void run() {
                zzaul.this.zzKl().zzMa().log("Tasks have been queued for a long time");
            }
        };
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void onServiceDisconnected(ComponentName componentName) {
        zzmR();
        if (this.zzbvB != null) {
            this.zzbvB = null;
            zzKl().zzMe().zzj("Disconnected from device MeasurementService", componentName);
            zzMZ();
        }
    }

    private boolean zzMX() {
        zzKn().zzLg();
        List queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    @WorkerThread
    private void zzMZ() {
        zzmR();
        zzoD();
    }

    @WorkerThread
    private void zzNa() {
        zzmR();
        zzKl().zzMe().zzj("Processing queued up service tasks", Integer.valueOf(this.zzbvF.size()));
        for (Runnable zzm : this.zzbvF) {
            zzKk().zzm(zzm);
        }
        this.zzbvF.clear();
        this.zzbvG.cancel();
    }

    @WorkerThread
    private void zzo(Runnable runnable) throws IllegalStateException {
        zzmR();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzbvF.size()) >= zzKn().zzLm()) {
            zzKl().zzLY().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzbvF.add(runnable);
            this.zzbvG.zzy(60000);
            zzoD();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzoo() {
        zzmR();
        this.zzbvE.start();
        this.zzbvD.zzy(zzKn().zzpq());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public void zzop() {
        zzmR();
        if (isConnected()) {
            zzKl().zzMe().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    public void disconnect() {
        zzmR();
        zzob();
        try {
            com.google.android.gms.common.stats.zza.zzyJ().zza(getContext(), this.zzbvA);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzbvB = null;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public boolean isConnected() {
        zzmR();
        zzob();
        return this.zzbvB != null;
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

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzMR() {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send app launch");
                    return;
                }
                try {
                    zzc.zza(zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) null);
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send app launch to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzMW() {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Failed to send measurementEnabled to service");
                    return;
                }
                try {
                    zzc.zzb(zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send measurementEnabled to the service", e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzMY() {
        zzmR();
        zzob();
        zzKn().zzLg();
        zzKl().zzMe().log("Checking service availability");
        switch (zze.zzuY().isGooglePlayServicesAvailable(getContext())) {
            case 0:
                zzKl().zzMe().log("Service available");
                return true;
            case 1:
                zzKl().zzMe().log("Service missing");
                return false;
            case 2:
                zzKl().zzMd().log("Service container out of date");
                return true;
            case 3:
                zzKl().zzMa().log("Service disabled");
                return false;
            case 9:
                zzKl().zzMa().log("Service invalid");
                return false;
            case 18:
                zzKl().zzMa().log("Service updating");
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(zzatt zzatt) {
        zzmR();
        zzac.zzw(zzatt);
        this.zzbvB = zzatt;
        zzoo();
        zzNa();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zza(zzatt zzatt, com.google.android.gms.common.internal.safeparcel.zza zza2) {
        zzmR();
        zzJW();
        zzob();
        int i = VERSION.SDK_INT;
        zzKn().zzLg();
        ArrayList<com.google.android.gms.common.internal.safeparcel.zza> arrayList = new ArrayList<>();
        zzKn().zzLp();
        int i2 = 100;
        for (int i3 = 0; i3 < 1001 && i2 == 100; i3++) {
            List zzlD = zzKf().zzlD(100);
            if (zzlD != null) {
                arrayList.addAll(zzlD);
                i2 = zzlD.size();
            } else {
                i2 = 0;
            }
            if (zza2 != null && i2 < 100) {
                arrayList.add(zza2);
            }
            for (com.google.android.gms.common.internal.safeparcel.zza zza3 : arrayList) {
                if (zza3 instanceof zzatq) {
                    try {
                        zzatt.zza((zzatq) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e) {
                        zzKl().zzLY().zzj("Failed to send event to the service", e);
                    }
                } else if (zza3 instanceof zzauq) {
                    try {
                        zzatt.zza((zzauq) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e2) {
                        zzKl().zzLY().zzj("Failed to send attribute to the service", e2);
                    }
                } else if (zza3 instanceof zzatg) {
                    try {
                        zzatt.zza((zzatg) zza3, zzKb().zzfD(zzKl().zzMf()));
                    } catch (RemoteException e3) {
                        zzKl().zzLY().zzj("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzKl().zzLY().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final zzf zzf) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Failed to send current screen to service");
                    return;
                }
                try {
                    if (zzf == null) {
                        zzc.zza(0, (String) null, (String) null, zzaul.this.getContext().getPackageName());
                    } else {
                        zzc.zza(zzf.zzbqh, zzf.zzbqf, zzf.zzbqg, zzaul.this.getContext().getPackageName());
                    }
                    zzaul.this.zzoo();
                } catch (RemoteException e) {
                    zzaul.this.zzKl().zzLY().zzj("Failed to send current screen to the service", e);
                }
            }
        });
    }

    @WorkerThread
    public void zza(final AtomicReference<String> atomicReference) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().log("Failed to get app instance id");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zzc(zzaul.this.zzKb().zzfD(null)));
                        zzaul.this.zzoo();
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to get app instance id", e);
                        atomicReference.notify();
                    } catch (Throwable th) {
                        atomicReference.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(AtomicReference<List<zzatg>> atomicReference, String str, String str2, String str3) {
        zzmR();
        zzob();
        final AtomicReference<List<zzatg>> atomicReference2 = atomicReference;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference2) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get conditional properties", zzatx.zzfE(str4), str5, str6);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference2.notify();
                            return;
                        }
                        if (TextUtils.isEmpty(str4)) {
                            atomicReference2.set(zzc.zza(str5, str6, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf())));
                        } else {
                            atomicReference2.set(zzc.zzn(str4, str5, str6));
                        }
                        zzaul.this.zzoo();
                        atomicReference2.notify();
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzd("Failed to get conditional properties", zzatx.zzfE(str4), str5, e);
                        atomicReference2.set(Collections.emptyList());
                        atomicReference2.notify();
                    } catch (Throwable th) {
                        atomicReference2.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(AtomicReference<List<zzauq>> atomicReference, String str, String str2, String str3, boolean z) {
        zzmR();
        zzob();
        final AtomicReference<List<zzauq>> atomicReference2 = atomicReference;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference2) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().zzd("Failed to get user properties", zzatx.zzfE(str4), str5, str6);
                            atomicReference2.set(Collections.emptyList());
                            atomicReference2.notify();
                            return;
                        }
                        if (TextUtils.isEmpty(str4)) {
                            atomicReference2.set(zzc.zza(str5, str6, z2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf())));
                        } else {
                            atomicReference2.set(zzc.zza(str4, str5, str6, z2));
                        }
                        zzaul.this.zzoo();
                        atomicReference2.notify();
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzd("Failed to get user properties", zzatx.zzfE(str4), str5, e);
                        atomicReference2.set(Collections.emptyList());
                        atomicReference2.notify();
                    } catch (Throwable th) {
                        atomicReference2.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(final AtomicReference<List<zzauq>> atomicReference, final boolean z) {
        zzmR();
        zzob();
        zzo(new Runnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                synchronized (atomicReference) {
                    try {
                        zzatt zzc = zzaul.this.zzbvB;
                        if (zzc == null) {
                            zzaul.this.zzKl().zzLY().log("Failed to get user properties");
                            atomicReference.notify();
                            return;
                        }
                        atomicReference.set(zzc.zza(zzaul.this.zzKb().zzfD(null), z));
                        zzaul.this.zzoo();
                        atomicReference.notify();
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to get user properties", e);
                        atomicReference.notify();
                    } catch (Throwable th) {
                        atomicReference.notify();
                        throw th;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzb(final zzauq zzauq) {
        zzmR();
        zzob();
        int i = VERSION.SDK_INT;
        zzKn().zzLg();
        final boolean z = zzKf().zza(zzauq);
        zzo(new Runnable() {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to set user attribute");
                    return;
                }
                zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) z ? null : zzauq);
                zzaul.this.zzoo();
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzc(zzatq zzatq, String str) {
        zzac.zzw(zzatq);
        zzmR();
        zzob();
        int i = VERSION.SDK_INT;
        zzKn().zzLg();
        final boolean z = zzKf().zza(zzatq);
        final zzatq zzatq2 = zzatq;
        final String str2 = str;
        zzo(new Runnable(true) {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send event to service");
                    return;
                }
                if (true) {
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) z ? null : zzatq2);
                } else {
                    try {
                        if (TextUtils.isEmpty(str2)) {
                            zzc.zza(zzatq2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            zzc.zza(zzatq2, str2, zzaul.this.zzKl().zzMf());
                        }
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to send event to the service", e);
                    }
                }
                zzaul.this.zzoo();
            }
        });
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzf(zzatg zzatg) {
        zzac.zzw(zzatg);
        zzmR();
        zzob();
        zzKn().zzLg();
        final boolean z = zzKf().zzc(zzatg);
        final zzatg zzatg2 = new zzatg(zzatg);
        final zzatg zzatg3 = zzatg;
        zzo(new Runnable(true) {
            public void run() {
                zzatt zzc = zzaul.this.zzbvB;
                if (zzc == null) {
                    zzaul.this.zzKl().zzLY().log("Discarding data. Failed to send conditional user property to service");
                    return;
                }
                if (true) {
                    zzaul.this.zza(zzc, (com.google.android.gms.common.internal.safeparcel.zza) z ? null : zzatg2);
                } else {
                    try {
                        if (TextUtils.isEmpty(zzatg3.packageName)) {
                            zzc.zza(zzatg2, zzaul.this.zzKb().zzfD(zzaul.this.zzKl().zzMf()));
                        } else {
                            zzc.zzb(zzatg2);
                        }
                    } catch (RemoteException e) {
                        zzaul.this.zzKl().zzLY().zzj("Failed to send conditional user property to the service", e);
                    }
                }
                zzaul.this.zzoo();
            }
        });
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zznR() {
        return super.zznR();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzoD() {
        zzmR();
        zzob();
        if (!isConnected()) {
            if (this.zzbvC == null) {
                this.zzbvC = zzKm().zzMm();
                if (this.zzbvC == null) {
                    zzKl().zzMe().log("State of service unknown");
                    this.zzbvC = Boolean.valueOf(zzMY());
                    zzKm().zzaK(this.zzbvC.booleanValue());
                }
            }
            if (this.zzbvC.booleanValue()) {
                zzKl().zzMe().log("Using measurement service");
                this.zzbvA.zzNb();
            } else if (zzMX()) {
                zzKl().zzMe().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context context = getContext();
                zzKn().zzLg();
                intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzbvA.zzz(intent);
            } else {
                zzKl().zzLY().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }
}
