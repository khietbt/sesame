package io.github.khietbt.modules.user.domain.entities;

import io.github.khietbt.modules.user.domain.valueobjects.*;
import io.github.khietbt.shared.domain.entities.DomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@ToString(callSuper = true)
public class User extends DomainEntity {
    private UserId id;

    private UserName name;

    private UserCreatedBy createdBy;

    private UserCreatedAt createdAt;

    private UserUpdatedAt updatedAt;

    private UserUpdatedBy updatedBy;
}
