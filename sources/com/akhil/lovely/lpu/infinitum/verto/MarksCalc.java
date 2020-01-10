package com.akhil.lovely.lpu.infinitum.verto;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MarksCalc extends Application {
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
