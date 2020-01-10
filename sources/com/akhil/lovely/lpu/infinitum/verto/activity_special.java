package com.akhil.lovely.lpu.infinitum.verto;

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

public class activity_special extends AppCompatActivity implements OnItemSelectedListener {
    /* access modifiers changed from: private */
    public float att = 0.0f;
    /* access modifiers changed from: private */
    public float attmarks = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: ca */
    public float f51ca = 0.0f;
    /* access modifiers changed from: private */
    public float ca1 = 0.0f;
    /* access modifiers changed from: private */
    public float ca2 = 0.0f;
    /* access modifiers changed from: private */
    public float ca3 = 0.0f;
    /* access modifiers changed from: private */
    public float ca4 = 0.0f;
    OnClickListener cook = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_special.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_special.this.getCurrentFocus().getWindowToken(), 2);
            activity_special.this.s_ca1.setError(null);
            activity_special.this.s_ca2.setError(null);
            activity_special.this.s_ca3.setError(null);
            activity_special.this.s_ca4.setError(null);
            activity_special.this.s_midTerm.setError(null);
            activity_special.this.s_attendance.setError(null);
            activity_special.this.status = true;
            activity_special.this.status = validate.valCA(activity_special.this.status, activity_special.this.s_ca1, activity_special.this.s_ca2, activity_special.this.s_ca3, activity_special.this.s_ca4).booleanValue();
            activity_special.this.status = validate.valMidTerm(activity_special.this.status, activity_special.this.s_midTerm).booleanValue();
            activity_special.this.status = validate.valAttendance(activity_special.this.status, activity_special.this.s_attendance).booleanValue();
            if (activity_special.this.status) {
                activity_special.this.ca1 = Float.parseFloat(activity_special.this.s_ca1.getText().toString());
                activity_special.this.ca2 = Float.parseFloat(activity_special.this.s_ca2.getText().toString());
                activity_special.this.ca3 = Float.parseFloat(activity_special.this.s_ca3.getText().toString());
                activity_special.this.ca4 = Float.parseFloat(activity_special.this.s_ca4.getText().toString());
                activity_special.this.mid = Float.parseFloat(activity_special.this.s_midTerm.getText().toString());
                activity_special.this.att = Float.parseFloat(activity_special.this.s_attendance.getText().toString());
                if (activity_special.this.course.matches("PES")) {
                    activity_special.this.f51ca = activity_special.this.ca1 + activity_special.this.ca2 + activity_special.this.ca3 + activity_special.this.ca4;
                    activity_special.this.attmarks = (float) cooker.attendanceSpecial(activity_special.this.att);
                    activity_special.this.target = (int) cooker.twoParams(activity_special.this.f51ca, 120.0f, 30.0f, activity_special.this.attmarks, 100.0f, 55.0f);
                    cooker.reportSet(activity_special.this.target, 100, activity_special.this.s_tt_target, activity_special.this.s_tt_targetMax, activity_special.this.s_tt_reportFooter);
                } else if (activity_special.this.course.matches("CSE101")) {
                    activity_special.this.f51ca = activity_special.this.ca1 + activity_special.this.ca2 + activity_special.this.ca3;
                    activity_special.this.attmarks = (float) cooker.attendance(activity_special.this.att);
                    if (activity_special.this.exam.matches("End Term")) {
                        activity_special.this.target = (int) cooker.fourParams(activity_special.this.f51ca, 90.0f, 25.0f, activity_special.this.mid, 50.0f, 20.0f, activity_special.this.attmarks, 70.0f, 50.0f);
                    } else if (activity_special.this.exam.matches("Reappear")) {
                        activity_special.this.target = (int) cooker.fourParams(activity_special.this.f51ca, 90.0f, 20.0f, activity_special.this.mid, 40.0f, 0.0f, activity_special.this.attmarks, 70.0f, 75.0f);
                    }
                    cooker.reportSet(activity_special.this.target, 70, activity_special.this.s_tt_target, activity_special.this.s_tt_targetMax, activity_special.this.s_tt_reportFooter);
                } else {
                    activity_special.this.f51ca = activity_special.this.ca1 + activity_special.this.ca2 + activity_special.this.ca3 + activity_special.this.ca4;
                    activity_special.this.attmarks = (float) cooker.attendanceSpecial(activity_special.this.att);
                    if (activity_special.this.exam.matches("End Term")) {
                        activity_special.this.target = (int) cooker.fourParams(activity_special.this.f51ca, 120.0f, 30.0f, activity_special.this.mid, 50.0f, 15.0f, activity_special.this.attmarks, 100.0f, 40.0f);
                    } else if (activity_special.this.exam.matches("Reappear")) {
                        activity_special.this.target = (int) cooker.fourParams(activity_special.this.f51ca, 120.0f, 30.0f, activity_special.this.mid, 40.0f, 0.0f, activity_special.this.attmarks, 100.0f, 55.0f);
                    }
                    cooker.reportSet(activity_special.this.target, 100, activity_special.this.s_tt_target, activity_special.this.s_tt_targetMax, activity_special.this.s_tt_reportFooter);
                }
                activity_special.this.s_report.setVisibility(0);
                activity_special.this.s_cook.setBackgroundColor(-7829368);
                activity_special.this.s_cook.setText("Reset");
                activity_special.this.s_cook.setOnClickListener(activity_special.this.reset);
                activity_special.this.s_ca2.setEnabled(false);
                activity_special.this.s_ca3.setEnabled(false);
                activity_special.this.s_ca4.setEnabled(false);
                activity_special.this.s_midTerm.setEnabled(false);
                activity_special.this.s_attendance.setEnabled(false);
                activity_special.this.s_ca1.setEnabled(false);
                activity_special.this.s_courseSpinner.setEnabled(false);
                activity_special.this.s_examSpinner.setEnabled(false);
                activity_special.this.s_report.requestFocus();
                activity_special.this.s_scroll.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
            Bundle bundle = new Bundle();
            bundle.putString(Param.ITEM_NAME, "Special");
            FirebaseAnalytics.getInstance(activity_special.this).logEvent(Event.SELECT_CONTENT, bundle);
        }
    };
    /* access modifiers changed from: private */
    public String course = "";
    OnItemSelectedListener courseListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            activity_special.this.course = activity_special.this.s_courseSpinner.getSelectedItem().toString();
            activity_special.this.s_examSpinner.setSelection(0);
            activity_special.this.s_ca1.setEnabled(true);
            activity_special.this.s_ca2.setEnabled(true);
            activity_special.this.s_ca3.setEnabled(true);
            activity_special.this.s_ca4.setEnabled(true);
            activity_special.this.s_midTerm.setEnabled(true);
            activity_special.this.s_attendance.setEnabled(true);
            activity_special.this.s_examSpinner.setEnabled(true);
            activity_special.this.s_cook.setEnabled(true);
            activity_special.this.s_cook.setText("Calculate Target");
            if (activity_special.this.s_courseSpinner.getSelectedItem().toString().equals("SELECT")) {
                activity_special.this.s_ca2.setEnabled(false);
                activity_special.this.s_ca3.setEnabled(false);
                activity_special.this.s_ca4.setEnabled(false);
                activity_special.this.s_midTerm.setEnabled(false);
                activity_special.this.s_attendance.setEnabled(false);
                activity_special.this.s_ca1.setEnabled(false);
                activity_special.this.s_cook.setEnabled(false);
                activity_special.this.s_cook.setText("Select the Course Above");
                activity_special.this.s_report.requestFocus();
                activity_special.this.s_examSpinner.setSelection(0);
                activity_special.this.s_examSpinner.setEnabled(false);
            }
            if (activity_special.this.s_courseSpinner.getSelectedItem().toString().matches("PES")) {
                activity_special.this.s_midTerm.setVisibility(4);
                activity_special.this.s_tt_midTerm.setVisibility(4);
                activity_special.this.s_tt_tip.setVisibility(0);
                activity_special.this.s_examSpinner.setSelection(0);
                activity_special.this.s_examSpinner.setEnabled(false);
                activity_special.this.s_cook.setText("Calculate Target");
            }
            if (!activity_special.this.s_courseSpinner.getSelectedItem().toString().matches("PES")) {
                activity_special.this.s_midTerm.setVisibility(0);
                activity_special.this.s_tt_midTerm.setVisibility(0);
                activity_special.this.s_tt_tip.setVisibility(8);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };
    /* access modifiers changed from: private */
    public String exam = "End Term";
    OnItemSelectedListener examListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            activity_special.this.exam = activity_special.this.s_examSpinner.getSelectedItem().toString();
            if (activity_special.this.exam.matches("Reappear")) {
                activity_special.this.s_midTerm.setVisibility(8);
                activity_special.this.s_tt_midTerm.setVisibility(8);
            } else if (activity_special.this.exam.matches("End Term") && !activity_special.this.course.matches("PES")) {
                activity_special.this.s_midTerm.setVisibility(0);
                activity_special.this.s_tt_midTerm.setVisibility(0);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };
    private AdView mAdView;
    /* access modifiers changed from: private */
    public float mid = 0.0f;
    OnClickListener reset = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_special.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_special.this.getCurrentFocus().getWindowToken(), 2);
            activity_special.this.s_ca1.setText("");
            activity_special.this.s_ca2.setText("");
            activity_special.this.s_ca3.setText("");
            activity_special.this.s_ca4.setText("");
            activity_special.this.s_midTerm.setText("");
            activity_special.this.s_attendance.setText("");
            activity_special.this.s_cook.setOnClickListener(activity_special.this.cook);
            activity_special.this.s_report.setVisibility(4);
            activity_special.this.s_midTerm.setVisibility(0);
            activity_special.this.s_tt_midTerm.setVisibility(0);
            activity_special.this.s_courseSpinner.setSelection(0);
            activity_special.this.s_cook.setText("Calculate Target");
            activity_special.this.s_cook.setBackgroundColor(Color.rgb(33, 150, 243));
            activity_special.this.s_courseSpinner.setEnabled(true);
            activity_special.this.s_examSpinner.setSelection(0);
            if (activity_special.this.s_courseSpinner.getSelectedItem().toString().matches("CSE101")) {
                activity_special.this.s_cook.setEnabled(true);
                activity_special.this.s_ca1.setEnabled(true);
                activity_special.this.s_ca2.setEnabled(true);
                activity_special.this.s_ca3.setEnabled(true);
                activity_special.this.s_ca4.setEnabled(true);
                activity_special.this.s_midTerm.setEnabled(true);
                activity_special.this.s_attendance.setEnabled(true);
                activity_special.this.s_examSpinner.setEnabled(true);
            } else {
                activity_special.this.s_cook.setEnabled(false);
                activity_special.this.s_examSpinner.setEnabled(false);
            }
            activity_special.this.exam = "End Term";
            activity_special.this.s_scroll.fullScroll(33);
            activity_special.this.s_courseSpinner.requestFocus();
        }
    };
    /* access modifiers changed from: private */
    public EditText s_attendance;
    /* access modifiers changed from: private */
    public EditText s_ca1;
    /* access modifiers changed from: private */
    public EditText s_ca2;
    /* access modifiers changed from: private */
    public EditText s_ca3;
    /* access modifiers changed from: private */
    public EditText s_ca4;
    /* access modifiers changed from: private */
    public Button s_cook;
    /* access modifiers changed from: private */
    public Spinner s_courseSpinner;
    private TextView s_enterAll4CA;
    /* access modifiers changed from: private */
    public Spinner s_examSpinner;
    /* access modifiers changed from: private */
    public EditText s_midTerm;
    /* access modifiers changed from: private */
    public RelativeLayout s_report;
    /* access modifiers changed from: private */
    public ScrollView s_scroll;
    private TextView s_tt_ca4;
    /* access modifiers changed from: private */
    public TextView s_tt_midTerm;
    /* access modifiers changed from: private */
    public TextView s_tt_reportFooter;
    /* access modifiers changed from: private */
    public TextView s_tt_target;
    /* access modifiers changed from: private */
    public TextView s_tt_targetMax;
    /* access modifiers changed from: private */
    public TextView s_tt_tip;
    private int special = 0;
    /* access modifiers changed from: private */
    public boolean status = true;
    /* access modifiers changed from: private */
    public int target = 0;
    private float wca = 0.0f;
    private float wmid = 0.0f;
    private float wtotal = 0.0f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(C0373R.anim.fadein, C0373R.anim.fadeout);
        setContentView((int) C0373R.layout.activity_special);
        setSupportActionBar((Toolbar) findViewById(C0373R.C0375id.toolbar));
        this.mAdView = (AdView) findViewById(C0373R.C0375id.s_adview);
        this.mAdView.loadAd(new Builder().build());
        this.special = getIntent().getIntExtra("carry", 0);
        this.s_ca1 = (EditText) findViewById(C0373R.C0375id.s_ca1);
        this.s_ca2 = (EditText) findViewById(C0373R.C0375id.s_ca2);
        this.s_ca3 = (EditText) findViewById(C0373R.C0375id.s_ca3);
        this.s_ca4 = (EditText) findViewById(C0373R.C0375id.s_ca4);
        this.s_midTerm = (EditText) findViewById(C0373R.C0375id.s_midTerm);
        this.s_attendance = (EditText) findViewById(C0373R.C0375id.s_attendance);
        this.s_report = (RelativeLayout) findViewById(C0373R.C0375id.s_report);
        this.s_cook = (Button) findViewById(C0373R.C0375id.s_cook);
        this.s_tt_midTerm = (TextView) findViewById(C0373R.C0375id.s_tt_midTerm);
        this.s_tt_target = (TextView) findViewById(C0373R.C0375id.s_tt_target);
        this.s_courseSpinner = (Spinner) findViewById(C0373R.C0375id.s_courseSpinner);
        this.s_examSpinner = (Spinner) findViewById(C0373R.C0375id.s_examSpinner);
        this.s_tt_targetMax = (TextView) findViewById(C0373R.C0375id.s_tt_targetMax);
        this.s_tt_reportFooter = (TextView) findViewById(C0373R.C0375id.s_tt_reportFooter);
        this.s_tt_ca4 = (TextView) findViewById(C0373R.C0375id.s_tt_ca4);
        this.s_enterAll4CA = (TextView) findViewById(C0373R.C0375id.s_tt_enterAll4CA);
        this.s_tt_tip = (TextView) findViewById(C0373R.C0375id.s_tt_tip);
        this.s_scroll = (ScrollView) findViewById(C0373R.C0375id.s_scroll);
        this.s_ca1.setEnabled(false);
        this.s_ca2.setEnabled(false);
        this.s_ca3.setEnabled(false);
        this.s_ca4.setEnabled(false);
        this.s_midTerm.setEnabled(false);
        this.s_attendance.setEnabled(false);
        this.s_cook.setEnabled(false);
        this.s_courseSpinner.requestFocus();
        this.s_cook.setOnClickListener(this.cook);
        this.s_courseSpinner.setOnItemSelectedListener(this.courseListener);
        List<String> categories2 = new ArrayList<>();
        categories2.add("End Term");
        categories2.add("Reappear");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, 17367048, categories2);
        dataAdapter2.setDropDownViewResource(17367049);
        this.s_examSpinner.setAdapter(dataAdapter2);
        this.s_examSpinner.setOnItemSelectedListener(this.examListener);
        this.s_examSpinner.setEnabled(false);
        if (this.special == 1) {
            List<String> categories = new ArrayList<>();
            categories.add("CSE101");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, 17367048, categories);
            dataAdapter.setDropDownViewResource(17367049);
            this.s_courseSpinner.setAdapter(dataAdapter);
            this.s_ca4.setVisibility(8);
            this.s_tt_ca4.setVisibility(8);
            this.s_enterAll4CA.setText("Enter all 3 CA Marks (Out of 30)");
        } else {
            List<String> categories3 = new ArrayList<>();
            categories3.add("SELECT");
            categories3.add("PEA");
            categories3.add("PES");
            categories3.add("PEV");
            categories3.add("PEL");
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(this, 17367048, categories3);
            dataAdapter3.setDropDownViewResource(17367049);
            this.s_courseSpinner.setAdapter(dataAdapter3);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
