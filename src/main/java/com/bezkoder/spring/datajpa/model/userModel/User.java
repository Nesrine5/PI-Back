package com.bezkoder.spring.datajpa.model.userModel;


import com.bezkoder.spring.datajpa.model.Besoin;
import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.FileDB;
import com.bezkoder.spring.datajpa.model.Livrable;
import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
/*@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)*/
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
    private String specialite;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();



    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-besoins")
    private List<Besoin> besoins;

   @OneToMany(mappedBy = "user")
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonIgnore()
    private List<Livrable> livrables;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-files")
    private List<FileDB> fileDBs;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore()
    private Set<Chat2> chats;

  public User() {
  }

  public User(String username, String email, String password ) {
    this.username = username;
    this.email = email;
    this.password = password;
   // this.specialite = specialite;
  }
   /* public User(String username, String email, String password, Set<Chat2> chats) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.chats = chats; // Affectation du paramètre aux champs de la classe
    }*/

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public Set<Role> getRoles() {
    return roles;
}

public void setRoles(Set<Role> roles) {
    this.roles = roles;
}

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    // getters and setters


    public Set<Chat2> getChats() {
        return chats;
    }

    public void setChats(Set<Chat2> chats) {
        this.chats = chats;
    }

}