package io.github.khietbt.repositories;

import io.github.khietbt.entities.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserRepository extends R2dbcRepository<User, UUID> {

}
