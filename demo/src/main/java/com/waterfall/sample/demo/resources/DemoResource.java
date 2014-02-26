package com.waterfall.sample.demo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.inject.Singleton;

@Path("demo")
@Singleton
public class DemoResource {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response test() {
        return Response.ok().build();
    }

}
