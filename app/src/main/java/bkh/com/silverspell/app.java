package bkh.com.silverspell;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import bkh.com.silverspell.utils.CommonUtils;
import io.fabric.sdk.android.Fabric;

public class app extends Application {
    private static app instance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fabric.with(instance, new Crashlytics());
        Stetho.initializeWithDefaults(instance);
        CommonUtils.getInstance().setLocale(instance);
    }
}
