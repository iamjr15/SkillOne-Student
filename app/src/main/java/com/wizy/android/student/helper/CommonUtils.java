package com.wizy.android.student.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Date;

public class CommonUtils {
    public static Bitmap blur(Context context, View view, float radius, float scale) {
        Bitmap image = loadBitmapFromView(view);

        int width = Math.round(image.getWidth() * scale);
        int height = Math.round(image.getHeight() * scale);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic;
        theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);


        return outputBitmap;
    }

    private static Bitmap loadBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view.draw(c);
        return b;
    }

    public static long differenceInMillis(Date start, Date end) {
        return end.getTime() - start.getTime();
    }


    public static int getSpanCountByItemWidth(int itemWidthDp, Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        return (int) (width / pxFromDp(context, itemWidthDp));
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static void applyAppBarHack(AppBarLayout appBar, RecyclerView recyclerView) {
        appBar.setLiftable(true);
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }

                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        appBar.setLifted(!isAtTop(recyclerView));
                    }
                });
    }

    private static boolean isAtTop(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null) {
            return layoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        }
        return false;
    }

    public static String getTimerText(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        String sec = String.valueOf(seconds);
        String min = String.valueOf(minutes);
        if (seconds < 10) {
            sec = "0" + String.valueOf(seconds);
        }
        if (minutes < 10) {
            min = "0" + String.valueOf(minutes);
        }
        return min + ":" + sec;
    }


}
