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
    @Autowired
    private PhotoFramesCustomizationService photoFramesCustomizationService;

    @PostMapping("/create")
    public ResponseEntity createPhotoFrame(@RequestBody PhotoFramesDto dto) {
        dto = photoFramesCustomizationService.createPhotoFrame(dto);
        return getResponseEntity(dto, null, HttpStatus.OK);
    }
}
