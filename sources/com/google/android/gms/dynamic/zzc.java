package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzc extends IInterface {

    public static abstract class zza extends Binder implements zzc {

        /* renamed from: com.google.android.gms.dynamic.zzc$zza$zza reason: collision with other inner class name */
        private static class C0944zza implements zzc {
            private IBinder zzrk;

            C0944zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public Bundle getArguments() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getRetainInstance() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTargetRequestCode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getUserVisibleHint() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isAdded() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isDetached() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isHidden() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInLayout() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isRemoving() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isResumed() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isVisible() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setHasOptionsMenu(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMenuVisibility(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRetainInstance(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setUserVisibleHint(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startActivity(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startActivityForResult(Intent intent, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper zzBO() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc zzBP() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return zza.zzcc(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper zzBQ() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc zzBR() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return zza.zzcc(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzD(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzE(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static zzc zzcc(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new C0944zza(iBinder) : (zzc) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v8, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v11, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v37, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v38, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v43, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v44, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v52, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v53, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v54, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v55, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v60, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v61, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v63 */
        /* JADX WARNING: type inference failed for: r0v64 */
        /* JADX WARNING: type inference failed for: r0v65 */
        /* JADX WARNING: type inference failed for: r0v66 */
        /* JADX WARNING: type inference failed for: r0v67 */
        /* JADX WARNING: type inference failed for: r0v68 */
        /* JADX WARNING: type inference failed for: r0v69 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.content.Intent, android.os.IBinder]
          uses: [android.content.Intent, android.os.IBinder]
          mth insns count: 223
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 8 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 0
                r2 = 0
                r1 = 1
                switch(r4) {
                    case 2: goto L_0x0011;
                    case 3: goto L_0x0027;
                    case 4: goto L_0x0040;
                    case 5: goto L_0x0050;
                    case 6: goto L_0x0066;
                    case 7: goto L_0x007c;
                    case 8: goto L_0x0092;
                    case 9: goto L_0x00a3;
                    case 10: goto L_0x00ba;
                    case 11: goto L_0x00cb;
                    case 12: goto L_0x00df;
                    case 13: goto L_0x00f6;
                    case 14: goto L_0x010a;
                    case 15: goto L_0x011e;
                    case 16: goto L_0x0132;
                    case 17: goto L_0x0146;
                    case 18: goto L_0x015a;
                    case 19: goto L_0x016e;
                    case 20: goto L_0x0182;
                    case 21: goto L_0x0197;
                    case 22: goto L_0x01ab;
                    case 23: goto L_0x01bf;
                    case 24: goto L_0x01d3;
                    case 25: goto L_0x01e7;
                    case 26: goto L_0x0202;
                    case 27: goto L_0x0221;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r1 = super.onTransact(r4, r5, r6, r7)
            L_0x000a:
                return r1
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r6.writeString(r0)
                goto L_0x000a
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r3.zzBO()
                r6.writeNoException()
                if (r2 == 0) goto L_0x0023
                android.os.IBinder r0 = r2.asBinder()
            L_0x0023:
                r6.writeStrongBinder(r0)
                goto L_0x000a
            L_0x0027:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                android.os.Bundle r0 = r3.getArguments()
                r6.writeNoException()
                if (r0 == 0) goto L_0x003c
                r6.writeInt(r1)
                r0.writeToParcel(r6, r1)
                goto L_0x000a
            L_0x003c:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x0040:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r3.getId()
                r6.writeNoException()
                r6.writeInt(r0)
                goto L_0x000a
            L_0x0050:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.zzc r2 = r3.zzBP()
                r6.writeNoException()
                if (r2 == 0) goto L_0x0062
                android.os.IBinder r0 = r2.asBinder()
            L_0x0062:
                r6.writeStrongBinder(r0)
                goto L_0x000a
            L_0x0066:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r3.zzBQ()
                r6.writeNoException()
                if (r2 == 0) goto L_0x0078
                android.os.IBinder r0 = r2.asBinder()
            L_0x0078:
                r6.writeStrongBinder(r0)
                goto L_0x000a
            L_0x007c:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.getRetainInstance()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0090
                r0 = r1
            L_0x008b:
                r6.writeInt(r0)
                goto L_0x000a
            L_0x0090:
                r0 = r2
                goto L_0x008b
            L_0x0092:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                java.lang.String r0 = r3.getTag()
                r6.writeNoException()
                r6.writeString(r0)
                goto L_0x000a
            L_0x00a3:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.zzc r2 = r3.zzBR()
                r6.writeNoException()
                if (r2 == 0) goto L_0x00b5
                android.os.IBinder r0 = r2.asBinder()
            L_0x00b5:
                r6.writeStrongBinder(r0)
                goto L_0x000a
            L_0x00ba:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r3.getTargetRequestCode()
                r6.writeNoException()
                r6.writeInt(r0)
                goto L_0x000a
            L_0x00cb:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.getUserVisibleHint()
                r6.writeNoException()
                if (r0 == 0) goto L_0x00da
                r2 = r1
            L_0x00da:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x00df:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r3.getView()
                r6.writeNoException()
                if (r2 == 0) goto L_0x00f1
                android.os.IBinder r0 = r2.asBinder()
            L_0x00f1:
                r6.writeStrongBinder(r0)
                goto L_0x000a
            L_0x00f6:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isAdded()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0105
                r2 = r1
            L_0x0105:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x010a:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isDetached()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0119
                r2 = r1
            L_0x0119:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x011e:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isHidden()
                r6.writeNoException()
                if (r0 == 0) goto L_0x012d
                r2 = r1
            L_0x012d:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x0132:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isInLayout()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0141
                r2 = r1
            L_0x0141:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x0146:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isRemoving()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0155
                r2 = r1
            L_0x0155:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x015a:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isResumed()
                r6.writeNoException()
                if (r0 == 0) goto L_0x0169
                r2 = r1
            L_0x0169:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x016e:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                boolean r0 = r3.isVisible()
                r6.writeNoException()
                if (r0 == 0) goto L_0x017d
                r2 = r1
            L_0x017d:
                r6.writeInt(r2)
                goto L_0x000a
            L_0x0182:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                android.os.IBinder r0 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                r3.zzD(r0)
                r6.writeNoException()
                goto L_0x000a
            L_0x0197:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x01a3
                r2 = r1
            L_0x01a3:
                r3.setHasOptionsMenu(r2)
                r6.writeNoException()
                goto L_0x000a
            L_0x01ab:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x01b7
                r2 = r1
            L_0x01b7:
                r3.setMenuVisibility(r2)
                r6.writeNoException()
                goto L_0x000a
            L_0x01bf:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x01cb
                r2 = r1
            L_0x01cb:
                r3.setRetainInstance(r2)
                r6.writeNoException()
                goto L_0x000a
            L_0x01d3:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                int r0 = r5.readInt()
                if (r0 == 0) goto L_0x01df
                r2 = r1
            L_0x01df:
                r3.setUserVisibleHint(r2)
                r6.writeNoException()
                goto L_0x000a
            L_0x01e7:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x01fa
                android.os.Parcelable$Creator r0 = android.content.Intent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.content.Intent r0 = (android.content.Intent) r0
            L_0x01fa:
                r3.startActivity(r0)
                r6.writeNoException()
                goto L_0x000a
            L_0x0202:
                java.lang.String r2 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r2)
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x0215
                android.os.Parcelable$Creator r0 = android.content.Intent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r5)
                android.content.Intent r0 = (android.content.Intent) r0
            L_0x0215:
                int r2 = r5.readInt()
                r3.startActivityForResult(r0, r2)
                r6.writeNoException()
                goto L_0x000a
            L_0x0221:
                java.lang.String r0 = "com.google.android.gms.dynamic.IFragmentWrapper"
                r5.enforceInterface(r0)
                android.os.IBinder r0 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                r3.zzE(r0)
                r6.writeNoException()
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamic.zzc.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    Bundle getArguments() throws RemoteException;

    int getId() throws RemoteException;

    boolean getRetainInstance() throws RemoteException;

    String getTag() throws RemoteException;

    int getTargetRequestCode() throws RemoteException;

    boolean getUserVisibleHint() throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    boolean isAdded() throws RemoteException;

    boolean isDetached() throws RemoteException;

    boolean isHidden() throws RemoteException;

    boolean isInLayout() throws RemoteException;

    boolean isRemoving() throws RemoteException;

    boolean isResumed() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void setHasOptionsMenu(boolean z) throws RemoteException;

    void setMenuVisibility(boolean z) throws RemoteException;

    void setRetainInstance(boolean z) throws RemoteException;

    void setUserVisibleHint(boolean z) throws RemoteException;

    void startActivity(Intent intent) throws RemoteException;

    void startActivityForResult(Intent intent, int i) throws RemoteException;

    IObjectWrapper zzBO() throws RemoteException;

    zzc zzBP() throws RemoteException;

    IObjectWrapper zzBQ() throws RemoteException;

    zzc zzBR() throws RemoteException;

    void zzD(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzE(IObjectWrapper iObjectWrapper) throws RemoteException;
}
