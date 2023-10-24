package io.github.khietbt.models;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@MappedSuperclass
@Setter
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
}
  