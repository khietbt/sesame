package io.github.khietbt.shared.application;

import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
@ToString
public abstract class Command {
    @TargetAggregateIdentifier
    private final AggregateId aggregateId;
}
