/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.CategoryDTO;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.service.CategoryService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ADMIN
 */
@Path("category")
public class CategoryAPI {
       
    private static final CategoryService categoryService = CategoryService.getInstance();
    
    @Context
    UriInfo ui;
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {

        List<CategoryDTO> list = categoryService.getAllCategories();
        if (list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();

        return Response.ok(list, MediaType.APPLICATION_JSON).build();
        

    }
    
    @GET
    @Path("{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("Id") int Id) {
        List<CategoryDTO> list = categoryService.getCategoryById(Id);
        if (list == null || list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();

        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
   
}
