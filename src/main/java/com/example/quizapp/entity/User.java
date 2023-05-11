package com.example.quizapp.entity;


import com.example.quizapp.entity.role.Permission;
import com.example.quizapp.entity.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User implements UserDetails {




    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = false;


    @ManyToMany(fetch = FetchType.EAGER)
    List<Permission> permissions;

    @Column(unique = true)
    private UUID emailCode;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //todo role and permissions
        return permissions;
    }
}
