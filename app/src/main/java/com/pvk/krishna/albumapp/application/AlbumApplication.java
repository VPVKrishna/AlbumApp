package com.pvk.krishna.albumapp.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.pvk.krishna.albumapp.volley.RequestManager;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.utils.Logger;

/**
 * Created by Krishna on 01/06/2015.
 */
public class AlbumApplication extends Application {

    private static final String APP_ID = "852596841501254";
    private static final String APP_NAMESPACE = "samplealbumapp";

    // key hash -->   b7T051clInri32j1CsUIT9xWeMc=

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializing the request manager.
        RequestManager.init(getApplicationContext());

        ImageLoader imageLoader = ImageLoader.getInstance();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .diskCacheFileCount(300)
                .threadPoolSize(5).threadPriority(Thread.MAX_PRIORITY).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(80 * 1024 * 1024).diskCacheFileCount(200).build();

        imageLoader.init(config);

        // set log to true
        Logger.DEBUG_WITH_STACKTRACE = true;

        // initialize facebook configuration
        Permission[] permissions = new Permission[]{
                Permission.PUBLIC_PROFILE,
                Permission.PUBLISH_ACTION,
        };

        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(APP_ID)
                .setNamespace(APP_NAMESPACE)
                .setPermissions(permissions)
                        //.setDefaultAudience(SessionDefaultAudience.FRIENDS)
                .setAskForAllPermissionsAtOnce(false)
                .build();

        SimpleFacebook.setConfiguration(configuration);
    }
}


/**
 * dependencies {
 * compile fileTree(dir: 'libs', include: ['*.jar'])
 * compile 'com.android.support:appcompat-v7:22.2.0'
 * compile 'com.android.support:recyclerview-v7:21.0.0'
 * compile 'com.android.support:cardview-v7:21.0.0'
 * compile 'com.jeremyfeinstein.slidingmenu:library:1.3@aar'
 * compile 'com.facebook.android:facebook-android-sdk:3.23.+'
 * compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.3'
 * compile 'com.mcxiaoke.volley:library:1.0.+'
 * compile 'com.google.code.gson:gson:2.2.2'
 * }
 */