package io.github.khietbt.entities;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class Auditable {

  @Id
  private Long id;

  private UUID uuid;

  @CreatedBy
  @Column("created_by")
  private String createdBy;

  @LastModifiedBy
  @Column("updated_by")
  private String updatedBy;

  @CreatedDate
  @Column("created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column("updated_at")
  private Instant updatedAt;
}
