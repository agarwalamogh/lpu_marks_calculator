package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.internal.zzhs.zza;

@zzme
public class zzhx extends zza {
    private final OnCustomTemplateAdLoadedListener zzHB;

    public zzhx(OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzHB = onCustomTemplateAdLoadedListener;
    }

    public void zza(zzhn zzhn) {
        this.zzHB.onCustomTemplateAdLoaded(new zzho(zzhn));
    }
}
