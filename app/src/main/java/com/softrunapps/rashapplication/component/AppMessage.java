package com.softrunapps.rashapplication.component;

import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class AppMessage {
    private Activity activity;

    private AppMessage() {
    }

    private AppMessage(Activity activity) {
        this.activity = activity;
    }

    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        snackbar.show();
    }

    public static AppMessage with(Activity activity) {
        return new AppMessage(activity);
    }
}
