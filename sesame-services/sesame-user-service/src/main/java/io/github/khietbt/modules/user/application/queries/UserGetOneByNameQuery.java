package io.github.khietbt.modules.user.application.queries;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class UserGetOneByNameQuery {
    private final UserName name;
}
