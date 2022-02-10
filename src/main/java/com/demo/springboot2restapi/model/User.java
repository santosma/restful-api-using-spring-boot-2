package com.demo.springboot2restapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ToString
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

    @Size(min = 6, message = "You provide a user name")
    @Column(unique = true, length = 50, nullable = false)
    private String userName;

    @Size(min = 2, message = "You provide a first name")
    @Column(length = 50, nullable = false)
    private String firstName;

    @Size(min = 2, message = "You provide a last name")
    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(length = 15)
    private String phoneNumber;
}
