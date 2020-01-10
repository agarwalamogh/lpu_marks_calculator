package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze {
    private static final Object zztX = new Object();
    private final zzh zzclv;

    zze(zzh zzh) {
        this.zzclv = zzh;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String zzabS() {
        String str = null;
        synchronized (zztX) {
            String string = this.zzclv.zzabX().getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public void zzjt(String str) {
        synchronized (zztX) {
            String string = this.zzclv.zzabX().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            this.zzclv.zzabX().edit().putString("topic_operaion_queue", new StringBuilder(String.valueOf(string).length() + String.valueOf(valueOf).length() + String.valueOf(str).length()).append(string).append(valueOf).append(str).toString()).apply();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzjx(String str) {
        boolean z;
        synchronized (zztX) {
            String string = this.zzclv.zzabX().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                String valueOf3 = String.valueOf(",");
                String valueOf4 = String.valueOf(str);
                this.zzclv.zzabX().edit().putString("topic_operaion_queue", string.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
