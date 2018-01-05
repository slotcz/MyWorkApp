package cz.o2.tvs.stb.servlet;

import cz.o2.tvs.stb.CheckerVersion;
import cz.o2.tvs.stb.SenderBin;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.InetAddress;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RequestSTB", urlPatterns = { "/upgrade/*" }) //
public class RequestSTB extends HttpServlet {
    @SuppressWarnings("compatibility:2991048014199066562")
    private static final long serialVersionUID = 5257497995762430951L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String varMAC = "";
    private String varFWversion = "";
    private String line = "";
    private String pathImage = "";
    private transient CheckerVersion checker;

    transient Logger _LOG = Logger.getLogger(RequestSTB.class.getName());

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        checker = new CheckerVersion();
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String strLog = " public void service line=" +  request.getRequestURL().toString() + "*endline*";
        _LOG.info(strLog);
        
        if (request.getParameterMap().size() <= 0) {
            SenderBin senderBin = new SenderBin();
            senderBin.sendData(request, response, "");
        } else {
            try {
                line = request.getRequestURL().toString();
                String[] splitPath = request.getPathInfo()
                                            .trim()
                                            .split("/");
                pathImage = splitPath[1]; // image
                varMAC = request.getParameter("mac");
                varFWversion = request.getParameter("current_firmware_version");
            } catch (Exception e) {
                _LOG.severe(e.getMessage());
            }

            strLog =
                "***** line=" + line + "*endline*" + " path=" + pathImage + "*endpath* " + " mac=" + varMAC + "*mac*" + " current_firmware_version=" +
                varFWversion + "*current_firmware_version*";
            _LOG.info(strLog);
            //TODO for debug only if (pathImage.equals("psi"))
            
            {
                if (checker.checkMAC(varMAC)) {
                    varFWversion = checker.getNewVersion(varMAC, pathImage, varFWversion);
                    if (!varFWversion.isEmpty()) {
                        responcer(response);
                        // responcerImage(response);
                    }
                } else {
                    _LOG.warning(" The device address " + varMAC + " is not in the database or is in the black list.");
                }
            }
        }
    }

    private void responcerImage(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.print(varFWversion + ",");
        out.print("Firmware version " + varFWversion + ",");
        out.print("http://10.32.204.193:8080/webappSTB/upgrade/" + pathImage + "/");
        out.print("http://localhost:8080/webappSTB/images/flower1.png"); // varFWversion
        out.println("");
        out.close();

    }

    private void responcer(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.print(varFWversion + ",");
        out.print("Firmware version " + varFWversion + ",");
        InetAddress ip = InetAddress.getLocalHost();

       out.print("http://pds.o2tv.cz/upgrade/" + pathImage + "/");

        //TODO debug only  out.print("http://" + ip.getHostName() + ":8080/upgrade/" + pathImage + "/");

        //       out.print("http://" + ip.getHostAddress() + ":28083/upgrade/" + pathImage + "/"); //10.32.204.199 /webUpgradeSTB 8080

        //   out.print("http://" + ip.getHostName() + ":8080/webUpgradeSTB/upgrade/" + pathImage + "/");

        out.print(checker.getcodePath(varFWversion, varMAC)); // varFWversion
        out.println("");
        out.close();
        String strLog =
            "line=" + line + "*endline*" + " path=" + pathImage + "*endpath* " + " mac=" + varMAC + "*mac*" + " current_firmware_version=" + varFWversion +
            "*current_firmware_version*";
        _LOG.info(strLog);
    }

    private void responcer2(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>RequestSTB</title></head>");
        out.println("<body>");
        out.println("<p>");
        out.println(varFWversion + ",");
        out.println("Firmware version " + varFWversion + ",");
        out.println("http://pds.o2tv.cz/upgrade/" + pathImage + "/");
        out.println(varFWversion + "/firmware.bin");
        out.println("</p>");
        out.println("</body></html>");
        out.close();
        String strLog =
            "line=" + line + "*endline*" + " path=" + pathImage + "*endpath* " + " mac=" + varMAC + "*mac*" + " current_firmware_version=" + varFWversion +
            "*current_firmware_version*";
        _LOG.info(strLog);
    }


    private void responcer1(HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>RequestSTB</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST or GET. This is the reply.</p>");
        out.println("<p>line=" + line + "*endline*</p>");
        out.println("<p>path=" + pathImage + "*endpath*</p>");
        out.println("<p>mac=" + varMAC + "*mac*</p>");
        out.println("<p>current_firmware_version=" + varFWversion + "*current_firmware_version*</p>");
        out.println("</body></html>");
        out.close();
        String strLog =
            "line=" + line + "*endline*" + " path=" + pathImage + "*endpath* " + " mac=" + varMAC + "*mac*" + " current_firmware_version=" + varFWversion +
            "*current_firmware_version*";
        _LOG.info(strLog);
    }


}
