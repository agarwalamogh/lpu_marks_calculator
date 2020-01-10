package com.akhil.lovely.lpu.infinitum.verto;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class _MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        FirebaseMessaging.getInstance().subscribeToTopic("global");
    }
}
