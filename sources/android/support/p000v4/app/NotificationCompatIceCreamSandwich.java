package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

@TargetApi(14)
@RequiresApi(14)
/* renamed from: android.support.v4.app.NotificationCompatIceCreamSandwich */
class NotificationCompatIceCreamSandwich {

    /* renamed from: android.support.v4.app.NotificationCompatIceCreamSandwich$Builder */
    public static class Builder implements NotificationBuilderWithBuilderAccessor {

        /* renamed from: b */
        private android.app.Notification.Builder f9b;

        public Builder(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate) {
            this.f9b = new android.app.Notification.Builder(context).setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setProgress(progressMax, progress, progressIndeterminate);
        }

        public android.app.Notification.Builder getBuilder() {
            return this.f9b;
        }

        public Notification build() {
            return this.f9b.getNotification();
        }
    }

    NotificationCompatIceCreamSandwich() {
    }
}
