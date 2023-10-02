package io.github.khietbt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
@Table("users")
@ToString(callSuper = true)
public class User extends Auditable {

  private String username;

  private String fullname;

  private String description;
}
