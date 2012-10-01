/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backlogfx;

import backlog4j.BacklogClient;
import backlog4j.BacklogClientFactory;
import backlog4j.conf.BacklogConfigure;
import backlog4j.conf.BacklogConfigureBuilder;
import com.google.inject.AbstractModule;

import java.io.IOException;

/**
 * @author eguchi
 */
public class BacklogFxModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BacklogClient.class).toProvider(BacklogClientProvider.class);
    }

}
