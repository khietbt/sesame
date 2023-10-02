package io.github.khietbt.callbacks;

import io.asyncer.r2dbc.mysql.internal.NotNullByDefault;
import io.github.khietbt.entities.Auditable;
import java.util.UUID;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class DefaultBeforeConvertCallback<T  extends Auditable> implements BeforeConvertCallback<T> {

  @Override
  public @NonNull Publisher<T> onBeforeConvert(@NonNull T entity, @NonNull SqlIdentifier table) {
    if (entity.getUuid() == null) {
      entity.setUuid(UUID.randomUUID());
    }

    return Mono.just(entity);
  }
}
