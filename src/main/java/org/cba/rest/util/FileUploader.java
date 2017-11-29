package org.cba.rest.util;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by adam on 27/11/2017.
 */
public class FileUploader {
    public String uploadFile(InputStream is, FormDataContentDisposition fileDisposition) throws IOException {
        String fileExtension = getFileExtension(fileDisposition);
        String fileName = new Date().getTime() + "." + fileExtension;
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

    private String getFileExtension(FormDataContentDisposition fileDisposition) {
        String realFileName = fileDisposition.getFileName();
        String regex = ".*(\\.(jpg|jpeg|png|tiff))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(realFileName);
        if (!matcher.find()) {
            throw new FileTypeNotSupportedException(realFileName);
        }
        return matcher.group(2);
    }

    class FileTypeNotSupportedException extends WebApplicationException {
        public FileTypeNotSupportedException(String fileName) {
            super(
                    "Unsupported file type fo the uploaded file: " +
                    fileName +
                    ". The supported file types are: jpg,jpeg,png,tiff",
                    400
            );
        }
    }
}
