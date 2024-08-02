package io.github.khietbt.modules.user.application.commands;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateUserNameClaimCommand {
    private final UserId userId;

    @TargetAggregateIdentifier
    private UserName userName;
}
