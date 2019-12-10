package com.ramkiopt.main.services.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, String email) throws Exception;

    Resource loadFileAsResource(String fileName) throws Exception;

    String updateFilePath(String avatarPath, String newEmail);
}
