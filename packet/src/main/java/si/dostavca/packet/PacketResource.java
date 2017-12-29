package si.dostavca.packet;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Metered;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Log
@Metered
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("packet")
@RequestScoped
public class PacketResource {

    @DiscoverService(value = "dostavca-notifications")
    Optional<WebTarget> targetNotifications;

    @DiscoverService(value = "dostavca-driver")
    Optional<WebTarget> targetDriver;

    @GET
    @Path("notifications")
    @CircuitBreaker
    @Fallback(fallbackMethod = "getNotificationsFallback")
    public Response getNotifications() {
        WebTarget service = targetNotifications.get().path("v1/notifications/all");

        Response response = service.request().get();

        return Response.fromResponse(response).build();
    }

    public Response getNotificationsFallback() {
        return Response.ok("{\"message\": \"Notifications are currently under maintenance.\"}").build();
    }

    @POST
    @Path("driver")
    @CircuitBreaker
    @Fallback(fallbackMethod = "requestDriverFallback")
    public Response requestDriver(Packet packet) {
        WebTarget service = targetDriver.get().path("v1/driver/request/");

        Response response = service.request().post(Entity.json(packet));

        return Response.fromResponse(response).build();
    }

    public Response requestDriverFallback(Packet packet) {
        return Response.ok("{\"message\": \"Error finding a driver. Please try again later.\"}").build();
    }

}
