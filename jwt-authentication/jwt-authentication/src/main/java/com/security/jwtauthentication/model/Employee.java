package com.security.jwtauthentication.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String id;
    private String name;
    private String email;
}
