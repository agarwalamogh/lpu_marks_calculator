package android.support.p003v7.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.p000v4.content.res.ConfigurationHelper;
import android.support.p000v4.view.ViewConfigurationCompat;
import android.support.p003v7.appcompat.C0269R;
import android.view.ViewConfiguration;

@RestrictTo({Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.ActionBarPolicy */
public class ActionBarPolicy {
    private Context mContext;

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    private ActionBarPolicy(Context context) {
        this.mContext = context;
    }

    public int getMaxActionButtons() {
        Resources res = this.mContext.getResources();
        int widthDp = ConfigurationHelper.getScreenWidthDp(res);
        int heightDp = ConfigurationHelper.getScreenHeightDp(res);
        if (ConfigurationHelper.getSmallestScreenWidthDp(res) > 600 || widthDp > 600 || ((widthDp > 960 && heightDp > 720) || (widthDp > 720 && heightDp > 960))) {
            return 5;
        }
        if (widthDp >= 500 || ((widthDp > 640 && heightDp > 480) || (widthDp > 480 && heightDp > 640))) {
            return 4;
        }
        if (widthDp >= 360) {
            return 3;
        }
        return 2;
    }

    public boolean showsOverflowMenuButton() {
        if (VERSION.SDK_INT < 19 && ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext))) {
            return false;
        }
        return true;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(C0269R.bool.abc_action_bar_embed_tabs);
    }

    public int getTabContainerHeight() {
        TypedArray a = this.mContext.obtainStyledAttributes(null, C0269R.styleable.ActionBar, C0269R.attr.actionBarStyle, 0);
        int height = a.getLayoutDimension(C0269R.styleable.ActionBar_height, 0);
        Resources r = this.mContext.getResources();
        if (!hasEmbeddedTabs()) {
            height = Math.min(height, r.getDimensionPixelSize(C0269R.dimen.abc_action_bar_stacked_max_height));
        }
        a.recycle();
        return height;
    }

    public boolean enableHomeButtonByDefault() {
        return this.mContext.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(C0269R.dimen.abc_action_bar_stacked_tab_max_width);
    }
}
