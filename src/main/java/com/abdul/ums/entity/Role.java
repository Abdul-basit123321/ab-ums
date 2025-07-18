package com.abdul.ums.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

}
