package cz.o2.vs.ustb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class ConfirmConnect extends HttpServlet {
    
    private static final String CONTENT_TYPE = "text/html; charset=windows-1250";
    String respStr = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\"> eto tovet ot apache";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        out.println(respStr);
        /*
        out.println("<html>");
        out.println("<head><title>ConfirmConnect</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
*/
        out.close();
    }
}
