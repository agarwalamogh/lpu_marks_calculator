package com.kobakei.ratethisapp;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import java.util.Date;

public class RateThisApp {
    public static final boolean DEBUG = false;
    private static final String KEY_INSTALL_DATE = "rta_install_date";
    private static final String KEY_LAUNCH_TIMES = "rta_launch_times";
    private static final String KEY_OPT_OUT = "rta_opt_out";
    private static final String PREF_NAME = "RateThisApp";
    private static final String TAG = RateThisApp.class.getSimpleName();
    private static Date mInstallDate = new Date();
    private static int mLaunchTimes = 0;
    private static boolean mOptOut = false;
    private static Config sConfig = new Config();

    public static class Config {
        /* access modifiers changed from: private */
        public int mCriteriaInstallDays;
        /* access modifiers changed from: private */
        public int mCriteriaLaunchTimes;
        /* access modifiers changed from: private */
        public int mMessageId;
        /* access modifiers changed from: private */
        public int mTitleId;

        public Config() {
            this(7, 10);
        }

        public Config(int criteriaInstallDays, int criteriaLaunchTimes) {
            this.mTitleId = 0;
            this.mMessageId = 0;
            this.mCriteriaInstallDays = criteriaInstallDays;
            this.mCriteriaLaunchTimes = criteriaLaunchTimes;
        }

        public void setTitle(int stringId) {
            this.mTitleId = stringId;
        }

        public void setMessage(int stringId) {
            this.mMessageId = stringId;
        }
    }

    public static void init(Config config) {
        sConfig = config;
    }

    public static void onStart(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        Editor editor = pref.edit();
        if (pref.getLong(KEY_INSTALL_DATE, 0) == 0) {
            Date now = new Date();
            editor.putLong(KEY_INSTALL_DATE, now.getTime());
            log("First install: " + now.toString());
        }
        int launchTimes = pref.getInt(KEY_LAUNCH_TIMES, 0) + 1;
        editor.putInt(KEY_LAUNCH_TIMES, launchTimes);
        log("Launch times; " + launchTimes);
        editor.commit();
        mInstallDate = new Date(pref.getLong(KEY_INSTALL_DATE, 0));
        mLaunchTimes = pref.getInt(KEY_LAUNCH_TIMES, 0);
        mOptOut = pref.getBoolean(KEY_OPT_OUT, false);
        printStatus(context);
    }

    public static boolean showRateDialogIfNeeded(Context context) {
        if (!shouldShowRateDialog()) {
            return false;
        }
        showRateDialog(context);
        return true;
    }

    private static boolean shouldShowRateDialog() {
        if (mOptOut) {
            return false;
        }
        if (mLaunchTimes >= sConfig.mCriteriaLaunchTimes) {
            return true;
        }
        if (new Date().getTime() - mInstallDate.getTime() >= ((long) (sConfig.mCriteriaInstallDays * 24 * 60 * 60)) * 1000) {
            return true;
        }
        return false;
    }

    public static void showRateDialog(final Context context) {
        Builder builder = new Builder(context);
        int titleId = sConfig.mTitleId != 0 ? sConfig.mTitleId : C0927R.string.rta_dialog_title;
        int messageId = sConfig.mMessageId != 0 ? sConfig.mMessageId : C0927R.string.rta_dialog_message;
        builder.setTitle(titleId);
        builder.setMessage(messageId);
        builder.setPositiveButton(C0927R.string.rta_dialog_ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
                RateThisApp.setOptOut(context, true);
            }
        });
        builder.setNeutralButton(C0927R.string.rta_dialog_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                RateThisApp.clearSharedPreferences(context);
            }
        });
        builder.setNegativeButton(C0927R.string.rta_dialog_no, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                RateThisApp.setOptOut(context, true);
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                RateThisApp.clearSharedPreferences(context);
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public static void clearSharedPreferences(Context context) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.remove(KEY_INSTALL_DATE);
        editor.remove(KEY_LAUNCH_TIMES);
        editor.commit();
    }

    /* access modifiers changed from: private */
    public static void setOptOut(Context context, boolean optOut) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putBoolean(KEY_OPT_OUT, optOut);
        editor.commit();
    }

    private static void printStatus(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        log("*** RateThisApp Status ***");
        log("Install Date: " + new Date(pref.getLong(KEY_INSTALL_DATE, 0)));
        log("Launch Times: " + pref.getInt(KEY_LAUNCH_TIMES, 0));
        log("Opt out: " + pref.getBoolean(KEY_OPT_OUT, false));
    }

    private static void log(String message) {
    }
}
