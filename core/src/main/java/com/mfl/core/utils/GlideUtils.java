package com.mfl.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {

    public static void loadPic(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadPic(Context context, int resourseId, ImageView imageView) {
        Glide.with(context).load(resourseId).into(imageView);
    }

    public static void loadPic(Context context, Bitmap bitmap, ImageView imageView) {
        Glide.with(context).load(bitmap).into(imageView);
    }

    public static void loadPic(Context context, Drawable drawable, ImageView imageView) {

        Glide.with(context).load(drawable).into(imageView);

    }

    public static void loadCirclePic(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    public static void loadCirclePic(Context context, int resourseId, ImageView imageView) {
        Glide.with(context).load(resourseId).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    public static void loadCirclePic(Context context, Bitmap bitmap, ImageView imageView) {
        Glide.with(context).load(bitmap).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    public static void loadCirclePic(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context).load(drawable).apply(RequestOptions.circleCropTransform()).into(imageView);
    }



//
//
//
//    public static void loadPic(FragmentActivity fragmentActivity, String url, ImageView imageView) {
//        Glide.with(fragmentActivity).load(url).into(imageView);
//    }
//
//    public static void loadPic(FragmentActivity fragmentActivity, int resourseId, ImageView imageView) {
//        Glide.with(fragmentActivity).load(resourseId).into(imageView);
//    }
//
//    public static void loadPic(FragmentActivity fragmentActivity, Bitmap bitmap, ImageView imageView) {
//        Glide.with(fragmentActivity).load(bitmap).into(imageView);
//    }
//
//    public static void loadPic(FragmentActivity fragmentActivity, Drawable drawable, ImageView imageView) {
//        Glide.with(fragmentActivity).load(drawable).into(imageView);
//    }
//
//
//
//
//    public static void loadPic(View view, String url, ImageView imageView) {
//        Glide.with(view).load(url).into(imageView);
//    }
//
//    public static void loadPic(View view, int resourseId, ImageView imageView) {
//        Glide.with(view).load(resourseId).into(imageView);
//    }
//
//    public static void loadPic(View view, Bitmap bitmap, ImageView imageView) {
//        Glide.with(view).load(bitmap).into(imageView);
//    }
//
//    public static void loadPic(View view, Drawable drawable, ImageView imageView) {
//        Glide.with(view).load(drawable).into(imageView);
//    }

}
