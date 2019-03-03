package com.company.transfers.configuration;

import com.company.transfers.resources.AccountBalanceResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(new ApplicationBinder());
        register(AccountBalanceResource.class);
        packages("com.company.transfers.resources");
    }
}
