package com.mrym.newsbulletion;

import android.app.Application;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;


/**
 * Created by Jian on 2016/8/25.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class NewsApplication extends Application {

    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(CONTEXT)
//                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParamsSupplier)
//                .setCacheKeyFactory(cacheKeyFactory)
//                .setDownsampleEnabled(true)
//                .setWebpSupportEnabled(true)
//                .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)
//                .setExecutorSupplier(executorSupplier)
//                .setImageCacheStatsTracker(imageCacheStatsTracker)
//                .setMainDiskCacheConfig(mainDiskCacheConfig)
//                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
//                .setNetworkFetchProducer(networkFetchProducer)
//                .setPoolFactory(poolFactory)
//                .setProgressiveJpegConfig(progressiveJpegConfig)
//                .setRequestListeners(requestListeners)
//                .setSmallImageDiskCacheConfig(smallImageDiskCacheConfig)
//                .build();
        Fresco.initialize(CONTEXT);
    }

    public static Context getContext() {
        return CONTEXT;
    }

}
