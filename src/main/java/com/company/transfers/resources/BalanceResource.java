package com.company.transfers.resources;

import com.company.transfers.resources.dto.Balance;
import com.company.transfers.service.BalanceService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Path("/balance")
public class BalanceResource {

    private final BalanceService balanceService;

    @Inject
    public BalanceResource(final BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @POST
    @Produces(APPLICATION_JSON)
    public Balance add(Balance balance) {
        return balanceService.save(balance);
    }
}
