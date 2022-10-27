/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mygroup.nestsonganver2.dto.ImageDTO;
import com.mygroup.nestsonganver2.service.ImageService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author dd220
 */

@Path("image")
public class ImageAPI {
    
    private ImageService imgService = ImageService.getImageService();
    
    @Context
    UriInfo ui;
    
    
    //Add image
    @POST
    @Path("add")
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addImage(ImageDTO img) throws Exception {
        int id = imgService.addImage(img);
        if (id == 0) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "image/" + id);
            return Response.created(uri).build();
        }
    }
    
    //Get Image by Id
    @Path("{id}")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public Response getImageById(@PathParam("id")int id) {
        ImageDTO img = imgService.getImageById(id);
        if (img == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(img, MediaType.APPLICATION_JSON).build();
    }
    
    //Get all Image
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public Response getAllImages() {
        List<ImageDTO> listImages = imgService.getAllImages();
        if (listImages == null || listImages.isEmpty()) 
        return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(listImages, MediaType.APPLICATION_JSON).build();
    }
    
    
    //Get Image by productId
    @GET
    @Path("product/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getImagesByProductId(@PathParam("id")int productId){
        List<ImageDTO> listImages = imgService.getImagesByProductId(productId);
        if (listImages == null || listImages.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(listImages, MediaType.APPLICATION_JSON).build();
    }

    //Get Image by newsId
    @GET
    @Path("news/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getImagesByNewsId(@PathParam("id")int newsId){
        List<ImageDTO> listImages = imgService.getImagesByNewsId(newsId);
        if (listImages == null || listImages.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(listImages, MediaType.APPLICATION_JSON).build();
    }
    
    //Update Image
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateImage(@PathParam("id")int id, ImageDTO dto) {
        ImageDTO updated = imgService.updateImageById(id, dto);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        if (dto == null)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        if(
            !updated.getImgPath().equals(dto) ||
            updated.getNewsId().intValue() != dto.getNewsId() ||
            updated.getProductId().intValue() != dto.getProductId()
            )
            return Response.ok(updated, MediaType.APPLICATION_JSON).build();
        
        return Response.notModified().build();
    }
    
    //Delete Image by ID
    @DELETE
    @Path("/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public Response deleteImage(@PathParam("id")int id) {
        if (imgService.getImageById(id) == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        boolean check = imgService.deleteImage(id); 
        if (check)
            return Response.accepted(id + " has been deleted").build();
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
