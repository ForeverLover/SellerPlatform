package com.templar.sellerplatform.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.templar.sellerplatform.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 14:23
 * 描述：$TODO
 */
public class StringUtils {

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if ("null".equals(str)) {
            return true;
        }
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static CharSequence getMerchantNameStr(String source, int splitIndex, Context context, int firstSize, int secondSize) {
        if (isEmpty(source))
            return "";
        Spannable wordtoSpan = new SpannableString(source);

        wordtoSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(context, firstSize), false), 0, splitIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(context, secondSize), false), splitIndex, source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return wordtoSpan;
    }

    public static String formateTimeStr(String data) {
        if (!isNumeric(data)) return "";
        long timeSrc = Long.parseLong("data") * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(timeSrc);
        return sdf.format(date);
    }


    public static CharSequence setSpecialSize(Context context, String str, int size) {
        if (isEmpty(str))
            return "";
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(str);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(DensityUtil.sp2px(context, size), false);//（fontSize，isDip)
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 返回指定字体大小的字符串
        return new SpannedString(ss); // 一定要进行转换,否则属性会消失

    }

    public static String getTweenTime(String timeStr, Context mContext) {
        MLog.v("Tag", "tweenTime:" + timeStr);
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = target.parse(timeStr);
            long timeStmp = date.getTime();
            long currentTime = System.currentTimeMillis();
            long tween = currentTime - timeStmp;
            long second = tween / 1000;
            long minute = second / 60;
            long hour = minute / 60;
            long day = hour / 24;
          /*  long month=tween/1000;
            long year=tween/12;*/
            MLog.v("Tag", day + " " + " " + hour + " " + minute + " " + second);
            if (day > 1) {
                return day + mContext.getString(R.string.order_day);
            }
            if (hour > 1) {
                return day + mContext.getString(R.string.order_hour);
            }
            if (minute > 1) {
                return day + mContext.getString(R.string.order_minute);
            }
            if (second > 1) {
                return day + mContext.getString(R.string.order_second);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

   /* private static Typeface getTextViewTypeFace(Context context) {
        AssetManager mgr = context.getAssets();//得到AssetManager
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/m_black.ttf");//根据路径得到Typeface

        return tf;
    }

    public static void setText(TextView tv, String text, Context mContext) {
        tv.setText(text);
        tv.setTypeface(getTextViewTypeFace(mContext));
    }*/
}
