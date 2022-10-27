/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.ImageDTO;
import com.mygroup.nestsonganver2.dto.NewsDTO;
import com.mygroup.nestsonganver2.service.ImageService;
import com.mygroup.nestsonganver2.service.NewsService;
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
 * @author dd220
 */
@Path("news")
public class NewsAPI {
    
    private NewsService newsService = NewsService.getNewsService();
    
    private ImageService imgService = ImageService.getImageService();
    
    @Context
    UriInfo ui;
    
    //Get all news
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews() {
        List<NewsDTO> list = newsService.getAllNews();
        for (NewsDTO news : list) {
            List<ImageDTO> listImage = imgService.getImagesByNewsId(news.getId());
            if (!listImage.isEmpty())
                news.getListImages().add(listImage.get(0));
        }
        if (list.isEmpty()) 
        return Response.noContent().build();
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    
    //Get news by id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsById(@PathParam("id") int id) {
        NewsDTO dto = newsService.getNewsById(id);
        List<ImageDTO> listImage = imgService.getImagesByNewsId(id);
        dto.getListImages().addAll(listImage);
        if (dto.getTitle() == null) 
        return Response.notModified().build();
        return Response.ok(dto, MediaType.APPLICATION_JSON).build();
    }
    
    //Add news - havent tested yet
    @POST
    @Path("add")   
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNews(NewsDTO dto) throws URISyntaxException {
        int id = newsService.addNews(dto);
        if (id == 0) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        URI uri = new URI(ui.getBaseUri() + "news/" + id);
        return Response.created(uri).build();
    }
    
    //update news by id - haven't tested yet
    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNews(@PathParam("id") int id, NewsDTO dto) {
        NewsDTO updated = newsService.updateNews(id, dto);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        
        if (dto == null)
            return Response.status(Response.Status.NOT_MODIFIED).build();

        if (
            !updated.getDescription().equalsIgnoreCase(dto.getDescription()) ||
            !updated.getShortDescription().equalsIgnoreCase(dto.getShortDescription()) ||
            updated.getEmpId() != dto.getEmpId() ||
            !updated.getTitle().equalsIgnoreCase(dto.getTitle())
            )
            return Response.ok(updated, MediaType.APPLICATION_JSON).build();
        return Response.notModified().build();
    }
    
    //Delete news by id
    @DELETE
    @Path("/{id}")
    public Response deleteNews(@PathParam("id")int id) {
        int check = newsService.deleteNews(id);
        if (check == 0)
            return Response.notModified().build();
        return Response.accepted().build();
    }
}
