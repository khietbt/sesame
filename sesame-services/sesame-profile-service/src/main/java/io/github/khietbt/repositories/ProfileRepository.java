package io.github.khietbt.repositories;

import io.github.khietbt.entities.Profile;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProfileRepository extends R2dbcRepository<Profile, UUID> {

}
