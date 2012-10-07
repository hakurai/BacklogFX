package backlogfx.core;

import com.google.inject.Guice;
import com.google.inject.Module;

/**
 * @author eguchi
 */
public class TaskFactory {

    private final Module module;

    public TaskFactory(Module module) {
        this.module = module;
    }

    public <T> T createTask(Class<T> klass) {

        return Guice.createInjector(module).getInstance(klass);

    }
}
