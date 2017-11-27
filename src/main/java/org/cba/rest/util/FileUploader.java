package org.cba.rest.util;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.*;

/**
 * Created by adam on 27/11/2017.
 */
public class FileUploader {
    public String uploadFile(InputStream is, FormDataContentDisposition fileDisposition) throws IOException {
        String fileName = fileDisposition.getFileName();
        String location = System.getenv("PROP_IMG_PATH") + fileName;
        File file = new File(location);
        OutputStream os = new FileOutputStream(file);
        byte[] buffer = new byte[256];
        int bytes;
        while ((bytes = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
        os.close();
        is.close();
        return System.getenv("PROP_IMG_HTTP_PATH") + fileName;
    }
}
