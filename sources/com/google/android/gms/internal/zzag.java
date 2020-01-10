package com.google.android.gms.internal;

import android.support.p003v7.widget.helper.ItemTouchHelper.Callback;
import java.io.IOException;

public interface zzag {

    public static final class zza extends zzbxn<zza> {
        public String zzaN;
        public String zzaP;
        public String zzaQ;
        public String zzaR;
        public Long zzbA;
        public Long zzbB;
        public Long zzbC;
        public zzb zzbD;
        public Long zzbE;
        public Long zzbF;
        public Long zzbG;
        public Long zzbH;
        public Long zzbI;
        public Long zzbJ;
        public Integer zzbK;
        public Integer zzbL;
        public Long zzbM;
        public Long zzbN;
        public Long zzbO;
        public Long zzbP;
        public Long zzbQ;
        public Integer zzbR;
        public C0955zza zzbS;
        public C0955zza[] zzbT;
        public zzb zzbU;
        public Long zzbV;
        public String zzbW;
        public Integer zzbX;
        public Boolean zzbY;
        public String zzbZ;
        public String zzba;
        public String zzbb;
        public Long zzbc;
        public Long zzbd;
        public Long zzbe;
        public Long zzbf;
        public Long zzbg;
        public Long zzbh;
        public Long zzbi;
        public Long zzbj;
        public Long zzbk;
        public Long zzbl;
        public String zzbm;
        public Long zzbn;
        public Long zzbo;
        public Long zzbp;
        public Long zzbq;
        public Long zzbr;
        public Long zzbs;
        public Long zzbt;
        public Long zzbu;
        public Long zzbv;
        public String zzbw;
        public Long zzbx;
        public Long zzby;
        public Long zzbz;
        public Long zzca;
        public zze zzcb;

        /* renamed from: com.google.android.gms.internal.zzag$zza$zza reason: collision with other inner class name */
        public static final class C0955zza extends zzbxn<C0955zza> {
            private static volatile C0955zza[] zzcc;
            public Long zzbn;
            public Long zzbo;
            public Long zzcd;
            public Long zzce;
            public Long zzcf;
            public Long zzcg;
            public Integer zzch;
            public Long zzci;
            public Long zzcj;
            public Long zzck;
            public Integer zzcl;
            public Long zzcm;

            public C0955zza() {
                this.zzbn = null;
                this.zzbo = null;
                this.zzcd = null;
                this.zzce = null;
                this.zzcf = null;
                this.zzcg = null;
                this.zzch = null;
                this.zzci = null;
                this.zzcj = null;
                this.zzck = null;
                this.zzcl = null;
                this.zzcm = null;
                this.zzcuR = -1;
            }

            public static C0955zza[] zzv() {
                if (zzcc == null) {
                    synchronized (zzbxr.zzcuQ) {
                        if (zzcc == null) {
                            zzcc = new C0955zza[0];
                        }
                    }
                }
                return zzcc;
            }

            public void zza(zzbxm zzbxm) throws IOException {
                if (this.zzbn != null) {
                    zzbxm.zzb(1, this.zzbn.longValue());
                }
                if (this.zzbo != null) {
                    zzbxm.zzb(2, this.zzbo.longValue());
                }
                if (this.zzcd != null) {
                    zzbxm.zzb(3, this.zzcd.longValue());
                }
                if (this.zzce != null) {
                    zzbxm.zzb(4, this.zzce.longValue());
                }
                if (this.zzcf != null) {
                    zzbxm.zzb(5, this.zzcf.longValue());
                }
                if (this.zzcg != null) {
                    zzbxm.zzb(6, this.zzcg.longValue());
                }
                if (this.zzch != null) {
                    zzbxm.zzJ(7, this.zzch.intValue());
                }
                if (this.zzci != null) {
                    zzbxm.zzb(8, this.zzci.longValue());
                }
                if (this.zzcj != null) {
                    zzbxm.zzb(9, this.zzcj.longValue());
                }
                if (this.zzck != null) {
                    zzbxm.zzb(10, this.zzck.longValue());
                }
                if (this.zzcl != null) {
                    zzbxm.zzJ(11, this.zzcl.intValue());
                }
                if (this.zzcm != null) {
                    zzbxm.zzb(12, this.zzcm.longValue());
                }
                super.zza(zzbxm);
            }

