package io.github.khietbt.modules.user.infrastructure.jpa.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.infrastructure.jpa.models.JpaUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, UUID>, UserRepository {
    Optional<JpaUser> findByName(String name);

    @Override
    default Page<User> getList(Integer number, Integer size) {
        return this.findAll(PageRequest.of(number, size))
                .map(JpaUser::toDomain);
    }

    @Override
    default Optional<User> getOne(UserId id) {
        return this.findById(id.getValue()).map(JpaUser::toDomain);
    }

    @Override
    default Optional<User> getOne(UserName name) {
        return this.findByName(name.getValue())
                .map(JpaUser::toDomain);
    }

    @Override
    default User create(String name) {
        return this.save(
                JpaUser
                        .builder()
                        .name(name)
                        .build()
        ).toDomain();
    }

    @Override
    default User create(UserName name) {
        return this.create(name.getValue());
    }

    @Override
    default User create(User user) {
        return this.save(
                JpaUser
                        .builder()
                        .id(user.getId().getValue())
                        .name(user.getName().getValue())
                        .version(user.getVersion().getValue())
                        .build()
        ).toDomain();
    }

    @Override
    default boolean exists(UserName name) {
        return this.exists(Example.of(JpaUser.builder().name(name.getValue()).build()));
    }

    @Override
    default User update(User existing) {
        var u = this.findById(existing.getId().getValue())
                .orElseThrow(
                        () -> new UserNotFoundException(existing.getId())
                );

        u.setName(existing.getName().getValue());

        return this.save(u).toDomain();
    }
}
