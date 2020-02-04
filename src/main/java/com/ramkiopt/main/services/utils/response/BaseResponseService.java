package com.ramkiopt.main.services.utils.response;

import com.ramkiopt.main.dto.ErrorDto;
import com.ramkiopt.main.dto.InfoDto;
import com.ramkiopt.main.dto.NotificationDto;
import com.ramkiopt.main.services.app.base.NotificationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BaseResponseService {

    public ResponseEntity<Object> createResponseEntity(Object body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    public ResponseEntity<Object> createErrorResponse(List<FieldError> fieldErrors,
                                                  String... excludedFields) {
        InfoDto infoDto = createInfoDtoWithErrors(fieldErrors, excludedFields);
        return createResponseEntity(infoDto, HttpStatus.BAD_REQUEST);
    }

    private InfoDto createInfoDtoWithErrors(List<FieldError> fieldErrors,
                                            String... excludedFields) {
        InfoDto infoDto = buildInfoDto(HttpStatus.BAD_REQUEST);
        List<NotificationDto> errorDtos = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            if (!Arrays.asList(excludedFields).contains(error.getField())) {
                errorDtos.add(createErrorDto(error));
            }
        }
        infoDto.setNotificationDtos(errorDtos);
        return infoDto;
    }

    private ErrorDto createErrorDto(ObjectError objectError) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(objectError.getDefaultMessage());
        errorDto.setType(NotificationType.ERROR);
        return errorDto;
    }

    public ResponseEntity<Object> createErrorResponse(Exception ex, HttpStatus httpStatus) {
        InfoDto infoDto = buildInfoDto(httpStatus);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setType(NotificationType.ERROR);
        infoDto.setNotificationDtos(Collections.singletonList(errorDto));
        return createResponseEntity(infoDto, httpStatus);
    }

    private InfoDto buildInfoDto(HttpStatus httpStatus) {
        InfoDto infoDto = new InfoDto();
        infoDto.setStatusCode(httpStatus.value());
        infoDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return infoDto;
    }
}
