package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class zzaty extends zzauh {

    @WorkerThread
    interface zza {
        void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map);
    }

    @WorkerThread
    private static class zzb implements Runnable {
        private final int zzJO;
        private final String zzRg;
        private final Throwable zzZa;
        private final zza zzbsP;
        private final byte[] zzbsQ;
        private final Map<String, List<String>> zzbsR;

        private zzb(String str, zza zza, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzac.zzw(zza);
            this.zzbsP = zza;
            this.zzJO = i;
            this.zzZa = th;
            this.zzbsQ = bArr;
            this.zzRg = str;
            this.zzbsR = map;
        }

        public void run() {
            this.zzbsP.zza(this.zzRg, this.zzJO, this.zzZa, this.zzbsQ, this.zzbsR);
        }
    }

    @WorkerThread
    private class zzc implements Runnable {
        private final URL zzIe;
        private final String zzRg;
        private final byte[] zzaIQ;
        private final zza zzbsS;
        private final Map<String, String> zzbsT;

        public zzc(String str, URL url, byte[] bArr, Map<String, String> map, zza zza) {
            zzac.zzdr(str);
            zzac.zzw(url);
            zzac.zzw(zza);
            this.zzIe = url;
            this.zzaIQ = bArr;
            this.zzbsS = zza;
            this.zzRg = str;
            this.zzbsT = map;
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x00e3 A[SYNTHETIC, Splitter:B:36:0x00e3] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00e8  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r13 = this;
                r4 = 0
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                r0.zzJX()
                r3 = 0
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x0124, all -> 0x00dc }
                java.net.URL r1 = r13.zzIe     // Catch:{ IOException -> 0x0124, all -> 0x00dc }
                java.net.HttpURLConnection r2 = r0.zzc(r1)     // Catch:{ IOException -> 0x0124, all -> 0x00dc }
                java.util.Map<java.lang.String, java.lang.String> r0 = r13.zzbsT     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                if (r0 == 0) goto L_0x005d
                java.util.Map<java.lang.String, java.lang.String> r0 = r13.zzbsT     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.util.Set r0 = r0.entrySet()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.util.Iterator r5 = r0.iterator()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
            L_0x001d:
                boolean r0 = r5.hasNext()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                if (r0 == 0) goto L_0x005d
                java.lang.Object r0 = r5.next()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.Object r1 = r0.getKey()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.Object r0 = r0.getValue()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r2.addRequestProperty(r1, r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                goto L_0x001d
            L_0x0039:
                r9 = move-exception
                r11 = r4
                r8 = r3
                r0 = r4
                r1 = r2
            L_0x003e:
                if (r0 == 0) goto L_0x0043
                r0.close()     // Catch:{ IOException -> 0x00c4 }
            L_0x0043:
                if (r1 == 0) goto L_0x0048
                r1.disconnect()
            L_0x0048:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r0 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r5 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r6 = r13.zzRg
                com.google.android.gms.internal.zzaty$zza r7 = r13.zzbsS
                r10 = r4
                r12 = r4
                r5.<init>(r6, r7, r8, r9, r10, r11)
                r0.zzm(r5)
            L_0x005c:
                return
            L_0x005d:
                byte[] r0 = r13.zzaIQ     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                if (r0 == 0) goto L_0x009d
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                com.google.android.gms.internal.zzaut r0 = r0.zzKh()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                byte[] r1 = r13.zzaIQ     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                byte[] r1 = r0.zzk(r1)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                com.google.android.gms.internal.zzatx r0 = r0.zzKl()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                com.google.android.gms.internal.zzatx$zza r0 = r0.zzMe()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.String r5 = "Uploading data. size"
                int r6 = r1.length     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r0.zzj(r5, r6)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r0 = 1
                r2.setDoOutput(r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.lang.String r0 = "Content-Encoding"
                java.lang.String r5 = "gzip"
                r2.addRequestProperty(r0, r5)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                int r0 = r1.length     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r2.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r2.connect()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.io.OutputStream r0 = r2.getOutputStream()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                r0.write(r1)     // Catch:{ IOException -> 0x012b, all -> 0x011c }
                r0.close()     // Catch:{ IOException -> 0x012b, all -> 0x011c }
            L_0x009d:
                int r3 = r2.getResponseCode()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                java.util.Map r6 = r2.getHeaderFields()     // Catch:{ IOException -> 0x0039, all -> 0x0117 }
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this     // Catch:{ IOException -> 0x0131, all -> 0x0120 }
                byte[] r5 = r0.zzc(r2)     // Catch:{ IOException -> 0x0131, all -> 0x0120 }
                if (r2 == 0) goto L_0x00b0
                r2.disconnect()
            L_0x00b0:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r8 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r0 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r1 = r13.zzRg
                com.google.android.gms.internal.zzaty$zza r2 = r13.zzbsS
                r7 = r4
                r0.<init>(r1, r2, r3, r4, r5, r6)
                r8.zzm(r0)
                goto L_0x005c
            L_0x00c4:
                r0 = move-exception
                com.google.android.gms.internal.zzaty r2 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzatx r2 = r2.zzKl()
                com.google.android.gms.internal.zzatx$zza r2 = r2.zzLY()
                java.lang.String r3 = "Error closing HTTP compressed POST connection output stream. appId"
                java.lang.String r5 = r13.zzRg
                java.lang.Object r5 = com.google.android.gms.internal.zzatx.zzfE(r5)
                r2.zze(r3, r5, r0)
                goto L_0x0043
            L_0x00dc:
                r0 = move-exception
                r8 = r0
                r6 = r4
                r2 = r4
                r0 = r4
            L_0x00e1:
                if (r0 == 0) goto L_0x00e6
                r0.close()     // Catch:{ IOException -> 0x0100 }
            L_0x00e6:
                if (r2 == 0) goto L_0x00eb
                r2.disconnect()
            L_0x00eb:
                com.google.android.gms.internal.zzaty r0 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzaud r9 = r0.zzKk()
                com.google.android.gms.internal.zzaty$zzb r0 = new com.google.android.gms.internal.zzaty$zzb
                java.lang.String r1 = r13.zzRg
                com.google.android.gms.internal.zzaty$zza r2 = r13.zzbsS
                r5 = r4
                r7 = r4
                r0.<init>(r1, r2, r3, r4, r5, r6)
                r9.zzm(r0)
                throw r8
            L_0x0100:
                r0 = move-exception
                com.google.android.gms.internal.zzaty r1 = com.google.android.gms.internal.zzaty.this
                com.google.android.gms.internal.zzatx r1 = r1.zzKl()
                com.google.android.gms.internal.zzatx$zza r1 = r1.zzLY()
                java.lang.String r5 = "Error closing HTTP compressed POST connection output stream. appId"
                java.lang.String r7 = r13.zzRg
                java.lang.Object r7 = com.google.android.gms.internal.zzatx.zzfE(r7)
                r1.zze(r5, r7, r0)
                goto L_0x00e6
            L_0x0117:
                r0 = move-exception
                r8 = r0
                r6 = r4
                r0 = r4
                goto L_0x00e1
            L_0x011c:
                r1 = move-exception
                r8 = r1
                r6 = r4
                goto L_0x00e1
            L_0x0120:
                r0 = move-exception
                r8 = r0
                r0 = r4
                goto L_0x00e1
            L_0x0124:
                r9 = move-exception
                r11 = r4
                r8 = r3
                r0 = r4
                r1 = r4
                goto L_0x003e
            L_0x012b:
                r9 = move-exception
                r11 = r4
                r8 = r3
                r1 = r2
                goto L_0x003e
            L_0x0131:
                r9 = move-exception
                r11 = r6
                r8 = r3
                r0 = r4
                r1 = r2
                goto L_0x003e
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaty.zzc.run():void");
        }
    }

    public zzaty(zzaue zzaue) {
        super(zzaue);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public byte[] zzc(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    @WorkerThread
    public void zza(String str, URL url, Map<String, String> map, zza zza2) {
        zzmR();
        zzob();
        zzac.zzw(url);
        zzac.zzw(zza2);
        zzKk().zzn(new zzc(str, url, null, map, zza2));
    }

    @WorkerThread
    public void zza(String str, URL url, byte[] bArr, Map<String, String> map, zza zza2) {
        zzmR();
        zzob();
        zzac.zzw(url);
        zzac.zzw(bArr);
        zzac.zzw(zza2);
        zzKk().zzn(new zzc(str, url, bArr, map, zza2));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain HTTP connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        zzKn().zzLd();
        httpURLConnection.setConnectTimeout(60000);
        zzKn().zzLe();
        httpURLConnection.setReadTimeout(61000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    public boolean zzqa() {
        NetworkInfo networkInfo;
        zzob();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
