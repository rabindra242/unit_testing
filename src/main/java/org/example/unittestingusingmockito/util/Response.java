package org.example.unittestingusingmockito.util;

import lombok.Data;

@Data
public class Response <T> {
    private String message;
    private T Response;
}
