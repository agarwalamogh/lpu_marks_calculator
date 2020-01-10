package com.akhil.lovely.lpu.infinitum.verto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.kobakei.ratethisapp.RateThisApp;
import com.kobakei.ratethisapp.RateThisApp.Config;

public class MainActivity extends AppCompatActivity {
    private TextView disclaimer;
    private ImageButton practical_c__button;
    private ImageButton practical_n__button;
    private ImageButton special_button;
    private ImageButton theory_button;

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        RateThisApp.onStart(this);
        RateThisApp.showRateDialogIfNeeded(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(C0373R.anim.fadein, C0373R.anim.fadeout);
        setContentView((int) C0373R.layout.activity_main);
        Config config = new Config(3, 7);
        config.setTitle(C0373R.string.my_own_title);
        config.setMessage(C0373R.string.my_own_message);
        RateThisApp.init(config);
        this.theory_button = (ImageButton) findViewById(C0373R.C0375id.theory_button_ui);
        this.special_button = (ImageButton) findViewById(C0373R.C0375id.special_button_ui);
        this.practical_c__button = (ImageButton) findViewById(C0373R.C0375id.practical_button_c_ui);
        this.practical_n__button = (ImageButton) findViewById(C0373R.C0375id.practical_button_n_ui);
        this.disclaimer = (TextView) findViewById(C0373R.C0375id.disclaimer);
        this.theory_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, activity_theory.class));
            }
        });
        this.special_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, activity_special.class));
            }
        });
        this.practical_n__button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, activity_practical_n.class));
            }
        });
        this.practical_c__button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, activity_practical_c.class));
            }
        });
        this.disclaimer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, disclaimer.class));
            }
        });
    }

    public void author_onclick(View v) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/akhilmarsonya")));
    }
}
