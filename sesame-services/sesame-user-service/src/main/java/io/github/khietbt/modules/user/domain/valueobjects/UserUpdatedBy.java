package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.StringValueObject;
import lombok.ToString;

@ToString(callSuper = true)
public class UserUpdatedBy extends StringValueObject {
    public UserUpdatedBy(String modifier) {
        super(modifier);
    }
}
