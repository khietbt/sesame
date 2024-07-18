package io.github.khietbt.modules.user.infrastructure.mysql.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.infrastructure.mysql.models.DatabaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DatabaseUserRepository extends JpaRepository<DatabaseUser, UUID>, UserRepository {
    Optional<DatabaseUser> findByName(String name);

    @Override
    default Optional<User> getOne(UserName name) {
        return this.findByName(name.getValue())
                .map(
                        u -> User
                                .builder()
                                .id(new UserId(u.getId()))
                                .name(new UserName(u.getName()))
                                .build()
                );
    }

    @Override
    default User create(User user) {
        var databaseUser = this.save(
                DatabaseUser
                        .builder()
                        .name(user.getName().getValue())
                        .build()
        );

        return User
                .builder()
                .id(new UserId(databaseUser.getId()))
                .name(new UserName(databaseUser.getName()))
                .build();
    }
}
