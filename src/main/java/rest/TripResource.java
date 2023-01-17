package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TripDTO;
import errorhandling.API_Exception;
import errorhandling.NotFoundException;
import utils.EMF_Creator;
import facades.TripFacade;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("trip")
public class TripResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final TripFacade FACADE =  TripFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"user"})
    public Response getAllTrips() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"user"})
    public Response getTripByID(@PathParam("id") Long id) throws NotFoundException {
        TripDTO tripDTO = FACADE.getById(id);
        return Response.ok().entity(tripDTO).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public Response createTopic(String jsonString) throws API_Exception {
        TripDTO tripDTO;
        try {
            tripDTO = GSON.fromJson(jsonString, TripDTO.class);
            tripDTO = FACADE.create(tripDTO);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        return Response.ok().entity(GSON.toJson(tripDTO)).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public Response updateTopic(String jsonString) throws API_Exception {
        TripDTO tripDTO;
        try {
            tripDTO = GSON.fromJson(jsonString, TripDTO.class);
            tripDTO = FACADE.update(tripDTO);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        return Response.ok().entity(GSON.toJson(tripDTO)).build();
    }

    @Path("{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public Response deleteTopic(@PathParam("id") Long tripID) throws API_Exception {
        TripDTO tripDTO;
        try {
            tripDTO = FACADE.deleteByID(tripID);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        return Response.ok().entity(GSON.toJson(tripDTO)).build();
    }
}
