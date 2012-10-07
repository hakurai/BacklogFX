/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx.core;

import backlog4j.BacklogClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;

/**
 * @author eguchi
 */
public class BacklogFxModule extends AbstractModule {

    private static final BacklogFxModule INSTANCE = new BacklogFxModule();
    private BacklogClientProvider backlogClientProvider;
    private UserRepository userRepository;
    private ModelFactory modelFactory;
    private ServiceFactory serviceFactory;
    private TaskFactory taskFactory;

    public static BacklogFxModule getInstance() {
        return INSTANCE;
    }

    private BacklogFxModule() {
        backlogClientProvider = new BacklogClientProvider();
        userRepository = new UserRepository(backlogClientProvider);
        modelFactory = new ModelFactory(this);
        serviceFactory = new ServiceFactory(this);
        taskFactory = new TaskFactory(this);

    }

    @Override
    protected void configure() {


        bind(BacklogClient.class).toProvider(backlogClientProvider);

        bind(UserRepository.class).toProvider(new Provider<UserRepository>() {
            @Override
            public UserRepository get() {
                return userRepository;
            }
        });

        bind(ModelFactory.class).toProvider(new Provider<ModelFactory>() {
            @Override
            public ModelFactory get() {
                return modelFactory;
            }
        });

        bind(ServiceFactory.class).toProvider(new Provider<ServiceFactory>() {
            @Override
            public ServiceFactory get() {
                return serviceFactory;
            }
        });

        bind(TaskFactory.class).toProvider(new Provider<TaskFactory>() {
            @Override
            public TaskFactory get() {
                return taskFactory;
            }
        });


    }
}
