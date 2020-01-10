package com.google.android.gms.internal;

import java.io.IOException;

public interface zzauv {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzbwK;
        public String name;
        public Boolean zzbwL;
        public Boolean zzbwM;

        public zza() {
            zzNu();
        }

        public static zza[] zzNt() {
            if (zzbwK == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwK == null) {
                        zzbwK = new zza[0];
                    }
                }
            }
            return zzbwK;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.zzbwL == null) {
                if (zza.zzbwL != null) {
                    return false;
                }
            } else if (!this.zzbwL.equals(zza.zzbwL)) {
                return false;
            }
            if (this.zzbwM == null) {
                if (zza.zzbwM != null) {
                    return false;
                }
            } else if (!this.zzbwM.equals(zza.zzbwM)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbwM == null ? 0 : this.zzbwM.hashCode()) + (((this.zzbwL == null ? 0 : this.zzbwL.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzM */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbxl.readString();
                        continue;
                    case 16:
                        this.zzbwL = Boolean.valueOf(zzbxl.zzaeu());
                        continue;
                    case 24:
                        this.zzbwM = Boolean.valueOf(zzbxl.zzaeu());
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zza zzNu() {
            this.name = null;
            this.zzbwL = null;
            this.zzbwM = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.name != null) {
                zzbxm.zzq(1, this.name);
            }
            if (this.zzbwL != null) {
                zzbxm.zzg(2, this.zzbwL.booleanValue());
            }
            if (this.zzbwM != null) {
                zzbxm.zzg(3, this.zzbwM.booleanValue());
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.name != null) {
                zzu += zzbxm.zzr(1, this.name);
            }
            if (this.zzbwL != null) {
                zzu += zzbxm.zzh(2, this.zzbwL.booleanValue());
            }
            return this.zzbwM != null ? zzu + zzbxm.zzh(3, this.zzbwM.booleanValue()) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public String zzbqL;
        public Long zzbwN;
        public Integer zzbwO;
        public zzc[] zzbwP;
        public zza[] zzbwQ;
        public com.google.android.gms.internal.zzauu.zza[] zzbwR;

        public zzb() {
            zzNv();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbwN == null) {
                if (zzb.zzbwN != null) {
                    return false;
                }
            } else if (!this.zzbwN.equals(zzb.zzbwN)) {
                return false;
            }
            if (this.zzbqL == null) {
                if (zzb.zzbqL != null) {
                    return false;
                }
            } else if (!this.zzbqL.equals(zzb.zzbqL)) {
                return false;
            }
            if (this.zzbwO == null) {
                if (zzb.zzbwO != null) {
                    return false;
                }
            } else if (!this.zzbwO.equals(zzb.zzbwO)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzbwP, (Object[]) zzb.zzbwP) || !zzbxr.equals((Object[]) this.zzbwQ, (Object[]) zzb.zzbwQ) || !zzbxr.equals((Object[]) this.zzbwR, (Object[]) zzb.zzbwR)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzb.zzcuI == null || zzb.zzcuI.isEmpty() : this.zzcuI.equals(zzb.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((this.zzbwO == null ? 0 : this.zzbwO.hashCode()) + (((this.zzbqL == null ? 0 : this.zzbqL.hashCode()) + (((this.zzbwN == null ? 0 : this.zzbwN.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzbxr.hashCode((Object[]) this.zzbwP)) * 31) + zzbxr.hashCode((Object[]) this.zzbwQ)) * 31) + zzbxr.hashCode((Object[]) this.zzbwR)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzN */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzbwN = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 18:
                        this.zzbqL = zzbxl.readString();
                        continue;
                    case 24:
                        this.zzbwO = Integer.valueOf(zzbxl.zzaes());
                        continue;
                    case 34:
                        int zzb = zzbxw.zzb(zzbxl, 34);
                        int length = this.zzbwP == null ? 0 : this.zzbwP.length;
                        zzc[] zzcArr = new zzc[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwP, 0, zzcArr, 0, length);
                        }
                        while (length < zzcArr.length - 1) {
                            zzcArr[length] = new zzc();
                            zzbxl.zza(zzcArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzcArr[length] = new zzc();
                        zzbxl.zza(zzcArr[length]);
                        this.zzbwP = zzcArr;
                        continue;
                    case 42:
                        int zzb2 = zzbxw.zzb(zzbxl, 42);
                        int length2 = this.zzbwQ == null ? 0 : this.zzbwQ.length;
                        zza[] zzaArr = new zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbwQ, 0, zzaArr, 0, length2);
                        }
                        while (length2 < zzaArr.length - 1) {
                            zzaArr[length2] = new zza();
                            zzbxl.zza(zzaArr[length2]);
                            zzbxl.zzaeo();
                            length2++;
                        }
                        zzaArr[length2] = new zza();
                        zzbxl.zza(zzaArr[length2]);
                        this.zzbwQ = zzaArr;
                        continue;
                    case 50:
                        int zzb3 = zzbxw.zzb(zzbxl, 50);
                        int length3 = this.zzbwR == null ? 0 : this.zzbwR.length;
                        com.google.android.gms.internal.zzauu.zza[] zzaArr2 = new com.google.android.gms.internal.zzauu.zza[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbwR, 0, zzaArr2, 0, length3);
                        }
                        while (length3 < zzaArr2.length - 1) {
                            zzaArr2[length3] = new com.google.android.gms.internal.zzauu.zza();
                            zzbxl.zza(zzaArr2[length3]);
                            zzbxl.zzaeo();
                            length3++;
                        }
                        zzaArr2[length3] = new com.google.android.gms.internal.zzauu.zza();
                        zzbxl.zza(zzaArr2[length3]);
                        this.zzbwR = zzaArr2;
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzb zzNv() {
            this.zzbwN = null;
            this.zzbqL = null;
            this.zzbwO = null;
            this.zzbwP = zzc.zzNw();
            this.zzbwQ = zza.zzNt();
            this.zzbwR = com.google.android.gms.internal.zzauu.zza.zzNj();
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbwN != null) {
                zzbxm.zzb(1, this.zzbwN.longValue());
            }
            if (this.zzbqL != null) {
                zzbxm.zzq(2, this.zzbqL);
            }
            if (this.zzbwO != null) {
                zzbxm.zzJ(3, this.zzbwO.intValue());
            }
            if (this.zzbwP != null && this.zzbwP.length > 0) {
                for (zzc zzc : this.zzbwP) {
                    if (zzc != null) {
                        zzbxm.zza(4, (zzbxt) zzc);
                    }
                }
            }
            if (this.zzbwQ != null && this.zzbwQ.length > 0) {
                for (zza zza : this.zzbwQ) {
                    if (zza != null) {
                        zzbxm.zza(5, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbwR != null && this.zzbwR.length > 0) {
                for (com.google.android.gms.internal.zzauu.zza zza2 : this.zzbwR) {
                    if (zza2 != null) {
                        zzbxm.zza(6, (zzbxt) zza2);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwN != null) {
                zzu += zzbxm.zzf(1, this.zzbwN.longValue());
            }
            if (this.zzbqL != null) {
                zzu += zzbxm.zzr(2, this.zzbqL);
            }
            if (this.zzbwO != null) {
                zzu += zzbxm.zzL(3, this.zzbwO.intValue());
            }
            if (this.zzbwP != null && this.zzbwP.length > 0) {
                int i = zzu;
                for (zzc zzc : this.zzbwP) {
                    if (zzc != null) {
                        i += zzbxm.zzc(4, (zzbxt) zzc);
                    }
                }
                zzu = i;
            }
            if (this.zzbwQ != null && this.zzbwQ.length > 0) {
                int i2 = zzu;
                for (zza zza : this.zzbwQ) {
                    if (zza != null) {
                        i2 += zzbxm.zzc(5, (zzbxt) zza);
                    }
                }
                zzu = i2;
            }
            if (this.zzbwR != null && this.zzbwR.length > 0) {
                for (com.google.android.gms.internal.zzauu.zza zza2 : this.zzbwR) {
                    if (zza2 != null) {
                        zzu += zzbxm.zzc(6, (zzbxt) zza2);
                    }
                }
            }
            return zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        private static volatile zzc[] zzbwS;
        public String value;
        public String zzaB;

        public zzc() {
            zzNx();
        }

        public static zzc[] zzNw() {
            if (zzbwS == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwS == null) {
                        zzbwS = new zzc[0];
                    }
                }
            }
            return zzbwS;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzaB == null) {
                if (zzc.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zzc.zzaB)) {
                return false;
            }
            if (this.value == null) {
                if (zzc.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zzc.value)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzc.zzcuI == null || zzc.zzcuI.isEmpty() : this.zzcuI.equals(zzc.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzNx() {
            this.zzaB = null;
            this.value = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzO */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.zzaB = zzbxl.readString();
                        continue;
                    case 18:
                        this.value = zzbxl.readString();
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaeo)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaB != null) {
                zzbxm.zzq(1, this.zzaB);
            }
            if (this.value != null) {
                zzbxm.zzq(2, this.value);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaB != null) {
                zzu += zzbxm.zzr(1, this.zzaB);
            }
            return this.value != null ? zzu + zzbxm.zzr(2, this.value) : zzu;
        }
    }
}
