package com.akhil.lovely.lpu.infinitum.verto;

import android.widget.TextView;

public class cooker {
    public static int attendance(float att) {
        if (att >= 90.0f) {
            return 5;
        }
        if (att >= 85.0f && att < 90.0f) {
            return 4;
        }
        if (att >= 80.0f && att < 85.0f) {
            return 3;
        }
        if (att < 75.0f || att >= 80.0f) {
            return 0;
        }
        return 2;
    }

    public static int attendanceSpecial(float att) {
        if (att >= 95.0f) {
            return 15;
        }
        if (att >= 90.0f && att < 95.0f) {
            return 10;
        }
        if (att < 85.0f || att >= 90.0f) {
            return 0;
        }
        return 5;
    }

    public static float fourParams(float ca, float caMax, float caWeight, float mid, float midMax, float midWeight, float attmarks, float endMax, float endWeight) {
        return (((((40.0f - ((caWeight * ca) / caMax)) - ((midWeight * mid) / midMax)) - attmarks) * endMax) / endWeight) + 1.0f;
    }

    public static float twoParams(float ca, float caMax, float caWeight, float attmarks, float endMax, float endWeight) {
        return ((((40.0f - ((caWeight * ca) / caMax)) - attmarks) * endMax) / endWeight) + 1.0f;
    }

    public static void reportSet(int target, int max, TextView tt_target, TextView tt_targetMax, TextView tt_reportFooter) {
        int passing = (max * 30) / 100;
        int holder = target;
        if (target > max) {
            target = max;
        } else if (target < passing) {
            target = passing;
        }
        tt_target.setText(target + "");
        tt_targetMax.setText("/ " + max + "");
        if (target <= passing) {
            tt_reportFooter.setText("You Actually need " + holder + ". But to Fulfil all Criteria you Need " + target);
        } else {
            tt_reportFooter.setText("To get past this Course...");
        }
    }
}
