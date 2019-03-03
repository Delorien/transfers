package com.company.transfers.resources;

import com.company.transfers.resources.dto.TransferDTO;
import com.company.transfers.service.TransferService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

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

    @GET @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public TransferDTO get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<TransferDTO> list() {
        return service.list();
    }
}
