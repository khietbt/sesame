package io.github.khietbt.modules.user.application.queries;

import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserGetOneByIdQuery {
    private UserId id;
}
