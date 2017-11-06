package com.ebs.customtextview.views;

/**
 * Created by barbaros.vasile on 7/31/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.ebs.customtextview.R;
import com.ebs.customtextview.util.FontManager;


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

    private void setPaddingBottom(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            if(getLineSpacingMultiplier() < 1) {
               // setLineSpacing(1,1);

                int value = (int) ((getLineSpacingMultiplier()*100)/2);
                System.out.println("spacing " +value);


              //
                System.out.println("spacing padding bottom" +getPaddingBottom());
                int fontHeight = getPaint().getFontMetricsInt(null);
                System.out.println("spacing fontHeight" +fontHeight);
                //getResources().getDimensionPixelSize()
                int valueInDp= (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_PX, fontHeight , getResources()
                                .getDisplayMetrics());
                System.out.println("spacing dp " +valueInDp);

               // setHeight(getLineCount() * fontHeight);
               //setPadding(0,0,0, (int) (fontHeight/1.5f));
            }
        }
    }

  /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setPaddingBottom();
    }*/
  private final Paint mPaint = new Paint();

    private final Rect mBounds = new Rect();
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        final String text = calculateTextParams();

        final int left = mBounds.left;
        final int bottom = mBounds.bottom;
        mBounds.offset(-mBounds.left, -mBounds.top);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        canvas.drawText(text, -left, mBounds.bottom - bottom, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculateTextParams();
        setMeasuredDimension(mBounds.width() + 1, -mBounds.top + 1);
    }

    private String calculateTextParams() {
        final String text = getText().toString();
        final int textLength = text.length();
        mPaint.setTextSize(getTextSize());
        mPaint.getTextBounds(text, 0, textLength, mBounds);
        if (textLength == 0) {
            mBounds.right = mBounds.left;
        }
        return text;
    }

    private void applyStyledFont(String fontName, Context context, AttributeSet attrs) {
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