package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzbxv {
    final int tag;
    final byte[] zzbxZ;

    zzbxv(int i, byte[] bArr) {
        this.tag = i;
        this.zzbxZ = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxv)) {
            return false;
        }
        zzbxv zzbxv = (zzbxv) obj;
        return this.tag == zzbxv.tag && Arrays.equals(this.zzbxZ, zzbxv.zzbxZ);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbxZ);
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzbxm zzbxm) throws IOException {
        zzbxm.zzrk(this.tag);
        zzbxm.zzaj(this.zzbxZ);
    }

    /* access modifiers changed from: 0000 */
    public int zzu() {
        return zzbxm.zzrl(this.tag) + 0 + this.zzbxZ.length;
    }
}
