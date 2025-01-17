package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.request.RestEmpRequest;
import com.plazoleta.application.handler.IRestEmpHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restEmpl")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class RestEmpRestController {

    private final IRestEmpHandler restEmpHandler;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> saveRestEmp(@RequestBody RestEmpRequest restEmpRequest) {
        restEmpHandler.saveRestEmp(restEmpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> deleteRestEmp(@RequestBody RestEmpRequest restEmpRequest){
        restEmpHandler.deleteRestEmp(restEmpRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
