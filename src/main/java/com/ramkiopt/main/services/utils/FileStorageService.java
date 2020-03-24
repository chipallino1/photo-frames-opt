package com.ramkiopt.main.services.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    String storeFile(String base64File);

    Resource loadFileAsResource(String fileName) throws Exception;
}
