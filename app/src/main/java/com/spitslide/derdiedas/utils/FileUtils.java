package com.spitslide.derdiedas.utils;


import android.content.Context;

import com.spitslide.derdiedas.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FileUtils {
    public static String getNounList(Context context) throws UnsupportedEncodingException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.list_nouns);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString("UTF-8");
    }

    static String[] getLines(String string){
        return string.split("\\r?\\n");
    }

    public static long getNounsCount(Context context) throws UnsupportedEncodingException {
        return getLines(getNounList(context)).length;
    }
}
