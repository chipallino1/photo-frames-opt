package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.services.app.sizes.SizesService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/sizes")
public class SizesController {
    private final BaseResponseService responseService;
    private final SizesService<SizesDto> sizesService;

    public SizesController(BaseResponseService responseService, SizesService<SizesDto> sizesService) {
        this.responseService = responseService;
        this.sizesService = sizesService;
    }

    @GetMapping("/")
    public ResponseEntity getSizes() {
        return responseService.createResponseEntity(sizesService.getAllFormatsDistinct(), HttpStatus.OK);
    }
}
