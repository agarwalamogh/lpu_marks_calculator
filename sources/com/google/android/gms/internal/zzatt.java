package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface zzatt extends IInterface {

    public static abstract class zza extends Binder implements zzatt {

        /* renamed from: com.google.android.gms.internal.zzatt$zza$zza reason: collision with other inner class name */
        private static class C0958zza implements zzatt {
            private IBinder zzrk;

            C0958zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public List<zzauq> zza(zzatd zzatd, boolean z) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzatg> zza(String str, String str2, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzatg.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzauq> zza(String str, String str2, String str3, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzauq> zza(String str, String str2, boolean z, zzatd zzatd) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(long j, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatg zzatg, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatg != null) {
                        obtain.writeInt(1);
                        zzatg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatq zzatq, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatq zzatq, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzauq zzauq, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzauq != null) {
                        obtain.writeInt(1);
                        zzauq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] zza(zzatq zzatq, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzatg zzatg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatg != null) {
                        obtain.writeInt(1);
                        zzatg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String zzc(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzatg> zzn(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzatg.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
        }

        public static zzatt zzes(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzatt)) ? new C0958zza(iBinder) : (zzatt) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z = false;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readInt() != 0 ? (zzatq) zzatq.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readInt() != 0 ? (zzauq) zzauq.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readInt() != 0 ? (zzatq) zzatq.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzb(parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzatd zzatd = parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null;
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    List zza = zza(zzatd, z);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zza);
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    byte[] zza2 = zza(parcel.readInt() != 0 ? (zzatq) zzatq.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(zza2);
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    String zzc = zzc(parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(zzc);
                    return true;
                case 12:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zza(parcel.readInt() != 0 ? (zzatg) zzatg.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    zzb(parcel.readInt() != 0 ? (zzatg) zzatg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    String readString = parcel.readString();
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    List zza3 = zza(readString, readString2, z, parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zza3);
                    return true;
                case 15:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    String readString3 = parcel.readString();
                    String readString4 = parcel.readString();
                    String readString5 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    List zza4 = zza(readString3, readString4, readString5, z);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zza4);
                    return true;
                case 16:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    List zza5 = zza(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (zzatd) zzatd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zza5);
                    return true;
                case 17:
                    parcel.enforceInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    List zzn = zzn(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(zzn);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.measurement.internal.IMeasurementService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    List<zzauq> zza(zzatd zzatd, boolean z) throws RemoteException;

    List<zzatg> zza(String str, String str2, zzatd zzatd) throws RemoteException;

    List<zzauq> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzauq> zza(String str, String str2, boolean z, zzatd zzatd) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(zzatd zzatd) throws RemoteException;

    void zza(zzatg zzatg, zzatd zzatd) throws RemoteException;

    void zza(zzatq zzatq, zzatd zzatd) throws RemoteException;

    void zza(zzatq zzatq, String str, String str2) throws RemoteException;

    void zza(zzauq zzauq, zzatd zzatd) throws RemoteException;

    byte[] zza(zzatq zzatq, String str) throws RemoteException;

    void zzb(zzatd zzatd) throws RemoteException;

    void zzb(zzatg zzatg) throws RemoteException;

    String zzc(zzatd zzatd) throws RemoteException;

    List<zzatg> zzn(String str, String str2, String str3) throws RemoteException;
}
