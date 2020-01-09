package com.ramkiopt.main.services.utils;

import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import java.io.IOException;

public final class FilesUtils {
    private FilesUtils() {
    }

    public static String getContentType(ServletContext servletContext, Resource resource) {
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = servletContext.getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}
