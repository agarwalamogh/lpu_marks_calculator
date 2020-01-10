package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.internal.zzhr.zza;

@zzme
public class zzhw extends zza {
    private final OnCustomClickListener zzHA;

    public zzhw(OnCustomClickListener onCustomClickListener) {
        this.zzHA = onCustomClickListener;
    }

    public void zza(zzhn zzhn, String str) {
        this.zzHA.onCustomClick(new zzho(zzhn), str);
    }
}
