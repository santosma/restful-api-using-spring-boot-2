package com.demo.springboot2restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50, nullable = false)
    private String userName;
    @Column(unique = true, length = 50, nullable = false)
    private String firstName;
    @Column(unique = true, length = 30, nullable = false)
    private String lastName;
    @Column(unique = true, length = 15, nullable = false)
    private String phoneNumber;
}
