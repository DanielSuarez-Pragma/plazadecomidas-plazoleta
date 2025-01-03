package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.PlateListRequest;
import com.plazoleta.application.dto.PlateListResponse;
import com.plazoleta.application.dto.PlateUpdateRequest;
import com.plazoleta.application.handler.IPlateListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateRestController {

    private final IPlateListHandler plateListHandler;

    @PostMapping("/savePlate")
    public ResponseEntity<Void> savePlate(@RequestBody PlateListRequest plateListRequest) {
        plateListHandler.savePlateInList(plateListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getPlates")
    public ResponseEntity<List<PlateListResponse>> getAllPlatesFromList() {
        return ResponseEntity.ok(plateListHandler.getAllPlatesFromList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlateListResponse> getPlateFromList(@PathVariable Long id) {
        return ResponseEntity.ok(plateListHandler.getPlateFromList(id));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updatePlate(@RequestBody PlateUpdateRequest plateUpdateRequest) {
        plateListHandler.updatePlateFromList(plateUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{plateId}")
    public ResponseEntity<Void> deletePlate(@PathVariable Long plateId) {
        plateListHandler.deletePlateFromList(plateId);
        return ResponseEntity.noContent().build();
    }
}