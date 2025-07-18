package com.abdul.ums.entity;

import jakarta.persistence.Column;

import java.util.Date;

public class BasicAuditable {

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private Date createdBy;

    @Column(name = "updated_by")
    private Date updatedBy;
}
