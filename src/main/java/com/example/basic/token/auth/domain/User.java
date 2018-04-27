package com.example.basic.token.auth.domain;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

  @Column(name = "username", nullable = false, unique = true)
  @Getter
  @Setter
  @NotBlank
  private String username;

  @Column(name = "password", nullable = false)
  @Getter
  @Setter
  @Size(min = 8, max = 100)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @Getter
  @Setter
  @Email
  private String email;

  @Column(name = "first_name")
  @Getter
  @Setter
  private String firstName;

  @Column(name = "last_name")
  @Getter
  @Setter
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_assignment",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
  @Getter
  @Setter
  private Collection<Role> roles = new HashSet<>();
}
