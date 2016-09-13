package fr.norsys.springbootexample.controler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import fr.norsys.springbootexample.bean.Message;
import fr.norsys.springbootexample.bean.MessageBean;

@Path("/")
@Controller
public class BaseController {

    @GET
    public String home() {
        return "Ceci est une application Spring-boot standalone!";
    }

    @GET
    @Path("/bonjour/{param}")
    public String homeWithParam(@PathParam("param") final String param) {
        return "Bonjour " + param;
    }

    @GET
    @Path("/json/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restCallJson(@PathParam("param") final String param) {
        return Response.ok().entity(new MessageBean("salut " + param)).build();
    }

    @GET
    @Path("/json2/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restCallJson2(@PathParam("param") final String param) {
        return Response.ok().entity(Message.build("coucou" + param)).build();
    }

}
