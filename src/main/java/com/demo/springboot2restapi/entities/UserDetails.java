package com.demo.springboot2restapi.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String firstName;
    private String lastName;
    private String city;

}
