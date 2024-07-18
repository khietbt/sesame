package io.github.khietbt.modules.user.application.queries;

import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGetOneByIdQuery {
    private UserId id;
}
