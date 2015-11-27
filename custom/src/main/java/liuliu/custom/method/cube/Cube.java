package liuliu.custom.method.cube;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

import liuliu.custom.method.cube.util.LocalDisplay;
import liuliu.custom.method.cube.util.NetworkStatusManager;

/**
 * Created by liuliu on 2015/11/27   14:51
 *
 * @author 柳伟杰
 * @Email 1031066280@qq.com
 */
public class Cube {

    private static Cube instance;

    private Application mApplication;

    public static void onCreate(Application app) {
        instance = new Cube(app);
    }

    private Cube(Application application) {
        mApplication = application;

        // local display
        LocalDisplay.init(application);

        // network status
        NetworkStatusManager.init(application);
    }

    public static Cube getInstance() {
        return instance;
    }

    public Context getContext() {
        return mApplication;
    }

    public String getAndroidId() {
        String id = Settings.Secure.getString(mApplication.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
}
