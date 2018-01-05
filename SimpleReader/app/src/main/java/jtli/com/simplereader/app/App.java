package jtli.com.simplereader.app;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;

import jtli.com.simplereader.injector.component.AppComponent;
import jtli.com.simplereader.injector.component.DaggerAppComponent;
import jtli.com.simplereader.injector.module.AppModule;

/**
 * Created by Jingtian(Tansent).
 */

public class App extends Application {

    private static App instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);//一个utils库的初始化 https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

}
