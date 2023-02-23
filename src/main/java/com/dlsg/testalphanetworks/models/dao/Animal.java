package com.dlsg.testalphanetworks.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
// the jsonIgnoreProperties will avoid circular reference between user and animal
@JsonIgnoreProperties("user")
public class Animal {
    @Id
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Basic(optional = false)
    @Column(name = "user_id")
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id",updatable = false, insertable = false)
    private User user;

    public Animal(){
        this.id = UUID.randomUUID();
    }


}
