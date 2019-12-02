package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController {
    private final PhotoFramesStructureService photoFramesStructureService;
    private final BaseResponseService responseService;

    @Autowired
    public PhotoFramesController(PhotoFramesStructureService photoFramesStructureService,
                                 BaseResponseService responseService) {
        this.photoFramesStructureService = photoFramesStructureService;
        this.responseService = responseService;
    }

    @PostMapping("/create")
    public ResponseEntity createPhotoFrame(@RequestBody @Valid PhotoFramesDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.createErrorInfo(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return responseService.createResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updatePhotoFrame(@RequestBody PhotoFramesDto photoFramesDto) {
        return responseService.createResponseEntity(photoFramesStructureService.updatePhotoFrame(photoFramesDto.getId(),
                photoFramesDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity readPhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.readPhotoFrame(id), HttpStatus.OK);
    }

    @GetMapping("/allByName")
    public ResponseEntity readAllPhotoFrames(@PathParam("name") String name,
                                             @PathParam("pageNumber") Integer pageNumber,
                                             @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService.createResponseEntity(photoFramesStructureService.readAllByName("%" + name + "%",
                pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.deletePhotoFrame(id), HttpStatus.OK);
    }
}
