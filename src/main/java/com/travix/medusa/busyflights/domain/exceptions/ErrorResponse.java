package com.travix.medusa.busyflights.domain.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;

}
