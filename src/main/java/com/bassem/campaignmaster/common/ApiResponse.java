package com.bassem.campaignmaster.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ApiResponse {
    private int  status;
    private boolean success;
    private Object body;
    private Object error;
}