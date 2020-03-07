package com.restapi.restcontroller;

import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;
import com.restapi.repository.hibernate.JavaHibAccountRepositoryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/account")
public class AccountRest {
    private final AccountRepository accountRepository = new JavaHibAccountRepositoryImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> list() throws Exception {
        return accountRepository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws Exception {
        Account account = accountRepository.getId(id);
        if (account != null) {
            return Response.ok(account, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Account account) throws Exception {
        accountRepository.create(account);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Account account) throws Exception {
        account.setId(id);
        accountRepository.update(account);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws Exception {
        Account account = accountRepository.getId(id);
        accountRepository.delete(account);
        return Response.ok().build();
    }
}
