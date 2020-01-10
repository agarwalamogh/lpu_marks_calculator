package com.akhil.lovely.lpu.infinitum.verto;

import android.graphics.Color;
import android.os.Bundle;
import android.support.p000v4.media.TransportMediator;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

public class activity_practical_c extends AppCompatActivity {
    /* access modifiers changed from: private */
    public float att = 0.0f;
    /* access modifiers changed from: private */
    public float attmarks = 0.0f;
    OnClickListener cook = new OnClickListener() {
        public void onClick(View v) {
            activity_practical_c.this.pc_pr1.setError(null);
            activity_practical_c.this.pc_pr2.setError(null);
            activity_practical_c.this.pc_pr3.setError(null);
            activity_practical_c.this.pc_attendance.setError(null);
            activity_practical_c.this.status = true;
            activity_practical_c.this.status = validate.valPractical(activity_practical_c.this.status, activity_practical_c.this.pc_pr1, activity_practical_c.this.pc_pr2, activity_practical_c.this.pc_pr3).booleanValue();
            activity_practical_c.this.status = validate.valAttendance(activity_practical_c.this.status, activity_practical_c.this.pc_attendance).booleanValue();
            if (activity_practical_c.this.status) {
                activity_practical_c.this.f31p1 = Float.parseFloat(activity_practical_c.this.pc_pr1.getText().toString());
                activity_practical_c.this.f32p2 = Float.parseFloat(activity_practical_c.this.pc_pr2.getText().toString());
                activity_practical_c.this.f33p3 = Float.parseFloat(activity_practical_c.this.pc_pr3.getText().toString());
                activity_practical_c.this.att = Float.parseFloat(activity_practical_c.this.pc_attendance.getText().toString());
                activity_practical_c.this.f30p = activity_practical_c.this.f31p1 + activity_practical_c.this.f32p2 + activity_practical_c.this.f33p3;
                activity_practical_c.this.attmarks = (float) cooker.attendance(activity_practical_c.this.att);
                activity_practical_c.this.target = (int) cooker.twoParams(activity_practical_c.this.f30p, 150.0f, 40.0f, activity_practical_c.this.attmarks, 100.0f, 55.0f);
                cooker.reportSet(activity_practical_c.this.target, 100, activity_practical_c.this.pc_tt_target, activity_practical_c.this.pc_tt_targetMax, activity_practical_c.this.pc_tt_reportFooter);
                activity_practical_c.this.pc_report.setVisibility(0);
                activity_practical_c.this.pc_cook.setBackgroundColor(-7829368);
                activity_practical_c.this.pc_cook.setText("Reset");
                activity_practical_c.this.pc_cook.setOnClickListener(activity_practical_c.this.reset);
                activity_practical_c.this.pc_pr2.setEnabled(false);
                activity_practical_c.this.pc_pr3.setEnabled(false);
                activity_practical_c.this.pc_attendance.setEnabled(false);
                activity_practical_c.this.pc_pr1.setEnabled(false);
                activity_practical_c.this.pc_report.requestFocus();
                activity_practical_c.this.pc_scroll.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
            Bundle bundle = new Bundle();
            bundle.putString(Param.ITEM_NAME, "Limited Practical");
            FirebaseAnalytics.getInstance(activity_practical_c.this).logEvent(Event.SELECT_CONTENT, bundle);
        }
    };
    private AdView mAdView;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public float f30p = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p1 */
    public float f31p1 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p2 */
    public float f32p2 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p3 */
    public float f33p3 = 0.0f;
    /* access modifiers changed from: private */
    public EditText pc_attendance;
    /* access modifiers changed from: private */
    public Button pc_cook;
    /* access modifiers changed from: private */
    public EditText pc_pr1;
    /* access modifiers changed from: private */
    public EditText pc_pr2;
    /* access modifiers changed from: private */
    public EditText pc_pr3;
    /* access modifiers changed from: private */
    public RelativeLayout pc_report;
    /* access modifiers changed from: private */
    public ScrollView pc_scroll;
    /* access modifiers changed from: private */
    public TextView pc_tt_reportFooter;
    /* access modifiers changed from: private */
    public TextView pc_tt_target;
    /* access modifiers changed from: private */
    public TextView pc_tt_targetMax;
    OnClickListener reset = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_practical_c.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_practical_c.this.getCurrentFocus().getWindowToken(), 2);
            activity_practical_c.this.pc_pr2.setText("");
            activity_practical_c.this.pc_pr3.setText("");
            activity_practical_c.this.pc_attendance.setText("");
            activity_practical_c.this.pc_pr1.setText("");
            activity_practical_c.this.pc_cook.setOnClickListener(activity_practical_c.this.cook);
            activity_practical_c.this.pc_report.setVisibility(4);
            activity_practical_c.this.pc_cook.setOnClickListener(activity_practical_c.this.cook);
            activity_practical_c.this.pc_cook.setText("Calculate Target");
            activity_practical_c.this.pc_cook.setBackgroundColor(Color.rgb(150, 39, 176));
            activity_practical_c.this.pc_report.setVisibility(4);
            activity_practical_c.this.pc_pr2.setEnabled(true);
            activity_practical_c.this.pc_pr3.setEnabled(true);
            activity_practical_c.this.pc_attendance.setEnabled(true);
            activity_practical_c.this.pc_pr1.setEnabled(true);
            activity_practical_c.this.pc_pr1.requestFocus();
            activity_practical_c.this.pc_scroll.fullScroll(33);
        }
    };
    /* access modifiers changed from: private */
    public boolean status = true;
    /* access modifiers changed from: private */
    public int target = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(C0373R.anim.fadein, C0373R.anim.fadeout);
        setContentView((int) C0373R.layout.activity_practical_c);
        setSupportActionBar((Toolbar) findViewById(C0373R.C0375id.toolbar));
        this.mAdView = (AdView) findViewById(C0373R.C0375id.pc_adview);
        this.mAdView.loadAd(new Builder().build());
        this.pc_pr1 = (EditText) findViewById(C0373R.C0375id.pc_practical1);
        this.pc_pr2 = (EditText) findViewById(C0373R.C0375id.pc_practical2);
        this.pc_pr3 = (EditText) findViewById(C0373R.C0375id.pc_practical3);
        this.pc_attendance = (EditText) findViewById(C0373R.C0375id.pc_attendance);
        this.pc_report = (RelativeLayout) findViewById(C0373R.C0375id.pc_report);
        this.pc_cook = (Button) findViewById(C0373R.C0375id.pc_cook);
        this.pc_tt_target = (TextView) findViewById(C0373R.C0375id.pc_tt_target);
        this.pc_tt_targetMax = (TextView) findViewById(C0373R.C0375id.pc_tt_targetMax);
        this.pc_tt_reportFooter = (TextView) findViewById(C0373R.C0375id.pc_tt_reportFooter);
        this.pc_scroll = (ScrollView) findViewById(C0373R.C0375id.pc_scroll);
        this.pc_cook.setOnClickListener(this.cook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
