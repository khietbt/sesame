package io.github.khietbt.callbacks;

import io.github.khietbt.entities.Auditable;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.util.UUID;

@Component
public class DefaultBeforeConvertCallback<T extends Auditable> implements BeforeConvertCallback<T> {

    @Override
    public @NonNull Publisher<T> onBeforeConvert(@NonNull T entity, @NonNull SqlIdentifier table) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }

        return Mono.just(entity);
    }
}
