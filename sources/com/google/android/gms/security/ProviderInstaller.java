package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.zzg;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    /* access modifiers changed from: private */
    public static final zze zzbEc = zze.zzuY();
    private static Method zzbEd = null;
    private static final Object zztX = new Object();

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzac.zzb(context, (Object) "Context must not be null");
        zzbEc.zzaE(context);
        Context remoteContext = zzg.getRemoteContext(context);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        synchronized (zztX) {
            try {
                if (zzbEd == null) {
                    zzbR(remoteContext);
                }
                zzbEd.invoke(null, new Object[]{remoteContext});
            } catch (Exception e) {
                String str = "ProviderInstaller";
                String str2 = "Failed to install provider: ";
                String valueOf = String.valueOf(e.getMessage());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    public static void installIfNeededAsync(final Context context, final ProviderInstallListener providerInstallListener) {
        zzac.zzb(context, (Object) "Context must not be null");
        zzac.zzb(providerInstallListener, (Object) "Listener must not be null");
        zzac.zzdj("Must be called on the UI thread");
        new AsyncTask<Void, Void, Integer>() {
            /* access modifiers changed from: protected */
            /* renamed from: zzb */
            public Integer doInBackground(Void... voidArr) {
                try {
                    ProviderInstaller.installIfNeeded(context);
                    return Integer.valueOf(0);
                } catch (GooglePlayServicesRepairableException e) {
                    return Integer.valueOf(e.getConnectionStatusCode());
                } catch (GooglePlayServicesNotAvailableException e2) {
                    return Integer.valueOf(e2.errorCode);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzg */
            public void onPostExecute(Integer num) {
                if (num.intValue() == 0) {
                    providerInstallListener.onProviderInstalled();
                    return;
                }
                providerInstallListener.onProviderInstallFailed(num.intValue(), ProviderInstaller.zzbEc.zzb(context, num.intValue(), "pi"));
            }
        }.execute(new Void[0]);
    }

    private static void zzbR(Context context) throws ClassNotFoundException, NoSuchMethodException {
        zzbEd = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
    }
}
