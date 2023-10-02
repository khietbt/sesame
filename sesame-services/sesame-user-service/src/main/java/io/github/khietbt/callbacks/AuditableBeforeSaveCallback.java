package io.github.khietbt.callbacks;

import io.github.khietbt.entities.Auditable;
import java.util.UUID;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuditableBeforeSaveCallback<T> implements BeforeConvertCallback<T> {

  @Override
  public Publisher<T> onBeforeConvert(T entity, SqlIdentifier table) {
    if (entity instanceof Auditable auditable) {

      if (auditable.getUuid() == null) {
        auditable.setUuid(UUID.randomUUID());
      }

      return Mono.just((T) auditable);
    }

    return Mono.just(entity);
  }
}
