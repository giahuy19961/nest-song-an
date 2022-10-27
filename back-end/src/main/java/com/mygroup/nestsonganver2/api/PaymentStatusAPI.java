/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.PaymentStatusDTO;
import com.mygroup.nestsonganver2.service.PaymentStatusService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author huy
 */
@Path("payment")
public class PaymentStatusAPI {
    private static final PaymentStatusService paymentService = PaymentStatusService.getPaymentStatusInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<PaymentStatusDTO> dtoList = paymentService.findAll();
        if (dtoList == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        if (dtoList.isEmpty()) return Response.status(Response.Status.NOT_MODIFIED).build();
        else return Response.ok(dtoList, MediaType.APPLICATION_JSON).build();
    }
}
