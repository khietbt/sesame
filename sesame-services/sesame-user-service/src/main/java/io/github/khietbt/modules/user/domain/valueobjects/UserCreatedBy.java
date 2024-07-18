package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.StringValueObject;
import lombok.ToString;

@ToString(callSuper = true)
public class UserCreatedBy extends StringValueObject {
    public UserCreatedBy(String creator) {
        super(creator);
    }
}
