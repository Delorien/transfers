package com.company.transfers.configuration;

import com.company.transfers.resources.AccountResource;
import com.company.transfers.resources.TransferResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(new ApplicationBinder());
        register(AccountResource.class);
        register(TransferResource.class);
        packages("com.company.transfers.resources");
    }
}
