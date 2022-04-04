import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/cookies/set")
public class SetCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie = new Cookie("hh-auth", "123456789101");
        cookie.setPath("/");

        try (PrintWriter writer = resp.getWriter()) {
            resp.addCookie(cookie);
            writer.println("cookie \"hh-auth\" added");
        }
    }
}
