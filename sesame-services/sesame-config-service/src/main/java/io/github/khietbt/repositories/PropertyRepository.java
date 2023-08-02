package io.github.khietbt.repositories;

import io.github.khietbt.models.Property;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

}
