package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;

public interface zzauw {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzbwT;
        public zzf zzbwU;
        public zzf zzbwV;
        public Boolean zzbwW;
        public Integer zzbwk;

        public zza() {
            zzNz();
        }

        public static zza[] zzNy() {
            if (zzbwT == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwT == null) {
                        zzbwT = new zza[0];
                    }
                }
            }
            return zzbwT;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzbwk == null) {
                if (zza.zzbwk != null) {
                    return false;
                }
            } else if (!this.zzbwk.equals(zza.zzbwk)) {
                return false;
            }
            if (this.zzbwU == null) {
                if (zza.zzbwU != null) {
                    return false;
                }
            } else if (!this.zzbwU.equals(zza.zzbwU)) {
                return false;
            }
            if (this.zzbwV == null) {
                if (zza.zzbwV != null) {
                    return false;
                }
            } else if (!this.zzbwV.equals(zza.zzbwV)) {
                return false;
            }
            if (this.zzbwW == null) {
                if (zza.zzbwW != null) {
                    return false;
                }
            } else if (!this.zzbwW.equals(zza.zzbwW)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zza.zzcuI == null || zza.zzcuI.isEmpty() : this.zzcuI.equals(zza.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbwW == null ? 0 : this.zzbwW.hashCode()) + (((this.zzbwV == null ? 0 : this.zzbwV.hashCode()) + (((this.zzbwU == null ? 0 : this.zzbwU.hashCode()) + (((this.zzbwk == null ? 0 : this.zzbwk.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zza zzNz() {
            this.zzbwk = null;
            this.zzbwU = null;
            this.zzbwV = null;
            this.zzbwW = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzP */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzbwk = Integer.valueOf(zzbxl.zzaes());
                        continue;
                    case 18:
                        if (this.zzbwU == null) {
                            this.zzbwU = new zzf();
                        }
                        zzbxl.zza(this.zzbwU);
                        continue;
                    case 26:
                        if (this.zzbwV == null) {
                            this.zzbwV = new zzf();
                        }
                        zzbxl.zza(this.zzbwV);
                        continue;
                    case 32:
                        this.zzbwW = Boolean.valueOf(zzbxl.zzaeu());
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
            if (this.zzbwk != null) {
                zzbxm.zzJ(1, this.zzbwk.intValue());
            }
            if (this.zzbwU != null) {
                zzbxm.zza(2, (zzbxt) this.zzbwU);
            }
            if (this.zzbwV != null) {
                zzbxm.zza(3, (zzbxt) this.zzbwV);
            }
            if (this.zzbwW != null) {
                zzbxm.zzg(4, this.zzbwW.booleanValue());
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwk != null) {
                zzu += zzbxm.zzL(1, this.zzbwk.intValue());
            }
            if (this.zzbwU != null) {
                zzu += zzbxm.zzc(2, (zzbxt) this.zzbwU);
            }
            if (this.zzbwV != null) {
                zzu += zzbxm.zzc(3, (zzbxt) this.zzbwV);
            }
            return this.zzbwW != null ? zzu + zzbxm.zzh(4, this.zzbwW.booleanValue()) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        private static volatile zzb[] zzbwX;
        public Integer count;
        public String name;
        public zzc[] zzbwY;
        public Long zzbwZ;
        public Long zzbxa;

        public zzb() {
            zzNB();
        }

        public static zzb[] zzNA() {
            if (zzbwX == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbwX == null) {
                        zzbwX = new zzb[0];
                    }
                }
            }
            return zzbwX;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (!zzbxr.equals((Object[]) this.zzbwY, (Object[]) zzb.zzbwY)) {
                return false;
            }
            if (this.name == null) {
                if (zzb.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzb.name)) {
                return false;
            }
            if (this.zzbwZ == null) {
                if (zzb.zzbwZ != null) {
                    return false;
                }
            } else if (!this.zzbwZ.equals(zzb.zzbwZ)) {
                return false;
            }
            if (this.zzbxa == null) {
                if (zzb.zzbxa != null) {
                    return false;
                }
            } else if (!this.zzbxa.equals(zzb.zzbxa)) {
                return false;
            }
            if (this.count == null) {
                if (zzb.count != null) {
                    return false;
                }
            } else if (!this.count.equals(zzb.count)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzb.zzcuI == null || zzb.zzcuI.isEmpty() : this.zzcuI.equals(zzb.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zzbxa == null ? 0 : this.zzbxa.hashCode()) + (((this.zzbwZ == null ? 0 : this.zzbwZ.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzbxr.hashCode((Object[]) this.zzbwY)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzb zzNB() {
            this.zzbwY = zzc.zzNC();
            this.name = null;
            this.zzbwZ = null;
            this.zzbxa = null;
            this.count = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzQ */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbxw.zzb(zzbxl, 10);
                        int length = this.zzbwY == null ? 0 : this.zzbwY.length;
                        zzc[] zzcArr = new zzc[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwY, 0, zzcArr, 0, length);
                        }
                        while (length < zzcArr.length - 1) {
                            zzcArr[length] = new zzc();
                            zzbxl.zza(zzcArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzcArr[length] = new zzc();
                        zzbxl.zza(zzcArr[length]);
                        this.zzbwY = zzcArr;
                        continue;
                    case 18:
                        this.name = zzbxl.readString();
                        continue;
                    case 24:
                        this.zzbwZ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 32:
                        this.zzbxa = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 40:
                        this.count = Integer.valueOf(zzbxl.zzaes());
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
            if (this.zzbwY != null && this.zzbwY.length > 0) {
                for (zzc zzc : this.zzbwY) {
                    if (zzc != null) {
                        zzbxm.zza(1, (zzbxt) zzc);
                    }
                }
            }
            if (this.name != null) {
                zzbxm.zzq(2, this.name);
            }
            if (this.zzbwZ != null) {
                zzbxm.zzb(3, this.zzbwZ.longValue());
            }
            if (this.zzbxa != null) {
                zzbxm.zzb(4, this.zzbxa.longValue());
            }
            if (this.count != null) {
                zzbxm.zzJ(5, this.count.intValue());
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbwY != null && this.zzbwY.length > 0) {
                for (zzc zzc : this.zzbwY) {
                    if (zzc != null) {
                        zzu += zzbxm.zzc(1, (zzbxt) zzc);
                    }
                }
            }
            if (this.name != null) {
                zzu += zzbxm.zzr(2, this.name);
            }
            if (this.zzbwZ != null) {
                zzu += zzbxm.zzf(3, this.zzbwZ.longValue());
            }
            if (this.zzbxa != null) {
                zzu += zzbxm.zzf(4, this.zzbxa.longValue());
            }
            return this.count != null ? zzu + zzbxm.zzL(5, this.count.intValue()) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        private static volatile zzc[] zzbxb;
        public String name;
        public String zzaGV;
        public Float zzbwe;
        public Double zzbwf;
        public Long zzbxc;

        public zzc() {
            zzND();
        }

        public static zzc[] zzNC() {
            if (zzbxb == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbxb == null) {
                        zzbxb = new zzc[0];
                    }
                }
            }
            return zzbxb;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.name == null) {
                if (zzc.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzc.name)) {
                return false;
            }
            if (this.zzaGV == null) {
                if (zzc.zzaGV != null) {
                    return false;
                }
            } else if (!this.zzaGV.equals(zzc.zzaGV)) {
                return false;
            }
            if (this.zzbxc == null) {
                if (zzc.zzbxc != null) {
                    return false;
                }
            } else if (!this.zzbxc.equals(zzc.zzbxc)) {
                return false;
            }
            if (this.zzbwe == null) {
                if (zzc.zzbwe != null) {
                    return false;
                }
            } else if (!this.zzbwe.equals(zzc.zzbwe)) {
                return false;
            }
            if (this.zzbwf == null) {
                if (zzc.zzbwf != null) {
                    return false;
                }
            } else if (!this.zzbwf.equals(zzc.zzbwf)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzc.zzcuI == null || zzc.zzcuI.isEmpty() : this.zzcuI.equals(zzc.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbwf == null ? 0 : this.zzbwf.hashCode()) + (((this.zzbwe == null ? 0 : this.zzbwe.hashCode()) + (((this.zzbxc == null ? 0 : this.zzbxc.hashCode()) + (((this.zzaGV == null ? 0 : this.zzaGV.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzND() {
            this.name = null;
            this.zzaGV = null;
            this.zzbxc = null;
            this.zzbwe = null;
            this.zzbwf = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzR */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzaGV = zzbxl.readString();
                        continue;
                    case 24:
                        this.zzbxc = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 37:
                        this.zzbwe = Float.valueOf(zzbxl.readFloat());
                        continue;
                    case 41:
                        this.zzbwf = Double.valueOf(zzbxl.readDouble());
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
            if (this.name != null) {
                zzbxm.zzq(1, this.name);
            }
            if (this.zzaGV != null) {
                zzbxm.zzq(2, this.zzaGV);
            }
            if (this.zzbxc != null) {
                zzbxm.zzb(3, this.zzbxc.longValue());
            }
            if (this.zzbwe != null) {
                zzbxm.zzc(4, this.zzbwe.floatValue());
            }
            if (this.zzbwf != null) {
                zzbxm.zza(5, this.zzbwf.doubleValue());
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.name != null) {
                zzu += zzbxm.zzr(1, this.name);
            }
            if (this.zzaGV != null) {
                zzu += zzbxm.zzr(2, this.zzaGV);
            }
            if (this.zzbxc != null) {
                zzu += zzbxm.zzf(3, this.zzbxc.longValue());
            }
            if (this.zzbwe != null) {
                zzu += zzbxm.zzd(4, this.zzbwe.floatValue());
            }
            return this.zzbwf != null ? zzu + zzbxm.zzb(5, this.zzbwf.doubleValue()) : zzu;
        }
    }

    public static final class zzd extends zzbxn<zzd> {
        public zze[] zzbxd;

        public zzd() {
            zzNE();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (zzbxr.equals((Object[]) this.zzbxd, (Object[]) zzd.zzbxd)) {
                return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzd.zzcuI == null || zzd.zzcuI.isEmpty() : this.zzcuI.equals(zzd.zzcuI);
            }
            return false;
        }

        public int hashCode() {
            return ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzbxr.hashCode((Object[]) this.zzbxd)) * 31);
        }

        public zzd zzNE() {
            this.zzbxd = zze.zzNF();
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzS */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbxw.zzb(zzbxl, 10);
                        int length = this.zzbxd == null ? 0 : this.zzbxd.length;
                        zze[] zzeArr = new zze[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbxd, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbxl.zza(zzeArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbxl.zza(zzeArr[length]);
                        this.zzbxd = zzeArr;
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
            if (this.zzbxd != null && this.zzbxd.length > 0) {
                for (zze zze : this.zzbxd) {
                    if (zze != null) {
                        zzbxm.zza(1, (zzbxt) zze);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbxd != null && this.zzbxd.length > 0) {
                for (zze zze : this.zzbxd) {
                    if (zze != null) {
                        zzu += zzbxm.zzc(1, (zzbxt) zze);
                    }
                }
            }
            return zzu;
        }
    }

    public static final class zze extends zzbxn<zze> {
        private static volatile zze[] zzbxe;
        public String zzaS;
        public String zzbb;
        public String zzbhN;
        public String zzbqL;
        public String zzbqM;
        public String zzbqP;
        public String zzbqT;
        public Integer zzbxA;
        public Integer zzbxB;
        public Integer zzbxC;
        public String zzbxD;
        public Long zzbxE;
        public Long zzbxF;
        public Integer zzbxf;
        public zzb[] zzbxg;
        public zzg[] zzbxh;
        public Long zzbxi;
        public Long zzbxj;
        public Long zzbxk;
        public Long zzbxl;
        public Long zzbxm;
        public String zzbxn;
        public String zzbxo;
        public String zzbxp;
        public Integer zzbxq;
        public Long zzbxr;
        public Long zzbxs;
        public String zzbxt;
        public Boolean zzbxu;
        public String zzbxv;
        public Long zzbxw;
        public Integer zzbxx;
        public Boolean zzbxy;
        public zza[] zzbxz;

        public zze() {
            zzNG();
        }

        public static zze[] zzNF() {
            if (zzbxe == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbxe == null) {
                        zzbxe = new zze[0];
                    }
                }
            }
            return zzbxe;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzbxf == null) {
                if (zze.zzbxf != null) {
                    return false;
                }
            } else if (!this.zzbxf.equals(zze.zzbxf)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzbxg, (Object[]) zze.zzbxg) || !zzbxr.equals((Object[]) this.zzbxh, (Object[]) zze.zzbxh)) {
                return false;
            }
            if (this.zzbxi == null) {
                if (zze.zzbxi != null) {
                    return false;
                }
            } else if (!this.zzbxi.equals(zze.zzbxi)) {
                return false;
            }
            if (this.zzbxj == null) {
                if (zze.zzbxj != null) {
                    return false;
                }
            } else if (!this.zzbxj.equals(zze.zzbxj)) {
                return false;
            }
            if (this.zzbxk == null) {
                if (zze.zzbxk != null) {
                    return false;
                }
            } else if (!this.zzbxk.equals(zze.zzbxk)) {
                return false;
            }
            if (this.zzbxl == null) {
                if (zze.zzbxl != null) {
                    return false;
                }
            } else if (!this.zzbxl.equals(zze.zzbxl)) {
                return false;
            }
            if (this.zzbxm == null) {
                if (zze.zzbxm != null) {
                    return false;
                }
            } else if (!this.zzbxm.equals(zze.zzbxm)) {
                return false;
            }
            if (this.zzbxn == null) {
                if (zze.zzbxn != null) {
                    return false;
                }
            } else if (!this.zzbxn.equals(zze.zzbxn)) {
                return false;
            }
            if (this.zzbb == null) {
                if (zze.zzbb != null) {
                    return false;
                }
            } else if (!this.zzbb.equals(zze.zzbb)) {
                return false;
            }
            if (this.zzbxo == null) {
                if (zze.zzbxo != null) {
                    return false;
                }
            } else if (!this.zzbxo.equals(zze.zzbxo)) {
                return false;
            }
            if (this.zzbxp == null) {
                if (zze.zzbxp != null) {
                    return false;
                }
            } else if (!this.zzbxp.equals(zze.zzbxp)) {
                return false;
            }
            if (this.zzbxq == null) {
                if (zze.zzbxq != null) {
                    return false;
                }
            } else if (!this.zzbxq.equals(zze.zzbxq)) {
                return false;
            }
            if (this.zzbqM == null) {
                if (zze.zzbqM != null) {
                    return false;
                }
            } else if (!this.zzbqM.equals(zze.zzbqM)) {
                return false;
            }
            if (this.zzaS == null) {
                if (zze.zzaS != null) {
                    return false;
                }
            } else if (!this.zzaS.equals(zze.zzaS)) {
                return false;
            }
            if (this.zzbhN == null) {
                if (zze.zzbhN != null) {
                    return false;
                }
            } else if (!this.zzbhN.equals(zze.zzbhN)) {
                return false;
            }
            if (this.zzbxr == null) {
                if (zze.zzbxr != null) {
                    return false;
                }
            } else if (!this.zzbxr.equals(zze.zzbxr)) {
                return false;
            }
            if (this.zzbxs == null) {
                if (zze.zzbxs != null) {
                    return false;
                }
            } else if (!this.zzbxs.equals(zze.zzbxs)) {
                return false;
            }
            if (this.zzbxt == null) {
                if (zze.zzbxt != null) {
                    return false;
                }
            } else if (!this.zzbxt.equals(zze.zzbxt)) {
                return false;
            }
            if (this.zzbxu == null) {
                if (zze.zzbxu != null) {
                    return false;
                }
            } else if (!this.zzbxu.equals(zze.zzbxu)) {
                return false;
            }
            if (this.zzbxv == null) {
                if (zze.zzbxv != null) {
                    return false;
                }
            } else if (!this.zzbxv.equals(zze.zzbxv)) {
                return false;
            }
            if (this.zzbxw == null) {
                if (zze.zzbxw != null) {
                    return false;
                }
            } else if (!this.zzbxw.equals(zze.zzbxw)) {
                return false;
            }
            if (this.zzbxx == null) {
                if (zze.zzbxx != null) {
                    return false;
                }
            } else if (!this.zzbxx.equals(zze.zzbxx)) {
                return false;
            }
            if (this.zzbqP == null) {
                if (zze.zzbqP != null) {
                    return false;
                }
            } else if (!this.zzbqP.equals(zze.zzbqP)) {
                return false;
            }
            if (this.zzbqL == null) {
                if (zze.zzbqL != null) {
                    return false;
                }
            } else if (!this.zzbqL.equals(zze.zzbqL)) {
                return false;
            }
            if (this.zzbxy == null) {
                if (zze.zzbxy != null) {
                    return false;
                }
            } else if (!this.zzbxy.equals(zze.zzbxy)) {
                return false;
            }
            if (!zzbxr.equals((Object[]) this.zzbxz, (Object[]) zze.zzbxz)) {
                return false;
            }
            if (this.zzbqT == null) {
                if (zze.zzbqT != null) {
                    return false;
                }
            } else if (!this.zzbqT.equals(zze.zzbqT)) {
                return false;
            }
            if (this.zzbxA == null) {
                if (zze.zzbxA != null) {
                    return false;
                }
            } else if (!this.zzbxA.equals(zze.zzbxA)) {
                return false;
            }
            if (this.zzbxB == null) {
                if (zze.zzbxB != null) {
                    return false;
                }
            } else if (!this.zzbxB.equals(zze.zzbxB)) {
                return false;
            }
            if (this.zzbxC == null) {
                if (zze.zzbxC != null) {
                    return false;
                }
            } else if (!this.zzbxC.equals(zze.zzbxC)) {
                return false;
            }
            if (this.zzbxD == null) {
                if (zze.zzbxD != null) {
                    return false;
                }
            } else if (!this.zzbxD.equals(zze.zzbxD)) {
                return false;
            }
            if (this.zzbxE == null) {
                if (zze.zzbxE != null) {
                    return false;
                }
            } else if (!this.zzbxE.equals(zze.zzbxE)) {
                return false;
            }
            if (this.zzbxF == null) {
                if (zze.zzbxF != null) {
                    return false;
                }
            } else if (!this.zzbxF.equals(zze.zzbxF)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zze.zzcuI == null || zze.zzcuI.isEmpty() : this.zzcuI.equals(zze.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbxF == null ? 0 : this.zzbxF.hashCode()) + (((this.zzbxE == null ? 0 : this.zzbxE.hashCode()) + (((this.zzbxD == null ? 0 : this.zzbxD.hashCode()) + (((this.zzbxC == null ? 0 : this.zzbxC.hashCode()) + (((this.zzbxB == null ? 0 : this.zzbxB.hashCode()) + (((this.zzbxA == null ? 0 : this.zzbxA.hashCode()) + (((this.zzbqT == null ? 0 : this.zzbqT.hashCode()) + (((((this.zzbxy == null ? 0 : this.zzbxy.hashCode()) + (((this.zzbqL == null ? 0 : this.zzbqL.hashCode()) + (((this.zzbqP == null ? 0 : this.zzbqP.hashCode()) + (((this.zzbxx == null ? 0 : this.zzbxx.hashCode()) + (((this.zzbxw == null ? 0 : this.zzbxw.hashCode()) + (((this.zzbxv == null ? 0 : this.zzbxv.hashCode()) + (((this.zzbxu == null ? 0 : this.zzbxu.hashCode()) + (((this.zzbxt == null ? 0 : this.zzbxt.hashCode()) + (((this.zzbxs == null ? 0 : this.zzbxs.hashCode()) + (((this.zzbxr == null ? 0 : this.zzbxr.hashCode()) + (((this.zzbhN == null ? 0 : this.zzbhN.hashCode()) + (((this.zzaS == null ? 0 : this.zzaS.hashCode()) + (((this.zzbqM == null ? 0 : this.zzbqM.hashCode()) + (((this.zzbxq == null ? 0 : this.zzbxq.hashCode()) + (((this.zzbxp == null ? 0 : this.zzbxp.hashCode()) + (((this.zzbxo == null ? 0 : this.zzbxo.hashCode()) + (((this.zzbb == null ? 0 : this.zzbb.hashCode()) + (((this.zzbxn == null ? 0 : this.zzbxn.hashCode()) + (((this.zzbxm == null ? 0 : this.zzbxm.hashCode()) + (((this.zzbxl == null ? 0 : this.zzbxl.hashCode()) + (((this.zzbxk == null ? 0 : this.zzbxk.hashCode()) + (((this.zzbxj == null ? 0 : this.zzbxj.hashCode()) + (((this.zzbxi == null ? 0 : this.zzbxi.hashCode()) + (((((((this.zzbxf == null ? 0 : this.zzbxf.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzbxr.hashCode((Object[]) this.zzbxg)) * 31) + zzbxr.hashCode((Object[]) this.zzbxh)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + zzbxr.hashCode((Object[]) this.zzbxz)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zze zzNG() {
            this.zzbxf = null;
            this.zzbxg = zzb.zzNA();
            this.zzbxh = zzg.zzNI();
            this.zzbxi = null;
            this.zzbxj = null;
            this.zzbxk = null;
            this.zzbxl = null;
            this.zzbxm = null;
            this.zzbxn = null;
            this.zzbb = null;
            this.zzbxo = null;
            this.zzbxp = null;
            this.zzbxq = null;
            this.zzbqM = null;
            this.zzaS = null;
            this.zzbhN = null;
            this.zzbxr = null;
            this.zzbxs = null;
            this.zzbxt = null;
            this.zzbxu = null;
            this.zzbxv = null;
            this.zzbxw = null;
            this.zzbxx = null;
            this.zzbqP = null;
            this.zzbqL = null;
            this.zzbxy = null;
            this.zzbxz = zza.zzNy();
            this.zzbqT = null;
            this.zzbxA = null;
            this.zzbxB = null;
            this.zzbxC = null;
            this.zzbxD = null;
            this.zzbxE = null;
            this.zzbxF = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzT */
        public zze zzb(zzbxl zzbxl2) throws IOException {
            while (true) {
                int zzaeo = zzbxl2.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzbxf = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 18:
                        int zzb = zzbxw.zzb(zzbxl2, 18);
                        int length = this.zzbxg == null ? 0 : this.zzbxg.length;
                        zzb[] zzbArr = new zzb[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbxg, 0, zzbArr, 0, length);
                        }
                        while (length < zzbArr.length - 1) {
                            zzbArr[length] = new zzb();
                            zzbxl2.zza(zzbArr[length]);
                            zzbxl2.zzaeo();
                            length++;
                        }
                        zzbArr[length] = new zzb();
                        zzbxl2.zza(zzbArr[length]);
                        this.zzbxg = zzbArr;
                        continue;
                    case 26:
                        int zzb2 = zzbxw.zzb(zzbxl2, 26);
                        int length2 = this.zzbxh == null ? 0 : this.zzbxh.length;
                        zzg[] zzgArr = new zzg[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbxh, 0, zzgArr, 0, length2);
                        }
                        while (length2 < zzgArr.length - 1) {
                            zzgArr[length2] = new zzg();
                            zzbxl2.zza(zzgArr[length2]);
                            zzbxl2.zzaeo();
                            length2++;
                        }
                        zzgArr[length2] = new zzg();
                        zzbxl2.zza(zzgArr[length2]);
                        this.zzbxh = zzgArr;
                        continue;
                    case 32:
                        this.zzbxi = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 40:
                        this.zzbxj = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 48:
                        this.zzbxk = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 56:
                        this.zzbxm = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 66:
                        this.zzbxn = zzbxl2.readString();
                        continue;
                    case 74:
                        this.zzbb = zzbxl2.readString();
                        continue;
                    case 82:
                        this.zzbxo = zzbxl2.readString();
                        continue;
                    case 90:
                        this.zzbxp = zzbxl2.readString();
                        continue;
                    case 96:
                        this.zzbxq = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 106:
                        this.zzbqM = zzbxl2.readString();
                        continue;
                    case 114:
                        this.zzaS = zzbxl2.readString();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        this.zzbhN = zzbxl2.readString();
                        continue;
                    case 136:
                        this.zzbxr = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 144:
                        this.zzbxs = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 154:
                        this.zzbxt = zzbxl2.readString();
                        continue;
                    case 160:
                        this.zzbxu = Boolean.valueOf(zzbxl2.zzaeu());
                        continue;
                    case 170:
                        this.zzbxv = zzbxl2.readString();
                        continue;
                    case 176:
                        this.zzbxw = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 184:
                        this.zzbxx = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 194:
                        this.zzbqP = zzbxl2.readString();
                        continue;
                    case 202:
                        this.zzbqL = zzbxl2.readString();
                        continue;
                    case 208:
                        this.zzbxl = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 224:
                        this.zzbxy = Boolean.valueOf(zzbxl2.zzaeu());
                        continue;
                    case 234:
                        int zzb3 = zzbxw.zzb(zzbxl2, 234);
                        int length3 = this.zzbxz == null ? 0 : this.zzbxz.length;
                        zza[] zzaArr = new zza[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbxz, 0, zzaArr, 0, length3);
                        }
                        while (length3 < zzaArr.length - 1) {
                            zzaArr[length3] = new zza();
                            zzbxl2.zza(zzaArr[length3]);
                            zzbxl2.zzaeo();
                            length3++;
                        }
                        zzaArr[length3] = new zza();
                        zzbxl2.zza(zzaArr[length3]);
                        this.zzbxz = zzaArr;
                        continue;
                    case 242:
                        this.zzbqT = zzbxl2.readString();
                        continue;
                    case 248:
                        this.zzbxA = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 256:
                        this.zzbxB = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 264:
                        this.zzbxC = Integer.valueOf(zzbxl2.zzaes());
                        continue;
                    case 274:
                        this.zzbxD = zzbxl2.readString();
                        continue;
                    case 280:
                        this.zzbxE = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    case 288:
                        this.zzbxF = Long.valueOf(zzbxl2.zzaer());
                        continue;
                    default:
                        if (!super.zza(zzbxl2, zzaeo)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zzbxm zzbxm2) throws IOException {
            if (this.zzbxf != null) {
                zzbxm2.zzJ(1, this.zzbxf.intValue());
            }
            if (this.zzbxg != null && this.zzbxg.length > 0) {
                for (zzb zzb : this.zzbxg) {
                    if (zzb != null) {
                        zzbxm2.zza(2, (zzbxt) zzb);
                    }
                }
            }
            if (this.zzbxh != null && this.zzbxh.length > 0) {
                for (zzg zzg : this.zzbxh) {
                    if (zzg != null) {
                        zzbxm2.zza(3, (zzbxt) zzg);
                    }
                }
            }
            if (this.zzbxi != null) {
                zzbxm2.zzb(4, this.zzbxi.longValue());
            }
            if (this.zzbxj != null) {
                zzbxm2.zzb(5, this.zzbxj.longValue());
            }
            if (this.zzbxk != null) {
                zzbxm2.zzb(6, this.zzbxk.longValue());
            }
            if (this.zzbxm != null) {
                zzbxm2.zzb(7, this.zzbxm.longValue());
            }
            if (this.zzbxn != null) {
                zzbxm2.zzq(8, this.zzbxn);
            }
            if (this.zzbb != null) {
                zzbxm2.zzq(9, this.zzbb);
            }
            if (this.zzbxo != null) {
                zzbxm2.zzq(10, this.zzbxo);
            }
            if (this.zzbxp != null) {
                zzbxm2.zzq(11, this.zzbxp);
            }
            if (this.zzbxq != null) {
                zzbxm2.zzJ(12, this.zzbxq.intValue());
            }
            if (this.zzbqM != null) {
                zzbxm2.zzq(13, this.zzbqM);
            }
            if (this.zzaS != null) {
                zzbxm2.zzq(14, this.zzaS);
            }
            if (this.zzbhN != null) {
                zzbxm2.zzq(16, this.zzbhN);
            }
            if (this.zzbxr != null) {
                zzbxm2.zzb(17, this.zzbxr.longValue());
            }
            if (this.zzbxs != null) {
                zzbxm2.zzb(18, this.zzbxs.longValue());
            }
            if (this.zzbxt != null) {
                zzbxm2.zzq(19, this.zzbxt);
            }
            if (this.zzbxu != null) {
                zzbxm2.zzg(20, this.zzbxu.booleanValue());
            }
            if (this.zzbxv != null) {
                zzbxm2.zzq(21, this.zzbxv);
            }
            if (this.zzbxw != null) {
                zzbxm2.zzb(22, this.zzbxw.longValue());
            }
            if (this.zzbxx != null) {
                zzbxm2.zzJ(23, this.zzbxx.intValue());
            }
            if (this.zzbqP != null) {
                zzbxm2.zzq(24, this.zzbqP);
            }
            if (this.zzbqL != null) {
                zzbxm2.zzq(25, this.zzbqL);
            }
            if (this.zzbxl != null) {
                zzbxm2.zzb(26, this.zzbxl.longValue());
            }
            if (this.zzbxy != null) {
                zzbxm2.zzg(28, this.zzbxy.booleanValue());
            }
            if (this.zzbxz != null && this.zzbxz.length > 0) {
                for (zza zza : this.zzbxz) {
                    if (zza != null) {
                        zzbxm2.zza(29, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbqT != null) {
                zzbxm2.zzq(30, this.zzbqT);
            }
            if (this.zzbxA != null) {
                zzbxm2.zzJ(31, this.zzbxA.intValue());
            }
            if (this.zzbxB != null) {
                zzbxm2.zzJ(32, this.zzbxB.intValue());
            }
            if (this.zzbxC != null) {
                zzbxm2.zzJ(33, this.zzbxC.intValue());
            }
            if (this.zzbxD != null) {
                zzbxm2.zzq(34, this.zzbxD);
            }
            if (this.zzbxE != null) {
                zzbxm2.zzb(35, this.zzbxE.longValue());
            }
            if (this.zzbxF != null) {
                zzbxm2.zzb(36, this.zzbxF.longValue());
            }
            super.zza(zzbxm2);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbxf != null) {
                zzu += zzbxm.zzL(1, this.zzbxf.intValue());
            }
            if (this.zzbxg != null && this.zzbxg.length > 0) {
                int i = zzu;
                for (zzb zzb : this.zzbxg) {
                    if (zzb != null) {
                        i += zzbxm.zzc(2, (zzbxt) zzb);
                    }
                }
                zzu = i;
            }
            if (this.zzbxh != null && this.zzbxh.length > 0) {
                int i2 = zzu;
                for (zzg zzg : this.zzbxh) {
                    if (zzg != null) {
                        i2 += zzbxm.zzc(3, (zzbxt) zzg);
                    }
                }
                zzu = i2;
            }
            if (this.zzbxi != null) {
                zzu += zzbxm.zzf(4, this.zzbxi.longValue());
            }
            if (this.zzbxj != null) {
                zzu += zzbxm.zzf(5, this.zzbxj.longValue());
            }
            if (this.zzbxk != null) {
                zzu += zzbxm.zzf(6, this.zzbxk.longValue());
            }
            if (this.zzbxm != null) {
                zzu += zzbxm.zzf(7, this.zzbxm.longValue());
            }
            if (this.zzbxn != null) {
                zzu += zzbxm.zzr(8, this.zzbxn);
            }
            if (this.zzbb != null) {
                zzu += zzbxm.zzr(9, this.zzbb);
            }
            if (this.zzbxo != null) {
                zzu += zzbxm.zzr(10, this.zzbxo);
            }
            if (this.zzbxp != null) {
                zzu += zzbxm.zzr(11, this.zzbxp);
            }
            if (this.zzbxq != null) {
                zzu += zzbxm.zzL(12, this.zzbxq.intValue());
            }
            if (this.zzbqM != null) {
                zzu += zzbxm.zzr(13, this.zzbqM);
            }
            if (this.zzaS != null) {
                zzu += zzbxm.zzr(14, this.zzaS);
            }
            if (this.zzbhN != null) {
                zzu += zzbxm.zzr(16, this.zzbhN);
            }
            if (this.zzbxr != null) {
                zzu += zzbxm.zzf(17, this.zzbxr.longValue());
            }
            if (this.zzbxs != null) {
                zzu += zzbxm.zzf(18, this.zzbxs.longValue());
            }
            if (this.zzbxt != null) {
                zzu += zzbxm.zzr(19, this.zzbxt);
            }
            if (this.zzbxu != null) {
                zzu += zzbxm.zzh(20, this.zzbxu.booleanValue());
            }
            if (this.zzbxv != null) {
                zzu += zzbxm.zzr(21, this.zzbxv);
            }
            if (this.zzbxw != null) {
                zzu += zzbxm.zzf(22, this.zzbxw.longValue());
            }
            if (this.zzbxx != null) {
                zzu += zzbxm.zzL(23, this.zzbxx.intValue());
            }
            if (this.zzbqP != null) {
                zzu += zzbxm.zzr(24, this.zzbqP);
            }
            if (this.zzbqL != null) {
                zzu += zzbxm.zzr(25, this.zzbqL);
            }
            if (this.zzbxl != null) {
                zzu += zzbxm.zzf(26, this.zzbxl.longValue());
            }
            if (this.zzbxy != null) {
                zzu += zzbxm.zzh(28, this.zzbxy.booleanValue());
            }
            if (this.zzbxz != null && this.zzbxz.length > 0) {
                for (zza zza : this.zzbxz) {
                    if (zza != null) {
                        zzu += zzbxm.zzc(29, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbqT != null) {
                zzu += zzbxm.zzr(30, this.zzbqT);
            }
            if (this.zzbxA != null) {
                zzu += zzbxm.zzL(31, this.zzbxA.intValue());
            }
            if (this.zzbxB != null) {
                zzu += zzbxm.zzL(32, this.zzbxB.intValue());
            }
            if (this.zzbxC != null) {
                zzu += zzbxm.zzL(33, this.zzbxC.intValue());
            }
            if (this.zzbxD != null) {
                zzu += zzbxm.zzr(34, this.zzbxD);
            }
            if (this.zzbxE != null) {
                zzu += zzbxm.zzf(35, this.zzbxE.longValue());
            }
            return this.zzbxF != null ? zzu + zzbxm.zzf(36, this.zzbxF.longValue()) : zzu;
        }
    }

    public static final class zzf extends zzbxn<zzf> {
        public long[] zzbxG;
        public long[] zzbxH;

        public zzf() {
            zzNH();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (!zzbxr.equals(this.zzbxG, zzf.zzbxG) || !zzbxr.equals(this.zzbxH, zzf.zzbxH)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzf.zzcuI == null || zzf.zzcuI.isEmpty() : this.zzcuI.equals(zzf.zzcuI);
        }

        public int hashCode() {
            return ((this.zzcuI == null || this.zzcuI.isEmpty()) ? 0 : this.zzcuI.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + zzbxr.hashCode(this.zzbxG)) * 31) + zzbxr.hashCode(this.zzbxH)) * 31);
        }

        public zzf zzNH() {
            this.zzbxG = zzbxw.zzcuX;
            this.zzbxH = zzbxw.zzcuX;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzU */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        int zzb = zzbxw.zzb(zzbxl, 8);
                        int length = this.zzbxG == null ? 0 : this.zzbxG.length;
                        long[] jArr = new long[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbxG, 0, jArr, 0, length);
                        }
                        while (length < jArr.length - 1) {
                            jArr[length] = zzbxl.zzaeq();
                            zzbxl.zzaeo();
                            length++;
                        }
                        jArr[length] = zzbxl.zzaeq();
                        this.zzbxG = jArr;
                        continue;
                    case 10:
                        int zzra = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaeq();
                            i++;
                        }
                        zzbxl.zzrc(position);
                        int length2 = this.zzbxG == null ? 0 : this.zzbxG.length;
                        long[] jArr2 = new long[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbxG, 0, jArr2, 0, length2);
                        }
                        while (length2 < jArr2.length) {
                            jArr2[length2] = zzbxl.zzaeq();
                            length2++;
                        }
                        this.zzbxG = jArr2;
                        zzbxl.zzrb(zzra);
                        continue;
                    case 16:
                        int zzb2 = zzbxw.zzb(zzbxl, 16);
                        int length3 = this.zzbxH == null ? 0 : this.zzbxH.length;
                        long[] jArr3 = new long[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbxH, 0, jArr3, 0, length3);
                        }
                        while (length3 < jArr3.length - 1) {
                            jArr3[length3] = zzbxl.zzaeq();
                            zzbxl.zzaeo();
                            length3++;
                        }
                        jArr3[length3] = zzbxl.zzaeq();
                        this.zzbxH = jArr3;
                        continue;
                    case 18:
                        int zzra2 = zzbxl.zzra(zzbxl.zzaex());
                        int position2 = zzbxl.getPosition();
                        int i2 = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaeq();
                            i2++;
                        }
                        zzbxl.zzrc(position2);
                        int length4 = this.zzbxH == null ? 0 : this.zzbxH.length;
                        long[] jArr4 = new long[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzbxH, 0, jArr4, 0, length4);
                        }
                        while (length4 < jArr4.length) {
                            jArr4[length4] = zzbxl.zzaeq();
                            length4++;
                        }
                        this.zzbxH = jArr4;
                        zzbxl.zzrb(zzra2);
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
            if (this.zzbxG != null && this.zzbxG.length > 0) {
                for (long zza : this.zzbxG) {
                    zzbxm.zza(1, zza);
                }
            }
            if (this.zzbxH != null && this.zzbxH.length > 0) {
                for (long zza2 : this.zzbxH) {
                    zzbxm.zza(2, zza2);
                }
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int i;
            int zzu = super.zzu();
            if (this.zzbxG == null || this.zzbxG.length <= 0) {
                i = zzu;
            } else {
                int i2 = 0;
                for (long zzbe : this.zzbxG) {
                    i2 += zzbxm.zzbe(zzbe);
                }
                i = zzu + i2 + (this.zzbxG.length * 1);
            }
            if (this.zzbxH == null || this.zzbxH.length <= 0) {
                return i;
            }
            int i3 = 0;
            for (long zzbe2 : this.zzbxH) {
                i3 += zzbxm.zzbe(zzbe2);
            }
            return i + i3 + (this.zzbxH.length * 1);
        }
    }

    public static final class zzg extends zzbxn<zzg> {
        private static volatile zzg[] zzbxI;
        public String name;
        public String zzaGV;
        public Float zzbwe;
        public Double zzbwf;
        public Long zzbxJ;
        public Long zzbxc;

        public zzg() {
            zzNJ();
        }

        public static zzg[] zzNI() {
            if (zzbxI == null) {
                synchronized (zzbxr.zzcuQ) {
                    if (zzbxI == null) {
                        zzbxI = new zzg[0];
                    }
                }
            }
            return zzbxI;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) obj;
            if (this.zzbxJ == null) {
                if (zzg.zzbxJ != null) {
                    return false;
                }
            } else if (!this.zzbxJ.equals(zzg.zzbxJ)) {
                return false;
            }
            if (this.name == null) {
                if (zzg.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzg.name)) {
                return false;
            }
            if (this.zzaGV == null) {
                if (zzg.zzaGV != null) {
                    return false;
                }
            } else if (!this.zzaGV.equals(zzg.zzaGV)) {
                return false;
            }
            if (this.zzbxc == null) {
                if (zzg.zzbxc != null) {
                    return false;
                }
            } else if (!this.zzbxc.equals(zzg.zzbxc)) {
                return false;
            }
            if (this.zzbwe == null) {
                if (zzg.zzbwe != null) {
                    return false;
                }
            } else if (!this.zzbwe.equals(zzg.zzbwe)) {
                return false;
            }
            if (this.zzbwf == null) {
                if (zzg.zzbwf != null) {
                    return false;
                }
            } else if (!this.zzbwf.equals(zzg.zzbwf)) {
                return false;
            }
            return (this.zzcuI == null || this.zzcuI.isEmpty()) ? zzg.zzcuI == null || zzg.zzcuI.isEmpty() : this.zzcuI.equals(zzg.zzcuI);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbwf == null ? 0 : this.zzbwf.hashCode()) + (((this.zzbwe == null ? 0 : this.zzbwe.hashCode()) + (((this.zzbxc == null ? 0 : this.zzbxc.hashCode()) + (((this.zzaGV == null ? 0 : this.zzaGV.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzbxJ == null ? 0 : this.zzbxJ.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzcuI != null && !this.zzcuI.isEmpty()) {
                i = this.zzcuI.hashCode();
            }
            return hashCode + i;
        }

        public zzg zzNJ() {
            this.zzbxJ = null;
            this.name = null;
            this.zzaGV = null;
            this.zzbxc = null;
            this.zzbwe = null;
            this.zzbwf = null;
            this.zzcuI = null;
            this.zzcuR = -1;
            return this;
        }

        /* renamed from: zzV */
        public zzg zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzbxJ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 18:
                        this.name = zzbxl.readString();
                        continue;
                    case 26:
                        this.zzaGV = zzbxl.readString();
                        continue;
                    case 32:
                        this.zzbxc = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 45:
                        this.zzbwe = Float.valueOf(zzbxl.readFloat());
                        continue;
                    case 49:
                        this.zzbwf = Double.valueOf(zzbxl.readDouble());
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
            if (this.zzbxJ != null) {
                zzbxm.zzb(1, this.zzbxJ.longValue());
            }
            if (this.name != null) {
                zzbxm.zzq(2, this.name);
            }
            if (this.zzaGV != null) {
                zzbxm.zzq(3, this.zzaGV);
            }
            if (this.zzbxc != null) {
                zzbxm.zzb(4, this.zzbxc.longValue());
            }
            if (this.zzbwe != null) {
                zzbxm.zzc(5, this.zzbwe.floatValue());
            }
            if (this.zzbwf != null) {
                zzbxm.zza(6, this.zzbwf.doubleValue());
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzbxJ != null) {
                zzu += zzbxm.zzf(1, this.zzbxJ.longValue());
            }
            if (this.name != null) {
                zzu += zzbxm.zzr(2, this.name);
            }
            if (this.zzaGV != null) {
                zzu += zzbxm.zzr(3, this.zzaGV);
            }
            if (this.zzbxc != null) {
                zzu += zzbxm.zzf(4, this.zzbxc.longValue());
            }
            if (this.zzbwe != null) {
                zzu += zzbxm.zzd(5, this.zzbwe.floatValue());
            }
            return this.zzbwf != null ? zzu + zzbxm.zzb(6, this.zzbwf.doubleValue()) : zzu;
        }
    }
}
