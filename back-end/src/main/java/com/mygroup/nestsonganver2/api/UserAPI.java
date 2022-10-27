/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.api;

import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.service.UserService;
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
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author huy
 */
@Path("user")
public class UserAPI {

    private static final UserService userService = UserService.getInstance();
    
    @Context
    private ContainerRequestContext ctx;
    
    @Context
    UriInfo ui;

    // Get all user in database
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
//        UserDTO dto = (UserDTO) ctx.getProperty("tokenObject");
        List<UserDTO> list = userService.findAllUsers("admin");
        if (list == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        if (list.isEmpty()) return Response.status(Response.Status.NOT_MODIFIED).build();
        else return Response.ok(list, MediaType.APPLICATION_JSON).build();
        


    }
    // -------------------------------------------------------------------------

   // get user by id 
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneUserById(@PathParam("isbn") int isbn) {
//        UserDTO dto = (UserDTO) httpRequest.getAttribute("tokenObject");
        UserDTO user = userService.getUserById(isbn, 1, "admin");
        if (user == null) return Response.status(Response.Status.UNAUTHORIZED).build();
        if (user.getId() == 0) return Response.status(Response.Status.NOT_MODIFIED).build();
        else return Response.ok(user, MediaType.APPLICATION_JSON).build();

    }

    //--------------------------------------------------------------------------
    // insert new user to database
    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(UserDTO user) throws URISyntaxException, NoSuchAlgorithmException {

            String token = userService.insertUser(user);
        if (token == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        } else {
            return Response.ok(token, MediaType.APPLICATION_JSON).build();
        }

    }

    //--------------------------------------------------------------------------
    // get user by username and password
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(UserDTO user) throws NoSuchAlgorithmException {

        String token = userService.checkLogin(user);
        if (token != null) {
            return Response.ok(token, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }
    
    // Register
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO user) {
        return null;
    }

    //--------------------------------------------------------------------------
    // Update an user in database
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("isbn") int isbn, UserDTO user) {

        int result;
        user.setId(isbn);
        result = userService.updateUser(user);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }

    }
    
    // Update user password
    
    @PUT
    @Path("update-password/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(@PathParam("isbn") int isbn, UserDTO user) throws NoSuchAlgorithmException {

        int result;       
        result = userService.updateUserPassword(isbn, user.getPassword());
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }

    } 

    //--------------------------------------------------------------------------
    // Delete an user by changing status
    @DELETE
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("isbn") int isbn) {

        int result ;
        result = userService.updateUserStatus(isbn, 0);
        if (result == 0) {
            return Response.notModified().build();
        } else {
            return Response.ok().build();
        }
    }
    //--------------------------------------------------------------------------
}
