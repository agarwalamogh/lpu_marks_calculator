package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbxn;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzbxo<M extends zzbxn<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzckM;
    protected final boolean zzcuJ;

    private zzbxo(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.zzckM = cls;
        this.tag = i2;
        this.zzcuJ = z;
    }

    public static <M extends zzbxn<M>, T extends zzbxt> zzbxo<M, T> zza(int i, Class<T> cls, long j) {
        return new zzbxo<>(i, cls, (int) j, false);
    }

    private T zzad(List<zzbxv> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzbxv zzbxv = (zzbxv) list.get(i);
            if (zzbxv.zzbxZ.length != 0) {
                zza(zzbxv, (List<Object>) arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T cast = this.zzckM.cast(Array.newInstance(this.zzckM.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(cast, i2, arrayList.get(i2));
        }
        return cast;
    }

    private T zzae(List<zzbxv> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzckM.cast(zzaN(zzbxl.zzaf(((zzbxv) list.get(list.size() - 1)).zzbxZ)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbxo)) {
            return false;
        }
        zzbxo zzbxo = (zzbxo) obj;
        return this.type == zzbxo.type && this.zzckM == zzbxo.zzckM && this.tag == zzbxo.tag && this.zzcuJ == zzbxo.zzcuJ;
    }

    public int hashCode() {
        return (this.zzcuJ ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzckM.hashCode()) * 31) + this.tag) * 31);
    }

    /* access modifiers changed from: protected */
    public void zza(zzbxv zzbxv, List<Object> list) {
        list.add(zzaN(zzbxl.zzaf(zzbxv.zzbxZ)));
    }

    /* access modifiers changed from: 0000 */
    public void zza(Object obj, zzbxm zzbxm) throws IOException {
        if (this.zzcuJ) {
            zzc(obj, zzbxm);
        } else {
            zzb(obj, zzbxm);
        }
    }

    /* access modifiers changed from: protected */
    public Object zzaN(zzbxl zzbxl) {
        Class<T> cls = this.zzcuJ ? this.zzckM.getComponentType() : this.zzckM;
        try {
            switch (this.type) {
                case 10:
                    zzbxt zzbxt = (zzbxt) cls.newInstance();
                    zzbxl.zza(zzbxt, zzbxw.zzrs(this.tag));
                    return zzbxt;
                case 11:
                    zzbxt zzbxt2 = (zzbxt) cls.newInstance();
                    zzbxl.zza(zzbxt2);
                    return zzbxt2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(cls);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(cls);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + 33).append("Error creating instance of class ").append(valueOf2).toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzaU(Object obj) {
        return this.zzcuJ ? zzaV(obj) : zzaW(obj);
    }

    /* access modifiers changed from: protected */
    public int zzaV(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzaW(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int zzaW(Object obj) {
        int zzrs = zzbxw.zzrs(this.tag);
        switch (this.type) {
            case 10:
                return zzbxm.zzb(zzrs, (zzbxt) obj);
            case 11:
                return zzbxm.zzc(zzrs, (zzbxt) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    /* access modifiers changed from: 0000 */
    public final T zzac(List<zzbxv> list) {
        if (list == null) {
            return null;
        }
        return this.zzcuJ ? zzad(list) : zzae(list);
    }

    /* access modifiers changed from: protected */
    public void zzb(Object obj, zzbxm zzbxm) {
        try {
            zzbxm.zzrk(this.tag);
            switch (this.type) {
                case 10:
                    zzbxt zzbxt = (zzbxt) obj;
                    int zzrs = zzbxw.zzrs(this.tag);
                    zzbxm.zzb(zzbxt);
                    zzbxm.zzN(zzrs, 4);
                    return;
                case 11:
                    zzbxm.zzc((zzbxt) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* access modifiers changed from: protected */
    public void zzc(Object obj, zzbxm zzbxm) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzbxm);
            }
        }
    }
}
