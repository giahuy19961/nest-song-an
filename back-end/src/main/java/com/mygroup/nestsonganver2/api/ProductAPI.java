/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.Filter;
import com.mygroup.nestsonganver2.dto.ProductDTO;
import com.mygroup.nestsonganver2.service.ProductService;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
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
 * @author ADMIN
 */
@Path("product")
public class ProductAPI {

    private static final ProductService productService = ProductService.getInstance();

    @Context
    UriInfo ui;

    // Show all products
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {

        List<ProductDTO> list = productService.showAllProducts();
        if (list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();

            return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByStatus(ProductDTO product){
        List<ProductDTO> list = productService.getByStatus(product.getStatus());
        if (list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();

            return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    //search products by name
    @GET
    @Path("search-by-name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByName(@PathParam("name") String keyword) {

        List<ProductDTO> list = productService.searchByName(keyword);
        if (list == null || list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();
 
        return Response.ok(list, MediaType.APPLICATION_JSON).build();

    }
    
     //Get all product by pages
    @GET
    @Path("/page/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByPages(@QueryParam("page") int page, @QueryParam("limit") int limit) {
        List<ProductDTO> product = productService.getAllByPages(page, limit);
        if (product == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }
    
    //Get count all product
    @GET
    @Path("/count/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountAllProduct(){
        int count = productService.countAllProduct();
        return Response.ok(count, MediaType.APPLICATION_JSON).build();
    }
    

    //add new product
    @PUT
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewProduct(ProductDTO product) throws URISyntaxException, NoSuchAlgorithmException {
        //authentication
        int id = productService.addNewProduct(product);
        if (id == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        } else {
            URI uri = new URI(ui.getBaseUri() + "product/" + id);
            return Response.created(uri).build();
        }

    }

    //search product by cate ID(show the products in the Category
    @GET
    @Path("search-by-cateid/{cateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByCateId(@PathParam("cateId") int cateId) {

        List<ProductDTO> list = productService.getProductByCateId(cateId);
        if (list == null || list.isEmpty()) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    //get product by cate ID(show the products in the Category
    @GET
    @Path("{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("Id") int Id) {
        ProductDTO product = productService.getProductById(Id);
        if (product == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }
    
    // update a product
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("isbn") int isbn, ProductDTO product)throws URISyntaxException, NoSuchAlgorithmException {
        product.setId(isbn);
        int result = productService.updateProduct(product);
        if (result == 0) 
            return Response.notModified().build();       
        else {
            URI uri = new URI(ui.getBaseUri() + "product/" + isbn);
            return Response.created(uri).build();
        }
        //return ve trang product
    }
    
    
    // Reactive product
    @DELETE
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("isbn") int isbn)throws URISyntaxException, NoSuchAlgorithmException {
        int result = productService.setProductStatus(isbn,0);
        if (result == 0) 
            return Response.notModified().build();       
        else {
            URI uri = new URI(ui.getBaseUri() + "product/" + isbn);
            return Response.created(uri).build();
        }
    }
    
    @PUT
    @Path("/reactive/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reactiveProduct(@PathParam("isbn") int isbn)throws URISyntaxException, NoSuchAlgorithmException {
        int result = productService.setProductStatus(isbn,1);
        if (result == 0) 
            return Response.notModified().build();       
        else {
            URI uri = new URI(ui.getBaseUri() + "product/" + isbn);
            return Response.created(uri).build();
        }
    }
   
    //filter 
    @POST
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filterProducts(Filter filter) throws NoSuchAlgorithmException {      
        List<ProductDTO> list = productService.filter(filter);
        if (list == null || list.isEmpty()) 
            return Response.status(Response.Status.NOT_MODIFIED).build();       
        return Response.ok(list, MediaType.APPLICATION_JSON).build();

    }
    
    //Show products by pages
    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByPages(@QueryParam("page") int page, @QueryParam("limit") int limit) {
        List<ProductDTO> product = productService.getProductByPages(page, limit);
        if (product == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }
    
   
    

}
