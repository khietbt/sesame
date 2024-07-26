package io.github.khietbt.shared.domain.exceptions;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ExceptionDetails implements Serializable {
    private String name;

    private String message;
}
