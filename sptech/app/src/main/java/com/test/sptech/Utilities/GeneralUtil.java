package com.test.sptech.Utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GeneralUtil {

    // print out details error
    public static String getStackTrace(Exception e) {

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        return errors.toString();

    }
}
