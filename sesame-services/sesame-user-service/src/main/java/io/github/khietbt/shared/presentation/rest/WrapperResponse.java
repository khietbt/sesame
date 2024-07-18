package io.github.khietbt.shared.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class WrapperResponse<T> {
    private String status;

    private T data;
}
