package io.github.khietbt.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class Auditable<T> {

  @Id
  private UUID id;

  @CreatedBy
  private T createdBy;

  @LastModifiedBy
  private T updatedBy;

  @CreatedDate
  private Timestamp createdAt;

  @LastModifiedDate
  private Timestamp updatedAt;

  @PrePersist
  public void prePersist() {
    id = UUID.randomUUID();
  }
}
