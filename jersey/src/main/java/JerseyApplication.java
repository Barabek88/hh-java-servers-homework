
import jakarta.ws.rs.core.Application;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication extends Application {

    private static Server createServer(int port) {
        Server server = new Server(port);
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitParameter("jakarta.ws.rs.Application", "application.CounterApplication");

        return server;
    }

    public static void main(String[] args) throws Exception {
        int port = 8081;
        Server server = createServer(port);
        server.start();
        server.join();
    }
}
