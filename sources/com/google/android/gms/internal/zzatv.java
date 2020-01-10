package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;

public class zzatv extends zzauh {
    private final zza zzbsx = new zza(getContext(), zzow());
    private boolean zzbsy;

    @TargetApi(11)
    private class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                if (VERSION.SDK_INT < 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                    zzatv.this.zzKl().zzLY().log("Opening the local database failed, dropping and recreating it");
                    String zzow = zzatv.this.zzow();
                    if (!zzatv.this.getContext().getDatabasePath(zzow).delete()) {
                        zzatv.this.zzKl().zzLY().zzj("Failed to delete corrupted local db file", zzow);
                    }
                    try {
                        return super.getWritableDatabase();
                    } catch (SQLiteException e2) {
                        zzatv.this.zzKl().zzLY().zzj("Failed to open local database. Events will bypass local storage", e2);
                        return null;
                    }
                } else {
                    throw e;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzatj.zza(zzatv.this.zzKl(), sQLiteDatabase);
        }

        @WorkerThread
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            zzatj.zza(zzatv.this.zzKl(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzatv(zzaue zzaue) {
        super(zzaue);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ce, code lost:
        r3 = r3 + 1;
     */
    @android.support.annotation.WorkerThread
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean zza(int r13, byte[] r14) {
        /*
            r12 = this;
            r12.zzJW()
            r12.zzmR()
            boolean r0 = r12.zzbsy
            if (r0 == 0) goto L_0x000c
            r0 = 0
        L_0x000b:
            return r0
        L_0x000c:
            android.content.ContentValues r6 = new android.content.ContentValues
            r6.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r6.put(r0, r1)
            java.lang.String r0 = "entry"
            r6.put(r0, r14)
            r1 = 5
            com.google.android.gms.internal.zzati r0 = r12.zzKn()
            r0.zzLp()
            r0 = 0
            r3 = r0
            r0 = r1
        L_0x002a:
            r1 = 5
            if (r3 >= r1) goto L_0x010d
            r2 = 0
            android.database.sqlite.SQLiteDatabase r2 = r12.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            if (r2 != 0) goto L_0x003e
            r1 = 1
            r12.zzbsy = r1     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            if (r2 == 0) goto L_0x003c
            r2.close()
        L_0x003c:
            r0 = 0
            goto L_0x000b
        L_0x003e:
            r2.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r4 = 0
            java.lang.String r1 = "select count(1) from messages"
            r7 = 0
            android.database.Cursor r1 = r2.rawQuery(r1, r7)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            if (r1 == 0) goto L_0x0057
            boolean r7 = r1.moveToFirst()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            if (r7 == 0) goto L_0x0057
            r4 = 0
            long r4 = r1.getLong(r4)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
        L_0x0057:
            r8 = 100000(0x186a0, double:4.94066E-319)
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x00a4
            com.google.android.gms.internal.zzatx r1 = r12.zzKl()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            java.lang.String r7 = "Data loss, local db full"
            r1.log(r7)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r8 = 100000(0x186a0, double:4.94066E-319)
            long r4 = r8 - r4
            r8 = 1
            long r4 = r4 + r8
            java.lang.String r1 = "messages"
            java.lang.String r7 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            r8 = 1
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r9 = 0
            java.lang.String r10 = java.lang.Long.toString(r4)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r8[r9] = r10     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            int r1 = r2.delete(r1, r7, r8)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            long r8 = (long) r1     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x00a4
            com.google.android.gms.internal.zzatx r1 = r12.zzKl()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            java.lang.String r7 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r10 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            java.lang.Long r11 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            long r4 = r4 - r8
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r1.zzd(r7, r10, r11, r4)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
        L_0x00a4:
            java.lang.String r1 = "messages"
            r4 = 0
            r2.insertOrThrow(r1, r4, r6)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r2.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            r2.endTransaction()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteException -> 0x00d3 }
            if (r2 == 0) goto L_0x00b5
            r2.close()
        L_0x00b5:
            r0 = 1
            goto L_0x000b
        L_0x00b8:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r4 = r12.zzKl()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "Error writing entry to local database"
            r4.zzj(r5, r1)     // Catch:{ all -> 0x0106 }
            r1 = 1
            r12.zzbsy = r1     // Catch:{ all -> 0x0106 }
            if (r2 == 0) goto L_0x00ce
            r2.close()
        L_0x00ce:
            int r1 = r3 + 1
            r3 = r1
            goto L_0x002a
        L_0x00d3:
            r1 = move-exception
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0106 }
            r5 = 11
            if (r4 < r5) goto L_0x00ea
            boolean r4 = r1 instanceof android.database.sqlite.SQLiteDatabaseLockedException     // Catch:{ all -> 0x0106 }
            if (r4 == 0) goto L_0x00ea
            long r4 = (long) r0     // Catch:{ all -> 0x0106 }
            android.os.SystemClock.sleep(r4)     // Catch:{ all -> 0x0106 }
            int r0 = r0 + 20
        L_0x00e4:
            if (r2 == 0) goto L_0x00ce
            r2.close()
            goto L_0x00ce
        L_0x00ea:
            if (r2 == 0) goto L_0x00f5
            boolean r4 = r2.inTransaction()     // Catch:{ all -> 0x0106 }
            if (r4 == 0) goto L_0x00f5
            r2.endTransaction()     // Catch:{ all -> 0x0106 }
        L_0x00f5:
            com.google.android.gms.internal.zzatx r4 = r12.zzKl()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "Error writing entry to local database"
            r4.zzj(r5, r1)     // Catch:{ all -> 0x0106 }
            r1 = 1
            r12.zzbsy = r1     // Catch:{ all -> 0x0106 }
            goto L_0x00e4
        L_0x0106:
            r0 = move-exception
            if (r2 == 0) goto L_0x010c
            r2.close()
        L_0x010c:
            throw r0
        L_0x010d:
            com.google.android.gms.internal.zzatx r0 = r12.zzKl()
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzMa()
            java.lang.String r1 = "Failed to write entry to local database"
            r0.log(r1)
            r0 = 0
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatv.zza(int, byte[]):boolean");
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        int i = VERSION.SDK_INT;
        if (this.zzbsy) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzbsx.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzbsy = true;
        return null;
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

    /* access modifiers changed from: 0000 */
    public boolean zzLM() {
        return getContext().getDatabasePath(zzow()).exists();
    }

    public boolean zza(zzatq zzatq) {
        int i = VERSION.SDK_INT;
        Parcel obtain = Parcel.obtain();
        zzatq.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzKl().zzMa().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public boolean zza(zzauq zzauq) {
        int i = VERSION.SDK_INT;
        Parcel obtain = Parcel.obtain();
        zzauq.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzKl().zzMa().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public boolean zzc(zzatg zzatg) {
        int i = VERSION.SDK_INT;
        byte[] zza2 = zzKh().zza((Parcelable) zzatg);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzKl().zzMa().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01a7 A[SYNTHETIC, Splitter:B:112:0x01a7] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x00c1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0121  */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.common.internal.safeparcel.zza> zzlD(int r14) {
        /*
            r13 = this;
            r13.zzmR()
            r13.zzJW()
            int r0 = android.os.Build.VERSION.SDK_INT
            boolean r0 = r13.zzbsy
            if (r0 == 0) goto L_0x000e
            r0 = 0
        L_0x000d:
            return r0
        L_0x000e:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            boolean r0 = r13.zzLM()
            if (r0 != 0) goto L_0x001b
            r0 = r10
            goto L_0x000d
        L_0x001b:
            r9 = 5
            r0 = 0
            r11 = r0
        L_0x001e:
            r0 = 5
            if (r11 >= r0) goto L_0x01c3
            r1 = 0
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01d9, SQLiteException -> 0x01d6 }
            if (r0 != 0) goto L_0x0032
            r1 = 1
            r13.zzbsy = r1     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            r0 = 0
            goto L_0x000d
        L_0x0032:
            r0.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            java.lang.String r1 = "messages"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r3 = 0
            java.lang.String r4 = "rowid"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r3 = 1
            java.lang.String r4 = "type"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r3 = 2
            java.lang.String r4 = "entry"
            r2[r3] = r4     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid asc"
            java.lang.String r8 = java.lang.Integer.toString(r14)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r2 = -1
        L_0x0059:
            boolean r1 = r6.moveToNext()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r1 == 0) goto L_0x016f
            r1 = 0
            long r4 = r6.getLong(r1)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r1 = 1
            int r1 = r6.getInt(r1)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r2 = 2
            byte[] r3 = r6.getBlob(r2)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r1 != 0) goto L_0x00c7
            android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r1 = 0
            int r7 = r3.length     // Catch:{ zza -> 0x008f }
            r2.unmarshall(r3, r1, r7)     // Catch:{ zza -> 0x008f }
            r1 = 0
            r2.setDataPosition(r1)     // Catch:{ zza -> 0x008f }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r1 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ zza -> 0x008f }
            java.lang.Object r1 = r1.createFromParcel(r2)     // Catch:{ zza -> 0x008f }
            com.google.android.gms.internal.zzatq r1 = (com.google.android.gms.internal.zzatq) r1     // Catch:{ zza -> 0x008f }
            r2.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r1 == 0) goto L_0x008d
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x008d:
            r2 = r4
            goto L_0x0059
        L_0x008f:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r1 = r13.zzKl()     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = "Failed to load event from local database"
            r1.log(r3)     // Catch:{ all -> 0x00a2 }
            r2.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r2 = r4
            goto L_0x0059
        L_0x00a2:
            r1 = move-exception
            r2.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            throw r1     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x00a7:
            r1 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
        L_0x00ab:
            com.google.android.gms.internal.zzatx r2 = r13.zzKl()     // Catch:{ all -> 0x01d3 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x01d3 }
            java.lang.String r3 = "Error reading entries from local database"
            r2.zzj(r3, r0)     // Catch:{ all -> 0x01d3 }
            r0 = 1
            r13.zzbsy = r0     // Catch:{ all -> 0x01d3 }
            if (r1 == 0) goto L_0x01dc
            r1.close()
            r0 = r9
        L_0x00c1:
            int r1 = r11 + 1
            r11 = r1
            r9 = r0
            goto L_0x001e
        L_0x00c7:
            r2 = 1
            if (r1 != r2) goto L_0x0125
            android.os.Parcel r7 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r2 = 0
            r1 = 0
            int r8 = r3.length     // Catch:{ zza -> 0x0103 }
            r7.unmarshall(r3, r1, r8)     // Catch:{ zza -> 0x0103 }
            r1 = 0
            r7.setDataPosition(r1)     // Catch:{ zza -> 0x0103 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzauq> r1 = com.google.android.gms.internal.zzauq.CREATOR     // Catch:{ zza -> 0x0103 }
            java.lang.Object r1 = r1.createFromParcel(r7)     // Catch:{ zza -> 0x0103 }
            com.google.android.gms.internal.zzauq r1 = (com.google.android.gms.internal.zzauq) r1     // Catch:{ zza -> 0x0103 }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x00e3:
            if (r1 == 0) goto L_0x008d
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            goto L_0x008d
        L_0x00e9:
            r1 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
        L_0x00ed:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01d3 }
            r3 = 11
            if (r2 < r3) goto L_0x01a5
            boolean r2 = r0 instanceof android.database.sqlite.SQLiteDatabaseLockedException     // Catch:{ all -> 0x01d3 }
            if (r2 == 0) goto L_0x01a5
            long r2 = (long) r9     // Catch:{ all -> 0x01d3 }
            android.os.SystemClock.sleep(r2)     // Catch:{ all -> 0x01d3 }
            int r0 = r9 + 20
        L_0x00fd:
            if (r1 == 0) goto L_0x00c1
            r1.close()
            goto L_0x00c1
        L_0x0103:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r1 = r13.zzKl()     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x0116 }
            java.lang.String r3 = "Failed to load user property from local database"
            r1.log(r3)     // Catch:{ all -> 0x0116 }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r1 = r2
            goto L_0x00e3
        L_0x0116:
            r1 = move-exception
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            throw r1     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x011b:
            r1 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
        L_0x011f:
            if (r1 == 0) goto L_0x0124
            r1.close()
        L_0x0124:
            throw r0
        L_0x0125:
            r2 = 2
            if (r1 != r2) goto L_0x0160
            android.os.Parcel r7 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r2 = 0
            r1 = 0
            int r8 = r3.length     // Catch:{ zza -> 0x0148 }
            r7.unmarshall(r3, r1, r8)     // Catch:{ zza -> 0x0148 }
            r1 = 0
            r7.setDataPosition(r1)     // Catch:{ zza -> 0x0148 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatg> r1 = com.google.android.gms.internal.zzatg.CREATOR     // Catch:{ zza -> 0x0148 }
            java.lang.Object r1 = r1.createFromParcel(r7)     // Catch:{ zza -> 0x0148 }
            com.google.android.gms.internal.zzatg r1 = (com.google.android.gms.internal.zzatg) r1     // Catch:{ zza -> 0x0148 }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x0141:
            if (r1 == 0) goto L_0x008d
            r10.add(r1)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            goto L_0x008d
        L_0x0148:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r1 = r13.zzKl()     // Catch:{ all -> 0x015b }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ all -> 0x015b }
            java.lang.String r3 = "Failed to load user property from local database"
            r1.log(r3)     // Catch:{ all -> 0x015b }
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r1 = r2
            goto L_0x0141
        L_0x015b:
            r1 = move-exception
            r7.recycle()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            throw r1     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x0160:
            com.google.android.gms.internal.zzatx r1 = r13.zzKl()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            java.lang.String r2 = "Unknown record type in local database"
            r1.log(r2)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            goto L_0x008d
        L_0x016f:
            r6.close()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            java.lang.String r1 = "messages"
            java.lang.String r4 = "rowid <= ?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r6 = 0
            java.lang.String r2 = java.lang.Long.toString(r2)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r5[r6] = r2     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            int r1 = r0.delete(r1, r4, r5)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            int r2 = r10.size()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r1 >= r2) goto L_0x0197
            com.google.android.gms.internal.zzatx r1 = r13.zzKl()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            java.lang.String r2 = "Fewer entries removed from local database than expected"
            r1.log(r2)     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
        L_0x0197:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            r0.endTransaction()     // Catch:{ SQLiteFullException -> 0x00a7, SQLiteException -> 0x00e9, all -> 0x011b }
            if (r0 == 0) goto L_0x01a2
            r0.close()
        L_0x01a2:
            r0 = r10
            goto L_0x000d
        L_0x01a5:
            if (r1 == 0) goto L_0x01b0
            boolean r2 = r1.inTransaction()     // Catch:{ all -> 0x01d3 }
            if (r2 == 0) goto L_0x01b0
            r1.endTransaction()     // Catch:{ all -> 0x01d3 }
        L_0x01b0:
            com.google.android.gms.internal.zzatx r2 = r13.zzKl()     // Catch:{ all -> 0x01d3 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x01d3 }
            java.lang.String r3 = "Error reading entries from local database"
            r2.zzj(r3, r0)     // Catch:{ all -> 0x01d3 }
            r0 = 1
            r13.zzbsy = r0     // Catch:{ all -> 0x01d3 }
            r0 = r9
            goto L_0x00fd
        L_0x01c3:
            com.google.android.gms.internal.zzatx r0 = r13.zzKl()
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzMa()
            java.lang.String r1 = "Failed to read events from database in reasonable time"
            r0.log(r1)
            r0 = 0
            goto L_0x000d
        L_0x01d3:
            r0 = move-exception
            goto L_0x011f
        L_0x01d6:
            r0 = move-exception
            goto L_0x00ed
        L_0x01d9:
            r0 = move-exception
            goto L_0x00ab
        L_0x01dc:
            r0 = r9
            goto L_0x00c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatv.zzlD(int):java.util.List");
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

    /* access modifiers changed from: 0000 */
    public String zzow() {
        return zzKn().zzLf();
    }
}
