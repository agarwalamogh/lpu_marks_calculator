package com.google.android.gms.iid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzb extends IInterface {

    public static abstract class zza extends Binder implements zzb {

        /* renamed from: com.google.android.gms.iid.zzb$zza$zza reason: collision with other inner class name */
        private static class C0950zza implements zzb {
            private IBinder zzrk;

            C0950zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void send(Message message) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzb zzcZ(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzb)) ? new C0950zza(iBinder) : (zzb) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.iid.IMessengerCompat");
                    send(parcel.readInt() != 0 ? (Message) Message.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.iid.IMessengerCompat");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void send(Message message) throws RemoteException;
}
