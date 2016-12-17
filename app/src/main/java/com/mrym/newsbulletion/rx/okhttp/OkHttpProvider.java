package com.mrym.newsbulletion.rx.okhttp;

import com.jakewharton.rxbinding.internal.Preconditions;
import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.utils.Logger;
import com.mrym.newsbulletion.utils.common.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jian on 2016/12/16.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class OkHttpProvider {

    private final static long DEFAULT_TIMEOUT = 10;

    public static OkHttpClient getDefaultOkHttpClient() {
        return getNoCacheOkHttpClient(new CacheControlInterceptor());
    }

    public static OkHttpClient getNoCacheOkHttpClient() {
        return getNoCacheOkHttpClient(new FromNetWorkControlInterceptor());
    }

    private static OkHttpClient getNoCacheOkHttpClient(Interceptor cacheControl) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
        File httpCacheDirectory = new File(NewsApplication.getContext().getCacheDir(), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        //设置拦截器
        httpClientBuilder.addInterceptor(new LoggingInterceptor());
        httpClientBuilder.addNetworkInterceptor(cacheControl);
        httpClientBuilder.addInterceptor(new UserAgentInterceptor("Android Device"));
        return httpClientBuilder.build();
    }

    /**
     * 缓存过滤器
     */
    private static class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(NewsApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);

            if (NetWorkUtils.isNetConnected(NewsApplication.getContext())) {
                int maxAge = 60 * 60;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 30;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    private static class FromNetWorkControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    }

    private static class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private final String userAgentHeaderValue;

        UserAgentInterceptor(String userAgentHeaderValue) {
            this.userAgentHeaderValue = Preconditions.checkNotNull(userAgentHeaderValue, "userAgentHeaderValue = null");
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    /**
     * Log信息拦截器
     */
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger.i(String.format(Locale.CHINA, "Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Logger.i(String.format(Locale.CHINA, "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
