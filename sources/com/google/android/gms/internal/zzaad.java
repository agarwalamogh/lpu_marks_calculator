package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;

public class zzaad {

    public static abstract class zza<R extends Result, A extends com.google.android.gms.common.api.Api.zzb> extends zzaaf<R> implements zzb<R> {
        private final Api<?> zzaxf;
        private final zzc<A> zzazY;

        @Deprecated
        protected zza(zzc<A> zzc, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzazY = (zzc) zzac.zzw(zzc);
            this.zzaxf = null;
        }

        protected zza(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzazY = api.zzvg();
            this.zzaxf = api;
        }

        private void zzc(RemoteException remoteException) {
            zzB(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public final Api<?> getApi() {
            return this.zzaxf;
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }

        public final void zzB(Status status) {
            zzac.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            zzb(zzc(status));
        }

        /* access modifiers changed from: protected */
        public abstract void zza(A a) throws RemoteException;

        public final void zzb(A a) throws DeadObjectException {
            try {
                zza(a);
            } catch (DeadObjectException e) {
                zzc(e);
                throw e;
            } catch (RemoteException e2) {
                zzc(e2);
            }
        }

        public final zzc<A> zzvg() {
            return this.zzazY;
        }
    }

    public interface zzb<R> {
        void setResult(R r);

        void zzB(Status status);
    }
}
