package io.github.khietbt.callbacks;

import io.github.khietbt.entities.Auditable;
import java.util.UUID;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuditableBeforeSaveCallback<T> implements BeforeSaveCallback<T> {

  @Override
  public Publisher<T> onBeforeSave(T entity, OutboundRow row, SqlIdentifier table) {
    if (entity instanceof Auditable) {
      var auditable = (Auditable) entity;

      if (auditable.getId() == null) {
        auditable.setId(UUID.randomUUID().toString());
      }

      return Mono.just((T) auditable);
    }

    return Mono.just(entity);
  }
}
