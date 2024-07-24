package io.github.khietbt.modules.user.infrastructure.mysql.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.*;
import io.github.khietbt.modules.user.infrastructure.mysql.models.DatabaseUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DatabaseUserRepository extends JpaRepository<DatabaseUser, UUID>, UserRepository {
    Optional<DatabaseUser> findByName(String name);

    @Override
    default Optional<User> getOne(UserId id) {
        return this.findById(id.getValue())
                .map(
                        u -> User
                                .builder()
                                .id(new UserId(u.getId()))
                                .name(new UserName(u.getName()))
                                .createdBy(new UserCreatedBy(u.getCreatedBy()))
                                .createdAt(new UserCreatedAt(u.getCreatedAt()))
                                .updatedBy(new UserUpdatedBy(u.getUpdatedBy()))
                                .updatedAt(new UserUpdatedAt(u.getUpdatedAt()))
                                .version(new UserVersion(u.getVersion()))
                                .build()
                );
    }

    @Override
    default Optional<User> getOne(UserName name) {
        return this.findByName(name.getValue())
                .map(
                        u -> User
                                .builder()
                                .id(new UserId(u.getId()))
                                .name(new UserName(u.getName()))
                                .createdBy(new UserCreatedBy(u.getCreatedBy()))
                                .createdAt(new UserCreatedAt(u.getCreatedAt()))
                                .updatedBy(new UserUpdatedBy(u.getUpdatedBy()))
                                .updatedAt(new UserUpdatedAt(u.getUpdatedAt()))
                                .version(new UserVersion(u.getVersion()))
                                .build()
                );
    }

    @Override
    default User create(User user) {
        var u = this.save(
                DatabaseUser
                        .builder()
                        .name(user.getName().getValue())
                        .build()
        );

        return User
                .builder()
                .id(new UserId(u.getId()))
                .name(new UserName(u.getName()))
                .createdBy(new UserCreatedBy(u.getCreatedBy()))
                .createdAt(new UserCreatedAt(u.getCreatedAt()))
                .updatedBy(new UserUpdatedBy(u.getUpdatedBy()))
                .updatedAt(new UserUpdatedAt(u.getUpdatedAt()))
                .version(new UserVersion(u.getVersion()))
                .build();
    }

    @Override
    default boolean exists(UserName name) {
        return this.exists(Example.of(DatabaseUser.builder().name(name.getValue()).build()));
    }

    @Override
    default User update(User existing) {
        var u = this.findById(existing.getId().getValue())
                .orElseThrow(
                        () -> new UserNotFoundException(existing.getId())
                );

        u.setName(existing.getName().getValue());

        u = this.save(u);

        return User
                .builder()
                .id(new UserId(u.getId()))
                .name(new UserName(u.getName()))
                .createdBy(new UserCreatedBy(u.getCreatedBy()))
                .createdAt(new UserCreatedAt(u.getCreatedAt()))
                .updatedBy(new UserUpdatedBy(u.getUpdatedBy()))
                .updatedAt(new UserUpdatedAt(u.getUpdatedAt()))
                .version(new UserVersion(u.getVersion()))
                .build();
    }
}
