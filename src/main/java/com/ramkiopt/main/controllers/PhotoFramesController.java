package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController {
    private final PhotoFramesStructureService photoFramesStructureService;
    private final BaseResponseService responseService;

    @Autowired
    public PhotoFramesController(PhotoFramesStructureService photoFramesStructureService, BaseResponseService responseService) {
        this.photoFramesStructureService = photoFramesStructureService;
        this.responseService = responseService;
    }

    @PostMapping("/create")
    public ResponseEntity createPhotoFrame(@RequestBody @Valid PhotoFramesDto dto, BindingResult bindingResult) {
        LocaleContextHolder.getLocale();
        if (bindingResult.hasErrors()) {
            return responseService.createErrorInfo(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return responseService.createResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updatePhotoFrame() {
        return null;
    }
}
