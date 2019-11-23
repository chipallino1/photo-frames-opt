package com.ramkiopt.main.services.utils.response;

import com.ramkiopt.main.dto.ErrorDto;
import com.ramkiopt.main.dto.InfoDto;
import com.ramkiopt.main.dto.NotificationDto;
import com.ramkiopt.main.services.app.base.NotificationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BaseResponseService {
    public ResponseEntity<Object> createResponseEntity(Object body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    public ResponseEntity<Object> createErrorInfo(List<ObjectError> objectErrors, HttpStatus httpStatus) {
        InfoDto infoDto = buildInfoDto(httpStatus);
        List<NotificationDto> errorDtos = new ArrayList<>();
        for (ObjectError error : objectErrors) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setMessage(error.getDefaultMessage());
            errorDto.setType(NotificationType.ERROR);
            errorDtos.add(errorDto);
        }
        infoDto.setNotificationDtos(errorDtos);
        return createResponseEntity(infoDto, httpStatus);
    }

    public ResponseEntity<Object> createErrorInfo(Exception ex, HttpStatus httpStatus) {
        InfoDto infoDto = buildInfoDto(httpStatus);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setType(NotificationType.ERROR);
        infoDto.setNotificationDtos(Collections.singletonList(errorDto));
        return createResponseEntity(infoDto, httpStatus);
    }

    private InfoDto buildInfoDto(HttpStatus httpStatus){
        InfoDto infoDto = new InfoDto();
        infoDto.setStatusCode(httpStatus.value());
        infoDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return infoDto;
    }
}
