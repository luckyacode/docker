package com.praveen.docker;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO<T> {
    private int status;
    private String message;
    private String data;
}
