package com.managment.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@IdClass(MemberId.class)
@Entity
public class Member {

    private String name;
    private String address;
    @Id
    private String email;
    @Id
    private String contactNumber;

}
