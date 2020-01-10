package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

@TargetApi(14)
@RequiresApi(14)
/* renamed from: android.support.v4.app.ShareCompatICS */
class ShareCompatICS {
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    ShareCompatICS() {
    }

    public static void configureMenuItem(MenuItem item, Activity callingActivity, Intent intent) {
        ShareActionProvider provider;
        ActionProvider itemProvider = item.getActionProvider();
        if (!(itemProvider instanceof ShareActionProvider)) {
            provider = new ShareActionProvider(callingActivity);
        } else {
            provider = (ShareActionProvider) itemProvider;
        }
        provider.setShareHistoryFileName(HISTORY_FILENAME_PREFIX + callingActivity.getClass().getName());
        provider.setShareIntent(intent);
        item.setActionProvider(provider);
    }
}
