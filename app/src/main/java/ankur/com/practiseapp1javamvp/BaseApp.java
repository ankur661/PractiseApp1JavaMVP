package ankur.com.practiseapp1javamvp;

import android.os.Bundle;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import ankur.com.practiseapp1javamvp.deps.Deps;
import ankur.com.practiseapp1javamvp.networking.NetworkModule;

public class BaseApp  extends AppCompatActivity {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}