package com.softrunapps.rashapplication.component.dagger;

import com.softrunapps.rashapplication.view.main.MainActivity;
import com.softrunapps.rashapplication.view.main.ui.video_content.VideoContentFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(VideoContentFragment videoContentFragment);
}
