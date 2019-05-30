package com.ramkiopt.main.services.utils;

import com.ramkiopt.main.dto.InfoDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface ResponseCustomizationService {

    default ResponseEntity getResponseEntity(Object body, HttpHeaders headers, HttpStatus httpStatus) {
        if (headers == null) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(body, headers, httpStatus);
        }
        return new ResponseEntity<>(body, headers, httpStatus);
    }

    default ResponseEntity info(String message, Integer statusCode) {
        InfoDto infoDto = new InfoDto();
        infoDto.setMessage(message);
        infoDto.setStatusCode(statusCode);
        return getResponseEntity(infoDto, null, HttpStatus.valueOf(statusCode));
    }
}
