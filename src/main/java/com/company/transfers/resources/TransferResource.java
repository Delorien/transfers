package com.company.transfers.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lneto on 02/03/19.
 */
@Path("/transfer")
public class TransferResource {

    @GET
    @Produces(APPLICATION_JSON)
    public String hello() {
        return "Hellow!";
    }
}
