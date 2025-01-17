package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.request.PlateListRequest;
import com.plazoleta.application.dto.request.PlateStateRequest;
import com.plazoleta.application.dto.response.PlateListResponse;
import com.plazoleta.application.dto.request.PlateUpdateRequest;
import com.plazoleta.application.handler.IPlateListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plates")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class PlateRestController {

    private final IPlateListHandler plateListHandler;

    @PostMapping("/savePlate")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> savePlate(@RequestBody PlateListRequest plateListRequest) {
        plateListHandler.savePlateInList(plateListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getPlates/restaurant/{id}/size/{size}/page/{page}")
    @PreAuthorize("hasAuthority('CLIENTe')")
    public ResponseEntity<List<PlateListResponse>> getAllPlatesFromList(@PathVariable Long id, @PathVariable Integer size, @PathVariable Integer page) {
       return ResponseEntity.ok(plateListHandler.getAllPlatesFromList(id, page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<PlateListResponse> getPlateFromList(@PathVariable Long id) {
        return ResponseEntity.ok(plateListHandler.getPlateFromList(id));
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> updatePlate(@RequestBody PlateUpdateRequest plateUpdateRequest) {
        plateListHandler.updatePlateFromList(plateUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/plateState")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> enableDisablePlate(@RequestBody PlateStateRequest plateStateRequest) {
        plateListHandler.enableDisablePlate(plateStateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{plateId}")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> deletePlate(@PathVariable Long plateId) {
        plateListHandler.deletePlateFromList(plateId);
        return ResponseEntity.noContent().build();
    }
}