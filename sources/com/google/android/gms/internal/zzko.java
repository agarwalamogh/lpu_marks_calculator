package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zza;
import java.util.Date;
import java.util.HashSet;

@zzme
public final class zzko {

    /* renamed from: com.google.android.gms.internal.zzko$1 */
    static /* synthetic */ class C07711 {
        static final /* synthetic */ int[] zzLM = new int[Gender.values().length];

        static {
            zzLN = new int[ErrorCode.values().length];
            try {
                zzLN[ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                zzLN[ErrorCode.INVALID_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                zzLN[ErrorCode.NETWORK_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                zzLN[ErrorCode.NO_FILL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                zzLM[Gender.FEMALE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                zzLM[Gender.MALE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                zzLM[Gender.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static Gender zzI(int i) {
        switch (i) {
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            default:
                return Gender.UNKNOWN;
        }
    }

    public static int zza(ErrorCode errorCode) {
        switch (errorCode) {
            case INVALID_REQUEST:
                return 1;
            case NETWORK_ERROR:
                return 2;
            case NO_FILL:
                return 3;
            default:
                return 0;
        }
    }

    public static AdSize zzc(zzeg zzeg) {
        AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        for (int i = 0; i < 6; i++) {
            if (adSizeArr[i].getWidth() == zzeg.width && adSizeArr[i].getHeight() == zzeg.height) {
                return adSizeArr[i];
            }
        }
        return new AdSize(zza.zza(zzeg.width, zzeg.height, zzeg.zzzy));
    }

    public static MediationAdRequest zzr(zzec zzec) {
        return new MediationAdRequest(new Date(zzec.zzyT), zzI(zzec.zzyU), zzec.zzyV != null ? new HashSet(zzec.zzyV) : null, zzec.zzyW, zzec.zzzb);
    }
}
