package com.kevin.zwk.kt_zidingyi_0618_1;

import android.content.Context;

/**
 * Created by ZhongHang on 2016/5/9.
 */
public class DpUtils {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
