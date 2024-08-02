package io.github.khietbt.modules.user.domain.events;

import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class UserUpdateUserNameClaimRejectedEvent extends UserEvent {
    private final UserId userId;

    private final UserName userName;
}
