package org.acme;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, " + idToken.getClaim("name");
    }


    @GET
    @Path("post-logout")
    @Produces(MediaType.TEXT_PLAIN)
    public String postLogout() {
        return "You were logged out";
    }
}