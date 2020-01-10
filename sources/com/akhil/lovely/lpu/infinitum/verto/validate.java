package com.akhil.lovely.lpu.infinitum.verto;

import android.widget.EditText;

public class validate {
    public static Boolean valCA(boolean sta, EditText... cas) {
        boolean status = sta;
        for (int i = 0; i < cas.length; i++) {
            String s = cas[i].getText().toString();
            if (s.length() == 0) {
                cas[i].setText("0");
            } else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 30) {
                cas[i].setError("Max : 30");
                status = false;
            }
        }
        return Boolean.valueOf(status);
    }

    public static Boolean valMidTerm(boolean sta, EditText... mids) {
        boolean status = sta;
        for (int i = 0; i < mids.length; i++) {
            String s = mids[i].getText().toString();
            if (s.length() == 0) {
                mids[i].setText("0");
            } else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 50) {
                mids[i].setError("Max : 50");
                status = false;
            }
        }
        return Boolean.valueOf(status);
    }

    public static Boolean valPractical(boolean sta, EditText... pras) {
        boolean status = sta;
        for (int i = 0; i < pras.length; i++) {
            String s = pras[i].getText().toString();
            if (s.length() == 0) {
                pras[i].setText("0");
            } else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 50) {
                pras[i].setError("Out of 50 Only!");
                status = false;
            }
        }
        return Boolean.valueOf(status);
    }

    public static Boolean valSpecial(boolean sta, EditText... specs) {
        boolean status = sta;
        for (int i = 0; i < specs.length; i++) {
            String s = specs[i].getText().toString();
            if (s.length() == 0) {
                specs[i].setText("0");
            } else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 10) {
                specs[i].setError("Max: 10");
                status = false;
            }
        }
        return Boolean.valueOf(status);
    }

    public static Boolean valAttendance(boolean sta, EditText attendance) {
        boolean status = sta;
        String s = attendance.getText().toString();
        if (s.length() == 0) {
            attendance.setText("0");
        } else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 100) {
            attendance.setError("Over 100!!");
            status = false;
        }
        return Boolean.valueOf(status);
    }
}
