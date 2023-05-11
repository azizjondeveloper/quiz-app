package com.example.quizapp.entity.role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;


    @Override
    public String getAuthority() {
        return name;
    }

}
