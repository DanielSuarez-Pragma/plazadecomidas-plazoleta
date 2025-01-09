package com.plazoleta.infraestructure.input.rest.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    private Long roleId;
    private String email;
    private String password;
}
