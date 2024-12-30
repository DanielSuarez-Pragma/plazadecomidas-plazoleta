package com.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long firstName;
    private String lastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private Long roleId;
}
