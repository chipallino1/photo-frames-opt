package com.ramkiopt.main.services.utils.impl;

import com.ramkiopt.main.configuration.properties.FileStorageProperties;
import com.ramkiopt.main.services.utils.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    @Override
    public String storeFile(MultipartFile file, String email) throws Exception {
        String fileName = StringUtils.cleanPath(System.currentTimeMillis() + file.getOriginalFilename());

        Path targetLocation = Paths.get(this.fileStorageLocation + "/" + fileName).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return "/image?fileName=" + fileName + "&email=" + email;
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

    @Override
    public String updateFilePath(String avatarPath, String newEmail) {
        String newAvatarPath = avatarPath.substring(0, avatarPath.lastIndexOf('=') + 1);
        newAvatarPath = newAvatarPath + newEmail;
        return newAvatarPath;
    }
}
