package com.company.transfers.resources;

import com.company.transfers.resources.dto.TransferDTO;
import com.company.transfers.resources.validation.TransferGroup;
import com.company.transfers.service.TransferService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

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
    public Response create(@Valid @ConvertGroup(to = TransferGroup.class) final TransferDTO transferDTO) {
        return Response.status(CREATED).entity(service.create(transferDTO)).build();
    }

    @GET @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public TransferDTO get(@PathParam("id") final Long id) {
        return service.get(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<TransferDTO> list() {
        return service.list();
    }
}
