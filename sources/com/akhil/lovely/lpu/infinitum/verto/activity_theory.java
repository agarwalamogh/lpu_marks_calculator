package com.akhil.lovely.lpu.infinitum.verto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.p000v4.media.TransportMediator;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.List;

public class activity_theory extends AppCompatActivity {
    /* access modifiers changed from: private */
    public float att = 0.0f;
    /* access modifiers changed from: private */
    public float attmarks = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: ca */
    public float f52ca = 0.0f;
    /* access modifiers changed from: private */
    public float ca1 = 0.0f;
    /* access modifiers changed from: private */
    public float ca2 = 0.0f;
    OnClickListener cook = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_theory.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_theory.this.getCurrentFocus().getWindowToken(), 2);
            activity_theory.this.t_ca1.setError(null);
            activity_theory.this.t_ca2.setError(null);
            activity_theory.this.t_midTerm.setError(null);
            activity_theory.this.t_attendance.setError(null);
            activity_theory.this.status = true;
            activity_theory.this.status = validate.valCA(activity_theory.this.status, activity_theory.this.t_ca1, activity_theory.this.t_ca2).booleanValue();
            activity_theory.this.status = validate.valMidTerm(activity_theory.this.status, activity_theory.this.t_midTerm).booleanValue();
            activity_theory.this.status = validate.valAttendance(activity_theory.this.status, activity_theory.this.t_attendance).booleanValue();
            if (activity_theory.this.status) {
                activity_theory.this.ca1 = Float.parseFloat(activity_theory.this.t_ca1.getText().toString());
                activity_theory.this.ca2 = Float.parseFloat(activity_theory.this.t_ca2.getText().toString());
                activity_theory.this.mid = Float.parseFloat(activity_theory.this.t_midTerm.getText().toString());
                activity_theory.this.att = Float.parseFloat(activity_theory.this.t_attendance.getText().toString());
                activity_theory.this.f52ca = activity_theory.this.ca1 + activity_theory.this.ca2;
                activity_theory.this.attmarks = (float) cooker.attendance(activity_theory.this.att);
                if (activity_theory.this.exam.matches("End Term")) {
                    activity_theory.this.target = (int) cooker.fourParams(activity_theory.this.f52ca, 60.0f, 25.0f, activity_theory.this.mid, 40.0f, 20.0f, activity_theory.this.attmarks, 70.0f, 50.0f);
                    cooker.reportSet(activity_theory.this.target, 70, activity_theory.this.t_tt_target, activity_theory.this.t_tt_targetMax, activity_theory.this.t_tt_reportFooter);
                } else if (activity_theory.this.exam.matches("Reappear - 70M")) {
                    activity_theory.this.target = (int) cooker.fourParams(activity_theory.this.f52ca, 60.0f, 20.0f, activity_theory.this.mid, 40.0f, 0.0f, activity_theory.this.attmarks, 70.0f, 75.0f);
                    cooker.reportSet(activity_theory.this.target, 70, activity_theory.this.t_tt_target, activity_theory.this.t_tt_targetMax, activity_theory.this.t_tt_reportFooter);
                } else if (activity_theory.this.exam.matches("Reappear - 100M")) {
                    activity_theory.this.target = (int) cooker.fourParams(activity_theory.this.f52ca, 60.0f, 20.0f, activity_theory.this.mid, 40.0f, 0.0f, activity_theory.this.attmarks, 100.0f, 75.0f);
                    cooker.reportSet(activity_theory.this.target, 100, activity_theory.this.t_tt_target, activity_theory.this.t_tt_targetMax, activity_theory.this.t_tt_reportFooter);
                }
                activity_theory.this.t_report.setVisibility(0);
                activity_theory.this.t_cook.setBackgroundColor(-7829368);
                activity_theory.this.t_cook.setText("Reset");
                activity_theory.this.t_cook.setOnClickListener(activity_theory.this.reset);
                activity_theory.this.t_ca1.setEnabled(false);
                activity_theory.this.t_ca2.setEnabled(false);
                activity_theory.this.t_midTerm.setEnabled(false);
                activity_theory.this.t_attendance.setEnabled(false);
                activity_theory.this.t_examSpinner.setEnabled(false);
                activity_theory.this.t_report.requestFocus();
                activity_theory.this.t_scroll.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
            Bundle bundle = new Bundle();
            bundle.putString(Param.ITEM_NAME, "Theory");
            FirebaseAnalytics.getInstance(activity_theory.this).logEvent(Event.SELECT_CONTENT, bundle);
        }
    };
    /* access modifiers changed from: private */
    public String exam = "End Term";
    OnItemSelectedListener examListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            activity_theory.this.exam = activity_theory.this.t_examSpinner.getSelectedItem().toString();
            if (activity_theory.this.exam.matches("Reappear - 70M") || activity_theory.this.exam.matches("Reappear - 100M")) {
                activity_theory.this.t_midTerm.setVisibility(8);
                activity_theory.this.t_tt_midTerm.setVisibility(8);
                return;
            }
            activity_theory.this.t_midTerm.setVisibility(0);
            activity_theory.this.t_tt_midTerm.setVisibility(0);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };
    private AdView mAdView;
    /* access modifiers changed from: private */
    public float mid = 0.0f;
    OnClickListener reset = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_theory.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_theory.this.getCurrentFocus().getWindowToken(), 2);
            activity_theory.this.t_ca1.setText("");
            activity_theory.this.t_ca2.setText("");
            activity_theory.this.t_midTerm.setText("");
            activity_theory.this.t_attendance.setText("");
            activity_theory.this.t_cook.setOnClickListener(activity_theory.this.cook);
            activity_theory.this.t_cook.setText("Calculate Target");
            activity_theory.this.t_cook.setBackgroundColor(Color.rgb(232, 30, 99));
            activity_theory.this.t_report.setVisibility(4);
            activity_theory.this.t_ca1.setEnabled(true);
            activity_theory.this.t_ca2.setEnabled(true);
            activity_theory.this.t_midTerm.setEnabled(true);
            activity_theory.this.t_attendance.setEnabled(true);
            activity_theory.this.t_examSpinner.setEnabled(true);
            activity_theory.this.t_examSpinner.setSelection(0);
            activity_theory.this.exam = "End Term";
            activity_theory.this.t_scroll.fullScroll(33);
            activity_theory.this.t_ca1.requestFocus();
        }
    };
    /* access modifiers changed from: private */
    public boolean status = true;
    /* access modifiers changed from: private */
    public EditText t_attendance;
    /* access modifiers changed from: private */
    public EditText t_ca1;
    /* access modifiers changed from: private */
    public EditText t_ca2;
    /* access modifiers changed from: private */
    public Button t_cook;
    private Button t_cse101;
    /* access modifiers changed from: private */
    public Spinner t_examSpinner;
    private Button t_mec103;
    /* access modifiers changed from: private */
    public EditText t_midTerm;
    /* access modifiers changed from: private */
    public RelativeLayout t_report;
    /* access modifiers changed from: private */
    public ScrollView t_scroll;
    /* access modifiers changed from: private */
    public TextView t_tt_midTerm;
    /* access modifiers changed from: private */
    public TextView t_tt_reportFooter;
    /* access modifiers changed from: private */
    public TextView t_tt_target;
    /* access modifiers changed from: private */
    public TextView t_tt_targetMax;
    /* access modifiers changed from: private */
    public int target = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(C0373R.anim.fadein, C0373R.anim.fadeout);
        setContentView((int) C0373R.layout.activity_theory);
        setSupportActionBar((Toolbar) findViewById(C0373R.C0375id.toolbar));
        this.mAdView = (AdView) findViewById(C0373R.C0375id.t_adview);
        this.mAdView.loadAd(new Builder().build());
        this.t_ca1 = (EditText) findViewById(C0373R.C0375id.t_ca1);
        this.t_ca2 = (EditText) findViewById(C0373R.C0375id.t_ca2);
        this.t_midTerm = (EditText) findViewById(C0373R.C0375id.t_midTerm);
        this.t_tt_midTerm = (TextView) findViewById(C0373R.C0375id.t_tt_midTerm);
        this.t_attendance = (EditText) findViewById(C0373R.C0375id.t_attendance);
        this.t_report = (RelativeLayout) findViewById(C0373R.C0375id.t_report);
        this.t_cook = (Button) findViewById(C0373R.C0375id.t_cook);
        this.t_tt_target = (TextView) findViewById(C0373R.C0375id.t_tt_target);
        this.t_tt_targetMax = (TextView) findViewById(C0373R.C0375id.t_tt_targetMax);
        this.t_tt_reportFooter = (TextView) findViewById(C0373R.C0375id.t_tt_reportFooter);
        this.t_cse101 = (Button) findViewById(C0373R.C0375id.t_cse101);
        this.t_mec103 = (Button) findViewById(C0373R.C0375id.t_mec103);
        this.t_examSpinner = (Spinner) findViewById(C0373R.C0375id.t_examSpinner);
        this.t_scroll = (ScrollView) findViewById(C0373R.C0375id.t_scroll);
        this.t_cook.setOnClickListener(this.cook);
        this.t_cse101.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity_theory.this, activity_special.class);
                intent.putExtra("carry", 1);
                activity_theory.this.startActivity(intent);
            }
        });
        this.t_mec103.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity_theory.this, activity_practical_n.class);
                intent.putExtra("carry", 1);
                activity_theory.this.startActivity(intent);
            }
        });
        List<String> types = new ArrayList<>();
        types.add("End Term");
        types.add("Reappear - 70M");
        types.add("Reappear - 100M");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, 17367048, types);
        dataAdapter.setDropDownViewResource(17367049);
        this.t_examSpinner.setAdapter(dataAdapter);
        this.t_examSpinner.setOnItemSelectedListener(this.examListener);
        this.t_examSpinner.requestFocus();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
