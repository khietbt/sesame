package io.github.khietbt.modules.user.domain.events;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class UserNameClaimCompletedEvent extends UserEvent {
    private UserName userName;
}
