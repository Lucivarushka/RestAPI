package com.restapi.restcontroller;

import com.restapi.model.Developer;
import com.restapi.repository.DeveloperRepository;
import com.restapi.repository.hibernate.JavaHibDeveloperRepositoryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dev")
public class DeveloperRest {
    private final DeveloperRepository developerRepository = new JavaHibDeveloperRepositoryImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> list() throws Exception {
        return developerRepository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws Exception {
        Developer developer = developerRepository.getId(id);
        if (developer != null) {
            return Response.ok(developer, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Developer developer) throws Exception {
        developerRepository.create(developer);
        return Response.ok(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Developer developer) throws Exception {
        developer.setId(id);
        developerRepository.update(developer);
        return Response.ok(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws Exception {
        Developer developer = developerRepository.getId(id);
        developerRepository.delete(developer);
        if (developer != null) {
            return Response.ok(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
