package com.ramkiopt.main.services.utils.response;

import com.ramkiopt.main.dto.InfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseCustomizationService {

    default ResponseEntity getResponseEntity(Object body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    default ResponseEntity info(String message, Integer statusCode) {
        InfoDto infoDto = new InfoDto();
        infoDto.setMessage(message);
        infoDto.setStatusCode(statusCode);
        return getResponseEntity(infoDto, HttpStatus.valueOf(statusCode));
    }
}
