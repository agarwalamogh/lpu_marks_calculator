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

public class activity_practical_n extends AppCompatActivity {
    /* access modifiers changed from: private */
    public float attendance = 0.0f;
    /* access modifiers changed from: private */
    public float attmarks = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: ca */
    public float f34ca = 0.0f;
    OnClickListener cook = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_practical_n.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_practical_n.this.getCurrentFocus().getWindowToken(), 2);
            activity_practical_n.this.pn_1.setError(null);
            activity_practical_n.this.pn_2.setError(null);
            activity_practical_n.this.pn_3.setError(null);
            activity_practical_n.this.pn_4.setError(null);
            activity_practical_n.this.pn_5.setError(null);
            activity_practical_n.this.pn_6.setError(null);
            activity_practical_n.this.pn_7.setError(null);
            activity_practical_n.this.pn_8.setError(null);
            activity_practical_n.this.pn_mtp1.setError(null);
            activity_practical_n.this.pn_mtp2.setError(null);
            activity_practical_n.this.pn_attendance.setError(null);
            activity_practical_n.this.status = true;
            if (activity_practical_n.this.special == 1) {
                activity_practical_n.this.status = validate.valSpecial(activity_practical_n.this.status, activity_practical_n.this.pn_1, activity_practical_n.this.pn_2, activity_practical_n.this.pn_3, activity_practical_n.this.pn_4, activity_practical_n.this.pn_5, activity_practical_n.this.pn_6, activity_practical_n.this.pn_7, activity_practical_n.this.pn_8).booleanValue();
                activity_practical_n.this.status = validate.valCA(activity_practical_n.this.status, activity_practical_n.this.pn_mtp1).booleanValue();
                activity_practical_n.this.status = validate.valMidTerm(activity_practical_n.this.status, activity_practical_n.this.pn_mtp2).booleanValue();
                activity_practical_n.this.status = validate.valAttendance(activity_practical_n.this.status, activity_practical_n.this.pn_attendance).booleanValue();
            } else {
                activity_practical_n.this.status = validate.valPractical(activity_practical_n.this.status, activity_practical_n.this.pn_1, activity_practical_n.this.pn_2, activity_practical_n.this.pn_3, activity_practical_n.this.pn_4, activity_practical_n.this.pn_5, activity_practical_n.this.pn_6, activity_practical_n.this.pn_7, activity_practical_n.this.pn_8).booleanValue();
                activity_practical_n.this.status = validate.valCA(activity_practical_n.this.status, activity_practical_n.this.pn_mtp1, activity_practical_n.this.pn_mtp2).booleanValue();
                activity_practical_n.this.status = validate.valAttendance(activity_practical_n.this.status, activity_practical_n.this.pn_attendance).booleanValue();
            }
            if (activity_practical_n.this.status) {
                activity_practical_n.this.f35p1 = Float.parseFloat(activity_practical_n.this.pn_1.getText().toString());
                activity_practical_n.this.f36p2 = Float.parseFloat(activity_practical_n.this.pn_2.getText().toString());
                activity_practical_n.this.f37p3 = Float.parseFloat(activity_practical_n.this.pn_3.getText().toString());
                activity_practical_n.this.f38p4 = Float.parseFloat(activity_practical_n.this.pn_4.getText().toString());
                activity_practical_n.this.f39p5 = Float.parseFloat(activity_practical_n.this.pn_5.getText().toString());
                activity_practical_n.this.f40p6 = Float.parseFloat(activity_practical_n.this.pn_6.getText().toString());
                activity_practical_n.this.f41p7 = Float.parseFloat(activity_practical_n.this.pn_7.getText().toString());
                activity_practical_n.this.f42p8 = Float.parseFloat(activity_practical_n.this.pn_8.getText().toString());
                activity_practical_n.this.mtp1 = Float.parseFloat(activity_practical_n.this.pn_mtp1.getText().toString());
                activity_practical_n.this.mtp2 = Float.parseFloat(activity_practical_n.this.pn_mtp2.getText().toString());
                activity_practical_n.this.attendance = Float.parseFloat(activity_practical_n.this.pn_attendance.getText().toString());
                if (activity_practical_n.this.special == 1) {
                    activity_practical_n.this.f34ca = ((((((((activity_practical_n.this.f35p1 + activity_practical_n.this.f36p2) + activity_practical_n.this.f37p3) + activity_practical_n.this.f38p4) + activity_practical_n.this.f39p5) + activity_practical_n.this.f40p6) + activity_practical_n.this.f41p7) + activity_practical_n.this.f42p8) * 30.0f) / 80.0f;
                    activity_practical_n.this.fca = activity_practical_n.this.f34ca + activity_practical_n.this.mtp1;
                    activity_practical_n.this.attmarks = (float) cooker.attendance(activity_practical_n.this.attendance);
                    if (activity_practical_n.this.exam.matches("End Term")) {
                        activity_practical_n.this.target = (int) cooker.fourParams(activity_practical_n.this.fca, 60.0f, 20.0f, activity_practical_n.this.mtp2, 40.0f, 25.0f, activity_practical_n.this.attmarks, 70.0f, 50.0f);
                    } else if (activity_practical_n.this.exam.matches("Reappear")) {
                        activity_practical_n.this.target = (int) cooker.fourParams(activity_practical_n.this.fca, 60.0f, 20.0f, activity_practical_n.this.mtp2, 40.0f, 0.0f, activity_practical_n.this.attmarks, 70.0f, 75.0f);
                    }
                    cooker.reportSet(activity_practical_n.this.target, 70, activity_practical_n.this.pn_tt_target, activity_practical_n.this.pn_tt_targetMax, activity_practical_n.this.pn_tt_reportFooter);
                } else {
                    activity_practical_n.this.f34ca = activity_practical_n.this.f35p1 + activity_practical_n.this.f36p2 + activity_practical_n.this.f37p3 + activity_practical_n.this.f38p4 + activity_practical_n.this.f39p5 + activity_practical_n.this.f40p6 + activity_practical_n.this.f41p7 + activity_practical_n.this.f42p8;
                    activity_practical_n.this.fca = activity_practical_n.this.mtp1 + activity_practical_n.this.mtp2;
                    activity_practical_n.this.attmarks = (float) cooker.attendance(activity_practical_n.this.attendance);
                    if (activity_practical_n.this.exam.matches("End Term")) {
                        activity_practical_n.this.target = (int) cooker.fourParams(activity_practical_n.this.f34ca, 400.0f, 25.0f, activity_practical_n.this.fca, 60.0f, 20.0f, activity_practical_n.this.attmarks, 100.0f, 50.0f);
                    } else if (activity_practical_n.this.exam.matches("Reappear")) {
                        activity_practical_n.this.target = (int) cooker.fourParams(activity_practical_n.this.f34ca, 400.0f, 25.0f, activity_practical_n.this.fca, 60.0f, 0.0f, activity_practical_n.this.attmarks, 100.0f, 70.0f);
                    }
                    cooker.reportSet(activity_practical_n.this.target, 100, activity_practical_n.this.pn_tt_target, activity_practical_n.this.pn_tt_targetMax, activity_practical_n.this.pn_tt_reportFooter);
                }
                activity_practical_n.this.pn_report.setVisibility(0);
                activity_practical_n.this.pn_cook.setBackgroundColor(-7829368);
                activity_practical_n.this.pn_cook.setText("Reset");
                activity_practical_n.this.pn_cook.setOnClickListener(activity_practical_n.this.reset);
                activity_practical_n.this.pn_1.setEnabled(false);
                activity_practical_n.this.pn_2.setEnabled(false);
                activity_practical_n.this.pn_3.setEnabled(false);
                activity_practical_n.this.pn_4.setEnabled(false);
                activity_practical_n.this.pn_5.setEnabled(false);
                activity_practical_n.this.pn_6.setEnabled(false);
                activity_practical_n.this.pn_7.setEnabled(false);
                activity_practical_n.this.pn_8.setEnabled(false);
                activity_practical_n.this.pn_mtp1.setEnabled(false);
                activity_practical_n.this.pn_mtp2.setEnabled(false);
                activity_practical_n.this.pn_attendance.setEnabled(false);
                activity_practical_n.this.pn_examSpinner.setEnabled(false);
                activity_practical_n.this.pn_report.requestFocus();
                activity_practical_n.this.pn_scroll.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
            Bundle bundle = new Bundle();
            bundle.putString(Param.ITEM_NAME, "Practical");
            FirebaseAnalytics.getInstance(activity_practical_n.this).logEvent(Event.SELECT_CONTENT, bundle);
        }
    };
    /* access modifiers changed from: private */
    public String exam = "End Term";
    OnItemSelectedListener examListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            activity_practical_n.this.exam = activity_practical_n.this.pn_examSpinner.getSelectedItem().toString();
            if (activity_practical_n.this.exam.matches("Reappear")) {
                activity_practical_n.this.pn_mtp1.setVisibility(8);
                activity_practical_n.this.pn_tt_mtp1.setVisibility(8);
                activity_practical_n.this.pn_mtp2.setVisibility(8);
                activity_practical_n.this.pn_tt_mtp2.setVisibility(8);
                return;
            }
            activity_practical_n.this.pn_mtp1.setVisibility(0);
            activity_practical_n.this.pn_tt_mtp1.setVisibility(0);
            activity_practical_n.this.pn_mtp2.setVisibility(0);
            activity_practical_n.this.pn_tt_mtp2.setVisibility(0);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };
    /* access modifiers changed from: private */
    public float fca = 0.0f;
    private AdView mAdView;
    /* access modifiers changed from: private */
    public float mtp1 = 0.0f;
    /* access modifiers changed from: private */
    public float mtp2 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p1 */
    public float f35p1 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p2 */
    public float f36p2 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p3 */
    public float f37p3 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p4 */
    public float f38p4 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p5 */
    public float f39p5 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p6 */
    public float f40p6 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p7 */
    public float f41p7 = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: p8 */
    public float f42p8 = 0.0f;
    /* access modifiers changed from: private */
    public EditText pn_1;
    /* access modifiers changed from: private */
    public EditText pn_2;
    /* access modifiers changed from: private */
    public EditText pn_3;
    /* access modifiers changed from: private */
    public EditText pn_4;
    /* access modifiers changed from: private */
    public EditText pn_5;
    /* access modifiers changed from: private */
    public EditText pn_6;
    /* access modifiers changed from: private */
    public EditText pn_7;
    /* access modifiers changed from: private */
    public EditText pn_8;
    /* access modifiers changed from: private */
    public EditText pn_attendance;
    /* access modifiers changed from: private */
    public Button pn_cook;
    /* access modifiers changed from: private */
    public Spinner pn_examSpinner;
    /* access modifiers changed from: private */
    public EditText pn_mtp1;
    /* access modifiers changed from: private */
    public EditText pn_mtp2;
    /* access modifiers changed from: private */
    public RelativeLayout pn_report;
    /* access modifiers changed from: private */
    public ScrollView pn_scroll;
    private TextView pn_tt_enterBestOf8;
    /* access modifiers changed from: private */
    public TextView pn_tt_mtp1;
    /* access modifiers changed from: private */
    public TextView pn_tt_mtp2;
    /* access modifiers changed from: private */
    public TextView pn_tt_reportFooter;
    /* access modifiers changed from: private */
    public TextView pn_tt_target;
    /* access modifiers changed from: private */
    public TextView pn_tt_targetMax;
    OnClickListener reset = new OnClickListener() {
        public void onClick(View v) {
            ((InputMethodManager) activity_practical_n.this.getSystemService("input_method")).hideSoftInputFromWindow(activity_practical_n.this.getCurrentFocus().getWindowToken(), 2);
            activity_practical_n.this.pn_1.setText("");
            activity_practical_n.this.pn_2.setText("");
            activity_practical_n.this.pn_3.setText("");
            activity_practical_n.this.pn_4.setText("");
            activity_practical_n.this.pn_5.setText("");
            activity_practical_n.this.pn_6.setText("");
            activity_practical_n.this.pn_7.setText("");
            activity_practical_n.this.pn_8.setText("");
            activity_practical_n.this.pn_mtp1.setText("");
            activity_practical_n.this.pn_mtp2.setText("");
            activity_practical_n.this.pn_attendance.setText("");
            activity_practical_n.this.pn_cook.setOnClickListener(activity_practical_n.this.cook);
            activity_practical_n.this.pn_report.setVisibility(4);
            activity_practical_n.this.pn_cook.setOnClickListener(activity_practical_n.this.cook);
            activity_practical_n.this.pn_cook.setText("Calculate Target");
            activity_practical_n.this.pn_cook.setBackgroundColor(Color.rgb(255, 152, 0));
            activity_practical_n.this.pn_report.setVisibility(4);
            activity_practical_n.this.pn_1.setEnabled(true);
            activity_practical_n.this.pn_2.setEnabled(true);
            activity_practical_n.this.pn_3.setEnabled(true);
            activity_practical_n.this.pn_4.setEnabled(true);
            activity_practical_n.this.pn_5.setEnabled(true);
            activity_practical_n.this.pn_6.setEnabled(true);
            activity_practical_n.this.pn_7.setEnabled(true);
            activity_practical_n.this.pn_8.setEnabled(true);
            activity_practical_n.this.pn_mtp1.setEnabled(true);
            activity_practical_n.this.pn_mtp2.setEnabled(true);
            activity_practical_n.this.pn_attendance.setEnabled(true);
            activity_practical_n.this.pn_examSpinner.setEnabled(true);
            activity_practical_n.this.pn_examSpinner.setSelection(0);
            activity_practical_n.this.exam = "End Term";
            activity_practical_n.this.pn_scroll.fullScroll(33);
            activity_practical_n.this.pn_1.requestFocus();
        }
    };

    /* renamed from: s1 */
    private TextView f43s1;

    /* renamed from: s2 */
    private TextView f44s2;

    /* renamed from: s3 */
    private TextView f45s3;

    /* renamed from: s4 */
    private TextView f46s4;

    /* renamed from: s5 */
    private TextView f47s5;

    /* renamed from: s6 */
    private TextView f48s6;

    /* renamed from: s7 */
    private TextView f49s7;

    /* renamed from: s8 */
    private TextView f50s8;
    /* access modifiers changed from: private */
    public int special = 0;
    /* access modifiers changed from: private */
    public boolean status = true;
    /* access modifiers changed from: private */
    public int target = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(C0373R.anim.fadein, C0373R.anim.fadeout);
        setContentView((int) C0373R.layout.activity_practical_n);
        setSupportActionBar((Toolbar) findViewById(C0373R.C0375id.toolbar));
        this.mAdView = (AdView) findViewById(C0373R.C0375id.pn_adview);
        this.mAdView.loadAd(new Builder().build());
        this.special = getIntent().getIntExtra("carry", 0);
        this.pn_1 = (EditText) findViewById(C0373R.C0375id.pn_pr1);
        this.pn_2 = (EditText) findViewById(C0373R.C0375id.pn_pr2);
        this.pn_3 = (EditText) findViewById(C0373R.C0375id.pn_pr3);
        this.pn_4 = (EditText) findViewById(C0373R.C0375id.pn_pr4);
        this.pn_5 = (EditText) findViewById(C0373R.C0375id.pn_pr5);
        this.pn_6 = (EditText) findViewById(C0373R.C0375id.pn_pr6);
        this.pn_7 = (EditText) findViewById(C0373R.C0375id.pn_pr7);
        this.pn_8 = (EditText) findViewById(C0373R.C0375id.pn_pr8);
        this.f43s1 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr1);
        this.f44s2 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr2);
        this.f45s3 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr3);
        this.f46s4 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr4);
        this.f47s5 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr5);
        this.f48s6 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr6);
        this.f49s7 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr7);
        this.f50s8 = (TextView) findViewById(C0373R.C0375id.pn_tt_pr8);
        this.pn_mtp1 = (EditText) findViewById(C0373R.C0375id.pn_mtp1);
        this.pn_mtp2 = (EditText) findViewById(C0373R.C0375id.pn_mtp2);
        this.pn_attendance = (EditText) findViewById(C0373R.C0375id.pn_attendance);
        this.pn_cook = (Button) findViewById(C0373R.C0375id.pn_cook);
        this.pn_tt_target = (TextView) findViewById(C0373R.C0375id.pn_tt_target);
        this.pn_report = (RelativeLayout) findViewById(C0373R.C0375id.pn_report);
        this.pn_tt_reportFooter = (TextView) findViewById(C0373R.C0375id.pn_tt_reportFooter);
        this.pn_tt_mtp1 = (TextView) findViewById(C0373R.C0375id.pn_tt_mtp1);
        this.pn_tt_mtp2 = (TextView) findViewById(C0373R.C0375id.pn_tt_mtp2);
        this.pn_tt_enterBestOf8 = (TextView) findViewById(C0373R.C0375id.pn_tt_enterBestOf8);
        this.pn_tt_targetMax = (TextView) findViewById(C0373R.C0375id.pn_tt_targetMax);
        this.pn_examSpinner = (Spinner) findViewById(C0373R.C0375id.pn_examSpinner);
        this.pn_scroll = (ScrollView) findViewById(C0373R.C0375id.pn_scroll);
        List<String> categories = new ArrayList<>();
        categories.add("End Term");
        categories.add("Reappear");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, 17367048, categories);
        dataAdapter.setDropDownViewResource(17367049);
        this.pn_examSpinner.setAdapter(dataAdapter);
        this.pn_examSpinner.setOnItemSelectedListener(this.examListener);
        if (this.special == 1) {
            this.f43s1.setText("Sheet 1");
            this.f44s2.setText("Sheet 2");
            this.f45s3.setText("Sheet 3");
            this.f46s4.setText("Sheet 4");
            this.f47s5.setText("Sheet 5");
            this.f48s6.setText("Sheet 6");
            this.f49s7.setText("Sheet 7");
            this.f50s8.setText("Sheet 8");
            this.pn_tt_mtp1.setText("Best CA");
            this.pn_tt_mtp2.setText("Mid Term");
            this.pn_tt_enterBestOf8.setText("Enter the Best of 8 Marks of Drawing Sheets OUT OF 10 & Best of 1 CA Below");
        }
        this.pn_cook.setOnClickListener(this.cook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
