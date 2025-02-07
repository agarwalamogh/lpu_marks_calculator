package com.google.android.gms.internal;

import java.io.IOException;

public interface zzae {

    public static final class zza extends zzbxn<zza> {
        public zzb zzaK;
        public zzc zzaL;

        public zza() {
            this.zzcuR = -1;
        }

        public static zza zzc(byte[] bArr) throws zzbxs {
            return (zza) zzbxt.zza(new zza(), bArr);
        }

        /* renamed from: zza */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzaK == null) {
                            this.zzaK = new zzb();
                        }
                        zzbxl.zza(this.zzaK);
                        continue;
                    case 18:
                        if (this.zzaL == null) {
                            this.zzaL = new zzc();
                        }
                        zzbxl.zza(this.zzaL);
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
            if (this.zzaK != null) {
                zzbxm.zza(1, (zzbxt) this.zzaK);
            }
            if (this.zzaL != null) {
                zzbxm.zza(2, (zzbxt) this.zzaL);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaK != null) {
                zzu += zzbxm.zzc(1, (zzbxt) this.zzaK);
            }
            return this.zzaL != null ? zzu + zzbxm.zzc(2, (zzbxt) this.zzaL) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public Integer zzaM;

        public zzb() {
            this.zzaM = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaM != null) {
                zzbxm.zzJ(27, this.zzaM.intValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzc */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 216:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzaM = Integer.valueOf(zzaes);
                                break;
                            default:
                                continue;
                        }
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

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            return this.zzaM != null ? zzu + zzbxm.zzL(27, this.zzaM.intValue()) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        public String zzaN;
        public String zzaO;
        public String zzaP;
        public String zzaQ;
        public String zzaR;

        public zzc() {
            this.zzaN = null;
            this.zzaO = null;
            this.zzaP = null;
            this.zzaQ = null;
            this.zzaR = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaN != null) {
                zzbxm.zzq(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzbxm.zzq(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzbxm.zzq(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzbxm.zzq(4, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzbxm.zzq(5, this.zzaR);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzd */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.zzaN = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzaO = zzbxl.readString();
                        continue;
                    case 26:
                        this.zzaP = zzbxl.readString();
                        continue;
                    case 34:
                        this.zzaQ = zzbxl.readString();
                        continue;
                    case 42:
                        this.zzaR = zzbxl.readString();
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

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaN != null) {
                zzu += zzbxm.zzr(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzu += zzbxm.zzr(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzu += zzbxm.zzr(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzu += zzbxm.zzr(4, this.zzaQ);
            }
            return this.zzaR != null ? zzu + zzbxm.zzr(5, this.zzaR) : zzu;
        }
    }
}
