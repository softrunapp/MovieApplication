package com.softrunapps.rashapplication.component;

import android.app.Application;

import com.softrunapps.rashapplication.component.dagger.AppComponent;
import com.softrunapps.rashapplication.component.dagger.AppModule;
import com.softrunapps.rashapplication.component.dagger.DaggerAppComponent;

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
