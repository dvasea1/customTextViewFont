package com.ebs.customtextview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.ebs.customtextview.R;
import com.ebs.customtextview.util.FontManager;

/**
 * Created by barbaros.vasile on 8/11/2017.
 */

public class EditTextStyled extends android.support.v7.widget.AppCompatEditText {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    public static String fontName ="";
    public static boolean fontType =false;

    public EditTextStyled(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EditTextStyled(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextStyled);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.EditTextStyled_fontName_edit) {
                fontName = a.getString(attr);
                applyStyledFont(fontName, context, attrs);
            }

            if (attr == R.styleable.EditTextStyled_fancyText_edit) {
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
                return FontManager.getInstance().getTypeface(fName+".ttf", context);
            case Typeface.ITALIC: // medium
                return FontManager.getInstance().getTypeface(fName+".ttf", context);
            case Typeface.BOLD_ITALIC: // bold italic
                return FontManager.getInstance().getTypeface(fName+".ttf", context);
            case Typeface.NORMAL: // regular
                return FontManager.getInstance().getTypeface(fName+".ttf", context);

            default:
                return FontManager.getInstance().getTypeface(fName+".ttf", context);
        }
    }
}