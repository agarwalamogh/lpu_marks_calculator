package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbxl {
    private final byte[] buffer;
    private int zzcuA;
    private int zzcuB;
    private int zzcuC;
    private int zzcuD = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int zzcuE;
    private int zzcuF = 64;
    private int zzcuG = 67108864;
    private int zzcuy;
    private int zzcuz;

    private zzbxl(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzcuy = i;
        this.zzcuz = i + i2;
        this.zzcuB = i;
    }

    public static long zzaZ(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void zzaeB() {
        this.zzcuz += this.zzcuA;
        int i = this.zzcuz;
        if (i > this.zzcuD) {
            this.zzcuA = i - this.zzcuD;
            this.zzcuz -= this.zzcuA;
            return;
        }
        this.zzcuA = 0;
    }

    public static zzbxl zzaf(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzbxl zzb(byte[] bArr, int i, int i2) {
        return new zzbxl(bArr, i, i2);
    }

    public static int zzqZ(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public int getPosition() {
        return this.zzcuB - this.zzcuy;
    }

    public byte[] readBytes() throws IOException {
        int zzaex = zzaex();
        if (zzaex < 0) {
            throw zzbxs.zzaeM();
        } else if (zzaex == 0) {
            return zzbxw.zzcvd;
        } else {
            if (zzaex > this.zzcuz - this.zzcuB) {
                throw zzbxs.zzaeL();
            }
            byte[] bArr = new byte[zzaex];
            System.arraycopy(this.buffer, this.zzcuB, bArr, 0, zzaex);
            this.zzcuB = zzaex + this.zzcuB;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(zzaeA());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(zzaez());
    }

    public String readString() throws IOException {
        int zzaex = zzaex();
        if (zzaex < 0) {
            throw zzbxs.zzaeM();
        } else if (zzaex > this.zzcuz - this.zzcuB) {
            throw zzbxs.zzaeL();
        } else {
            String str = new String(this.buffer, this.zzcuB, zzaex, zzbxr.UTF_8);
            this.zzcuB = zzaex + this.zzcuB;
            return str;
        }
    }

    public byte[] zzI(int i, int i2) {
        if (i2 == 0) {
            return zzbxw.zzcvd;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzcuy + i, bArr, 0, i2);
        return bArr;
    }

    public void zza(zzbxt zzbxt) throws IOException {
        int zzaex = zzaex();
        if (this.zzcuE >= this.zzcuF) {
            throw zzbxs.zzaeR();
        }
        int zzra = zzra(zzaex);
        this.zzcuE++;
        zzbxt.zzb(this);
        zzqX(0);
        this.zzcuE--;
        zzrb(zzra);
    }

    public void zza(zzbxt zzbxt, int i) throws IOException {
        if (this.zzcuE >= this.zzcuF) {
            throw zzbxs.zzaeR();
        }
        this.zzcuE++;
        zzbxt.zzb(this);
        zzqX(zzbxw.zzO(i, 4));
        this.zzcuE--;
    }

    public long zzaeA() throws IOException {
        byte zzaeE = zzaeE();
        byte zzaeE2 = zzaeE();
        return ((((long) zzaeE2) & 255) << 8) | (((long) zzaeE) & 255) | ((((long) zzaeE()) & 255) << 16) | ((((long) zzaeE()) & 255) << 24) | ((((long) zzaeE()) & 255) << 32) | ((((long) zzaeE()) & 255) << 40) | ((((long) zzaeE()) & 255) << 48) | ((((long) zzaeE()) & 255) << 56);
    }

    public int zzaeC() {
        if (this.zzcuD == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzcuD - this.zzcuB;
    }

    public boolean zzaeD() {
        return this.zzcuB == this.zzcuz;
    }

    public byte zzaeE() throws IOException {
        if (this.zzcuB == this.zzcuz) {
            throw zzbxs.zzaeL();
        }
        byte[] bArr = this.buffer;
        int i = this.zzcuB;
        this.zzcuB = i + 1;
        return bArr[i];
    }

    public int zzaeo() throws IOException {
        if (zzaeD()) {
            this.zzcuC = 0;
            return 0;
        }
        this.zzcuC = zzaex();
        if (this.zzcuC != 0) {
            return this.zzcuC;
        }
        throw zzbxs.zzaeO();
    }

    public void zzaep() throws IOException {
        int zzaeo;
        do {
            zzaeo = zzaeo();
            if (zzaeo == 0) {
                return;
            }
        } while (zzqY(zzaeo));
    }

    public long zzaeq() throws IOException {
        return zzaey();
    }

    public long zzaer() throws IOException {
        return zzaey();
    }

    public int zzaes() throws IOException {
        return zzaex();
    }

    public long zzaet() throws IOException {
        return zzaeA();
    }

    public boolean zzaeu() throws IOException {
        return zzaex() != 0;
    }

    public int zzaev() throws IOException {
        return zzqZ(zzaex());
    }

    public long zzaew() throws IOException {
        return zzaZ(zzaey());
    }

    public int zzaex() throws IOException {
        byte zzaeE = zzaeE();
        if (zzaeE >= 0) {
            return zzaeE;
        }
        byte b = zzaeE & Byte.MAX_VALUE;
        byte zzaeE2 = zzaeE();
        if (zzaeE2 >= 0) {
            return b | (zzaeE2 << 7);
        }
        byte b2 = b | ((zzaeE2 & Byte.MAX_VALUE) << 7);
        byte zzaeE3 = zzaeE();
        if (zzaeE3 >= 0) {
            return b2 | (zzaeE3 << 14);
        }
        byte b3 = b2 | ((zzaeE3 & Byte.MAX_VALUE) << 14);
        byte zzaeE4 = zzaeE();
        if (zzaeE4 >= 0) {
            return b3 | (zzaeE4 << 21);
        }
        byte b4 = b3 | ((zzaeE4 & Byte.MAX_VALUE) << 21);
        byte zzaeE5 = zzaeE();
        byte b5 = b4 | (zzaeE5 << 28);
        if (zzaeE5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzaeE() >= 0) {
                return b5;
            }
        }
        throw zzbxs.zzaeN();
    }

    public long zzaey() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzaeE = zzaeE();
            j |= ((long) (zzaeE & Byte.MAX_VALUE)) << i;
            if ((zzaeE & 128) == 0) {
                return j;
            }
        }
        throw zzbxs.zzaeN();
    }

    public int zzaez() throws IOException {
        return (zzaeE() & 255) | ((zzaeE() & 255) << 8) | ((zzaeE() & 255) << 16) | ((zzaeE() & 255) << 24);
    }

    public void zzqX(int i) throws zzbxs {
        if (this.zzcuC != i) {
            throw zzbxs.zzaeP();
        }
    }

    public boolean zzqY(int i) throws IOException {
        switch (zzbxw.zzrr(i)) {
            case 0:
                zzaes();
                return true;
            case 1:
                zzaeA();
                return true;
            case 2:
                zzrd(zzaex());
                return true;
            case 3:
                zzaep();
                zzqX(zzbxw.zzO(zzbxw.zzrs(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzaez();
                return true;
            default:
                throw zzbxs.zzaeQ();
        }
    }

    public int zzra(int i) throws zzbxs {
        if (i < 0) {
            throw zzbxs.zzaeM();
        }
        int i2 = this.zzcuB + i;
        int i3 = this.zzcuD;
        if (i2 > i3) {
            throw zzbxs.zzaeL();
        }
        this.zzcuD = i2;
        zzaeB();
        return i3;
    }

    public void zzrb(int i) {
        this.zzcuD = i;
        zzaeB();
    }

    public void zzrc(int i) {
        if (i > this.zzcuB - this.zzcuy) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzcuB - this.zzcuy));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzcuB = this.zzcuy + i;
        }
    }

    public void zzrd(int i) throws IOException {
        if (i < 0) {
            throw zzbxs.zzaeM();
        } else if (this.zzcuB + i > this.zzcuD) {
            zzrd(this.zzcuD - this.zzcuB);
            throw zzbxs.zzaeL();
        } else if (i <= this.zzcuz - this.zzcuB) {
            this.zzcuB += i;
        } else {
            throw zzbxs.zzaeL();
        }
    }
}
