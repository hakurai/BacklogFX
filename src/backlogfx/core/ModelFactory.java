package backlogfx.core;

import com.google.inject.Guice;
import com.google.inject.Module;

/**
 * @author eguchi
 */
public class ModelFactory {

    private final Module module;

    public ModelFactory(Module module) {
        this.module = module;
    }

    public <T> T createModel(Class<T> klass) {

        return Guice.createInjector(module).getInstance(klass);

    }

}
