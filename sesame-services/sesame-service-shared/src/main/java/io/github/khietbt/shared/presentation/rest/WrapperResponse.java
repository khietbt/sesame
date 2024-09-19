package io.github.khietbt.shared.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Getter
@ToString
public class WrapperResponse implements Serializable {
    private String status;

    private Object data;

    public static WrapperResponse success(Object data) {
        return new WrapperResponse("success", data);
    }

    public static WrapperResponse error(Object data) {
        return new WrapperResponse("error", data);
    }
}
