package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauw.zze;
import com.google.android.gms.internal.zzauw.zzf;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzatj extends zzauh {
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrg = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrh = new ArrayMap(18);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbri = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrj = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbrk = new ArrayMap(1);
    private final zzc zzbrl = new zzc(getContext(), zzow());
    /* access modifiers changed from: private */
    public final zzauo zzbrm = new zzauo(zznR());

    public static class zza {
        long zzbrn;
        long zzbro;
        long zzbrp;
        long zzbrq;
        long zzbrr;
    }

    interface zzb {
        boolean zza(long j, com.google.android.gms.internal.zzauw.zzb zzb);

        void zzb(zze zze);
    }

    private class zzc extends SQLiteOpenHelper {
        zzc(Context context, String str) {
            super(context, str, null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            if (!zzatj.this.zzbrm.zzA(zzatj.this.zzKn().zzLc())) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzatj.this.zzbrm.start();
                zzatj.this.zzKl().zzLY().log("Opening the database failed, dropping and recreating it");
                String zzow = zzatj.this.zzow();
                if (!zzatj.this.getContext().getDatabasePath(zzow).delete()) {
                    zzatj.this.zzKl().zzLY().zzj("Failed to delete corrupted db file", zzow);
                }
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzatj.this.zzbrm.clear();
                    return writableDatabase;
                } catch (SQLiteException e2) {
                    zzatj.this.zzKl().zzLY().zzj("Failed to open freshly created database", e2);
                    throw e2;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase);
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
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "conditional_properties", "CREATE TABLE IF NOT EXISTS conditional_properties ( app_id TEXT NOT NULL, origin TEXT NOT NULL, name TEXT NOT NULL, value BLOB NOT NULL, creation_timestamp INTEGER NOT NULL, active INTEGER NOT NULL, trigger_event_name TEXT, trigger_timeout INTEGER NOT NULL, timed_out_event BLOB,triggered_event BLOB, triggered_timestamp INTEGER NOT NULL, time_to_live INTEGER NOT NULL, expired_event BLOB, PRIMARY KEY (app_id, name)) ;", "app_id,origin,name,value,active,trigger_event_name,trigger_timeout,creation_timestamp,timed_out_event,triggered_event,triggered_timestamp,time_to_live,expired_event", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", zzatj.zzbrg);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zzatj.zzbrh);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", zzatj.zzbrj);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", zzatj.zzbri);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
            zzatj.zza(zzatj.this.zzKl(), sQLiteDatabase, "app2", "CREATE TABLE IF NOT EXISTS app2 ( app_id TEXT NOT NULL, first_open_count INTEGER NOT NULL, PRIMARY KEY (app_id));", "app_id,first_open_count", zzatj.zzbrk);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static {
        zzbrg.put(Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;");
        zzbrh.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzbrh.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzbrh.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzbrh.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzbrh.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzbrh.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzbrh.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzbrh.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzbrh.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzbrh.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzbrh.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzbrh.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzbrh.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzbrh.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzbrh.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzbrh.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
        zzbrh.put("daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;");
        zzbrh.put("health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;");
        zzbrh.put("android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;");
        zzbri.put("realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;");
        zzbrj.put("has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;");
        zzbrk.put("previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;");
    }

    zzatj(zzaue zzaue) {
        super(zzaue);
    }

    private boolean zzLM() {
        return getContext().getDatabasePath(zzow()).exists();
    }

    @WorkerThread
    @TargetApi(11)
    static int zza(Cursor cursor, int i) {
        int i2 = VERSION.SDK_INT;
        return cursor.getType(i);
    }

    @WorkerThread
    private long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase) {
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        int i = VERSION.SDK_INT;
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzatx.zzMa().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzatx.zzMa().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzatx.zzMa().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            zzatx.zzMa().log("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) throws SQLiteException {
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzatx, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(zzatx, sQLiteDatabase, str, str3, map);
        } catch (SQLiteException e) {
            zzatx.zzLY().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    static void zza(zzatx zzatx, SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) throws SQLiteException {
        String[] split;
        if (zzatx == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Set zzb2 = zzb(sQLiteDatabase, str);
        for (String str3 : str2.split(",")) {
            if (!zzb2.remove(str3)) {
                throw new SQLiteException(new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str3).length()).append("Table ").append(str).append(" is missing required column: ").append(str3).toString());
            }
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (!zzb2.remove(entry.getKey())) {
                    sQLiteDatabase.execSQL((String) entry.getValue());
                }
            }
        }
        if (!zzb2.isEmpty()) {
            zzatx.zzMa().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb2));
        }
    }

    @WorkerThread
    private void zza(String str, com.google.android.gms.internal.zzauu.zza zza2) {
        boolean z = false;
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zza2);
        zzac.zzw(zza2.zzbwm);
        zzac.zzw(zza2.zzbwl);
        if (zza2.zzbwk == null) {
            zzKl().zzMa().zzj("Audience with no ID. appId", zzatx.zzfE(str));
            return;
        }
        int intValue = zza2.zzbwk.intValue();
        for (com.google.android.gms.internal.zzauu.zzb zzb2 : zza2.zzbwm) {
            if (zzb2.zzbwo == null) {
                zzKl().zzMa().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzatx.zzfE(str), zza2.zzbwk);
                return;
            }
        }
        for (zzauu.zze zze : zza2.zzbwl) {
            if (zze.zzbwo == null) {
                zzKl().zzMa().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzatx.zzfE(str), zza2.zzbwk);
                return;
            }
        }
        boolean z2 = true;
        com.google.android.gms.internal.zzauu.zzb[] zzbArr = zza2.zzbwm;
        int length = zzbArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!zza(str, intValue, zzbArr[i])) {
                z2 = false;
                break;
            } else {
                i++;
            }
        }
        if (z2) {
            zzauu.zze[] zzeArr = zza2.zzbwl;
            int length2 = zzeArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    break;
                } else if (!zza(str, intValue, zzeArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        z = z2;
        if (!z) {
            zzA(str, intValue);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zza(com.google.android.gms.internal.zzatx r10, android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
        /*
            r8 = 0
            r9 = 0
            if (r10 != 0) goto L_0x000c
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Monitor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x000c:
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r0 = 0
            r4[r0] = r12     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r11
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0030, all -> 0x0042 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x004c }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r0
        L_0x0030:
            r0 = move-exception
            r1 = r9
        L_0x0032:
            com.google.android.gms.internal.zzatx$zza r2 = r10.zzMa()     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = "Error querying for table"
            r2.zze(r3, r12, r0)     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            r0 = r8
            goto L_0x002f
        L_0x0042:
            r0 = move-exception
        L_0x0043:
            if (r9 == 0) goto L_0x0048
            r9.close()
        L_0x0048:
            throw r0
        L_0x0049:
            r0 = move-exception
            r9 = r1
            goto L_0x0043
        L_0x004c:
            r0 = move-exception
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(com.google.android.gms.internal.zzatx, android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    @WorkerThread
    private boolean zza(String str, int i, com.google.android.gms.internal.zzauu.zzb zzb2) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzb2);
        if (TextUtils.isEmpty(zzb2.zzbwp)) {
            zzKl().zzMa().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzatx.zzfE(str), Integer.valueOf(i), String.valueOf(zzb2.zzbwo));
            return false;
        }
        try {
            byte[] bArr = new byte[zzb2.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzb2.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzb2.zzbwo);
            contentValues.put("event_name", zzb2.zzbwp);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzKl().zzLY().zzj("Failed to insert event filter (got -1). appId", zzatx.zzfE(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing event filter. appId", zzatx.zzfE(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize event filter. appId", zzatx.zzfE(str), e2);
            return false;
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, zzauu.zze zze) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zze);
        if (TextUtils.isEmpty(zze.zzbwE)) {
            zzKl().zzMa().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzatx.zzfE(str), Integer.valueOf(i), String.valueOf(zze.zzbwo));
            return false;
        }
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zze.zzbwo);
            contentValues.put("property_name", zze.zzbwE);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert property filter (got -1). appId", zzatx.zzfE(str));
                return false;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing property filter. appId", zzatx.zzfE(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize property filter. appId", zzatx.zzfE(str), e2);
            return false;
        }
    }

    @WorkerThread
    private long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    @WorkerThread
    public void beginTransaction() {
        zzob();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public void endTransaction() {
        zzob();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        zzmR();
        try {
            return this.zzbrl.getWritableDatabase();
        } catch (SQLiteException e) {
            zzKl().zzMa().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public void setTransactionSuccessful() {
        zzob();
        getWritableDatabase().setTransactionSuccessful();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzA(String str, int i) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
        writableDatabase.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
    }

    public void zzJ(List<Long> list) {
        zzac.zzw(list);
        zzmR();
        zzob();
        StringBuilder sb = new StringBuilder("rowid in (");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(((Long) list.get(i2)).longValue());
            i = i2 + 1;
        }
        sb.append(")");
        int delete = getWritableDatabase().delete("raw_events", sb.toString(), null);
        if (delete != list.size()) {
            zzKl().zzLY().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzLD() {
        /*
            r5 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.getWritableDatabase()
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            r3 = 0
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0023, all -> 0x0038 }
            boolean r1 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0043 }
            if (r1 == 0) goto L_0x001d
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x0043 }
            if (r2 == 0) goto L_0x001c
            r2.close()
        L_0x001c:
            return r0
        L_0x001d:
            if (r2 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x0023:
            r1 = move-exception
            r2 = r0
        L_0x0025:
            com.google.android.gms.internal.zzatx r3 = r5.zzKl()     // Catch:{ all -> 0x0041 }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x0041 }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzj(r4, r1)     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x001c
            r2.close()
            goto L_0x001c
        L_0x0038:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x003b:
            if (r2 == 0) goto L_0x0040
            r2.close()
        L_0x0040:
            throw r0
        L_0x0041:
            r0 = move-exception
            goto L_0x003b
        L_0x0043:
            r1 = move-exception
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzLD():java.lang.String");
    }

    public boolean zzLE() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzLF() {
        zzmR();
        zzob();
        if (zzLM()) {
            long j = zzKm().zzbtc.get();
            long elapsedRealtime = zznR().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzKn().zzLk()) {
                zzKm().zzbtc.set(elapsedRealtime);
                zzLG();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzLG() {
        zzmR();
        zzob();
        if (zzLM()) {
            int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zznR().currentTimeMillis()), String.valueOf(zzKn().zzLj())});
            if (delete > 0) {
                zzKl().zzMe().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
            }
        }
    }

    @WorkerThread
    public long zzLH() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public long zzLI() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public boolean zzLJ() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public boolean zzLK() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public long zzLL() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zzj("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatn zzQ(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r10 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r13)
            com.google.android.gms.common.internal.zzac.zzdr(r14)
            r12.zzmR()
            r12.zzob()
            android.database.sqlite.SQLiteDatabase r0 = r12.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            java.lang.String r1 = "events"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 0
            java.lang.String r4 = "lifetime_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 1
            java.lang.String r4 = "current_bundle_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 2
            java.lang.String r4 = "last_fire_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            java.lang.String r3 = "app_id=? and name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 0
            r4[r5] = r13     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 1
            r4[r5] = r14     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r11 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            if (r0 != 0) goto L_0x0044
            if (r11 == 0) goto L_0x0042
            r11.close()
        L_0x0042:
            r1 = r10
        L_0x0043:
            return r1
        L_0x0044:
            r0 = 0
            long r4 = r11.getLong(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r0 = 1
            long r6 = r11.getLong(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r0 = 2
            long r8 = r11.getLong(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            com.google.android.gms.internal.zzatn r1 = new com.google.android.gms.internal.zzatn     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r2 = r13
            r3 = r14
            r1.<init>(r2, r3, r4, r6, r8)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            boolean r0 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            if (r0 == 0) goto L_0x0071
            com.google.android.gms.internal.zzatx r0 = r12.zzKl()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            java.lang.String r2 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r0.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
        L_0x0071:
            if (r11 == 0) goto L_0x0043
            r11.close()
            goto L_0x0043
        L_0x0077:
            r0 = move-exception
            r1 = r10
        L_0x0079:
            com.google.android.gms.internal.zzatx r2 = r12.zzKl()     // Catch:{ all -> 0x009b }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x009b }
            java.lang.String r3 = "Error querying events. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r13)     // Catch:{ all -> 0x009b }
            r2.zzd(r3, r4, r14, r0)     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x008f
            r1.close()
        L_0x008f:
            r1 = r10
            goto L_0x0043
        L_0x0091:
            r0 = move-exception
        L_0x0092:
            if (r10 == 0) goto L_0x0097
            r10.close()
        L_0x0097:
            throw r0
        L_0x0098:
            r0 = move-exception
            r10 = r11
            goto L_0x0092
        L_0x009b:
            r0 = move-exception
            r10 = r1
            goto L_0x0092
        L_0x009e:
            r0 = move-exception
            r1 = r11
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzQ(java.lang.String, java.lang.String):com.google.android.gms.internal.zzatn");
    }

    @WorkerThread
    public void zzR(String str, String str2) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        try {
            zzKl().zzMe().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzKl().zzLY().zzd("Error deleting user attribute. appId", zzatx.zzfE(str), str2, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzaus zzS(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r10)
            com.google.android.gms.common.internal.zzac.zzdr(r11)
            r9.zzmR()
            r9.zzob()
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            java.lang.String r1 = "user_attributes"
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 0
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 1
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r3 = 2
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            java.lang.String r3 = "app_id=? and name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 1
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0091 }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            if (r0 != 0) goto L_0x0044
            if (r7 == 0) goto L_0x0042
            r7.close()
        L_0x0042:
            r0 = r8
        L_0x0043:
            return r0
        L_0x0044:
            r0 = 0
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r0 = 1
            java.lang.Object r6 = r9.zzb(r7, r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r0 = 2
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            com.google.android.gms.internal.zzaus r0 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r1 = r10
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            boolean r1 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            if (r1 == 0) goto L_0x0071
            com.google.android.gms.internal.zzatx r1 = r9.zzKl()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
            r1.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0098 }
        L_0x0071:
            if (r7 == 0) goto L_0x0043
            r7.close()
            goto L_0x0043
        L_0x0077:
            r0 = move-exception
            r1 = r8
        L_0x0079:
            com.google.android.gms.internal.zzatx r2 = r9.zzKl()     // Catch:{ all -> 0x009b }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x009b }
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ all -> 0x009b }
            r2.zzd(r3, r4, r11, r0)     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x008f
            r1.close()
        L_0x008f:
            r0 = r8
            goto L_0x0043
        L_0x0091:
            r0 = move-exception
        L_0x0092:
            if (r8 == 0) goto L_0x0097
            r8.close()
        L_0x0097:
            throw r0
        L_0x0098:
            r0 = move-exception
            r8 = r7
            goto L_0x0092
        L_0x009b:
            r0 = move-exception
            r8 = r1
            goto L_0x0092
        L_0x009e:
            r0 = move-exception
            r1 = r7
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzS(java.lang.String, java.lang.String):com.google.android.gms.internal.zzaus");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x013d  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatg zzT(java.lang.String r22, java.lang.String r23) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r22)
            com.google.android.gms.common.internal.zzac.zzdr(r23)
            r21.zzmR()
            r21.zzob()
            r10 = 0
            android.database.sqlite.SQLiteDatabase r2 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            java.lang.String r3 = "conditional_properties"
            r4 = 11
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 0
            java.lang.String r6 = "origin"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 1
            java.lang.String r6 = "value"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 2
            java.lang.String r6 = "active"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 3
            java.lang.String r6 = "trigger_event_name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 4
            java.lang.String r6 = "trigger_timeout"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 5
            java.lang.String r6 = "timed_out_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 6
            java.lang.String r6 = "creation_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 7
            java.lang.String r6 = "triggered_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 8
            java.lang.String r6 = "triggered_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 9
            java.lang.String r6 = "time_to_live"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r5 = 10
            java.lang.String r6 = "expired_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            java.lang.String r5 = "app_id=? and name=?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r7 = 0
            r6[r7] = r22     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r7 = 1
            r6[r7] = r23     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r20 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x011b, all -> 0x0138 }
            boolean r2 = r20.moveToFirst()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            if (r2 != 0) goto L_0x0070
            if (r20 == 0) goto L_0x006e
            r20.close()
        L_0x006e:
            r5 = 0
        L_0x006f:
            return r5
        L_0x0070:
            r2 = 0
            r0 = r20
            java.lang.String r7 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 1
            r0 = r21
            r1 = r20
            java.lang.Object r6 = r0.zzb(r1, r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 2
            r0 = r20
            int r2 = r0.getInt(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            if (r2 == 0) goto L_0x0118
            r11 = 1
        L_0x008a:
            r2 = 3
            r0 = r20
            java.lang.String r12 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 4
            r0 = r20
            long r14 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzaut r2 = r21.zzKh()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r3 = 5
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r4 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable r13 = r2.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzatq r13 = (com.google.android.gms.internal.zzatq) r13     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 6
            r0 = r20
            long r9 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzaut r2 = r21.zzKh()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r3 = 7
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r4 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable r16 = r2.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzatq r16 = (com.google.android.gms.internal.zzatq) r16     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 8
            r0 = r20
            long r4 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r2 = 9
            r0 = r20
            long r17 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzaut r2 = r21.zzKh()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r3 = 10
            r0 = r20
            byte[] r3 = r0.getBlob(r3)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r8 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            android.os.Parcelable r19 = r2.zzb(r3, r8)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzatq r19 = (com.google.android.gms.internal.zzatq) r19     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzauq r2 = new com.google.android.gms.internal.zzauq     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r3 = r23
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzatg r5 = new com.google.android.gms.internal.zzatg     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r6 = r22
            r8 = r2
            r5.<init>(r6, r7, r8, r9, r11, r12, r13, r14, r16, r17, r19)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            boolean r2 = r20.moveToNext()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            if (r2 == 0) goto L_0x0111
            com.google.android.gms.internal.zzatx r2 = r21.zzKl()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            java.lang.String r3 = "Got multiple records for conditional property, expected one"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r22)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
            r0 = r23
            r2.zze(r3, r4, r0)     // Catch:{ SQLiteException -> 0x0147, all -> 0x0141 }
        L_0x0111:
            if (r20 == 0) goto L_0x006f
            r20.close()
            goto L_0x006f
        L_0x0118:
            r11 = 0
            goto L_0x008a
        L_0x011b:
            r2 = move-exception
            r3 = r10
        L_0x011d:
            com.google.android.gms.internal.zzatx r4 = r21.zzKl()     // Catch:{ all -> 0x0143 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0143 }
            java.lang.String r5 = "Error querying conditional property"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r22)     // Catch:{ all -> 0x0143 }
            r0 = r23
            r4.zzd(r5, r6, r0, r2)     // Catch:{ all -> 0x0143 }
            if (r3 == 0) goto L_0x0135
            r3.close()
        L_0x0135:
            r5 = 0
            goto L_0x006f
        L_0x0138:
            r2 = move-exception
            r20 = r10
        L_0x013b:
            if (r20 == 0) goto L_0x0140
            r20.close()
        L_0x0140:
            throw r2
        L_0x0141:
            r2 = move-exception
            goto L_0x013b
        L_0x0143:
            r2 = move-exception
            r20 = r3
            goto L_0x013b
        L_0x0147:
            r2 = move-exception
            r3 = r20
            goto L_0x011d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzT(java.lang.String, java.lang.String):com.google.android.gms.internal.zzatg");
    }

    @WorkerThread
    public int zzU(String str, String str2) {
        boolean z = false;
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzKl().zzLY().zzd("Error deleting conditional property", zzatx.zzfE(str), str2, e);
            return z;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzauu.zzb>> zzV(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzob()
            r10.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r11)
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.getWritableDatabase()
            java.lang.String r1 = "event_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            java.lang.String r3 = "app_id=? AND event_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 0
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 1
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0047
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0098 }
            if (r1 == 0) goto L_0x0046
            r1.close()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = 1
            byte[] r0 = r1.getBlob(r0)     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzbxl r0 = com.google.android.gms.internal.zzbxl.zzaf(r0)     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzauu$zzb r2 = new com.google.android.gms.internal.zzauu$zzb     // Catch:{ SQLiteException -> 0x0098 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0098 }
            r2.zzb(r0)     // Catch:{ IOException -> 0x0085 }
            r0 = 0
            int r3 = r1.getInt(r0)     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Object r0 = r8.get(r0)     // Catch:{ SQLiteException -> 0x0098 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0075
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0098 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098 }
            r8.put(r3, r0)     // Catch:{ SQLiteException -> 0x0098 }
        L_0x0075:
            r0.add(r2)     // Catch:{ SQLiteException -> 0x0098 }
        L_0x0078:
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0047
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            r0 = r8
            goto L_0x0046
        L_0x0085:
            r0 = move-exception
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r11)     // Catch:{ SQLiteException -> 0x0098 }
            r2.zze(r3, r4, r0)     // Catch:{ SQLiteException -> 0x0098 }
            goto L_0x0078
        L_0x0098:
            r0 = move-exception
        L_0x0099:
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00b9 }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r11)     // Catch:{ all -> 0x00b9 }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00af
            r1.close()
        L_0x00af:
            r0 = r9
            goto L_0x0046
        L_0x00b1:
            r0 = move-exception
            r1 = r9
        L_0x00b3:
            if (r1 == 0) goto L_0x00b8
            r1.close()
        L_0x00b8:
            throw r0
        L_0x00b9:
            r0 = move-exception
            goto L_0x00b3
        L_0x00bb:
            r0 = move-exception
            r1 = r9
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzV(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.zzauu.zze>> zzW(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            r9 = 0
            r10.zzob()
            r10.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r11)
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r10.getWritableDatabase()
            java.lang.String r1 = "property_filters"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            java.lang.String r3 = "app_id=? AND property_name=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 0
            r4[r5] = r11     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 1
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00bb, all -> 0x00b1 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0047
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0098 }
            if (r1 == 0) goto L_0x0046
            r1.close()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = 1
            byte[] r0 = r1.getBlob(r0)     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzbxl r0 = com.google.android.gms.internal.zzbxl.zzaf(r0)     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzauu$zze r2 = new com.google.android.gms.internal.zzauu$zze     // Catch:{ SQLiteException -> 0x0098 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0098 }
            r2.zzb(r0)     // Catch:{ IOException -> 0x0085 }
            r0 = 0
            int r3 = r1.getInt(r0)     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Object r0 = r8.get(r0)     // Catch:{ SQLiteException -> 0x0098 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0075
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0098 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x0098 }
            r8.put(r3, r0)     // Catch:{ SQLiteException -> 0x0098 }
        L_0x0075:
            r0.add(r2)     // Catch:{ SQLiteException -> 0x0098 }
        L_0x0078:
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0098 }
            if (r0 != 0) goto L_0x0047
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            r0 = r8
            goto L_0x0046
        L_0x0085:
            r0 = move-exception
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()     // Catch:{ SQLiteException -> 0x0098 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r11)     // Catch:{ SQLiteException -> 0x0098 }
            r2.zze(r3, r4, r0)     // Catch:{ SQLiteException -> 0x0098 }
            goto L_0x0078
        L_0x0098:
            r0 = move-exception
        L_0x0099:
            com.google.android.gms.internal.zzatx r2 = r10.zzKl()     // Catch:{ all -> 0x00b9 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00b9 }
            java.lang.String r3 = "Database error querying filters. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r11)     // Catch:{ all -> 0x00b9 }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00af
            r1.close()
        L_0x00af:
            r0 = r9
            goto L_0x0046
        L_0x00b1:
            r0 = move-exception
            r1 = r9
        L_0x00b3:
            if (r1 == 0) goto L_0x00b8
            r1.close()
        L_0x00b8:
            throw r0
        L_0x00b9:
            r0 = move-exception
            goto L_0x00b3
        L_0x00bb:
            r0 = move-exception
            r1 = r9
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzW(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public long zzX(String str, String str2) {
        SQLiteException e;
        long j;
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            j = zza(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Integer.valueOf(0));
                contentValues.put("previous_install_count", Integer.valueOf(0));
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzKl().zzLY().zze("Failed to insert column (got -1). appId", zzatx.zzfE(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzKl().zzLY().zze("Failed to update column (got 0). appId", zzatx.zzfE(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzKl().zzLY().zzd("Error inserting column. appId", zzatx.zzfE(str), str2, e);
                    return j;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzKl().zzLY().zzd("Error inserting column. appId", zzatx.zzfE(str), str2, e);
            return j;
        }
    }

    public long zza(zze zze) throws IOException {
        zzmR();
        zzob();
        zzac.zzw(zze);
        zzac.zzdr(zze.zzaS);
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            long zzz = zzKh().zzz(bArr);
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaS);
            contentValues.put("metadata_fingerprint", Long.valueOf(zzz));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return zzz;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing raw event metadata. appId", zzatx.zzfE(zze.zzaS), e);
                throw e;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Data loss. Failed to serialize event metadata. appId", zzatx.zzfE(zze.zzaS), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0135  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatj.zza zza(long r12, java.lang.String r14, boolean r15, boolean r16, boolean r17, boolean r18, boolean r19) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r14)
            r11.zzmR()
            r11.zzob()
            r0 = 1
            java.lang.String[] r10 = new java.lang.String[r0]
            r0 = 0
            r10[r0] = r14
            com.google.android.gms.internal.zzatj$zza r8 = new com.google.android.gms.internal.zzatj$zza
            r8.<init>()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            java.lang.String r1 = "apps"
            r2 = 6
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 0
            java.lang.String r4 = "day"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 1
            java.lang.String r4 = "daily_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 2
            java.lang.String r4 = "daily_public_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 3
            java.lang.String r4 = "daily_conversions_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 4
            java.lang.String r4 = "daily_error_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r3 = 5
            java.lang.String r4 = "daily_realtime_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r5 = 0
            r4[r5] = r14     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0131 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x013b }
            if (r2 != 0) goto L_0x0069
            com.google.android.gms.internal.zzatx r0 = r11.zzKl()     // Catch:{ SQLiteException -> 0x013b }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzMa()     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r2 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r14)     // Catch:{ SQLiteException -> 0x013b }
            r0.zzj(r2, r3)     // Catch:{ SQLiteException -> 0x013b }
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            r0 = r8
        L_0x0068:
            return r0
        L_0x0069:
            r2 = 0
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            int r2 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x0095
            r2 = 1
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            r8.zzbro = r2     // Catch:{ SQLiteException -> 0x013b }
            r2 = 2
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            r8.zzbrn = r2     // Catch:{ SQLiteException -> 0x013b }
            r2 = 3
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            r8.zzbrp = r2     // Catch:{ SQLiteException -> 0x013b }
            r2 = 4
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            r8.zzbrq = r2     // Catch:{ SQLiteException -> 0x013b }
            r2 = 5
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x013b }
            r8.zzbrr = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x0095:
            if (r15 == 0) goto L_0x009e
            long r2 = r8.zzbro     // Catch:{ SQLiteException -> 0x013b }
            r4 = 1
            long r2 = r2 + r4
            r8.zzbro = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x009e:
            if (r16 == 0) goto L_0x00a7
            long r2 = r8.zzbrn     // Catch:{ SQLiteException -> 0x013b }
            r4 = 1
            long r2 = r2 + r4
            r8.zzbrn = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x00a7:
            if (r17 == 0) goto L_0x00b0
            long r2 = r8.zzbrp     // Catch:{ SQLiteException -> 0x013b }
            r4 = 1
            long r2 = r2 + r4
            r8.zzbrp = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x00b0:
            if (r18 == 0) goto L_0x00b9
            long r2 = r8.zzbrq     // Catch:{ SQLiteException -> 0x013b }
            r4 = 1
            long r2 = r2 + r4
            r8.zzbrq = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x00b9:
            if (r19 == 0) goto L_0x00c2
            long r2 = r8.zzbrr     // Catch:{ SQLiteException -> 0x013b }
            r4 = 1
            long r2 = r2 + r4
            r8.zzbrr = r2     // Catch:{ SQLiteException -> 0x013b }
        L_0x00c2:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x013b }
            r2.<init>()     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "day"
            java.lang.Long r4 = java.lang.Long.valueOf(r12)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "daily_public_events_count"
            long r4 = r8.zzbrn     // Catch:{ SQLiteException -> 0x013b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "daily_events_count"
            long r4 = r8.zzbro     // Catch:{ SQLiteException -> 0x013b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "daily_conversions_count"
            long r4 = r8.zzbrp     // Catch:{ SQLiteException -> 0x013b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "daily_error_events_count"
            long r4 = r8.zzbrq     // Catch:{ SQLiteException -> 0x013b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "daily_realtime_events_count"
            long r4 = r8.zzbrr     // Catch:{ SQLiteException -> 0x013b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x013b }
            r2.put(r3, r4)     // Catch:{ SQLiteException -> 0x013b }
            java.lang.String r3 = "apps"
            java.lang.String r4 = "app_id=?"
            r0.update(r3, r2, r4, r10)     // Catch:{ SQLiteException -> 0x013b }
            if (r1 == 0) goto L_0x0113
            r1.close()
        L_0x0113:
            r0 = r8
            goto L_0x0068
        L_0x0116:
            r0 = move-exception
            r1 = r9
        L_0x0118:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x0139 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0139 }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r14)     // Catch:{ all -> 0x0139 }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x012e
            r1.close()
        L_0x012e:
            r0 = r8
            goto L_0x0068
        L_0x0131:
            r0 = move-exception
            r1 = r9
        L_0x0133:
            if (r1 == 0) goto L_0x0138
            r1.close()
        L_0x0138:
            throw r0
        L_0x0139:
            r0 = move-exception
            goto L_0x0133
        L_0x013b:
            r0 = move-exception
            goto L_0x0118
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.zzatj$zza");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zza(ContentValues contentValues, String str, Object obj) {
        zzac.zzdr(str);
        zzac.zzw(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    public void zza(zzatc zzatc) {
        zzac.zzw(zzatc);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatc.zzke());
        contentValues.put("app_instance_id", zzatc.getAppInstanceId());
        contentValues.put("gmp_app_id", zzatc.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzatc.zzKp());
        contentValues.put("last_bundle_index", Long.valueOf(zzatc.zzKy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzatc.zzKr()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzatc.zzKs()));
        contentValues.put("app_version", zzatc.zzmZ());
        contentValues.put("app_store", zzatc.zzKu());
        contentValues.put("gmp_version", Long.valueOf(zzatc.zzKv()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzatc.zzKw()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzatc.zzKx()));
        contentValues.put("day", Long.valueOf(zzatc.zzKC()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzatc.zzKD()));
        contentValues.put("daily_events_count", Long.valueOf(zzatc.zzKE()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzatc.zzKF()));
        contentValues.put("config_fetched_time", Long.valueOf(zzatc.zzKz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzatc.zzKA()));
        contentValues.put("app_version_int", Long.valueOf(zzatc.zzKt()));
        contentValues.put("firebase_instance_id", zzatc.zzKq());
        contentValues.put("daily_error_events_count", Long.valueOf(zzatc.zzKH()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzatc.zzKG()));
        contentValues.put("health_monitor_sample", zzatc.zzKI());
        contentValues.put("android_id", Long.valueOf(zzatc.zzuW()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzatc.zzke()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update app (got -1). appId", zzatx.zzfE(zzatc.zzke()));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing app. appId", zzatx.zzfE(zzatc.zzke()), e);
        }
    }

    @WorkerThread
    public void zza(zzatn zzatn) {
        zzac.zzw(zzatn);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatn.mAppId);
        contentValues.put("name", zzatn.mName);
        contentValues.put("lifetime_count", Long.valueOf(zzatn.zzbrA));
        contentValues.put("current_bundle_count", Long.valueOf(zzatn.zzbrB));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzatn.zzbrC));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update event aggregates (got -1). appId", zzatx.zzfE(zzatn.mAppId));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing event aggregates. appId", zzatx.zzfE(zzatn.mAppId), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(String str, int i, zzf zzf) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzf);
        try {
            byte[] bArr = new byte[zzf.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzf.zza(zzag);
            zzag.zzaeG();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("current_results", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    zzKl().zzLY().zzj("Failed to insert filter results (got -1). appId", zzatx.zzfE(str));
                }
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing filter results. appId", zzatx.zzfE(str), e);
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Configuration loss. Failed to serialize filter results. appId", zzatx.zzfE(str), e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:98:0x0248  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(java.lang.String r15, long r16, long r18, com.google.android.gms.internal.zzatj.zzb r20) {
        /*
            r14 = this;
            com.google.android.gms.common.internal.zzac.zzw(r20)
            r14.zzmR()
            r14.zzob()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r2 = r14.getWritableDatabase()     // Catch:{ SQLiteException -> 0x024f }
            boolean r4 = android.text.TextUtils.isEmpty(r15)     // Catch:{ SQLiteException -> 0x024f }
            if (r4 == 0) goto L_0x00c1
            r4 = -1
            int r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0067
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x024f }
            r5 = 0
            java.lang.String r6 = java.lang.String.valueOf(r18)     // Catch:{ SQLiteException -> 0x024f }
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x024f }
            r5 = 1
            java.lang.String r6 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x024f }
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x024f }
            r5 = r4
        L_0x002c:
            r6 = -1
            int r4 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0073
            java.lang.String r4 = "rowid <= ? and "
        L_0x0034:
            java.lang.String r6 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x024f }
            int r6 = r6.length()     // Catch:{ SQLiteException -> 0x024f }
            int r6 = r6 + 148
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x024f }
            r7.<init>(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r6 = "select app_id, metadata_fingerprint from raw_events where "
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r6 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteException -> 0x024f }
            android.database.Cursor r3 = r2.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x024f }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x024f }
            if (r4 != 0) goto L_0x0076
            if (r3 == 0) goto L_0x0066
            r3.close()
        L_0x0066:
            return
        L_0x0067:
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x024f }
            r5 = 0
            java.lang.String r6 = java.lang.String.valueOf(r16)     // Catch:{ SQLiteException -> 0x024f }
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x024f }
            r5 = r4
            goto L_0x002c
        L_0x0073:
            java.lang.String r4 = ""
            goto L_0x0034
        L_0x0076:
            r4 = 0
            java.lang.String r15 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x024f }
            r4 = 1
            java.lang.String r4 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x024f }
            r3.close()     // Catch:{ SQLiteException -> 0x024f }
            r12 = r4
            r11 = r3
        L_0x0085:
            java.lang.String r3 = "raw_events_metadata"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r5 = 0
            java.lang.String r6 = "metadata"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 0
            r6[r7] = r15     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 1
            r6[r7] = r12     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "2"
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            boolean r3 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            if (r3 != 0) goto L_0x0128
            com.google.android.gms.internal.zzatx r2 = r14.zzKl()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            java.lang.String r3 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            if (r11 == 0) goto L_0x0066
            r11.close()
            goto L_0x0066
        L_0x00c1:
            r4 = -1
            int r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0111
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x024f }
            r5 = 0
            r4[r5] = r15     // Catch:{ SQLiteException -> 0x024f }
            r5 = 1
            java.lang.String r6 = java.lang.String.valueOf(r18)     // Catch:{ SQLiteException -> 0x024f }
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x024f }
            r5 = r4
        L_0x00d5:
            r6 = -1
            int r4 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0119
            java.lang.String r4 = " and rowid <= ?"
        L_0x00dd:
            java.lang.String r6 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x024f }
            int r6 = r6.length()     // Catch:{ SQLiteException -> 0x024f }
            int r6 = r6 + 84
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x024f }
            r7.<init>(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r6 = "select metadata_fingerprint from raw_events where app_id = ?"
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r6 = " order by rowid limit 1;"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteException -> 0x024f }
            android.database.Cursor r3 = r2.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x024f }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x024f }
            if (r4 != 0) goto L_0x011c
            if (r3 == 0) goto L_0x0066
            r3.close()
            goto L_0x0066
        L_0x0111:
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x024f }
            r5 = 0
            r4[r5] = r15     // Catch:{ SQLiteException -> 0x024f }
            r5 = r4
            goto L_0x00d5
        L_0x0119:
            java.lang.String r4 = ""
            goto L_0x00dd
        L_0x011c:
            r4 = 0
            java.lang.String r4 = r3.getString(r4)     // Catch:{ SQLiteException -> 0x024f }
            r3.close()     // Catch:{ SQLiteException -> 0x024f }
            r12 = r4
            r11 = r3
            goto L_0x0085
        L_0x0128:
            r3 = 0
            byte[] r3 = r11.getBlob(r3)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            com.google.android.gms.internal.zzbxl r3 = com.google.android.gms.internal.zzbxl.zzaf(r3)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            com.google.android.gms.internal.zzauw$zze r4 = new com.google.android.gms.internal.zzauw$zze     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r4.<init>()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r4.zzb(r3)     // Catch:{ IOException -> 0x01b0 }
            boolean r3 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            if (r3 == 0) goto L_0x0150
            com.google.android.gms.internal.zzatx r3 = r14.zzKl()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzMa()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            java.lang.String r5 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3.zzj(r5, r6)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
        L_0x0150:
            r11.close()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r0 = r20
            r0.zzb(r4)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r4 = -1
            int r3 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x01c9
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r3 = 3
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3 = 0
            r6[r3] = r15     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3 = 1
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3 = 2
            java.lang.String r4 = java.lang.String.valueOf(r18)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r6[r3] = r4     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
        L_0x0170:
            java.lang.String r3 = "raw_events"
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 0
            java.lang.String r8 = "rowid"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 1
            java.lang.String r8 = "name"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 2
            java.lang.String r8 = "timestamp"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 3
            java.lang.String r8 = "data"
            r4[r7] = r8     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            r10 = 0
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x024f }
            if (r2 != 0) goto L_0x01ef
            com.google.android.gms.internal.zzatx r2 = r14.zzKl()     // Catch:{ SQLiteException -> 0x024f }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzMa()     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r4 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ SQLiteException -> 0x024f }
            r2.zzj(r4, r5)     // Catch:{ SQLiteException -> 0x024f }
            if (r3 == 0) goto L_0x0066
            r3.close()
            goto L_0x0066
        L_0x01b0:
            r2 = move-exception
            com.google.android.gms.internal.zzatx r3 = r14.zzKl()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3.zze(r4, r5, r2)     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            if (r11 == 0) goto L_0x0066
            r11.close()
            goto L_0x0066
        L_0x01c9:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3 = 0
            r6[r3] = r15     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            r3 = 1
            r6[r3] = r12     // Catch:{ SQLiteException -> 0x01d5, all -> 0x024c }
            goto L_0x0170
        L_0x01d5:
            r2 = move-exception
            r3 = r11
        L_0x01d7:
            com.google.android.gms.internal.zzatx r4 = r14.zzKl()     // Catch:{ all -> 0x0245 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0245 }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ all -> 0x0245 }
            r4.zze(r5, r6, r2)     // Catch:{ all -> 0x0245 }
            if (r3 == 0) goto L_0x0066
            r3.close()
            goto L_0x0066
        L_0x01ef:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x024f }
            r2 = 3
            byte[] r2 = r3.getBlob(r2)     // Catch:{ SQLiteException -> 0x024f }
            com.google.android.gms.internal.zzbxl r2 = com.google.android.gms.internal.zzbxl.zzaf(r2)     // Catch:{ SQLiteException -> 0x024f }
            com.google.android.gms.internal.zzauw$zzb r6 = new com.google.android.gms.internal.zzauw$zzb     // Catch:{ SQLiteException -> 0x024f }
            r6.<init>()     // Catch:{ SQLiteException -> 0x024f }
            r6.zzb(r2)     // Catch:{ IOException -> 0x0226 }
            r2 = 1
            java.lang.String r2 = r3.getString(r2)     // Catch:{ SQLiteException -> 0x024f }
            r6.name = r2     // Catch:{ SQLiteException -> 0x024f }
            r2 = 2
            long r8 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x024f }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x024f }
            r6.zzbwZ = r2     // Catch:{ SQLiteException -> 0x024f }
            r0 = r20
            boolean r2 = r0.zza(r4, r6)     // Catch:{ SQLiteException -> 0x024f }
            if (r2 != 0) goto L_0x0238
            if (r3 == 0) goto L_0x0066
            r3.close()
            goto L_0x0066
        L_0x0226:
            r2 = move-exception
            com.google.android.gms.internal.zzatx r4 = r14.zzKl()     // Catch:{ SQLiteException -> 0x024f }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r5 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r15)     // Catch:{ SQLiteException -> 0x024f }
            r4.zze(r5, r6, r2)     // Catch:{ SQLiteException -> 0x024f }
        L_0x0238:
            boolean r2 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x024f }
            if (r2 != 0) goto L_0x01ef
            if (r3 == 0) goto L_0x0066
            r3.close()
            goto L_0x0066
        L_0x0245:
            r2 = move-exception
        L_0x0246:
            if (r3 == 0) goto L_0x024b
            r3.close()
        L_0x024b:
            throw r2
        L_0x024c:
            r2 = move-exception
            r3 = r11
            goto L_0x0246
        L_0x024f:
            r2 = move-exception
            goto L_0x01d7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zza(java.lang.String, long, long, com.google.android.gms.internal.zzatj$zzb):void");
    }

    @WorkerThread
    public boolean zza(zzatg zzatg) {
        zzac.zzw(zzatg);
        zzmR();
        zzob();
        if (zzS(zzatg.packageName, zzatg.zzbqW.name) == null) {
            long zzb2 = zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzatg.packageName});
            zzKn().zzKZ();
            if (zzb2 >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzatg.packageName);
        contentValues.put(Param.ORIGIN, zzatg.zzbqV);
        contentValues.put("name", zzatg.zzbqW.name);
        zza(contentValues, Param.VALUE, zzatg.zzbqW.getValue());
        contentValues.put("active", Boolean.valueOf(zzatg.zzbqY));
        contentValues.put("trigger_event_name", zzatg.zzbqZ);
        contentValues.put("trigger_timeout", Long.valueOf(zzatg.zzbrb));
        contentValues.put("timed_out_event", zzKh().zza((Parcelable) zzatg.zzbra));
        contentValues.put("creation_timestamp", Long.valueOf(zzatg.zzbqX));
        contentValues.put("triggered_event", zzKh().zza((Parcelable) zzatg.zzbrc));
        contentValues.put("triggered_timestamp", Long.valueOf(zzatg.zzbqW.zzbwc));
        contentValues.put("time_to_live", Long.valueOf(zzatg.zzbrd));
        contentValues.put("expired_event", zzKh().zza((Parcelable) zzatg.zzbre));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update conditional user property (got -1)", zzatx.zzfE(zzatg.packageName));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing conditional user property", zzatx.zzfE(zzatg.packageName), e);
        }
        return true;
    }

    public boolean zza(zzatm zzatm, long j, boolean z) {
        zzmR();
        zzob();
        zzac.zzw(zzatm);
        zzac.zzdr(zzatm.mAppId);
        com.google.android.gms.internal.zzauw.zzb zzb2 = new com.google.android.gms.internal.zzauw.zzb();
        zzb2.zzbxa = Long.valueOf(zzatm.zzbry);
        zzb2.zzbwY = new com.google.android.gms.internal.zzauw.zzc[zzatm.zzbrz.size()];
        Iterator it = zzatm.zzbrz.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            com.google.android.gms.internal.zzauw.zzc zzc2 = new com.google.android.gms.internal.zzauw.zzc();
            int i2 = i + 1;
            zzb2.zzbwY[i] = zzc2;
            zzc2.name = str;
            zzKh().zza(zzc2, zzatm.zzbrz.get(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzb2.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzb2.zza(zzag);
            zzag.zzaeG();
            zzKl().zzMe().zze("Saving event, name, data size", zzatm.mName, Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzatm.mAppId);
            contentValues.put("name", zzatm.mName);
            contentValues.put("timestamp", Long.valueOf(zzatm.zzaxb));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert raw event (got -1). appId", zzatx.zzfE(zzatm.mAppId));
                return false;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing raw event. appId", zzatx.zzfE(zzatm.mAppId), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Data loss. Failed to serialize event params/data. appId", zzatx.zzfE(zzatm.mAppId), e2);
            return false;
        }
    }

    @WorkerThread
    public boolean zza(zzaus zzaus) {
        zzac.zzw(zzaus);
        zzmR();
        zzob();
        if (zzS(zzaus.mAppId, zzaus.mName) == null) {
            if (zzaut.zzfT(zzaus.mName)) {
                long zzb2 = zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzaus.mAppId});
                zzKn().zzKW();
                if (zzb2 >= 25) {
                    return false;
                }
            } else {
                long zzb3 = zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzaus.mAppId, zzaus.mOrigin});
                zzKn().zzKY();
                if (zzb3 >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaus.mAppId);
        contentValues.put(Param.ORIGIN, zzaus.mOrigin);
        contentValues.put("name", zzaus.mName);
        contentValues.put("set_timestamp", Long.valueOf(zzaus.zzbwg));
        zza(contentValues, Param.VALUE, zzaus.mValue);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzKl().zzLY().zzj("Failed to insert/update user property (got -1). appId", zzatx.zzfE(zzaus.mAppId));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing user property. appId", zzatx.zzfE(zzaus.mAppId), e);
        }
        return true;
    }

    @WorkerThread
    public boolean zza(zze zze, boolean z) {
        zzmR();
        zzob();
        zzac.zzw(zze);
        zzac.zzdr(zze.zzaS);
        zzac.zzw(zze.zzbxk);
        zzLF();
        long currentTimeMillis = zznR().currentTimeMillis();
        if (zze.zzbxk.longValue() < currentTimeMillis - zzKn().zzLj() || zze.zzbxk.longValue() > zzKn().zzLj() + currentTimeMillis) {
            zzKl().zzMa().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzatx.zzfE(zze.zzaS), Long.valueOf(currentTimeMillis), zze.zzbxk);
        }
        try {
            byte[] bArr = new byte[zze.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zze.zza(zzag);
            zzag.zzaeG();
            byte[] zzk = zzKh().zzk(bArr);
            zzKl().zzMe().zzj("Saving bundle, size", Integer.valueOf(zzk.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaS);
            contentValues.put("bundle_end_timestamp", zze.zzbxk);
            contentValues.put("data", zzk);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzKl().zzLY().zzj("Failed to insert bundle (got -1). appId", zzatx.zzfE(zze.zzaS));
                return false;
            } catch (SQLiteException e) {
                zzKl().zzLY().zze("Error storing bundle. appId", zzatx.zzfE(zze.zzaS), e);
                return false;
            }
        } catch (IOException e2) {
            zzKl().zzLY().zze("Data loss. Failed to serialize bundle. appId", zzatx.zzfE(zze.zzaS), e2);
            return false;
        }
    }

    @WorkerThread
    public void zzan(long j) {
        zzmR();
        zzob();
        try {
            if (getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(j)}) != 1) {
                throw new SQLiteException("Deleted fewer rows from queue than expected");
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zzj("Failed to delete a bundle in a queue table", e);
            throw e;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzao(long r8) {
        /*
            r7 = this;
            r0 = 0
            r7.zzmR()
            r7.zzob()
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            r4 = 0
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            r3[r4] = r5     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            android.database.Cursor r2 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x003f, all -> 0x0054 }
            boolean r1 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x005f }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.zzatx r1 = r7.zzKl()     // Catch:{ SQLiteException -> 0x005f }
            com.google.android.gms.internal.zzatx$zza r1 = r1.zzMe()     // Catch:{ SQLiteException -> 0x005f }
            java.lang.String r3 = "No expired configs for apps with pending events"
            r1.log(r3)     // Catch:{ SQLiteException -> 0x005f }
            if (r2 == 0) goto L_0x0033
            r2.close()
        L_0x0033:
            return r0
        L_0x0034:
            r1 = 0
            java.lang.String r0 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x005f }
            if (r2 == 0) goto L_0x0033
            r2.close()
            goto L_0x0033
        L_0x003f:
            r1 = move-exception
            r2 = r0
        L_0x0041:
            com.google.android.gms.internal.zzatx r3 = r7.zzKl()     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.zzatx$zza r3 = r3.zzLY()     // Catch:{ all -> 0x005d }
            java.lang.String r4 = "Error selecting expired configs"
            r3.zzj(r4, r1)     // Catch:{ all -> 0x005d }
            if (r2 == 0) goto L_0x0033
            r2.close()
            goto L_0x0033
        L_0x0054:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0057:
            if (r2 == 0) goto L_0x005c
            r2.close()
        L_0x005c:
            throw r0
        L_0x005d:
            r0 = move-exception
            goto L_0x0057
        L_0x005f:
            r1 = move-exception
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzao(long):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public Object zzb(Cursor cursor, int i) {
        int zza2 = zza(cursor, i);
        switch (zza2) {
            case 0:
                zzKl().zzLY().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzKl().zzLY().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzKl().zzLY().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(zza2));
                return null;
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzb(String str, com.google.android.gms.internal.zzauu.zza[] zzaArr) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        zzac.zzw(zzaArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzfx(str);
            for (com.google.android.gms.internal.zzauu.zza zza2 : zzaArr) {
                zza(str, zza2);
            }
            ArrayList arrayList = new ArrayList();
            for (com.google.android.gms.internal.zzauu.zza zza3 : zzaArr) {
                arrayList.add(zza3.zzbwk);
            }
            zzd(str, (List<Integer>) arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public List<zzatg> zzc(String str, String str2, long j) {
        zzac.zzdr(str);
        zzac.zzdr(str2);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zzd("Invalid time querying triggered conditional properties", zzatx.zzfE(str), str2, Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x017c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzatg> zzc(java.lang.String r24, java.lang.String[] r25) {
        /*
            r23 = this;
            r23.zzmR()
            r23.zzob()
            java.util.ArrayList r20 = new java.util.ArrayList
            r20.<init>()
            r11 = 0
            android.database.sqlite.SQLiteDatabase r2 = r23.getWritableDatabase()     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            java.lang.String r3 = "conditional_properties"
            r4 = 13
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 0
            java.lang.String r6 = "app_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 1
            java.lang.String r6 = "origin"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 2
            java.lang.String r6 = "name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 3
            java.lang.String r6 = "value"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 4
            java.lang.String r6 = "active"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 5
            java.lang.String r6 = "trigger_event_name"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 6
            java.lang.String r6 = "trigger_timeout"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 7
            java.lang.String r6 = "timed_out_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 8
            java.lang.String r6 = "creation_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 9
            java.lang.String r6 = "triggered_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 10
            java.lang.String r6 = "triggered_timestamp"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 11
            java.lang.String r6 = "time_to_live"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 12
            java.lang.String r6 = "expired_event"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            com.google.android.gms.internal.zzati r5 = r23.zzKn()     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5.zzKZ()     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = 1001(0x3e9, float:1.403E-42)
            java.lang.String r10 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            r5 = r24
            r6 = r25
            android.database.Cursor r21 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x015d, all -> 0x0177 }
            boolean r2 = r21.moveToFirst()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            if (r2 != 0) goto L_0x0083
            if (r21 == 0) goto L_0x0080
            r21.close()
        L_0x0080:
            r2 = r20
        L_0x0082:
            return r2
        L_0x0083:
            int r2 = r20.size()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzati r3 = r23.zzKn()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            int r3 = r3.zzKZ()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            if (r2 < r3) goto L_0x00b2
            com.google.android.gms.internal.zzatx r2 = r23.zzKl()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            java.lang.String r3 = "Read more than the max allowed conditional properties, ignoring extra"
            com.google.android.gms.internal.zzati r4 = r23.zzKn()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            int r4 = r4.zzKZ()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
        L_0x00aa:
            if (r21 == 0) goto L_0x00af
            r21.close()
        L_0x00af:
            r2 = r20
            goto L_0x0082
        L_0x00b2:
            r2 = 0
            r0 = r21
            java.lang.String r8 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 1
            r0 = r21
            java.lang.String r7 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 2
            r0 = r21
            java.lang.String r3 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 3
            r0 = r23
            r1 = r21
            java.lang.Object r6 = r0.zzb(r1, r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 4
            r0 = r21
            int r2 = r0.getInt(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            if (r2 == 0) goto L_0x015a
            r11 = 1
        L_0x00da:
            r2 = 5
            r0 = r21
            java.lang.String r12 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 6
            r0 = r21
            long r14 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzaut r2 = r23.zzKh()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r4 = 7
            r0 = r21
            byte[] r4 = r0.getBlob(r4)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r5 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            android.os.Parcelable r13 = r2.zzb(r4, r5)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzatq r13 = (com.google.android.gms.internal.zzatq) r13     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 8
            r0 = r21
            long r9 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzaut r2 = r23.zzKh()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r4 = 9
            r0 = r21
            byte[] r4 = r0.getBlob(r4)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r5 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            android.os.Parcelable r16 = r2.zzb(r4, r5)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzatq r16 = (com.google.android.gms.internal.zzatq) r16     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 10
            r0 = r21
            long r4 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2 = 11
            r0 = r21
            long r17 = r0.getLong(r2)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzaut r2 = r23.zzKh()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r19 = 12
            r0 = r21
            r1 = r19
            byte[] r19 = r0.getBlob(r1)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r22 = com.google.android.gms.internal.zzatq.CREATOR     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r0 = r19
            r1 = r22
            android.os.Parcelable r19 = r2.zzb(r0, r1)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzatq r19 = (com.google.android.gms.internal.zzatq) r19     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzauq r2 = new com.google.android.gms.internal.zzauq     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            com.google.android.gms.internal.zzatg r5 = new com.google.android.gms.internal.zzatg     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r6 = r8
            r8 = r2
            r5.<init>(r6, r7, r8, r9, r11, r12, r13, r14, r16, r17, r19)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            r0 = r20
            r0.add(r5)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            boolean r2 = r21.moveToNext()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0180 }
            if (r2 != 0) goto L_0x0083
            goto L_0x00aa
        L_0x015a:
            r11 = 0
            goto L_0x00da
        L_0x015d:
            r2 = move-exception
            r3 = r11
        L_0x015f:
            com.google.android.gms.internal.zzatx r4 = r23.zzKl()     // Catch:{ all -> 0x0182 }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ all -> 0x0182 }
            java.lang.String r5 = "Error querying conditional user property value"
            r4.zzj(r5, r2)     // Catch:{ all -> 0x0182 }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0182 }
            if (r3 == 0) goto L_0x0082
            r3.close()
            goto L_0x0082
        L_0x0177:
            r2 = move-exception
            r21 = r11
        L_0x017a:
            if (r21 == 0) goto L_0x017f
            r21.close()
        L_0x017f:
            throw r2
        L_0x0180:
            r2 = move-exception
            goto L_0x017a
        L_0x0182:
            r2 = move-exception
            r21 = r3
            goto L_0x017a
        L_0x0186:
            r2 = move-exception
            r3 = r21
            goto L_0x015f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzc(java.lang.String, java.lang.String[]):java.util.List");
    }

    @WorkerThread
    public void zzd(String str, byte[] bArr) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzKl().zzLY().zzj("Failed to update remote config (got 0). appId", zzatx.zzfE(str));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error storing remote config. appId", zzatx.zzfE(str), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzd(String str, List<Integer> list) {
        zzac.zzdr(str);
        zzob();
        zzmR();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int zzfo = zzKn().zzfo(str);
            if (zzb2 <= ((long) zzfo)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Integer num = (Integer) list.get(i);
                    if (num == null || !(num instanceof Integer)) {
                        return false;
                    }
                    arrayList.add(Integer.toString(num.intValue()));
                }
            }
            String valueOf = String.valueOf(TextUtils.join(",", arrayList));
            String sb = new StringBuilder(String.valueOf(valueOf).length() + 2).append("(").append(valueOf).append(")").toString();
            return writableDatabase.delete("audience_filter_values", new StringBuilder(String.valueOf(sb).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(sb).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(zzfo)}) > 0;
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Database error querying filters. appId", zzatx.zzfE(str), e);
            return false;
        }
    }

    @WorkerThread
    public long zzfA(String str) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        return zzX(str, "first_open_count");
    }

    public void zzfB(String str) {
        try {
            getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str, str});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Failed to remove unused event metadata. appId", zzatx.zzfE(str), e);
        }
    }

    public long zzfC(String str) {
        zzac.zzdr(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b4  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzaus> zzft(java.lang.String r12) {
        /*
            r11 = this;
            r10 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            r11.zzmR()
            r11.zzob()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            java.lang.String r1 = "user_attributes"
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r3 = 0
            java.lang.String r4 = "name"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r3 = 1
            java.lang.String r4 = "origin"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r3 = 2
            java.lang.String r4 = "set_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r3 = 3
            java.lang.String r4 = "value"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            com.google.android.gms.internal.zzati r8 = r11.zzKn()     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            int r8 = r8.zzKX()     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00be, all -> 0x00b1 }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0055
            if (r7 == 0) goto L_0x0053
            r7.close()
        L_0x0053:
            r0 = r9
        L_0x0054:
            return r0
        L_0x0055:
            r0 = 0
            java.lang.String r3 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            r0 = 1
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            if (r2 != 0) goto L_0x0063
            java.lang.String r2 = ""
        L_0x0063:
            r0 = 2
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            r0 = 3
            java.lang.Object r6 = r11.zzb(r7, r0)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            if (r6 != 0) goto L_0x008d
            com.google.android.gms.internal.zzatx r0 = r11.zzKl()     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            java.lang.String r1 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r2 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            r0.zzj(r1, r2)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
        L_0x0080:
            boolean r0 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0055
            if (r7 == 0) goto L_0x008b
            r7.close()
        L_0x008b:
            r0 = r9
            goto L_0x0054
        L_0x008d:
            com.google.android.gms.internal.zzaus r0 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x0097, all -> 0x00b8 }
            goto L_0x0080
        L_0x0097:
            r0 = move-exception
            r1 = r7
        L_0x0099:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x00bb }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00bb }
            java.lang.String r3 = "Error querying user properties. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x00bb }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x00bb }
            if (r1 == 0) goto L_0x00af
            r1.close()
        L_0x00af:
            r0 = r10
            goto L_0x0054
        L_0x00b1:
            r0 = move-exception
        L_0x00b2:
            if (r10 == 0) goto L_0x00b7
            r10.close()
        L_0x00b7:
            throw r0
        L_0x00b8:
            r0 = move-exception
            r10 = r7
            goto L_0x00b2
        L_0x00bb:
            r0 = move-exception
            r10 = r1
            goto L_0x00b2
        L_0x00be:
            r0 = move-exception
            r1 = r10
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzft(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x01eb  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzatc zzfu(java.lang.String r12) {
        /*
            r11 = this;
            r10 = 0
            r9 = 1
            r8 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            r11.zzmR()
            r11.zzob()
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            java.lang.String r1 = "apps"
            r2 = 23
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 0
            java.lang.String r4 = "app_instance_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 1
            java.lang.String r4 = "gmp_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 2
            java.lang.String r4 = "resettable_device_id_hash"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 3
            java.lang.String r4 = "last_bundle_index"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 4
            java.lang.String r4 = "last_bundle_start_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 5
            java.lang.String r4 = "last_bundle_end_timestamp"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 6
            java.lang.String r4 = "app_version"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 7
            java.lang.String r4 = "app_store"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 8
            java.lang.String r4 = "gmp_version"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 9
            java.lang.String r4 = "dev_cert_hash"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 10
            java.lang.String r4 = "measurement_enabled"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 11
            java.lang.String r4 = "day"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 12
            java.lang.String r4 = "daily_public_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 13
            java.lang.String r4 = "daily_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 14
            java.lang.String r4 = "daily_conversions_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 15
            java.lang.String r4 = "config_fetched_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 16
            java.lang.String r4 = "failed_config_fetch_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 17
            java.lang.String r4 = "app_version_int"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 18
            java.lang.String r4 = "firebase_instance_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 19
            java.lang.String r4 = "daily_error_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 20
            java.lang.String r4 = "daily_realtime_events_count"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 21
            java.lang.String r4 = "health_monitor_sample"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r3 = 22
            java.lang.String r4 = "android_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x01cc, all -> 0x01e7 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x01f1 }
            if (r0 != 0) goto L_0x00b4
            if (r1 == 0) goto L_0x00b2
            r1.close()
        L_0x00b2:
            r0 = r8
        L_0x00b3:
            return r0
        L_0x00b4:
            com.google.android.gms.internal.zzatc r0 = new com.google.android.gms.internal.zzatc     // Catch:{ SQLiteException -> 0x01f1 }
            com.google.android.gms.internal.zzaue r2 = r11.zzbqc     // Catch:{ SQLiteException -> 0x01f1 }
            r0.<init>(r2, r12)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 0
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzfd(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzfe(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 2
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzff(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 3
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzad(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 4
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzY(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 5
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzZ(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 6
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.setAppVersion(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 7
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzfh(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 8
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzab(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 9
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzac(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 10
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            if (r2 == 0) goto L_0x01b2
            r2 = r9
        L_0x0116:
            if (r2 == 0) goto L_0x01ba
            r2 = r9
        L_0x0119:
            r0.setMeasurementEnabled(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 11
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzag(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 12
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzah(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 13
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzai(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 14
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzaj(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 15
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzae(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 16
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzaf(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 17
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            if (r2 == 0) goto L_0x01bd
            r2 = -2147483648(0xffffffff80000000, double:NaN)
        L_0x015d:
            r0.zzaa(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 18
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzfg(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 19
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzal(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 20
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzak(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 21
            java.lang.String r2 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzfi(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r2 = 22
            boolean r2 = r1.isNull(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            if (r2 == 0) goto L_0x01c5
            r2 = 0
        L_0x018e:
            r0.zzam(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            r0.zzKo()     // Catch:{ SQLiteException -> 0x01f1 }
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x01f1 }
            if (r2 == 0) goto L_0x01ab
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ SQLiteException -> 0x01f1 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x01f1 }
            java.lang.String r3 = "Got multiple records for app, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x01f1 }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x01f1 }
        L_0x01ab:
            if (r1 == 0) goto L_0x00b3
            r1.close()
            goto L_0x00b3
        L_0x01b2:
            r2 = 10
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            goto L_0x0116
        L_0x01ba:
            r2 = r10
            goto L_0x0119
        L_0x01bd:
            r2 = 17
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            long r2 = (long) r2     // Catch:{ SQLiteException -> 0x01f1 }
            goto L_0x015d
        L_0x01c5:
            r2 = 22
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x01f1 }
            goto L_0x018e
        L_0x01cc:
            r0 = move-exception
            r1 = r8
        L_0x01ce:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x01ef }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x01ef }
            java.lang.String r3 = "Error querying app. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x01ef }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x01ef }
            if (r1 == 0) goto L_0x01e4
            r1.close()
        L_0x01e4:
            r0 = r8
            goto L_0x00b3
        L_0x01e7:
            r0 = move-exception
            r1 = r8
        L_0x01e9:
            if (r1 == 0) goto L_0x01ee
            r1.close()
        L_0x01ee:
            throw r0
        L_0x01ef:
            r0 = move-exception
            goto L_0x01e9
        L_0x01f1:
            r0 = move-exception
            goto L_0x01ce
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfu(java.lang.String):com.google.android.gms.internal.zzatc");
    }

    public long zzfv(String str) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(zzKn().zzfs(str))});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error deleting over the limit events. appId", zzatx.zzfE(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] zzfw(java.lang.String r10) {
        /*
            r9 = this;
            r8 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r10)
            r9.zzmR()
            r9.zzob()
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            java.lang.String r1 = "apps"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r3 = 0
            java.lang.String r4 = "remote_config"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0056, all -> 0x0070 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x007a }
            if (r0 != 0) goto L_0x0034
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            r0 = r8
        L_0x0033:
            return r0
        L_0x0034:
            r0 = 0
            byte[] r0 = r1.getBlob(r0)     // Catch:{ SQLiteException -> 0x007a }
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x007a }
            if (r2 == 0) goto L_0x0050
            com.google.android.gms.internal.zzatx r2 = r9.zzKl()     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ SQLiteException -> 0x007a }
            java.lang.String r3 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ SQLiteException -> 0x007a }
            r2.zzj(r3, r4)     // Catch:{ SQLiteException -> 0x007a }
        L_0x0050:
            if (r1 == 0) goto L_0x0033
            r1.close()
            goto L_0x0033
        L_0x0056:
            r0 = move-exception
            r1 = r8
        L_0x0058:
            com.google.android.gms.internal.zzatx r2 = r9.zzKl()     // Catch:{ all -> 0x0078 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0078 }
            java.lang.String r3 = "Error querying remote config. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ all -> 0x0078 }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x006e
            r1.close()
        L_0x006e:
            r0 = r8
            goto L_0x0033
        L_0x0070:
            r0 = move-exception
            r1 = r8
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            throw r0
        L_0x0078:
            r0 = move-exception
            goto L_0x0072
        L_0x007a:
            r0 = move-exception
            goto L_0x0058
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfw(java.lang.String):byte[]");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzfx(String str) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=?", new String[]{str});
        writableDatabase.delete("event_filters", "app_id=?", new String[]{str});
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Integer, com.google.android.gms.internal.zzauw.zzf> zzfy(java.lang.String r10) {
        /*
            r9 = this;
            r8 = 0
            r9.zzob()
            r9.zzmR()
            com.google.android.gms.common.internal.zzac.zzdr(r10)
            android.database.sqlite.SQLiteDatabase r0 = r9.getWritableDatabase()
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            r3 = 0
            java.lang.String r4 = "audience_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            r3 = 1
            java.lang.String r4 = "current_results"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            r5 = 0
            r4[r5] = r10     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x00a1, all -> 0x0097 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x007e }
            if (r0 != 0) goto L_0x0039
            if (r1 == 0) goto L_0x0037
            r1.close()
        L_0x0037:
            r0 = r8
        L_0x0038:
            return r0
        L_0x0039:
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap     // Catch:{ SQLiteException -> 0x007e }
            r0.<init>()     // Catch:{ SQLiteException -> 0x007e }
        L_0x003e:
            r2 = 0
            int r2 = r1.getInt(r2)     // Catch:{ SQLiteException -> 0x007e }
            r3 = 1
            byte[] r3 = r1.getBlob(r3)     // Catch:{ SQLiteException -> 0x007e }
            com.google.android.gms.internal.zzbxl r3 = com.google.android.gms.internal.zzbxl.zzaf(r3)     // Catch:{ SQLiteException -> 0x007e }
            com.google.android.gms.internal.zzauw$zzf r4 = new com.google.android.gms.internal.zzauw$zzf     // Catch:{ SQLiteException -> 0x007e }
            r4.<init>()     // Catch:{ SQLiteException -> 0x007e }
            r4.zzb(r3)     // Catch:{ IOException -> 0x0067 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007e }
            r0.put(r2, r4)     // Catch:{ SQLiteException -> 0x007e }
        L_0x005b:
            boolean r2 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x007e }
            if (r2 != 0) goto L_0x003e
            if (r1 == 0) goto L_0x0038
            r1.close()
            goto L_0x0038
        L_0x0067:
            r3 = move-exception
            com.google.android.gms.internal.zzatx r4 = r9.zzKl()     // Catch:{ SQLiteException -> 0x007e }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x007e }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ SQLiteException -> 0x007e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007e }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x007e }
            goto L_0x005b
        L_0x007e:
            r0 = move-exception
        L_0x007f:
            com.google.android.gms.internal.zzatx r2 = r9.zzKl()     // Catch:{ all -> 0x009f }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x009f }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r10)     // Catch:{ all -> 0x009f }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x009f }
            if (r1 == 0) goto L_0x0095
            r1.close()
        L_0x0095:
            r0 = r8
            goto L_0x0038
        L_0x0097:
            r0 = move-exception
            r1 = r8
        L_0x0099:
            if (r1 == 0) goto L_0x009e
            r1.close()
        L_0x009e:
            throw r0
        L_0x009f:
            r0 = move-exception
            goto L_0x0099
        L_0x00a1:
            r0 = move-exception
            r1 = r8
            goto L_0x007f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzfy(java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zzfz(String str) {
        zzob();
        zzmR();
        zzac.zzdr(str);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr);
            if (delete > 0) {
                zzKl().zzMe().zze("Deleted application data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error deleting application data. appId, error", zzatx.zzfE(str), e);
        }
    }

    @WorkerThread
    public List<zzatg> zzh(String str, long j) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zze("Invalid time querying timed out conditional properties", zzatx.zzfE(str), Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
    }

    @WorkerThread
    public List<zzatg> zzi(String str, long j) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        if (j < 0) {
            zzKl().zzMa().zze("Invalid time querying expired conditional properties", zzatx.zzfE(str), Long.valueOf(j));
            return Collections.emptyList();
        }
        return zzc("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x011f, code lost:
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0127, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0128, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0087] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.internal.zzaus> zzk(java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            r10 = 0
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            r11.zzmR()
            r11.zzob()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r1 = 3
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r0.add(r12)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r1 = "app_id=?"
            r3.<init>(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            boolean r1 = android.text.TextUtils.isEmpty(r13)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            if (r1 != 0) goto L_0x002d
            r0.add(r13)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r1 = " and origin=?"
            r3.append(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
        L_0x002d:
            boolean r1 = android.text.TextUtils.isEmpty(r14)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            if (r1 != 0) goto L_0x0045
            java.lang.String r1 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r2 = "*"
            java.lang.String r1 = r1.concat(r2)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r0.add(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r1 = " and name glob ?"
            r3.append(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
        L_0x0045:
            int r1 = r0.size()     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.Object[] r4 = r0.toArray(r1)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r1 = "user_attributes"
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r5 = 0
            java.lang.String r6 = "name"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r5 = 1
            java.lang.String r6 = "set_timestamp"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r5 = 2
            java.lang.String r6 = "value"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r5 = 3
            java.lang.String r6 = "origin"
            r2[r5] = r6     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            com.google.android.gms.internal.zzati r8 = r11.zzKn()     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r8.zzKX()     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            r8 = 1001(0x3e9, float:1.403E-42)
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0124, all -> 0x0117 }
            boolean r0 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            if (r0 != 0) goto L_0x0095
            if (r7 == 0) goto L_0x0092
            r7.close()
        L_0x0092:
            r0 = r9
        L_0x0093:
            return r0
        L_0x0094:
            r13 = r2
        L_0x0095:
            int r0 = r9.size()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            com.google.android.gms.internal.zzati r1 = r11.zzKn()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            int r1 = r1.zzKX()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            if (r0 < r1) goto L_0x00c3
            com.google.android.gms.internal.zzatx r0 = r11.zzKl()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            java.lang.String r1 = "Read more than the max allowed user properties, ignoring excess"
            com.google.android.gms.internal.zzati r2 = r11.zzKn()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            int r2 = r2.zzKX()     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            r0.zzj(r1, r2)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
        L_0x00bc:
            if (r7 == 0) goto L_0x00c1
            r7.close()
        L_0x00c1:
            r0 = r9
            goto L_0x0093
        L_0x00c3:
            r0 = 0
            java.lang.String r3 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            r0 = 1
            long r4 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            r0 = 2
            java.lang.Object r6 = r11.zzb(r7, r0)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            r0 = 3
            java.lang.String r2 = r7.getString(r0)     // Catch:{ SQLiteException -> 0x0127, all -> 0x011e }
            if (r6 != 0) goto L_0x00f1
            com.google.android.gms.internal.zzatx r0 = r11.zzKl()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            com.google.android.gms.internal.zzatx$zza r0 = r0.zzLY()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            java.lang.String r1 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r3 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            r0.zzd(r1, r3, r2, r14)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
        L_0x00ea:
            boolean r0 = r7.moveToNext()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            if (r0 != 0) goto L_0x0094
            goto L_0x00bc
        L_0x00f1:
            com.google.android.gms.internal.zzaus r0 = new com.google.android.gms.internal.zzaus     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r6)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x011e }
            goto L_0x00ea
        L_0x00fb:
            r0 = move-exception
            r1 = r7
            r13 = r2
        L_0x00fe:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x0121 }
            r2.zzd(r3, r4, r13, r0)     // Catch:{ all -> 0x0121 }
            if (r1 == 0) goto L_0x0114
            r1.close()
        L_0x0114:
            r0 = r10
            goto L_0x0093
        L_0x0117:
            r0 = move-exception
        L_0x0118:
            if (r10 == 0) goto L_0x011d
            r10.close()
        L_0x011d:
            throw r0
        L_0x011e:
            r0 = move-exception
            r10 = r7
            goto L_0x0118
        L_0x0121:
            r0 = move-exception
            r10 = r1
            goto L_0x0118
        L_0x0124:
            r0 = move-exception
            r1 = r10
            goto L_0x00fe
        L_0x0127:
            r0 = move-exception
            r1 = r7
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzk(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public List<zzatg> zzl(String str, String str2, String str3) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzc(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e6  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.util.Pair<com.google.android.gms.internal.zzauw.zze, java.lang.Long>> zzn(java.lang.String r12, int r13, int r14) {
        /*
            r11 = this;
            r10 = 0
            r1 = 1
            r9 = 0
            r11.zzmR()
            r11.zzob()
            if (r13 <= 0) goto L_0x004e
            r0 = r1
        L_0x000c:
            com.google.android.gms.common.internal.zzac.zzax(r0)
            if (r14 <= 0) goto L_0x0050
        L_0x0011:
            com.google.android.gms.common.internal.zzac.zzax(r1)
            com.google.android.gms.common.internal.zzac.zzdr(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            java.lang.String r1 = "queue"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            r3 = 0
            java.lang.String r4 = "rowid"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            r3 = 1
            java.lang.String r4 = "data"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            java.lang.String r3 = "app_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            r5 = 0
            r4[r5] = r12     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "rowid"
            java.lang.String r8 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00ef, all -> 0x00e2 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            if (r0 != 0) goto L_0x0052
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            if (r2 == 0) goto L_0x004d
            r2.close()
        L_0x004d:
            return r0
        L_0x004e:
            r0 = r9
            goto L_0x000c
        L_0x0050:
            r1 = r9
            goto L_0x0011
        L_0x0052:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r0.<init>()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r3 = r9
        L_0x0058:
            r1 = 0
            long r4 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r1 = 1
            byte[] r1 = r2.getBlob(r1)     // Catch:{ IOException -> 0x007a }
            com.google.android.gms.internal.zzaut r6 = r11.zzKh()     // Catch:{ IOException -> 0x007a }
            byte[] r1 = r6.zzx(r1)     // Catch:{ IOException -> 0x007a }
            boolean r6 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            if (r6 != 0) goto L_0x0097
            int r6 = r1.length     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            int r6 = r6 + r3
            if (r6 <= r14) goto L_0x0097
        L_0x0074:
            if (r2 == 0) goto L_0x004d
            r2.close()
            goto L_0x004d
        L_0x007a:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r4 = r11.zzKl()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            java.lang.String r5 = "Failed to unzip queued bundle. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r4.zze(r5, r6, r1)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r1 = r3
        L_0x008d:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            if (r3 == 0) goto L_0x0074
            if (r1 > r14) goto L_0x0074
            r3 = r1
            goto L_0x0058
        L_0x0097:
            com.google.android.gms.internal.zzbxl r6 = com.google.android.gms.internal.zzbxl.zzaf(r1)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            com.google.android.gms.internal.zzauw$zze r7 = new com.google.android.gms.internal.zzauw$zze     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r7.<init>()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r7.zzb(r6)     // Catch:{ IOException -> 0x00ce }
            int r1 = r1.length     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            int r1 = r1 + r3
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            android.util.Pair r3 = android.util.Pair.create(r7, r3)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            goto L_0x008d
        L_0x00b1:
            r0 = move-exception
            r1 = r2
        L_0x00b3:
            com.google.android.gms.internal.zzatx r2 = r11.zzKl()     // Catch:{ all -> 0x00ec }
            com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = "Error querying bundles. appId"
            java.lang.Object r4 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ all -> 0x00ec }
            r2.zze(r3, r4, r0)     // Catch:{ all -> 0x00ec }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00ec }
            if (r1 == 0) goto L_0x004d
            r1.close()
            goto L_0x004d
        L_0x00ce:
            r1 = move-exception
            com.google.android.gms.internal.zzatx r4 = r11.zzKl()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            com.google.android.gms.internal.zzatx$zza r4 = r4.zzLY()     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            java.lang.String r5 = "Failed to merge queued bundle. appId"
            java.lang.Object r6 = com.google.android.gms.internal.zzatx.zzfE(r12)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r4.zze(r5, r6, r1)     // Catch:{ SQLiteException -> 0x00b1, all -> 0x00ea }
            r1 = r3
            goto L_0x008d
        L_0x00e2:
            r0 = move-exception
            r2 = r10
        L_0x00e4:
            if (r2 == 0) goto L_0x00e9
            r2.close()
        L_0x00e9:
            throw r0
        L_0x00ea:
            r0 = move-exception
            goto L_0x00e4
        L_0x00ec:
            r0 = move-exception
            r2 = r1
            goto L_0x00e4
        L_0x00ef:
            r0 = move-exception
            r1 = r10
            goto L_0x00b3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatj.zzn(java.lang.String, int, int):java.util.List");
    }

    /* access modifiers changed from: 0000 */
    public String zzow() {
        return zzKn().zzpv();
    }

    @WorkerThread
    public void zzz(String str, int i) {
        zzac.zzdr(str);
        zzmR();
        zzob();
        try {
            getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(i)});
        } catch (SQLiteException e) {
            zzKl().zzLY().zze("Error pruning currencies. appId", zzatx.zzfE(str), e);
        }
    }
}
