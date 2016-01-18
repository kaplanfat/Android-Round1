package com.kaplan.hotelquickly.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.kaplan.hotelquickly.R;


public class HotelQuicklyProgressDialog extends Dialog {
    private static HotelQuicklyProgressDialog instance;


    public synchronized static HotelQuicklyProgressDialog getInstance(Context context) {
        if (instance == null) instance = new HotelQuicklyProgressDialog(context);
        return instance;
    }

    public static HotelQuicklyProgressDialog show(Context context, CharSequence title,
                                             CharSequence message) {
        return show(context, title, message, false);
    }

    public static HotelQuicklyProgressDialog show(Context context, CharSequence title,
                                             CharSequence message, boolean indeterminate) {
        return getHotelQuicklyProgressDialog(context, title, message, indeterminate, false, null);
    }

    public static HotelQuicklyProgressDialog show(Context context, CharSequence title,
                                             CharSequence message, boolean indeterminate, boolean cancelable) {
        return getHotelQuicklyProgressDialog(context, title, message, indeterminate, cancelable, null);
    }

    public static HotelQuicklyProgressDialog getHotelQuicklyProgressDialog(Context context, CharSequence title,
                                                                 CharSequence message, boolean indeterminate,
                                                                 boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        HotelQuicklyProgressDialog dialog = new HotelQuicklyProgressDialog(context);
//        dialog.setTitle(title);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.setContentView(R.layout.progress_layout);
//        dialog.show();

        /* The next line will add the ProgressBar to the dialog. */
//        dialog.addContentView(new ProgressBar(context), new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        return dialog;
    }

    public HotelQuicklyProgressDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
    }
}
