package com.home.vod.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Alok on 08-04-2017.
 */
public class FontUtls {

    private static Typeface tf;

    public static void loadFont(Context context, String fontPath, TextView textView){
        try {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
            textView.setTypeface(tf);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
            //Log.e("Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
        }
    }

}



