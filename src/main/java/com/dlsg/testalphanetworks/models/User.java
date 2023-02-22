package com.dlsg.testalphanetworks.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @org.hibernate.annotations.Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private String firstname;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Animal> animals;


    public User(){}
}
