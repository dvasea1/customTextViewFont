package com.ebs.customtextview;

/**
 * Created by barbaros.vasile on 7/31/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.HashMap;


public class TextViewStyled extends android.support.v7.widget.AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    public static String fontName ="";
    public static boolean fontType =false;

    public TextViewStyled(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TextViewStyled(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewStyled);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TextViewStyled_fontName) {
                fontName = a.getString(attr);
                applyStyledFont(fontName, context, attrs);
            }

            if (attr == R.styleable.TextViewStyled_fancyText) {
                fontType = a.getBoolean(attr, false);
            }
        }
        a.recycle();
    }

    private void applyStyledFont(String fontName,Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle, fontName);
        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle,String fName) {
        switch (textStyle) {
            case Typeface.BOLD: // bold
                return FontCache.getTypeface(fName+".ttf", context);
            case Typeface.ITALIC: // medium
                return FontCache.getTypeface(fName+".ttf", context);
            case Typeface.BOLD_ITALIC: // bold italic
                return FontCache.getTypeface(fName+".ttf", context);
            case Typeface.NORMAL: // regular
                return FontCache.getTypeface(fName+".ttf", context);

            default:
                return FontCache.getTypeface(fName+".ttf", context);
        }
    }

    private static class FontCache {
        private static HashMap<String, Typeface> fontCache = new HashMap<>();
        static Typeface getTypeface(String fontname, Context context) {
            Typeface typeface = fontCache.get(fontname);
            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(context.getAssets(), fontname);
                } catch (Exception e) {
                    return null;
                }
                fontCache.put(fontname, typeface);
            }
            return typeface;
        }
    }
}