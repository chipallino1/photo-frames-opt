package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.utils.response.ResponseCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController implements ResponseCustomizationService {
    private final PhotoFramesStructureService photoFramesStructureService;

    @Autowired
    public PhotoFramesController(PhotoFramesStructureService photoFramesStructureService) {
        this.photoFramesStructureService = photoFramesStructureService;
    }

    @PostMapping("/create")
    public ResponseEntity createPhotoFrame(@RequestBody @Valid PhotoFramesDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages  new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return info();
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return getResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updatePhotoFrame() {
        return null;
    }

    private boolean validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {

            }
        }
        return true;
    }
}
