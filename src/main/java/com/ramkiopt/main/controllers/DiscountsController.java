package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.services.app.discounts.DiscountsService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {
    private final DiscountsService<DiscountsDto> discountsService;
    private final BaseResponseService responseService;

    public DiscountsController(DiscountsService<DiscountsDto> discountsService, BaseResponseService responseService) {
        this.discountsService = discountsService;
        this.responseService = responseService;
    }

    @PostMapping("/create")
    public ResponseEntity createDiscounts(@RequestBody DiscountsDto discountsDto) {
        return responseService.createResponseEntity(discountsService.create(discountsDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody DiscountsDto discountsDto) {
        return responseService.createResponseEntity(discountsService.update(discountsDto.getId(), discountsDto),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(discountsService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(discountsService.delete(id), HttpStatus.OK);
    }
}
