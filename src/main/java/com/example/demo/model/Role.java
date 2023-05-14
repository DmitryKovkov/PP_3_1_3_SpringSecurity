package com.example.demo.model;

//import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Override
    public String toString() {
        return this.name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

   //@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  //  private Set<User> users;

    public Role() {}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }


  //  public Set<User> getUsers() {
  //      return users;
  //  }

  //  public void setUsers(Set<User> users) {
  //      this.users = users;
  //  }
}
