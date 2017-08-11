package com.ebs.customtextview.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by barbaros.vasile on 8/11/2017.
 */

public class FontManager {
    private static final FontManager ourInstance = new FontManager();

    public static FontManager getInstance() {
        return ourInstance;
    }

    private FontManager() {
    }

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public Typeface getTypeface(String fontName, Context context) {
        Typeface typeFace = fontCache.get(fontName);
        if (typeFace == null) {
            try {
                typeFace = Typeface.createFromAsset(context.getAssets(), fontName);
                System.out.println("FONT CREATE FROM ASSETS "+fontName);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, typeFace);
        } else {
            System.out.println("FONT FROM CACHE "+fontName);
        }

        return typeFace;
    }
}
