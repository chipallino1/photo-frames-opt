package com.ramkiopt.main.services.utils.impl;

import com.ramkiopt.main.configuration.properties.FileStorageProperties;
import com.ramkiopt.main.services.utils.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final static String IMAGE_URL = "http://localhost:8080/photo-frames/getFile/";
    private Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    @Override
    public String storeFile(MultipartFile file) {

        String fileName = getFileName(file.getOriginalFilename());
        String imageUrl = IMAGE_URL + fileName;
        try {
            saveFile(file.getInputStream(), getTargetLocation(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    private void saveFile(InputStream inputStream, Path targetLocation) throws IOException {
        Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    private Path getTargetLocation(String fileName) {
        return Paths.get(this.fileStorageLocation + "/" + fileName).toAbsolutePath().normalize();
    }

    private String getFileName(String originalFileName) {
        return StringUtils.cleanPath(System.currentTimeMillis() + originalFileName);
    }

    private String getFileName() {
        return StringUtils.cleanPath(System.currentTimeMillis() + "originalFileName.png");
    }

    @Override
    public String storeFile(String base64File) {
        base64File = base64File.substring(base64File.indexOf(",") + 1);
        byte[] decodedBytes = Base64.getDecoder().decode(base64File);
        InputStream inputStream = new ByteArrayInputStream(decodedBytes);
        String fileName = getFileName();
        String imageUrl = IMAGE_URL + fileName;
        try {
            saveFile(inputStream, getTargetLocation(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = Paths.get(this.fileStorageLocation + "/" + fileName).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }
}
