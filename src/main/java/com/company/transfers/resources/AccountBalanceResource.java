package com.company.transfers.resources;

import com.company.transfers.resources.dto.AccountBalanceDTO;
import com.company.transfers.service.AccountBalanceService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Path("/account-balances")
public class AccountBalanceResource {

    private final AccountBalanceService accountBalanceService;

    @Inject
    public AccountBalanceResource(final AccountBalanceService accountBalanceService) {
        this.accountBalanceService = accountBalanceService;
    }

    @POST
    @Produces(APPLICATION_JSON)
    public AccountBalanceDTO add(AccountBalanceDTO accountBalance) {
        return accountBalanceService.save(accountBalance);
    }
}
