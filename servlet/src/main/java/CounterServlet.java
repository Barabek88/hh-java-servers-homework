import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    public static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("counter = " + counter);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        counter.incrementAndGet();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getHeader("Subtraction-Value");

        if (StringUtils.isNumeric(value)) {
            counter.getAndAdd(-Integer.parseInt(value));
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
        }
    }

    public static void clearCounter() {
        counter.set(0);
    }

}
