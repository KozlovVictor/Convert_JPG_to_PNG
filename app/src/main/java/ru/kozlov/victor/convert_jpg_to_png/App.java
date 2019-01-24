package ru.kozlov.victor.convert_jpg_to_png;

import android.app.Application;

public class App extends Application {

    private static  App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
