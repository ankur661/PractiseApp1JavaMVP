package ankur.com.practiseapp1javamvp.deps;

import javax.inject.Singleton;

import ankur.com.practiseapp1javamvp.home.HomeActivity;
import ankur.com.practiseapp1javamvp.networking.NetworkModule;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}