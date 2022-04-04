import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;

@WebServlet(urlPatterns = "/counter/clear")
public class CounterCleanerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String cookieName = "hh-auth";
        Cookie[] cookies = req.getCookies();
        String cookieValue;

        if (cookies != null) {
            cookieValue = Arrays.stream(cookies)
                    .filter(c -> cookieName.equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse("");
        } else {
            cookieValue = "";
        }

        if (cookieValue.length() > 10) {
            CounterServlet.clearCounter();
        } else {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
        }
    }
}
