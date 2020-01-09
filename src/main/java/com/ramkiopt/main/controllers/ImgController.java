package com.ramkiopt.main.controllers;

import com.ramkiopt.main.services.utils.FileStorageService;
import com.ramkiopt.main.services.utils.FilesUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/files")
public class ImgController {

    private final FileStorageService fileStorageService;

    public ImgController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public ResponseEntity<Resource> getFile(@RequestParam String fileName, HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", FilesUtils.getContentType(request.getServletContext(), resource));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
