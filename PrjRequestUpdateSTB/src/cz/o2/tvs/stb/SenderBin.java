package cz.o2.tvs.stb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SenderBin {

    Logger _LOG = Logger.getLogger(SenderBin.class.getName());

    private static final String CONTENT_TYPE = "image/bin";
   // private static final String FILES_PREFIX = "c:/files/upgrade";
   private static final String FILES_PREFIX = "/mnt/data/sftp/stb_firmware/files/upgrade";

    public void init(String filePatch) {
        File file = new File(filePatch);
        if (file.exists()) {
        }

    }


    public void sendData(HttpServletRequest request, HttpServletResponse response, String filePatch) throws ServletException, IOException {

        filePatch = request.getPathInfo().toString();
        String strLog = " sendData ##### path=" + filePatch;
        _LOG.info(strLog);
        response.setContentType(CONTENT_TYPE);

        // filePatch = "/mnt/data/sftp/stb_firmware/files/upgrade/psi/4.12.11enc/firmware.bin";
        filePatch = FILES_PREFIX + filePatch;
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
        } else {
            strLog = " sendData #####  file=" + file.getAbsolutePath() + " notfound";
            _LOG.info(strLog);
        }
        strLog = " sendData #####  file=" + file.getAbsolutePath();
        _LOG.info(strLog);
    }


}