            /* renamed from: zzg */
            public C0955zza zzb(zzbxl zzbxl) throws IOException {
                while (true) {
                    int zzaeo = zzbxl.zzaeo();
                    switch (zzaeo) {
                        case 0:
                            break;
                        case 8:
                            this.zzbn = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 16:
                            this.zzbo = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 24:
                            this.zzcd = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 32:
                            this.zzce = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 40:
                            this.zzcf = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 48:
                            this.zzcg = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 56:
                            int zzaes = zzbxl.zzaes();
                            switch (zzaes) {
                                case 0:
                                case 1:
                                case 2:
                                case 1000:
                                    this.zzch = Integer.valueOf(zzaes);
                                    break;
                                default:
                                    continue;
                            }
                        case 64:
                            this.zzci = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 72:
                            this.zzcj = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 80:
                            this.zzck = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 88:
                            int zzaes2 = zzbxl.zzaes();
                            switch (zzaes2) {
                                case 0:
                                case 1:
                                case 2:
                                case 1000:
                                    this.zzcl = Integer.valueOf(zzaes2);
                                    break;
                                default:
                                    continue;
                            }
                        case 96:
                            this.zzcm = Long.valueOf(zzbxl.zzaer());
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
                if (this.zzbn != null) {
                    zzu += zzbxm.zzf(1, this.zzbn.longValue());
                }
                if (this.zzbo != null) {
                    zzu += zzbxm.zzf(2, this.zzbo.longValue());
                }
                if (this.zzcd != null) {
                    zzu += zzbxm.zzf(3, this.zzcd.longValue());
                }
                if (this.zzce != null) {
                    zzu += zzbxm.zzf(4, this.zzce.longValue());
                }
                if (this.zzcf != null) {
                    zzu += zzbxm.zzf(5, this.zzcf.longValue());
                }
                if (this.zzcg != null) {
                    zzu += zzbxm.zzf(6, this.zzcg.longValue());
                }
                if (this.zzch != null) {
                    zzu += zzbxm.zzL(7, this.zzch.intValue());
                }
                if (this.zzci != null) {
                    zzu += zzbxm.zzf(8, this.zzci.longValue());
                }
                if (this.zzcj != null) {
                    zzu += zzbxm.zzf(9, this.zzcj.longValue());
                }
                if (this.zzck != null) {
                    zzu += zzbxm.zzf(10, this.zzck.longValue());
                }
                if (this.zzcl != null) {
                    zzu += zzbxm.zzL(11, this.zzcl.intValue());
                }
                return this.zzcm != null ? zzu + zzbxm.zzf(12, this.zzcm.longValue()) : zzu;
            }
        }

        public static final class zzb extends zzbxn<zzb> {
            public Long zzbP;
            public Long zzbQ;
            public Long zzcn;

            public zzb() {
                this.zzbP = null;
                this.zzbQ = null;
                this.zzcn = null;
                this.zzcuR = -1;
            }

            public void zza(zzbxm zzbxm) throws IOException {
                if (this.zzbP != null) {
                    zzbxm.zzb(1, this.zzbP.longValue());
                }
                if (this.zzbQ != null) {
                    zzbxm.zzb(2, this.zzbQ.longValue());
                }
                if (this.zzcn != null) {
                    zzbxm.zzb(3, this.zzcn.longValue());
                }
                super.zza(zzbxm);
            }

            /* renamed from: zzh */
            public zzb zzb(zzbxl zzbxl) throws IOException {
                while (true) {
                    int zzaeo = zzbxl.zzaeo();
                    switch (zzaeo) {
                        case 0:
                            break;
                        case 8:
                            this.zzbP = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 16:
                            this.zzbQ = Long.valueOf(zzbxl.zzaer());
                            continue;
                        case 24:
                            this.zzcn = Long.valueOf(zzbxl.zzaer());
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
                if (this.zzbP != null) {
                    zzu += zzbxm.zzf(1, this.zzbP.longValue());
                }
                if (this.zzbQ != null) {
                    zzu += zzbxm.zzf(2, this.zzbQ.longValue());
                }
                return this.zzcn != null ? zzu + zzbxm.zzf(3, this.zzcn.longValue()) : zzu;
            }
        }

        public zza() {
            this.zzbb = null;
            this.zzba = null;
            this.zzbc = null;
            this.zzbd = null;
            this.zzbe = null;
            this.zzbf = null;
            this.zzbg = null;
            this.zzbh = null;
            this.zzbi = null;
            this.zzbj = null;
            this.zzbk = null;
            this.zzbl = null;
            this.zzbm = null;
            this.zzbn = null;
            this.zzbo = null;
            this.zzbp = null;
            this.zzbq = null;
            this.zzbr = null;
            this.zzbs = null;
            this.zzbt = null;
            this.zzbu = null;
            this.zzbv = null;
            this.zzaN = null;
            this.zzbw = null;
            this.zzbx = null;
            this.zzby = null;
            this.zzbz = null;
            this.zzaP = null;
            this.zzbA = null;
            this.zzbB = null;
            this.zzbC = null;
            this.zzbE = null;
            this.zzbF = null;
            this.zzbG = null;
            this.zzbH = null;
            this.zzbI = null;
            this.zzbJ = null;
            this.zzaQ = null;
            this.zzaR = null;
            this.zzbK = null;
            this.zzbL = null;
            this.zzbM = null;
            this.zzbN = null;
            this.zzbO = null;
            this.zzbP = null;
            this.zzbQ = null;
            this.zzbR = null;
            this.zzbT = C0955zza.zzv();
            this.zzbV = null;
            this.zzbW = null;
            this.zzbX = null;
            this.zzbY = null;
            this.zzbZ = null;
            this.zzca = null;
            this.zzcuR = -1;
        }

        public static zza zzd(byte[] bArr) throws zzbxs {
            return (zza) zzbxt.zza(new zza(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzbb != null) {
                zzbxm.zzq(1, this.zzbb);
            }
            if (this.zzba != null) {
                zzbxm.zzq(2, this.zzba);
            }
            if (this.zzbc != null) {
                zzbxm.zzb(3, this.zzbc.longValue());
            }
            if (this.zzbd != null) {
                zzbxm.zzb(4, this.zzbd.longValue());
            }
            if (this.zzbe != null) {
                zzbxm.zzb(5, this.zzbe.longValue());
            }
            if (this.zzbf != null) {
                zzbxm.zzb(6, this.zzbf.longValue());
            }
            if (this.zzbg != null) {
                zzbxm.zzb(7, this.zzbg.longValue());
            }
            if (this.zzbh != null) {
                zzbxm.zzb(8, this.zzbh.longValue());
            }
            if (this.zzbi != null) {
                zzbxm.zzb(9, this.zzbi.longValue());
            }
            if (this.zzbj != null) {
                zzbxm.zzb(10, this.zzbj.longValue());
            }
            if (this.zzbk != null) {
                zzbxm.zzb(11, this.zzbk.longValue());
            }
            if (this.zzbl != null) {
                zzbxm.zzb(12, this.zzbl.longValue());
            }
            if (this.zzbm != null) {
                zzbxm.zzq(13, this.zzbm);
            }
            if (this.zzbn != null) {
                zzbxm.zzb(14, this.zzbn.longValue());
            }
            if (this.zzbo != null) {
                zzbxm.zzb(15, this.zzbo.longValue());
            }
            if (this.zzbp != null) {
                zzbxm.zzb(16, this.zzbp.longValue());
            }
            if (this.zzbq != null) {
                zzbxm.zzb(17, this.zzbq.longValue());
            }
            if (this.zzbr != null) {
                zzbxm.zzb(18, this.zzbr.longValue());
            }
            if (this.zzbs != null) {
                zzbxm.zzb(19, this.zzbs.longValue());
            }
            if (this.zzbt != null) {
                zzbxm.zzb(20, this.zzbt.longValue());
            }
            if (this.zzbV != null) {
                zzbxm.zzb(21, this.zzbV.longValue());
            }
            if (this.zzbu != null) {
                zzbxm.zzb(22, this.zzbu.longValue());
            }
            if (this.zzbv != null) {
                zzbxm.zzb(23, this.zzbv.longValue());
            }
            if (this.zzbW != null) {
                zzbxm.zzq(24, this.zzbW);
            }
            if (this.zzca != null) {
                zzbxm.zzb(25, this.zzca.longValue());
            }
            if (this.zzbX != null) {
                zzbxm.zzJ(26, this.zzbX.intValue());
            }
            if (this.zzaN != null) {
                zzbxm.zzq(27, this.zzaN);
            }
            if (this.zzbY != null) {
                zzbxm.zzg(28, this.zzbY.booleanValue());
            }
            if (this.zzbw != null) {
                zzbxm.zzq(29, this.zzbw);
            }
            if (this.zzbZ != null) {
                zzbxm.zzq(30, this.zzbZ);
            }
            if (this.zzbx != null) {
                zzbxm.zzb(31, this.zzbx.longValue());
            }
            if (this.zzby != null) {
                zzbxm.zzb(32, this.zzby.longValue());
            }
            if (this.zzbz != null) {
                zzbxm.zzb(33, this.zzbz.longValue());
            }
            if (this.zzaP != null) {
                zzbxm.zzq(34, this.zzaP);
            }
            if (this.zzbA != null) {
                zzbxm.zzb(35, this.zzbA.longValue());
            }
            if (this.zzbB != null) {
                zzbxm.zzb(36, this.zzbB.longValue());
            }
            if (this.zzbC != null) {
                zzbxm.zzb(37, this.zzbC.longValue());
            }
            if (this.zzbD != null) {
                zzbxm.zza(38, (zzbxt) this.zzbD);
            }
            if (this.zzbE != null) {
                zzbxm.zzb(39, this.zzbE.longValue());
            }
            if (this.zzbF != null) {
                zzbxm.zzb(40, this.zzbF.longValue());
            }
            if (this.zzbG != null) {
                zzbxm.zzb(41, this.zzbG.longValue());
            }
            if (this.zzbH != null) {
                zzbxm.zzb(42, this.zzbH.longValue());
            }
            if (this.zzbT != null && this.zzbT.length > 0) {
                for (C0955zza zza : this.zzbT) {
                    if (zza != null) {
                        zzbxm.zza(43, (zzbxt) zza);
                    }
                }
            }
            if (this.zzbI != null) {
                zzbxm.zzb(44, this.zzbI.longValue());
            }
            if (this.zzbJ != null) {
                zzbxm.zzb(45, this.zzbJ.longValue());
            }
            if (this.zzaQ != null) {
                zzbxm.zzq(46, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzbxm.zzq(47, this.zzaR);
            }
            if (this.zzbK != null) {
                zzbxm.zzJ(48, this.zzbK.intValue());
            }
            if (this.zzbL != null) {
                zzbxm.zzJ(49, this.zzbL.intValue());
            }
            if (this.zzbS != null) {
                zzbxm.zza(50, (zzbxt) this.zzbS);
            }
            if (this.zzbM != null) {
                zzbxm.zzb(51, this.zzbM.longValue());
            }
            if (this.zzbN != null) {
                zzbxm.zzb(52, this.zzbN.longValue());
            }
            if (this.zzbO != null) {
                zzbxm.zzb(53, this.zzbO.longValue());
            }
            if (this.zzbP != null) {
                zzbxm.zzb(54, this.zzbP.longValue());
            }
            if (this.zzbQ != null) {
                zzbxm.zzb(55, this.zzbQ.longValue());
            }
            if (this.zzbR != null) {
                zzbxm.zzJ(56, this.zzbR.intValue());
            }
            if (this.zzbU != null) {
                zzbxm.zza(57, (zzbxt) this.zzbU);
            }
            if (this.zzcb != null) {
                zzbxm.zza(201, (zzbxt) this.zzcb);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzf */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.zzbb = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzba = zzbxl.readString();
                        continue;
                    case 24:
                        this.zzbc = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 32:
                        this.zzbd = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 40:
                        this.zzbe = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 48:
                        this.zzbf = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 56:
                        this.zzbg = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 64:
                        this.zzbh = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 72:
                        this.zzbi = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 80:
                        this.zzbj = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 88:
                        this.zzbk = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 96:
                        this.zzbl = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 106:
                        this.zzbm = zzbxl.readString();
                        continue;
                    case 112:
                        this.zzbn = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 120:
                        this.zzbo = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 128:
                        this.zzbp = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 136:
                        this.zzbq = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 144:
                        this.zzbr = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 152:
                        this.zzbs = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 160:
                        this.zzbt = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 168:
                        this.zzbV = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 176:
                        this.zzbu = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 184:
                        this.zzbv = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 194:
                        this.zzbW = zzbxl.readString();
                        continue;
                    case Callback.DEFAULT_DRAG_ANIMATION_DURATION /*200*/:
                        this.zzca = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 208:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzbX = Integer.valueOf(zzaes);
                                break;
                            default:
                                continue;
                        }
                    case 218:
                        this.zzaN = zzbxl.readString();
                        continue;
                    case 224:
                        this.zzbY = Boolean.valueOf(zzbxl.zzaeu());
                        continue;
                    case 234:
                        this.zzbw = zzbxl.readString();
                        continue;
                    case 242:
                        this.zzbZ = zzbxl.readString();
                        continue;
                    case 248:
                        this.zzbx = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 256:
                        this.zzby = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 264:
                        this.zzbz = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 274:
                        this.zzaP = zzbxl.readString();
                        continue;
                    case 280:
                        this.zzbA = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 288:
                        this.zzbB = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 296:
                        this.zzbC = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 306:
                        if (this.zzbD == null) {
                            this.zzbD = new zzb();
                        }
                        zzbxl.zza(this.zzbD);
                        continue;
                    case 312:
                        this.zzbE = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 320:
                        this.zzbF = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 328:
                        this.zzbG = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 336:
                        this.zzbH = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 346:
                        int zzb2 = zzbxw.zzb(zzbxl, 346);
                        int length = this.zzbT == null ? 0 : this.zzbT.length;
                        C0955zza[] zzaArr = new C0955zza[(zzb2 + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbT, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new C0955zza();
                            zzbxl.zza(zzaArr[length]);
                            zzbxl.zzaeo();
                            length++;
                        }
                        zzaArr[length] = new C0955zza();
                        zzbxl.zza(zzaArr[length]);
                        this.zzbT = zzaArr;
                        continue;
                    case 352:
                        this.zzbI = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 360:
                        this.zzbJ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 370:
                        this.zzaQ = zzbxl.readString();
                        continue;
                    case 378:
                        this.zzaR = zzbxl.readString();
                        continue;
                    case 384:
                        int zzaes2 = zzbxl.zzaes();
                        switch (zzaes2) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzbK = Integer.valueOf(zzaes2);
                                break;
                            default:
                                continue;
                        }
                    case 392:
                        int zzaes3 = zzbxl.zzaes();
                        switch (zzaes3) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzbL = Integer.valueOf(zzaes3);
                                break;
                            default:
                                continue;
                        }
                    case 402:
                        if (this.zzbS == null) {
                            this.zzbS = new C0955zza();
                        }
                        zzbxl.zza(this.zzbS);
                        continue;
                    case 408:
                        this.zzbM = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 416:
                        this.zzbN = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 424:
                        this.zzbO = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 432:
                        this.zzbP = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 440:
                        this.zzbQ = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 448:
                        int zzaes4 = zzbxl.zzaes();
                        switch (zzaes4) {
                            case 0:
                            case 1:
                            case 2:
                            case 1000:
                                this.zzbR = Integer.valueOf(zzaes4);
                                break;
                            default:
                                continue;
                        }
                    case 458:
                        if (this.zzbU == null) {
                            this.zzbU = new zzb();
                        }
                        zzbxl.zza(this.zzbU);
                        continue;
                    case 1610:
                        if (this.zzcb == null) {
                            this.zzcb = new zze();
                        }
                        zzbxl.zza(this.zzcb);
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
            if (this.zzbb != null) {
                zzu += zzbxm.zzr(1, this.zzbb);
            }
            if (this.zzba != null) {
                zzu += zzbxm.zzr(2, this.zzba);
            }
            if (this.zzbc != null) {
                zzu += zzbxm.zzf(3, this.zzbc.longValue());
            }
            if (this.zzbd != null) {
                zzu += zzbxm.zzf(4, this.zzbd.longValue());
            }
            if (this.zzbe != null) {
                zzu += zzbxm.zzf(5, this.zzbe.longValue());
            }
            if (this.zzbf != null) {
                zzu += zzbxm.zzf(6, this.zzbf.longValue());
            }
            if (this.zzbg != null) {
                zzu += zzbxm.zzf(7, this.zzbg.longValue());
            }
            if (this.zzbh != null) {
                zzu += zzbxm.zzf(8, this.zzbh.longValue());
            }
            if (this.zzbi != null) {
                zzu += zzbxm.zzf(9, this.zzbi.longValue());
            }
            if (this.zzbj != null) {
                zzu += zzbxm.zzf(10, this.zzbj.longValue());
            }
            if (this.zzbk != null) {
                zzu += zzbxm.zzf(11, this.zzbk.longValue());
            }
            if (this.zzbl != null) {
                zzu += zzbxm.zzf(12, this.zzbl.longValue());
            }
            if (this.zzbm != null) {
                zzu += zzbxm.zzr(13, this.zzbm);
            }
            if (this.zzbn != null) {
                zzu += zzbxm.zzf(14, this.zzbn.longValue());
            }
            if (this.zzbo != null) {
                zzu += zzbxm.zzf(15, this.zzbo.longValue());
            }
            if (this.zzbp != null) {
                zzu += zzbxm.zzf(16, this.zzbp.longValue());
            }
            if (this.zzbq != null) {
                zzu += zzbxm.zzf(17, this.zzbq.longValue());
            }
            if (this.zzbr != null) {
                zzu += zzbxm.zzf(18, this.zzbr.longValue());
            }
            if (this.zzbs != null) {
                zzu += zzbxm.zzf(19, this.zzbs.longValue());
            }
            if (this.zzbt != null) {
                zzu += zzbxm.zzf(20, this.zzbt.longValue());
            }
            if (this.zzbV != null) {
                zzu += zzbxm.zzf(21, this.zzbV.longValue());
            }
            if (this.zzbu != null) {
                zzu += zzbxm.zzf(22, this.zzbu.longValue());
            }
            if (this.zzbv != null) {
                zzu += zzbxm.zzf(23, this.zzbv.longValue());
            }
            if (this.zzbW != null) {
                zzu += zzbxm.zzr(24, this.zzbW);
            }
            if (this.zzca != null) {
                zzu += zzbxm.zzf(25, this.zzca.longValue());
            }
            if (this.zzbX != null) {
                zzu += zzbxm.zzL(26, this.zzbX.intValue());
            }
            if (this.zzaN != null) {
                zzu += zzbxm.zzr(27, this.zzaN);
            }
            if (this.zzbY != null) {
                zzu += zzbxm.zzh(28, this.zzbY.booleanValue());
            }
            if (this.zzbw != null) {
                zzu += zzbxm.zzr(29, this.zzbw);
            }
            if (this.zzbZ != null) {
                zzu += zzbxm.zzr(30, this.zzbZ);
            }
            if (this.zzbx != null) {
                zzu += zzbxm.zzf(31, this.zzbx.longValue());
            }
            if (this.zzby != null) {
                zzu += zzbxm.zzf(32, this.zzby.longValue());
            }
            if (this.zzbz != null) {
                zzu += zzbxm.zzf(33, this.zzbz.longValue());
            }
            if (this.zzaP != null) {
                zzu += zzbxm.zzr(34, this.zzaP);
            }
            if (this.zzbA != null) {
                zzu += zzbxm.zzf(35, this.zzbA.longValue());
            }
            if (this.zzbB != null) {
                zzu += zzbxm.zzf(36, this.zzbB.longValue());
            }
            if (this.zzbC != null) {
                zzu += zzbxm.zzf(37, this.zzbC.longValue());
            }
            if (this.zzbD != null) {
                zzu += zzbxm.zzc(38, (zzbxt) this.zzbD);
            }
            if (this.zzbE != null) {
                zzu += zzbxm.zzf(39, this.zzbE.longValue());
            }
            if (this.zzbF != null) {
                zzu += zzbxm.zzf(40, this.zzbF.longValue());
            }
            if (this.zzbG != null) {
                zzu += zzbxm.zzf(41, this.zzbG.longValue());
            }
            if (this.zzbH != null) {
                zzu += zzbxm.zzf(42, this.zzbH.longValue());
            }
            if (this.zzbT != null && this.zzbT.length > 0) {
                int i = zzu;
                for (C0955zza zza : this.zzbT) {
                    if (zza != null) {
                        i += zzbxm.zzc(43, (zzbxt) zza);
                    }
                }
                zzu = i;
            }
            if (this.zzbI != null) {
                zzu += zzbxm.zzf(44, this.zzbI.longValue());
            }
            if (this.zzbJ != null) {
                zzu += zzbxm.zzf(45, this.zzbJ.longValue());
            }
            if (this.zzaQ != null) {
                zzu += zzbxm.zzr(46, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzu += zzbxm.zzr(47, this.zzaR);
            }
            if (this.zzbK != null) {
                zzu += zzbxm.zzL(48, this.zzbK.intValue());
            }
            if (this.zzbL != null) {
                zzu += zzbxm.zzL(49, this.zzbL.intValue());
            }
            if (this.zzbS != null) {
                zzu += zzbxm.zzc(50, (zzbxt) this.zzbS);
            }
            if (this.zzbM != null) {
                zzu += zzbxm.zzf(51, this.zzbM.longValue());
            }
            if (this.zzbN != null) {
                zzu += zzbxm.zzf(52, this.zzbN.longValue());
            }
            if (this.zzbO != null) {
                zzu += zzbxm.zzf(53, this.zzbO.longValue());
            }
            if (this.zzbP != null) {
                zzu += zzbxm.zzf(54, this.zzbP.longValue());
            }
            if (this.zzbQ != null) {
                zzu += zzbxm.zzf(55, this.zzbQ.longValue());
            }
            if (this.zzbR != null) {
                zzu += zzbxm.zzL(56, this.zzbR.intValue());
            }
            if (this.zzbU != null) {
                zzu += zzbxm.zzc(57, (zzbxt) this.zzbU);
            }
            return this.zzcb != null ? zzu + zzbxm.zzc(201, (zzbxt) this.zzcb) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public Long zzco;
        public Integer zzcp;
        public Boolean zzcq;
        public int[] zzcr;
        public Long zzcs;

        public zzb() {
            this.zzco = null;
            this.zzcp = null;
            this.zzcq = null;
            this.zzcr = zzbxw.zzcuW;
            this.zzcs = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzco != null) {
                zzbxm.zzb(1, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzbxm.zzJ(2, this.zzcp.intValue());
            }
            if (this.zzcq != null) {
                zzbxm.zzg(3, this.zzcq.booleanValue());
            }
            if (this.zzcr != null && this.zzcr.length > 0) {
                for (int zzJ : this.zzcr) {
                    zzbxm.zzJ(4, zzJ);
                }
            }
            if (this.zzcs != null) {
                zzbxm.zza(5, this.zzcs.longValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzi */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzco = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 16:
                        this.zzcp = Integer.valueOf(zzbxl.zzaes());
                        continue;
                    case 24:
                        this.zzcq = Boolean.valueOf(zzbxl.zzaeu());
                        continue;
                    case 32:
                        int zzb = zzbxw.zzb(zzbxl, 32);
                        int length = this.zzcr == null ? 0 : this.zzcr.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcr, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zzbxl.zzaes();
                            zzbxl.zzaeo();
                            length++;
                        }
                        iArr[length] = zzbxl.zzaes();
                        this.zzcr = iArr;
                        continue;
                    case 34:
                        int zzra = zzbxl.zzra(zzbxl.zzaex());
                        int position = zzbxl.getPosition();
                        int i = 0;
                        while (zzbxl.zzaeC() > 0) {
                            zzbxl.zzaes();
                            i++;
                        }
                        zzbxl.zzrc(position);
                        int length2 = this.zzcr == null ? 0 : this.zzcr.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcr, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zzbxl.zzaes();
                            length2++;
                        }
                        this.zzcr = iArr2;
                        zzbxl.zzrb(zzra);
                        continue;
                    case 40:
                        this.zzcs = Long.valueOf(zzbxl.zzaeq());
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
            if (this.zzco != null) {
                zzu += zzbxm.zzf(1, this.zzco.longValue());
            }
            if (this.zzcp != null) {
                zzu += zzbxm.zzL(2, this.zzcp.intValue());
            }
            if (this.zzcq != null) {
                zzu += zzbxm.zzh(3, this.zzcq.booleanValue());
            }
            if (this.zzcr != null && this.zzcr.length > 0) {
                int i = 0;
                for (int zzrg : this.zzcr) {
                    i += zzbxm.zzrg(zzrg);
                }
                zzu = zzu + i + (this.zzcr.length * 1);
            }
            return this.zzcs != null ? zzu + zzbxm.zze(5, this.zzcs.longValue()) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        public byte[] zzct;
        public byte[] zzcu;

        public zzc() {
            this.zzct = null;
            this.zzcu = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzct != null) {
                zzbxm.zzb(1, this.zzct);
            }
            if (this.zzcu != null) {
                zzbxm.zzb(2, this.zzcu);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzj */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.zzct = zzbxl.readBytes();
                        continue;
                    case 18:
                        this.zzcu = zzbxl.readBytes();
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
            if (this.zzct != null) {
                zzu += zzbxm.zzc(1, this.zzct);
            }
            return this.zzcu != null ? zzu + zzbxm.zzc(2, this.zzcu) : zzu;
        }
    }

    public static final class zzd extends zzbxn<zzd> {
        public byte[] data;
        public byte[] zzcv;
        public byte[] zzcw;
        public byte[] zzcx;

        public zzd() {
            this.data = null;
            this.zzcv = null;
            this.zzcw = null;
            this.zzcx = null;
            this.zzcuR = -1;
        }

        public static zzd zze(byte[] bArr) throws zzbxs {
            return (zzd) zzbxt.zza(new zzd(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.data != null) {
                zzbxm.zzb(1, this.data);
            }
            if (this.zzcv != null) {
                zzbxm.zzb(2, this.zzcv);
            }
            if (this.zzcw != null) {
                zzbxm.zzb(3, this.zzcw);
            }
            if (this.zzcx != null) {
                zzbxm.zzb(4, this.zzcx);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzk */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        this.data = zzbxl.readBytes();
                        continue;
                    case 18:
                        this.zzcv = zzbxl.readBytes();
                        continue;
                    case 26:
                        this.zzcw = zzbxl.readBytes();
                        continue;
                    case 34:
                        this.zzcx = zzbxl.readBytes();
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
            if (this.data != null) {
                zzu += zzbxm.zzc(1, this.data);
            }
            if (this.zzcv != null) {
                zzu += zzbxm.zzc(2, this.zzcv);
            }
            if (this.zzcw != null) {
                zzu += zzbxm.zzc(3, this.zzcw);
            }
            return this.zzcx != null ? zzu + zzbxm.zzc(4, this.zzcx) : zzu;
        }
    }

    public static final class zze extends zzbxn<zze> {
        public Long zzco;
        public String zzcy;
        public byte[] zzcz;

        public zze() {
            this.zzco = null;
            this.zzcy = null;
            this.zzcz = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzco != null) {
                zzbxm.zzb(1, this.zzco.longValue());
            }
            if (this.zzcy != null) {
                zzbxm.zzq(3, this.zzcy);
            }
            if (this.zzcz != null) {
                zzbxm.zzb(4, this.zzcz);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzl */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 8:
                        this.zzco = Long.valueOf(zzbxl.zzaer());
                        continue;
                    case 26:
                        this.zzcy = zzbxl.readString();
                        continue;
                    case 34:
                        this.zzcz = zzbxl.readBytes();
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
            if (this.zzco != null) {
                zzu += zzbxm.zzf(1, this.zzco.longValue());
            }
            if (this.zzcy != null) {
                zzu += zzbxm.zzr(3, this.zzcy);
            }
            return this.zzcz != null ? zzu + zzbxm.zzc(4, this.zzcz) : zzu;
        }
    }

    public static final class zzf extends zzbxn<zzf> {
        public byte[][] zzcA;
        public Integer zzcB;
        public Integer zzcC;
        public byte[] zzcv;

        public zzf() {
            this.zzcA = zzbxw.zzcvc;
            this.zzcv = null;
            this.zzcB = null;
            this.zzcC = null;
            this.zzcuR = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcA != null && this.zzcA.length > 0) {
                for (byte[] bArr : this.zzcA) {
                    if (bArr != null) {
                        zzbxm.zzb(1, bArr);
                    }
                }
            }
            if (this.zzcv != null) {
                zzbxm.zzb(2, this.zzcv);
            }
            if (this.zzcB != null) {
                zzbxm.zzJ(3, this.zzcB.intValue());
            }
            if (this.zzcC != null) {
                zzbxm.zzJ(4, this.zzcC.intValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzm */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaeo = zzbxl.zzaeo();
                switch (zzaeo) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbxw.zzb(zzbxl, 10);
                        int length = this.zzcA == null ? 0 : this.zzcA.length;
                        byte[][] bArr = new byte[(zzb + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzcA, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zzbxl.readBytes();
                            zzbxl.zzaeo();
                            length++;
                        }
                        bArr[length] = zzbxl.readBytes();
                        this.zzcA = bArr;
                        continue;
                    case 18:
                        this.zzcv = zzbxl.readBytes();
                        continue;
                    case 24:
                        int zzaes = zzbxl.zzaes();
                        switch (zzaes) {
                            case 0:
                            case 1:
                                this.zzcB = Integer.valueOf(zzaes);
                                break;
                            default:
                                continue;
                        }
                    case 32:
                        int zzaes2 = zzbxl.zzaes();
                        switch (zzaes2) {
                            case 0:
                            case 1:
                                this.zzcC = Integer.valueOf(zzaes2);
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
            int i;
            int zzu = super.zzu();
            if (this.zzcA == null || this.zzcA.length <= 0) {
                i = zzu;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (byte[] bArr : this.zzcA) {
                    if (bArr != null) {
                        i3++;
                        i2 += zzbxm.zzai(bArr);
                    }
                }
                i = zzu + i2 + (i3 * 1);
            }
            if (this.zzcv != null) {
                i += zzbxm.zzc(2, this.zzcv);
            }
            if (this.zzcB != null) {
                i += zzbxm.zzL(3, this.zzcB.intValue());
            }
            return this.zzcC != null ? i + zzbxm.zzL(4, this.zzcC.intValue()) : i;
        }
    }
}
