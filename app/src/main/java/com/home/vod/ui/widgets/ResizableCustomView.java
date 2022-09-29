package com.home.vod.ui.widgets;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_LESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.VIEW_LESS;
import static com.home.vod.preferences.LanguagePreference.VIEW_MORE;

import android.content.Context;
import android.os.SystemClock;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;

import java.util.regex.Pattern;

/**
 * Created by user on 5/2/2016.
 */
public class ResizableCustomView {
    public static Context mcontext;
    public static boolean isViewMore = false;


    public static void doResizeTextView(Context context, final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {
        mcontext = context;
        tv.setTag(null);

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }


        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);


                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if ((tv.getLineCount() <= maxLine && isViewMore == false) || (tv.getLineCount() < maxLine)) {
                    tv.setText(tv.getText());
                    tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                } else {
                    isViewMore = true;
                    if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                        try {

                            tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            int lineEndIndex = tv.getLayout().getLineEnd(3 - 1);

                            String text = null;


                            String xxx = "" + tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1);

                            text = (tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1)).toString().trim() + "..." + expandText;
                            tv.setText(text);
                            tv.setMovementMethod(LinkMovementMethod.getInstance());
                            tv.setText(
                                    addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, 3, expandText,
                                            viewMore), TextView.BufferType.SPANNABLE);

                        } catch (StringIndexOutOfBoundsException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                            String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;

                            String removedot = Pattern.quote("...");
                            if (text.contains("..."))
                                text = text.replace(removedot, " ");
                            tv.setText(text);
                            tv.setMovementMethod(LinkMovementMethod.getInstance());
                            tv.setText(
                                    addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                            viewMore), TextView.BufferType.SPANNABLE);

                            tv.requestFocus();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new MySpannable(false) {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                        doResizeTextView(mcontext, tv, -1, LanguagePreference.getLanguagePreference(mcontext).getTextofLanguage(VIEW_LESS, DEFAULT_VIEW_LESS), false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                        doResizeTextView(mcontext, tv, 2, LanguagePreference.getLanguagePreference(mcontext).getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);
                        tv.invalidate();
                        tv.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), (int)MotionEvent.ACTION_DOWN, tv.getX(), tv.getY(), 0));

                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

    public static class MySpannable extends ClickableSpan {
        private boolean isUnderline = false;

        /**
         * Constructor
         */
        public MySpannable(boolean isUnderline) {
            this.isUnderline = isUnderline;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(isUnderline);
            ds.setColor(mcontext.getResources().getColor(R.color.view_more_less_text_color));

        }

        @Override
        public void onClick(View widget) {

        }
    }
}