package io.github.khietbt.modules.user.domain.entities;

import io.github.khietbt.modules.user.domain.valueobjects.*;
import io.github.khietbt.shared.domain.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class User extends DomainEntity {
    private UserId id;

    private UserName name;

    private UserCreatedBy createdBy;

    private UserCreatedAt createdAt;

    private UserUpdatedAt updatedAt;

    private UserUpdatedBy updatedBy;

    private UserVersion version;
}
