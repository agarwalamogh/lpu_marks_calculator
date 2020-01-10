package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauu.zza;
import com.google.android.gms.internal.zzauu.zzb;
import com.google.android.gms.internal.zzauu.zzc;
import com.google.android.gms.internal.zzauu.zzd;
import com.google.android.gms.internal.zzauu.zze;
import com.google.android.gms.internal.zzauu.zzf;
import com.google.android.gms.internal.zzauw.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

class zzatf extends zzauh {
    zzatf(zzaue zzaue) {
        super(zzaue);
    }

    private Boolean zza(zzb zzb, zzauw.zzb zzb2, long j) {
        zzc[] zzcArr;
        zzauw.zzc[] zzcArr2;
        zzc[] zzcArr3;
        Boolean zza;
        if (zzb.zzbws != null) {
            Boolean zza2 = zza(j, zzb.zzbws);
            if (zza2 == null) {
                return null;
            }
            if (!zza2.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        HashSet hashSet = new HashSet();
        for (zzc zzc : zzb.zzbwq) {
            if (TextUtils.isEmpty(zzc.zzbwx)) {
                zzKl().zzMa().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            hashSet.add(zzc.zzbwx);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzauw.zzc zzc2 : zzb2.zzbwY) {
            if (hashSet.contains(zzc2.name)) {
                if (zzc2.zzbxc != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbxc);
                } else if (zzc2.zzbwf != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbwf);
                } else if (zzc2.zzaGV != null) {
                    arrayMap.put(zzc2.name, zzc2.zzaGV);
                } else {
                    zzKl().zzMa().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                    return null;
                }
            }
        }
        for (zzc zzc3 : zzb.zzbwq) {
            boolean equals = Boolean.TRUE.equals(zzc3.zzbww);
            String str = zzc3.zzbwx;
            if (TextUtils.isEmpty(str)) {
                zzKl().zzMa().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (zzc3.zzbwv == null) {
                    zzKl().zzMa().zze("No number filter for long param. event, param", zzb2.name, str);
                    return null;
                }
                Boolean zza3 = zza(((Long) obj).longValue(), zzc3.zzbwv);
                if (zza3 == null) {
                    return null;
                }
                if ((!zza3.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzc3.zzbwv == null) {
                    zzKl().zzMa().zze("No number filter for double param. event, param", zzb2.name, str);
                    return null;
                }
                Boolean zza4 = zza(((Double) obj).doubleValue(), zzc3.zzbwv);
                if (zza4 == null) {
                    return null;
                }
                if ((!zza4.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzc3.zzbwu != null) {
                    zza = zza((String) obj, zzc3.zzbwu);
                } else if (zzc3.zzbwv == null) {
                    zzKl().zzMa().zze("No filter for String param. event, param", zzb2.name, str);
                    return null;
                } else if (zzaut.zzgf((String) obj)) {
                    zza = zza((String) obj, zzc3.zzbwv);
                } else {
                    zzKl().zzMa().zze("Invalid param value for number filter. event, param", zzb2.name, str);
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if ((!zza.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzKl().zzMe().zze("Missing param for filter. event, param", zzb2.name, str);
                return Boolean.valueOf(false);
            } else {
                zzKl().zzMa().zze("Unknown param type. event, param", zzb2.name, str);
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private Boolean zza(zze zze, zzg zzg) {
        zzc zzc = zze.zzbwF;
        if (zzc == null) {
            zzKl().zzMa().zzj("Missing property filter. property", zzg.name);
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzc.zzbww);
        if (zzg.zzbxc != null) {
            if (zzc.zzbwv != null) {
                return zza(zza(zzg.zzbxc.longValue(), zzc.zzbwv), equals);
            }
            zzKl().zzMa().zzj("No number filter for long property. property", zzg.name);
            return null;
        } else if (zzg.zzbwf != null) {
            if (zzc.zzbwv != null) {
                return zza(zza(zzg.zzbwf.doubleValue(), zzc.zzbwv), equals);
            }
            zzKl().zzMa().zzj("No number filter for double property. property", zzg.name);
            return null;
        } else if (zzg.zzaGV == null) {
            zzKl().zzMa().zzj("User property has no value, property", zzg.name);
            return null;
        } else if (zzc.zzbwu != null) {
            return zza(zza(zzg.zzaGV, zzc.zzbwu), equals);
        } else {
            if (zzc.zzbwv == null) {
                zzKl().zzMa().zzj("No string or number filter defined. property", zzg.name);
                return null;
            } else if (zzaut.zzgf(zzg.zzaGV)) {
                return zza(zza(zzg.zzaGV, zzc.zzbwv), equals);
            } else {
                zzKl().zzMa().zze("Invalid user property value for Numeric number filter. property, value", zzg.name, zzg.zzaGV);
                return null;
            }
        }
    }

    static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private Boolean zza(BigDecimal bigDecimal, int i, BigDecimal bigDecimal2, BigDecimal bigDecimal3, BigDecimal bigDecimal4, double d) {
        boolean z = true;
        if (bigDecimal == null) {
            return null;
        }
        if (i == 4) {
            if (bigDecimal3 == null || bigDecimal4 == null) {
                return null;
            }
        } else if (bigDecimal2 == null) {
            return null;
        }
        switch (i) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal2) != -1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal2) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal2) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (bigDecimal.compareTo(bigDecimal3) == -1 || bigDecimal.compareTo(bigDecimal4) == 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        ArrayList arrayList = new ArrayList();
        for (String upperCase : strArr) {
            arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zza(double d, zzd zzd) {
        try {
            return zza(new BigDecimal(d), zzd, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(long j, zzd zzd) {
        try {
            return zza(new BigDecimal(j), zzd, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(String str, zzd zzd) {
        Boolean bool = null;
        if (!zzaut.zzgf(str)) {
            return bool;
        }
        try {
            return zza(new BigDecimal(str), zzd, 0.0d);
        } catch (NumberFormatException e) {
            return bool;
        }
    }

    /* access modifiers changed from: 0000 */
    public Boolean zza(String str, zzf zzf) {
        String str2 = null;
        zzac.zzw(zzf);
        if (str == null || zzf.zzbwG == null || zzf.zzbwG.intValue() == 0) {
            return null;
        }
        if (zzf.zzbwG.intValue() == 6) {
            if (zzf.zzbwJ == null || zzf.zzbwJ.length == 0) {
                return null;
            }
        } else if (zzf.zzbwH == null) {
            return null;
        }
        int intValue = zzf.zzbwG.intValue();
        boolean z = zzf.zzbwI != null && zzf.zzbwI.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzf.zzbwH : zzf.zzbwH.toUpperCase(Locale.ENGLISH);
        List zza = zzf.zzbwJ == null ? null : zza(zzf.zzbwJ, z);
        if (intValue == 1) {
            str2 = upperCase;
        }
        return zza(str, intValue, z, upperCase, zza, str2);
    }

    /* access modifiers changed from: 0000 */
    public Boolean zza(BigDecimal bigDecimal, zzd zzd, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        zzac.zzw(zzd);
        if (zzd.zzbwy == null || zzd.zzbwy.intValue() == 0) {
            return null;
        }
        if (zzd.zzbwy.intValue() == 4) {
            if (zzd.zzbwB == null || zzd.zzbwC == null) {
                return null;
            }
        } else if (zzd.zzbwA == null) {
            return null;
        }
        int intValue = zzd.zzbwy.intValue();
        if (zzd.zzbwy.intValue() == 4) {
            if (!zzaut.zzgf(zzd.zzbwB) || !zzaut.zzgf(zzd.zzbwC)) {
                return null;
            }
            try {
                bigDecimal4 = new BigDecimal(zzd.zzbwB);
                bigDecimal3 = new BigDecimal(zzd.zzbwC);
                bigDecimal2 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!zzaut.zzgf(zzd.zzbwA)) {
            return null;
        } else {
            try {
                bigDecimal2 = new BigDecimal(zzd.zzbwA);
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        return zza(bigDecimal, intValue, bigDecimal2, bigDecimal4, bigDecimal3, d);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public void zza(String str, zza[] zzaArr) {
        zzb[] zzbArr;
        zze[] zzeArr;
        zzc[] zzcArr;
        zzac.zzw(zzaArr);
        for (zza zza : zzaArr) {
            for (zzb zzb : zza.zzbwm) {
                String str2 = (String) AppMeasurement.zza.zzbqd.get(zzb.zzbwp);
                if (str2 != null) {
                    zzb.zzbwp = str2;
                }
                for (zzc zzc : zzb.zzbwq) {
                    String str3 = (String) AppMeasurement.zze.zzbqe.get(zzc.zzbwx);
                    if (str3 != null) {
                        zzc.zzbwx = str3;
                    }
                }
            }
            for (zze zze : zza.zzbwl) {
                String str4 = (String) AppMeasurement.zzg.zzbqi.get(zze.zzbwE);
                if (str4 != null) {
                    zze.zzbwE = str4;
                }
            }
        }
        zzKg().zzb(str, zzaArr);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public zzauw.zza[] zza(String str, zzauw.zzb[] zzbArr, zzg[] zzgArr) {
        Map map;
        zze zze;
        zzatn zzLV;
        Map map2;
        zzac.zzdr(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zzfy = zzKg().zzfy(str);
        if (zzfy != null) {
            for (Integer intValue : zzfy.keySet()) {
                int intValue2 = intValue.intValue();
                zzauw.zzf zzf = (zzauw.zzf) zzfy.get(Integer.valueOf(intValue2));
                BitSet bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue2));
                BitSet bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue2));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue2), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue2), bitSet2);
                }
                for (int i = 0; i < zzf.zzbxG.length * 64; i++) {
                    if (zzaut.zza(zzf.zzbxG, i)) {
                        zzKl().zzMe().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue2), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzaut.zza(zzf.zzbxH, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzauw.zza zza = new zzauw.zza();
                arrayMap.put(Integer.valueOf(intValue2), zza);
                zza.zzbwW = Boolean.valueOf(false);
                zza.zzbwV = zzf;
                zza.zzbwU = new zzauw.zzf();
                zza.zzbwU.zzbxH = zzaut.zza(bitSet);
                zza.zzbwU.zzbxG = zzaut.zza(bitSet2);
            }
        }
        if (zzbArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            int length = zzbArr.length;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= length) {
                    break;
                }
                zzauw.zzb zzb = zzbArr[i3];
                zzatn zzQ = zzKg().zzQ(str, zzb.name);
                if (zzQ == null) {
                    zzKl().zzMa().zze("Event aggregate wasn't created during raw event logging. appId, event", zzatx.zzfE(str), zzb.name);
                    zzLV = new zzatn(str, zzb.name, 1, 1, zzb.zzbwZ.longValue());
                } else {
                    zzLV = zzQ.zzLV();
                }
                zzKg().zza(zzLV);
                long j = zzLV.zzbrA;
                Map map3 = (Map) arrayMap4.get(zzb.name);
                if (map3 == null) {
                    Map zzV = zzKg().zzV(str, zzb.name);
                    if (zzV == null) {
                        zzV = new ArrayMap();
                    }
                    arrayMap4.put(zzb.name, zzV);
                    map2 = zzV;
                } else {
                    map2 = map3;
                }
                for (Integer intValue3 : map2.keySet()) {
                    int intValue4 = intValue3.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzKl().zzMe().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        BitSet bitSet3 = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        BitSet bitSet4 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzauw.zza) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            zzauw.zza zza2 = new zzauw.zza();
                            arrayMap.put(Integer.valueOf(intValue4), zza2);
                            zza2.zzbwW = Boolean.valueOf(true);
                            bitSet3 = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet3);
                            bitSet4 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet4);
                        }
                        for (zzb zzb2 : (List) map2.get(Integer.valueOf(intValue4))) {
                            if (zzKl().zzak(2)) {
                                zzKl().zzMe().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue4), zzb2.zzbwo, zzb2.zzbwp);
                                zzKl().zzMe().zzj("Filter definition", zzaut.zza(zzb2));
                            }
                            if (zzb2.zzbwo == null || zzb2.zzbwo.intValue() > 256) {
                                zzKl().zzMa().zze("Invalid event filter ID. appId, id", zzatx.zzfE(str), String.valueOf(zzb2.zzbwo));
                            } else if (bitSet3.get(zzb2.zzbwo.intValue())) {
                                zzKl().zzMe().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), zzb2.zzbwo);
                            } else {
                                Boolean zza3 = zza(zzb2, zzb, j);
                                zzKl().zzMe().zzj("Event filter result", zza3 == 0 ? "null" : zza3);
                                if (zza3 == 0) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet4.set(zzb2.zzbwo.intValue());
                                    if (zza3.booleanValue()) {
                                        bitSet3.set(zzb2.zzbwo.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = i3 + 1;
            }
        }
        if (zzgArr != null) {
            ArrayMap arrayMap5 = new ArrayMap();
            for (zzg zzg : zzgArr) {
                Map map4 = (Map) arrayMap5.get(zzg.name);
                if (map4 == null) {
                    Map zzW = zzKg().zzW(str, zzg.name);
                    if (zzW == null) {
                        zzW = new ArrayMap();
                    }
                    arrayMap5.put(zzg.name, zzW);
                    map = zzW;
                } else {
                    map = map4;
                }
                for (Integer intValue5 : map.keySet()) {
                    int intValue6 = intValue5.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue6))) {
                        zzKl().zzMe().zzj("Skipping failed audience ID", Integer.valueOf(intValue6));
                    } else {
                        BitSet bitSet5 = (BitSet) arrayMap2.get(Integer.valueOf(intValue6));
                        BitSet bitSet6 = (BitSet) arrayMap3.get(Integer.valueOf(intValue6));
                        if (((zzauw.zza) arrayMap.get(Integer.valueOf(intValue6))) == null) {
                            zzauw.zza zza4 = new zzauw.zza();
                            arrayMap.put(Integer.valueOf(intValue6), zza4);
                            zza4.zzbwW = Boolean.valueOf(true);
                            bitSet5 = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue6), bitSet5);
                            bitSet6 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue6), bitSet6);
                        }
                        Iterator it = ((List) map.get(Integer.valueOf(intValue6))).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            zze = (zze) it.next();
                            if (zzKl().zzak(2)) {
                                zzKl().zzMe().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue6), zze.zzbwo, zze.zzbwE);
                                zzKl().zzMe().zzj("Filter definition", zzaut.zza(zze));
                            }
                            if (zze.zzbwo == null || zze.zzbwo.intValue() > 256) {
                                zzKl().zzMa().zze("Invalid property filter ID. appId, id", zzatx.zzfE(str), String.valueOf(zze.zzbwo));
                                hashSet.add(Integer.valueOf(intValue6));
                            } else if (bitSet5.get(zze.zzbwo.intValue())) {
                                zzKl().zzMe().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue6), zze.zzbwo);
                            } else {
                                Boolean zza5 = zza(zze, zzg);
                                zzKl().zzMe().zzj("Property filter result", zza5 == 0 ? "null" : zza5);
                                if (zza5 == 0) {
                                    hashSet.add(Integer.valueOf(intValue6));
                                } else {
                                    bitSet6.set(zze.zzbwo.intValue());
                                    if (zza5.booleanValue()) {
                                        bitSet5.set(zze.zzbwo.intValue());
                                    }
                                }
                            }
                        }
                        zzKl().zzMa().zze("Invalid property filter ID. appId, id", zzatx.zzfE(str), String.valueOf(zze.zzbwo));
                        hashSet.add(Integer.valueOf(intValue6));
                    }
                }
            }
        }
        zzauw.zza[] zzaArr = new zzauw.zza[arrayMap2.size()];
        int i4 = 0;
        for (Integer intValue7 : arrayMap2.keySet()) {
            int intValue8 = intValue7.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue8))) {
                zzauw.zza zza6 = (zzauw.zza) arrayMap.get(Integer.valueOf(intValue8));
                if (zza6 == null) {
                    zza6 = new zzauw.zza();
                }
                zzauw.zza zza7 = zza6;
                int i5 = i4 + 1;
                zzaArr[i4] = zza7;
                zza7.zzbwk = Integer.valueOf(intValue8);
                zza7.zzbwU = new zzauw.zzf();
                zza7.zzbwU.zzbxH = zzaut.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue8)));
                zza7.zzbwU.zzbxG = zzaut.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue8)));
                zzKg().zza(str, intValue8, zza7.zzbwU);
                i4 = i5;
            }
        }
        return (zzauw.zza[]) Arrays.copyOf(zzaArr, i4);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }
}
