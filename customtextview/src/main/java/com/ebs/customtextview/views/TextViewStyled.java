package com.ebs.customtextview.views;

/**
 * Created by barbaros.vasile on 7/31/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;

import com.ebs.customtextview.R;
import com.ebs.customtextview.util.FontManager;


public class TextViewStyled extends android.support.v7.widget.AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    public static String fontName ="";
    public static boolean fontType =false;
    private boolean mNegativeLineSpacing = false;

    public TextViewStyled(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TextViewStyled(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewStyled);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TextViewStyled_fontName_text) {
                fontName = a.getString(attr);
                applyStyledFont(fontName, context, attrs);
            }

            if (attr == R.styleable.TextViewStyled_fancyText_text) {
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mNegativeLineSpacing) { // If you are only supporting Api Level 16 and up, you could use the getLineSpacingExtra() and getLineSpacingMultiplier() methods here to check for a less than 1 spacing instead.
            Layout layout = getLayout();
            int truncatedHeight = layout.getLineDescent(layout.getLineCount()-1);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + truncatedHeight);
        }
    }

    @Override
    public void setLineSpacing(float add, float mult) {
        mNegativeLineSpacing = add < 0 || mult < 1;
        super.setLineSpacing(add, mult);
    }
}