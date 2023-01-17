package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.GuideDTO;
import dtos.TripDTO;
import errorhandling.NotFoundException;
import facades.GuideFacade;
import facades.TripFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("guide")
public class GuideResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final GuideFacade FACADE =  GuideFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllGuides() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGuideByID(@PathParam("id") Long id) throws NotFoundException {
        GuideDTO guideDTO = FACADE.getById(id);
        return Response.ok().entity(guideDTO).build();
    }
}
