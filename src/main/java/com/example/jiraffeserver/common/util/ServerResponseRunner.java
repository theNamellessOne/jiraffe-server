package com.example.jiraffeserver.common.util;

import org.springframework.http.HttpStatus;

import java.util.function.Function;

public class ServerResponseRunner {
    public static <A, T> ServerResponse<T> safeRun(Function<A, T> function, A args) {
        ServerResponse<T> serverResponse;

        try {
            T data = function.apply(args);
            serverResponse = ServerResponse.<T>builder()
                    .msg("")
                    .data(data)
                    .status(HttpStatus.OK)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            var status = HttpStatus.INTERNAL_SERVER_ERROR;

            if (e.getMessage().equals("No value present")) {
               status = HttpStatus.BAD_REQUEST;
            }

            serverResponse = ServerResponse.<T>builder()
                    .msg(e.getMessage())
                    .data(null)
                    .status(status)
                    .build();
        }

        return serverResponse;
    }
}
