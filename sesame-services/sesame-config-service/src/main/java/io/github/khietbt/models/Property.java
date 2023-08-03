package io.github.khietbt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
@Table(name = "properties")
public class Property extends Auditable<String> {

  private String application;

  private String profile;

  private String label;

  private String k;

  private String v;
}
