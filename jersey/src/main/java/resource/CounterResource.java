package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Counter;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/counter")
public class CounterResource {
    public static final AtomicInteger counter = new AtomicInteger(0);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Counter getCounter() {
        return new Counter(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), counter.get());
    }

    @POST
    public Response addCounter() {
        counter.incrementAndGet();
        return Response.ok("The counter is incremented").build();
    }

    @DELETE
    public Response subtractCounter(@Context HttpHeaders headerParam) {
        String value = headerParam.getHeaderString("Subtraction-Value");
        if (StringUtils.isNumeric(value)) {
            counter.getAndAdd(-Integer.parseInt(value));
            return Response.ok("The counter is subtracted").build();
        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@Context HttpHeaders headerParam) {
        Cookie cookieValue = headerParam.getCookies().get("hh-auth");

        if (cookieValue != null && cookieValue.getValue().length() > 10) {
            counter.set(0);
            return Response.ok("The counter is cleared").build();
        } else {
            return Response.status(HttpStatus.FORBIDDEN_403).build();
        }
    }
}
