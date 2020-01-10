package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbxs;
import com.google.android.gms.internal.zzbxz.zza;
import com.google.android.gms.internal.zzbxz.zzb;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class zzc {
    @Nullable
    static zzb zzU(@NonNull byte[] bArr) {
        try {
            return zzb.zzak(bArr);
        } catch (zzbxs e) {
            return null;
        }
    }

    static int zza(@NonNull zzb zzb, int i) {
        if (zzb.zzcwa != 0) {
            return zzb.zzcwa;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    static Bundle zza(@NonNull zzb zzb) {
        return zzam(zzb.zzcvP, zzb.zzcvQ);
    }

    @Nullable
    static Object zza(@NonNull zzb zzb, @NonNull String str, @NonNull zzb zzb2) {
        Object obj;
        String str2 = null;
        try {
            Class cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zza = zza(zzb);
            obj = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(obj, str);
                cls.getField("mCreationTimestamp").set(obj, Long.valueOf(zzb.zzcvR));
                cls.getField("mName").set(obj, zzb.zzcvP);
                cls.getField("mValue").set(obj, zzb.zzcvQ);
                if (!TextUtils.isEmpty(zzb.zzcvS)) {
                    str2 = zzb.zzcvS;
                }
                cls.getField("mTriggerEventName").set(obj, str2);
                cls.getField("mTimedOutEventName").set(obj, zzd(zzb, zzb2));
                cls.getField("mTimedOutEventParams").set(obj, zza);
                cls.getField("mTriggerTimeout").set(obj, Integer.valueOf(zzb.zzcvT));
                cls.getField("mTriggeredEventName").set(obj, zzb(zzb, zzb2));
                cls.getField("mTriggeredEventParams").set(obj, zza);
                cls.getField("mTimeToLive").set(obj, Integer.valueOf(zzb.zzcvU));
                cls.getField("mExpiredEventName").set(obj, zze(zzb, zzb2));
                cls.getField("mExpiredEventParams").set(obj, zza);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
                e = e;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return obj;
            }
        } catch (ClassNotFoundException e2) {
            e = e2;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        } catch (NoSuchMethodException e3) {
            e = e3;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        } catch (IllegalAccessException e4) {
            e = e4;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        } catch (InvocationTargetException e5) {
            e = e5;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        } catch (NoSuchFieldException e6) {
            e = e6;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        } catch (InstantiationException e7) {
            e = e7;
            obj = null;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        }
        return obj;
    }

    static String zza(@NonNull zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvV) ? zzb.zzcvV : zzb2.zzUZ();
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str5 = "FirebaseAbtUtil";
            String str6 = "_CE(experimentId) called by ";
            String valueOf = String.valueOf(str);
            Log.v(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
        }
        if (zzco(context)) {
            AppMeasurement zzbj = zzbj(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str2).length() + 17 + String.valueOf(str3).length()).append("Clearing _E: [").append(str2).append(", ").append(str3).append("]").toString());
                }
                declaredMethod.invoke(zzbj, new Object[]{str2, str4, zzam(str2, str3)});
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull byte[] bArr, @NonNull zzb zzb, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = "FirebaseAbtUtil";
            String str3 = "_SE called by ";
            String valueOf = String.valueOf(str);
            Log.v(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        if (zzco(context)) {
            AppMeasurement zzbj = zzbj(context);
            zzb zzU = zzU(bArr);
            if (zzU != null) {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    boolean z = false;
                    for (Object next : zzb(zzbj, str)) {
                        String zzab = zzab(next);
                        String zzac = zzac(next);
                        long zzaI = zzaI(next);
                        if (!zzU.zzcvP.equals(zzab) || !zzU.zzcvQ.equals(zzac)) {
                            boolean z2 = false;
                            zza[] zzaArr = zzU.zzcwb;
                            int length = zzaArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length) {
                                    break;
                                } else if (zzaArr[i2].zzcvP.equals(zzab)) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab).length() + 33 + String.valueOf(zzac).length()).append("_E is found in the _OE list. [").append(zzab).append(", ").append(zzac).append("]").toString());
                                    }
                                    z2 = true;
                                } else {
                                    i2++;
                                }
                            }
                            if (!z2) {
                                if (zzU.zzcvR > zzaI) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab).length() + 115 + String.valueOf(zzac).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzab).append(", ").append(zzac).append("]").toString());
                                    }
                                    zza(context, str, zzab, zzac, zzc(zzU, zzb));
                                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab).length() + 109 + String.valueOf(zzac).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzab).append(", ").append(zzac).append("]").toString());
                                }
                            }
                        } else {
                            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab).length() + 23 + String.valueOf(zzac).length()).append("_E is already set. [").append(zzab).append(", ").append(zzac).append("]").toString());
                            }
                            z = true;
                        }
                    }
                    if (!z) {
                        zza(zzbj, context, str, zzU, zzb, i);
                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        String valueOf2 = String.valueOf(zzU.zzcvP);
                        String valueOf3 = String.valueOf(zzU.zzcvQ);
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(valueOf2).length() + 44 + String.valueOf(valueOf3).length()).append("_E is already set. Not setting it again [").append(valueOf2).append(", ").append(valueOf3).append("]").toString());
                    }
                } catch (ClassNotFoundException e) {
                    e = e;
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                } catch (IllegalAccessException e2) {
                    e = e2;
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                } catch (NoSuchFieldException e3) {
                    e = e3;
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
            }
        }
    }

    static void zza(@NonNull AppMeasurement appMeasurement, @NonNull Context context, @NonNull String str, @NonNull zzb zzb, @NonNull zzb zzb2, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(zzb.zzcvP);
            String valueOf2 = String.valueOf(zzb.zzcvQ);
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(valueOf).length() + 7 + String.valueOf(valueOf2).length()).append("_SEI: ").append(valueOf).append(" ").append(valueOf2).toString());
        }
        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List zzb3 = zzb(appMeasurement, str);
            if (zza(appMeasurement, str)) {
                if (zza(zzb, i) == 1) {
                    Object obj = zzb3.get(0);
                    String zzab = zzab(obj);
                    String zzac = zzac(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab).length() + 38).append("Clearing _E due to overflow policy: [").append(zzab).append("]").toString());
                    }
                    zza(context, str, zzab, zzac, zzc(zzb, zzb2));
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    String valueOf3 = String.valueOf(zzb.zzcvP);
                    String valueOf4 = String.valueOf(zzb.zzcvQ);
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(valueOf3).length() + 44 + String.valueOf(valueOf4).length()).append("_E won't be set due to overflow policy. [").append(valueOf3).append(", ").append(valueOf4).append("]").toString());
                    return;
                } else {
                    return;
                }
            }
            for (Object next : zzb3) {
                String zzab2 = zzab(next);
                String zzac2 = zzac(next);
                if (zzab2.equals(zzb.zzcvP) && !zzac2.equals(zzb.zzcvQ) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzab2).length() + 77 + String.valueOf(zzac2).length()).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(zzab2).append(", ").append(zzac2).append("].").toString());
                    zza(context, str, zzab2, zzac2, zzc(zzb, zzb2));
                }
            }
            Object zza = zza(zzb, str, zzb2);
            if (zza != null) {
                zza(appMeasurement, zzb, zzb2, zza, str);
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                String valueOf5 = String.valueOf(zzb.zzcvP);
                String valueOf6 = String.valueOf(zzb.zzcvQ);
                Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(valueOf5).length() + 42 + String.valueOf(valueOf6).length()).append("Could not create _CUP for: [").append(valueOf5).append(", ").append(valueOf6).append("]. Skipping.").toString());
            }
        } catch (ClassNotFoundException e) {
            e = e;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (IllegalAccessException e2) {
            e = e2;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        } catch (NoSuchFieldException e3) {
            e = e3;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    static void zza(@NonNull AppMeasurement appMeasurement, @NonNull zzb zzb, @NonNull zzb zzb2, @NonNull Object obj, @NonNull String str) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(zzb.zzcvP);
            String valueOf2 = String.valueOf(zzb.zzcvQ);
            String valueOf3 = String.valueOf(zzb.zzcvS);
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(valueOf).length() + 27 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append("Setting _CUP for _E: [").append(valueOf).append(", ").append(valueOf2).append(", ").append(valueOf3).append("]").toString());
        }
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty")});
            declaredMethod.setAccessible(true);
            appMeasurement.logEventInternal(str, zza(zzb, zzb2), zza(zzb));
            declaredMethod.invoke(appMeasurement, new Object[]{obj});
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
    }

    static boolean zza(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        return zzb(appMeasurement, str).size() >= zzc(appMeasurement, str);
    }

    static long zzaI(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(obj)).longValue();
    }

    static String zzab(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    static String zzac(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    static Bundle zzam(@NonNull String str, @NonNull String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    static String zzb(@NonNull zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvW) ? zzb.zzcvW : zzb2.zzVa();
    }

    static List<Object> zzb(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        List<Object> list;
        List<Object> arrayList = new ArrayList<>();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            list = arrayList;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
        }
        return list;
    }

    @Nullable
    static AppMeasurement zzbj(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    static int zzc(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    static String zzc(@Nullable zzb zzb, @NonNull zzb zzb2) {
        return (zzb == null || TextUtils.isEmpty(zzb.zzcvX)) ? zzb2.zzVd() : zzb.zzcvX;
    }

    private static boolean zzco(Context context) {
        if (zzbj(context) != null) {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                return true;
            } catch (ClassNotFoundException e) {
                if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                    return false;
                }
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
                return false;
            }
        } else if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
            return false;
        } else {
            Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            return false;
        }
    }

    static String zzd(@NonNull zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvY) ? zzb.zzcvY : zzb2.zzVb();
    }

    static String zze(@NonNull zzb zzb, @NonNull zzb zzb2) {
        return !TextUtils.isEmpty(zzb.zzcvZ) ? zzb.zzcvZ : zzb2.zzVc();
    }
}
