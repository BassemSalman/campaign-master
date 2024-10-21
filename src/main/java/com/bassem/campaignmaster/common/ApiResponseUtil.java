package com.bassem.campaignmaster.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {
    public static ResponseEntity<?> constructResponse(Object content, HttpStatus status){
        Object body  = !status.isError() ? content : null;
        Object error =  status.isError() ? content : null;

        ApiResponse response = ApiResponse.builder()
                .status (status.value())
                .success(!status.isError())
                .body(body)
                .error(error)
                .build();

        return new ResponseEntity<>(response, status);
    }
}
