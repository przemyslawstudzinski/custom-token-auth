package com.example.basic.token.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

  @Column(name = "name", unique = true, nullable = false)
  @Getter
  @Setter
  @NotBlank
  private String name;

  @Column(name = "decryption")
  @Getter
  @Setter
  private String decryption;

}
