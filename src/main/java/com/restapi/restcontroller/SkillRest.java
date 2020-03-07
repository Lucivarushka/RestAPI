package com.restapi.restcontroller;

import com.restapi.model.Skill;
import com.restapi.repository.SkillRepository;
import com.restapi.repository.hibernate.JavaHibSkillRepositoryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/skill")
public class SkillRest {
    private final SkillRepository skillRepository = new JavaHibSkillRepositoryImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skill> list() throws Exception {
        return skillRepository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws Exception {
        Skill skill = skillRepository.getId(id);
        if (skill != null) {
            return Response.ok(skill, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Skill skill) throws Exception {
        skillRepository.create(skill);
        return Response.ok(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Skill skill) throws Exception {
        skill.setId(id);
        skillRepository.update(skill);
        return Response.ok(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws Exception {
        Skill skill = skillRepository.getId(id);
        skillRepository.delete(skill);
        if (skill != null) {
            return Response.ok(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
