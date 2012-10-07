package backlogfx.core;

import com.google.inject.Guice;
import com.google.inject.Module;

/**
 * @author eguchi
 */
public class ServiceFactory {

    private final Module module;

    public ServiceFactory(Module module) {
        this.module = module;
    }

    public <T> T createService(Class<T> klass) {

        return Guice.createInjector(module).getInstance(klass);

    }
}
