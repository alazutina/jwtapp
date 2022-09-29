package com.anna.jwtappdemo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="files")
@Data
public class File extends BaseEntity {

    @Column(name="path")
    private String path;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="events",
            joinColumns = {@JoinColumn(name ="file_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")})
    private List<Role> users;
}