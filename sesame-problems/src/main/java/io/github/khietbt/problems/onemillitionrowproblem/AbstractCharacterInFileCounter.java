package io.github.khietbt.problems.onemillitionrowproblem;

import lombok.Getter;

@Getter
public abstract class AbstractCharacterInFileCounter implements CharacterCounter {
    protected final String file;

    protected AbstractCharacterInFileCounter(String file) {
        this.file = file;
    }
}
