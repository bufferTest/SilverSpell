package bkh.com.silverspell.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class CommonUtils {
    private static final CommonUtils ourInstance = new CommonUtils();

    private CommonUtils() {
    }

    public static CommonUtils getInstance() {
        return ourInstance;
    }

    public void setLocale(Context mContext) {

        String language = AppPreference.getInstance().getString("APP_LANGUAGE");

        final Resources resources = mContext.getResources();
        final Configuration configuration = resources.getConfiguration();
        if (language != null && language.trim().length() > 0) {
            Locale locale = new Locale(language);
            if (!configuration.locale.equals(locale)) {
                configuration.setLocale(locale);
                resources.updateConfiguration(configuration, null);
            }
        }

    }

}
