package io.github.khietbt.modules.user.infrastructure.mysql.models;

import io.github.khietbt.shared.infrastructure.jpa.JpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Table(name = "users")
@ToString(callSuper = true)
public class DatabaseUser extends JpaEntity {
    private String name;
}
