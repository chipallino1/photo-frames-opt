package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesCustomizationService;
import com.ramkiopt.main.services.utils.response.ResponseCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController implements ResponseCustomizationService {
    private final PhotoFramesCustomizationService photoFramesCustomizationService;

    @Autowired
    public PhotoFramesController(PhotoFramesCustomizationService photoFramesCustomizationService) {
        this.photoFramesCustomizationService = photoFramesCustomizationService;
    }

    @PostMapping("/create")
    public ResponseEntity createPhotoFrame(@RequestBody PhotoFramesDto dto) {
        dto = photoFramesCustomizationService.createPhotoFrame(dto);
        if (dto == null) {
            return info("Error: sizes or colors did not set.", 400);
        }
        return getResponseEntity(dto, HttpStatus.OK);
    }
}
