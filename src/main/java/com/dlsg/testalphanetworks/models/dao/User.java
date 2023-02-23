package com.dlsg.testalphanetworks.models.dao;

import com.dlsg.testalphanetworks.models.dto.UserRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID id;
    private String name;
    private String firstname;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Animal> animals;


    public User(){
        this.id = UUID.randomUUID();
    }
    public User(UserRequest userRequest){
        this();
        this.name = userRequest.getName();
        this.firstname = userRequest.getFirstname();
    }
}
