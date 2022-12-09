package ru.itmentor.spring.boot_security.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String username;

   @Column
   private String password;

   public User() {}

   public User(String username, String password) {
      this.username = username;
      this.password = password;
   }

   @ManyToMany
   @JoinTable(
           name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )

   @JsonIgnore
   private Set<Role> roles = new HashSet<>();

   @JsonIgnore
   @Override
   public Set<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }


   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @JsonIgnore
   public Set<Role> getRoles() {
      return roles;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void addRole(Role role) {
      roles.add(role);
   }
}
