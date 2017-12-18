package cz.o2.tvs.stb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet(name = "SenderBin", urlPatterns = { "/SenderBin/*" })

public class SenderBin /*extends HttpServlet */ {

    Logger _LOG = Logger.getLogger(SenderBin.class.getName());
   
    private static final String CONTENT_TYPE = "image/bin"; //"";text/html; charset=UTF-8

    public void init(String filePatch) {
        File file = new File(filePatch);

        if (file.exists()) {
        }

    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    public void sendData(HttpServletRequest request, HttpServletResponse response, String filePatch) throws ServletException, IOException {


        String strLog = " sendData ##### line=" + request.getRequestURL().toString();
        _LOG.info(strLog);

        response.setContentType(CONTENT_TYPE);


        //filePatch = "/home/slotcz/files/upgrade/psi/4.10.7enc/firmware.bin";
        filePatch = "c:/files/upgrade/psi/4.10.7enc/firmware.bin";

        File file = new File(filePatch);

        if (file.exists()) {

            // Prepare streams.
            FileInputStream fis = new FileInputStream(file);

            ServletOutputStream out;
            out = response.getOutputStream();


            BufferedInputStream bin;
            bin = new BufferedInputStream(fis);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch = 0;

            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }

            bin.close();
            fis.close();
            bout.close();
            out.close();

        }

        /* out.println("<html>");
        out.println("<head><title>SenderBin</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST or GET. This is the reply.</p>");
        out.println("</body></html>");*/

    }

    
}
