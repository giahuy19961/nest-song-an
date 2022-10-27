/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mygroup.nestsonganver2.dto.BillDTO;
import com.mygroup.nestsonganver2.service.BillService;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Silver King
 */
@Path("bill")
public class BillAPI {

    private static final BillService BILLS_SERVICE = BillService.getInstance();

    @Context
    UriInfo ui;

    //--------------------------------------------------------------------------
    //find bill
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBill() {
        List<BillDTO> list = BILLS_SERVICE.getAllBill();
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getBillById(@PathParam("id") int id) {
        BillDTO bill = BILLS_SERVICE.getBillById(id);
        if (bill == null) {
            return Response.notModified().build();
        } else {
            return Response.ok(bill, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/status/{status}")
    public Response getBillByStatus(@PathParam("status") int status) {
        List<BillDTO> list = BILLS_SERVICE.getBillByStatus(status);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer-id/{customerId}")
    public Response getBillByCustomerId(@PathParam("customerId") int customerId) {
        List<BillDTO> list = BILLS_SERVICE.getBillByCustomerId(customerId);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employee-id/{empId}")
    public Response getBillByEmpId(@PathParam("empId") int empId) {
        List<BillDTO> list = BILLS_SERVICE.getBillByEmpId(empId);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/employee-id-status")
    public Response getBillByEmpIdAndStatus(@QueryParam("empId") int empId, @QueryParam("Status") int status) {
        List<BillDTO> list = BILLS_SERVICE.getBillByEmpIdAndStatus(empId, status);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer-id-status")
    public Response getBillByCustomerIdAndStatus(@QueryParam("customerId") int customerId, @QueryParam("Status") int status) {
        List<BillDTO> list = BILLS_SERVICE.getBillByCUstomerIdAndStatus(customerId, status);
        if (list.isEmpty()) {
            return Response.notModified().build();
        } else {
            return Response.ok(list, MediaType.APPLICATION_JSON).build();
        }
    }

    //--------------------------------------------------------------------------
    //insert bill
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBill(BillDTO bill) throws URISyntaxException {
        int id = BILLS_SERVICE.insertNewBill(bill);
        if (id == 0) {
            return Response.notModified().build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "bill/" + id);
            return Response.created(uri).build();
        }
    }

    //-------------------------------------------------------------------------
    //update bill
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateBill(@PathParam("id") int id, BillDTO bill) {
        int result = BILLS_SERVICE.updateBill(id, bill);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }

    //--------------------------------------------------------------------------
    // (change status)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update-status")
    public Response updateStatus(@QueryParam("id") int id, @QueryParam("status") int status) {
        int result = BILLS_SERVICE.updateStatus(id, status);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }
    
    //--------------------------------------------------------------------------
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response deleteOrder(@PathParam("id") int id){
          int result = BILLS_SERVICE.updateStatus(id, 4);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }
}
