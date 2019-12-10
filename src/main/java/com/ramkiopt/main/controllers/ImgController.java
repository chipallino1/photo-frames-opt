package com.ramkiopt.main.controllers;

import com.ramkiopt.main.services.utils.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ImgController {

    private final FileStorageService fileStorageService;

    public ImgController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getFile(@RequestParam String fileName, HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        HttpHeaders headers = new HttpHeaders();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        headers.set("Content-type", contentType);
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
