package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.ErrorDto;
import com.ramkiopt.main.dto.MessageInfoDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.utils.MessageInfoType;
import com.ramkiopt.main.services.utils.response.ResponseCustomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        LocaleContextHolder.getLocale();
        if (bindingResult.hasErrors()) {
            return info(getMessageInfoDtosForError(bindingResult), 400);
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return getResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updatePhotoFrame() {
        return null;
    }

    private List<MessageInfoDto> getMessageInfoDtosForError(BindingResult bindingResult) {
        List<MessageInfoDto> messageInfoDtos = new ArrayList<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            MessageInfoDto messageInfoDto = new MessageInfoDto();
            messageInfoDto.setMessageInfoType(MessageInfoType.ERROR);
            messageInfoDto.setMessage(getErrorDto(error));
            messageInfoDtos.add(messageInfoDto);
        }
        return messageInfoDtos;
    }

    private ErrorDto getErrorDto(ObjectError error) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setFieldName(((FieldError) error).getField());
        errorDto.setObjectName(error.getObjectName());
        errorDto.setMessage(error.getDefaultMessage());
        return errorDto;
    }
}
