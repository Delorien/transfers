package com.company.transfers.resources;

import com.company.transfers.resources.dto.TransferDTO;
import com.company.transfers.service.TransferService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lneto on 02/03/19.
 */
@Path("/transfers")
public class TransferResource {

    private final TransferService service;

    @Inject
    public TransferResource(final TransferService service) {
        this.service = service;
    }

    @POST
    @Produces(APPLICATION_JSON)
    public TransferDTO create(TransferDTO transferDTO) {
        return service.create(transferDTO);
    }
}
