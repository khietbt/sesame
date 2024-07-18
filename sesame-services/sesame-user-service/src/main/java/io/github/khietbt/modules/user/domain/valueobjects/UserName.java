package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.StringValueObject;
import lombok.ToString;

@ToString(callSuper = true)
public class UserName extends StringValueObject {
    public UserName(String name) {
        super(name);
    }
}
