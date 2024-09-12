package io.github.khietbt.problems.onemillitionrowproblem;

import lombok.Getter;

@Getter
public abstract class AbstractCharacterInFileCounter implements CharacterCounter {
    private final String file;

    protected AbstractCharacterInFileCounter(String file) {
        this.file = file;
    }
}
