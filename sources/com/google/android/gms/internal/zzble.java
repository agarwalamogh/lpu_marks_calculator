package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzble {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzbVN = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbVO = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbVP = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzbVQ = new AtomicBoolean();
    static HashMap<String, String> zzbVR;
    private static Object zzbVS;
    private static boolean zzbVT;
    static String[] zzbVU = new String[0];

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        String string = getString(contentResolver, str);
        if (string == null) {
            return j;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return j;
        }
    }

    @Deprecated
    public static String getString(ContentResolver contentResolver, String str) {
        return zza(contentResolver, str, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (zzbVT == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (zzbVR.isEmpty() == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        zzc(r9, zzbVU);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (zzbVR.containsKey(r10) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r0 = (java.lang.String) zzbVR.get(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        if (r0 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        r11 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r9, java.lang.String r10, java.lang.String r11) {
        /*
            r8 = 1
            r3 = 0
            r2 = 0
            java.lang.Class<com.google.android.gms.internal.zzble> r1 = com.google.android.gms.internal.zzble.class
            monitor-enter(r1)
            zza(r9)     // Catch:{ all -> 0x0054 }
            java.lang.Object r6 = zzbVS     // Catch:{ all -> 0x0054 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbVR     // Catch:{ all -> 0x0054 }
            boolean r0 = r0.containsKey(r10)     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0020
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbVR     // Catch:{ all -> 0x0054 }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x0054 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x001e
            r11 = r0
        L_0x001e:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
        L_0x001f:
            return r11
        L_0x0020:
            java.lang.String[] r4 = zzbVU     // Catch:{ all -> 0x0054 }
            int r5 = r4.length     // Catch:{ all -> 0x0054 }
            r0 = r3
        L_0x0024:
            if (r0 >= r5) goto L_0x005c
            r7 = r4[r0]     // Catch:{ all -> 0x0054 }
            boolean r7 = r10.startsWith(r7)     // Catch:{ all -> 0x0054 }
            if (r7 == 0) goto L_0x0059
            boolean r0 = zzbVT     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x003a
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbVR     // Catch:{ all -> 0x0054 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0057
        L_0x003a:
            java.lang.String[] r0 = zzbVU     // Catch:{ all -> 0x0054 }
            zzc(r9, r0)     // Catch:{ all -> 0x0054 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbVR     // Catch:{ all -> 0x0054 }
            boolean r0 = r0.containsKey(r10)     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0057
            java.util.HashMap<java.lang.String, java.lang.String> r0 = zzbVR     // Catch:{ all -> 0x0054 }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x0054 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0052
            r11 = r0
        L_0x0052:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            goto L_0x001f
        L_0x0054:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            throw r0
        L_0x0057:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            goto L_0x001f
        L_0x0059:
            int r0 = r0 + 1
            goto L_0x0024
        L_0x005c:
            monitor-exit(r1)     // Catch:{ all -> 0x0054 }
            android.net.Uri r1 = CONTENT_URI
            java.lang.String[] r4 = new java.lang.String[r8]
            r4[r3] = r10
            r0 = r9
            r3 = r2
            r5 = r2
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)
            if (r1 == 0) goto L_0x0072
            boolean r0 = r1.moveToFirst()     // Catch:{ all -> 0x0096 }
            if (r0 != 0) goto L_0x007c
        L_0x0072:
            r0 = 0
            zza(r6, r10, r0)     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x001f
            r1.close()
            goto L_0x001f
        L_0x007c:
            r0 = 1
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0096 }
            if (r0 == 0) goto L_0x008a
            boolean r2 = r0.equals(r11)     // Catch:{ all -> 0x0096 }
            if (r2 == 0) goto L_0x008a
            r0 = r11
        L_0x008a:
            zza(r6, r10, r0)     // Catch:{ all -> 0x0096 }
            if (r0 == 0) goto L_0x0090
            r11 = r0
        L_0x0090:
            if (r1 == 0) goto L_0x001f
            r1.close()
            goto L_0x001f
        L_0x0096:
            r0 = move-exception
            if (r1 == 0) goto L_0x009c
            r1.close()
        L_0x009c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzble.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    public static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbVN, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbVR == null) {
            zzbVQ.set(false);
            zzbVR = new HashMap<>();
            zzbVS = new Object();
            zzbVT = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new ContentObserver(null) {
                public void onChange(boolean z) {
                    zzble.zzbVQ.set(true);
                }
            });
        } else if (zzbVQ.getAndSet(false)) {
            zzbVR.clear();
            zzbVS = new Object();
            zzbVT = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzble.class) {
            if (obj == zzbVS) {
                zzbVR.put(str, str2);
            }
        }
    }

    public static void zzb(ContentResolver contentResolver, String... strArr) {
        if (strArr.length != 0) {
            synchronized (zzble.class) {
                zza(contentResolver);
                String[] zzk = zzk(strArr);
                if (!zzbVT || zzbVR.isEmpty()) {
                    zzc(contentResolver, zzbVU);
                } else if (zzk.length != 0) {
                    zzc(contentResolver, zzk);
                }
            }
        }
    }

    private static void zzc(ContentResolver contentResolver, String[] strArr) {
        zzbVR.putAll(zza(contentResolver, strArr));
        zzbVT = true;
    }

    private static String[] zzk(String[] strArr) {
        HashSet hashSet = new HashSet((((zzbVU.length + strArr.length) * 4) / 3) + 1);
        hashSet.addAll(Arrays.asList(zzbVU));
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (hashSet.add(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return new String[0];
        }
        zzbVU = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
