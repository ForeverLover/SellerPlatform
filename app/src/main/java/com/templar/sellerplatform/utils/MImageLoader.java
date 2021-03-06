package com.templar.sellerplatform.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

public class MImageLoader {
    private static MImageLoader mImageLoader;

    private ImageLoader imageLoader;
    private DisplayImageOptions displayImageOptions;

    public static final String URL_IMGCACHE = Constants.Path.PATH_CACHE
            + File.separator + "ImgCache";

    private MImageLoader(Context context) {

        if (imageLoader == null) {

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    context)
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .writeDebugLogs() // Remove for release app
                    .threadPoolSize(4)
                    .diskCache(new UnlimitedDiskCache(new File(URL_IMGCACHE))).
                            build();

            displayImageOptions = new DisplayImageOptions.Builder().
            cacheOnDisk(true).cacheInMemory(false).
                    displayer(new RoundedBitmapDisplayer(0))
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();

            imageLoader = ImageLoader.getInstance();
            imageLoader.init(config);
        }
    }

    public static MImageLoader getInstance(Context context) {

        syncInit(context);

        return mImageLoader;
    }

    private static synchronized void syncInit(Context context) {
        if (mImageLoader == null) {
            mImageLoader = new MImageLoader(context);
        }
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void displayImage(String uri, ImageView imageView) {
        imageLoader.displayImage(uri, imageView, displayImageOptions);
    }

    public void displayImageM(String uri, ImageView imageView) {
        displayImage(getAbsoluteURL(uri), imageView);
    }

    public void displayImage(String uri, ImageView imageView,
                             DisplayImageOptions displayImageOptions) {
        imageLoader.displayImage(uri, imageView, displayImageOptions);

    }

    public void displayImage(String uri, ImageView imageView,
                             DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        imageLoader.displayImage(uri, imageView, displayImageOptions, imageLoadingListener);

    }

    public void displayImage(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        imageLoader.displayImage(uri, imageView, imageLoadingListener);
    }

    public void loadImage(String uri) {

        imageLoader.loadImage(uri, displayImageOptions, null);
    }

    public void loadImage(String uri, ImageLoadingListener listener) {

        imageLoader.loadImage(uri, displayImageOptions, listener);
    }

    public Bitmap loadImageSync(String uri) {
        return imageLoader.loadImageSync(uri);
    }

    public Bitmap loadImageSync(String uri, ImageSize targetImageSize) {
        return imageLoader.loadImageSync(uri, targetImageSize);
    }

    public static String getAbsoluteURL(String url) {
        return Constants.Url.URL_PREFIX_FILE + url;
    }

}
