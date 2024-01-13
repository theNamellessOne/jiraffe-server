package com.example.jiraffeserver.common.util;

import lombok.Data;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ServerResponse<T> {
    private T data;
    private String msg;
    private HttpStatus status;
}
