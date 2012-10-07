package backlogfx.core;

import com.google.inject.Guice;
import com.google.inject.Module;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

/**
 * @author eguchi
 */
public class InjectFXMLLoader {

    private final Module module;
    private final URL url;
    private FXMLLoader loader;

    public InjectFXMLLoader(Module module, URL url) {
        this.module = module;
        this.url = url;
    }

    public Object load() throws IOException {
        loader = new FXMLLoader(url);
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return Guice.createInjector(module).getInstance(aClass);
            }
        });

        return loader.load();

    }

    public Object getController() {
        return loader.getController();
    }


}
