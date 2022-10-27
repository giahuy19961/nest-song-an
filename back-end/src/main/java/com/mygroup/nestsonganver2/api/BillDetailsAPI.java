/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.BillDetailsDTO;
import com.mygroup.nestsonganver2.service.BillDetailsService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Silver King
 */
@Path("bill-details")
public class BillDetailsAPI {

    private static final BillDetailsService billDetailsService = BillDetailsService.getInstance();

    @Context
    UriInfo ui;

    //--------------------------------------------------------------------------
    //create new bill details
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertNewBillDetails(BillDetailsDTO billDetails) throws URISyntaxException {
        int id = billDetailsService.insertNewBillDetails(billDetails);
        if (id == 0) {
            return Response.notModified().build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "bill-details/" + id);
            return Response.created(uri).build();
        }
    }

    //--------------------------------------------------------------------------
    //find bill details by...
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillDetails() {
        List<BillDetailsDTO> list = billDetailsService.findAll();
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        BillDetailsDTO billDetails = billDetailsService.findById(id);
        if (billDetails == null) {
            return Response.notModified().build();
        } else {
            return Response.ok(billDetails, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bill-id/{id}")
    public Response getByBillId(@PathParam("id") int id) {
        List<BillDetailsDTO> list = billDetailsService.findByBillId(id);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    //--------------------------------------------------------------------------
    //update
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateBillDetails(@PathParam("id") int id, BillDetailsDTO billDetails) {
        int result = billDetailsService.updateBillDetails(id, billDetails);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }

    //--------------------------------------------------------------------------
    //delete
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteBillDetails(@PathParam("id") int id, BillDetailsDTO billDetails) {
        billDetails.setId(id);
        int result = billDetailsService.deleteBillDetails(billDetails);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }
}
