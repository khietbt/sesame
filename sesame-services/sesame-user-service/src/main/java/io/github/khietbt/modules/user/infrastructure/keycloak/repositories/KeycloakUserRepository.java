package io.github.khietbt.modules.user.infrastructure.keycloak.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.infrastructure.keycloak.clients.KeycloakUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class KeycloakUserRepository implements UserRepository {
    @Autowired
    private KeycloakUserClient keycloakUserClient;

    @Value("${KEYCLOAK_REALM}")
    private String realm;

    @Override
    public Page<User> getList(Integer number, Integer size) {
        return null;
    }

    @Override
    public Optional<User> getOne(UserId id) {
        var u = keycloakUserClient.getOne(this.realm, id.getValue().toString());

        return Optional.ofNullable(u.toDomain());
    }

    @Override
    public Optional<User> getOne(UserName name) {
        return Optional.empty();
    }

    @Override
    public User create(String name) {
        return null;
    }

    @Override
    public User create(UserName name) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public boolean exists(UserName name) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
