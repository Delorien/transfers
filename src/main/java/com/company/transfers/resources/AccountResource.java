package com.company.transfers.resources;

import com.company.transfers.resources.dto.AccountDTO;
import com.company.transfers.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Path("/accounts")
public class AccountResource {

    private final AccountService service;

    @Inject
    public AccountResource(final AccountService service) {
        this.service = service;
    }

    @POST
    @Produces(APPLICATION_JSON)
    public AccountDTO add(AccountDTO account) {
        return service.save(account);
    }

    @GET @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public AccountDTO get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<AccountDTO> list() {
        return service.list();
    }
}
