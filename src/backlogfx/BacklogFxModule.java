/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx;

import com.google.inject.AbstractModule;

/**
 * @author eguchi
 */
public class BacklogFxModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BacklogFxContext.class).toProvider(BacklogFxContextProvider.class).asEagerSingleton();
    }
}
