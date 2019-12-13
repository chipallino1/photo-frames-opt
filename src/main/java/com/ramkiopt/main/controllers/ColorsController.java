package com.ramkiopt.main.controllers;


import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/colors")
public class ColorsController {
    private final ColorsService<ColorsDto> colorsService;
    private final BaseResponseService responseService;

    public ColorsController(ColorsService<ColorsDto> colorsService, BaseResponseService responseService) {
        this.colorsService = colorsService;
        this.responseService = responseService;
    }

    @GetMapping("/")
    public ResponseEntity getColors() {
        return responseService.createResponseEntity(colorsService.getAllColorNamesDistinct(), HttpStatus.OK);
    }
}
