package com.abdul.ums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "role")
public class Role {

    @Id
    private String name;
}
