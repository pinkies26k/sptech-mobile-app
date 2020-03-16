package com.test.sptech.Utilities;

import android.content.Context;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GeneralUtil {

    // print out details error
    public static String getStackTrace(Exception e) {

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        return errors.toString();

    }

    /**
     *
     * @param context
     * @param msg
     * @return
     */
    public static Toast makeToast(Context context, String msg){
        return Toast.makeText(context, msg,
                Toast.LENGTH_LONG);
    }
}
