package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.RestListRequest;
import com.plazoleta.application.dto.RestListResponse;
import com.plazoleta.application.handler.IRestListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestListHandler restListHandler;

    @PostMapping("/saveRest")
    public ResponseEntity<Void> saveRest(@RequestBody RestListRequest restListRequest) {
        restListHandler.saveRestInList(restListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getRests")
    public ResponseEntity<List<RestListResponse>> getRestsFromList() {
        return ResponseEntity.ok(restListHandler.getAllRestFromList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestListResponse> getRestFromId(@PathVariable Long id) {
        return ResponseEntity.ok(restListHandler.getRestFromList(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestFromId(@PathVariable Long id) {
        restListHandler.deleteRestFromList(id);
        return ResponseEntity.noContent().build();
    }
}
