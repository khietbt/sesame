package io.github.khietbt.modules.user.infrastructure.jpa.models;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.*;
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
public class JpaUser extends JpaEntity {
    private String name;

    @Override
    public User toDomain() {
        return User
                .builder()
                .id(new UserId(this.getId()))
                .name(new UserName(this.getName()))
                .createdBy(new UserCreatedBy(this.getCreatedBy()))
                .createdAt(new UserCreatedAt(this.getCreatedAt()))
                .updatedBy(new UserUpdatedBy(this.getUpdatedBy()))
                .updatedAt(new UserUpdatedAt(this.getUpdatedAt()))
                .version(new UserVersion(this.getVersion()))
                .build();
    }
}
