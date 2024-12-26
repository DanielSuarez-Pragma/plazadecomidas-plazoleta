package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.RestListRequest;
import com.plazoleta.application.dto.RestListResponse;
import com.plazoleta.application.handler.IRestListHandler;
import com.plazoleta.infraestructure.input.rest.client.UserFeingClient;
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
        System.out.println("Probando ando:"+restListRequest.toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getRests")
    public ResponseEntity<List<RestListResponse>> getRestsFromList() {
        return ResponseEntity.ok(restListHandler.getAllRestFromList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestListResponse> getRestFromId(@PathVariable Long id) {
        System.out.println("Probando ando en RestController:"+restListHandler.getRestFromList(id).getName());
        return ResponseEntity.ok(restListHandler.getRestFromList(id));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteRestFromId(@PathVariable Long userId) {
        restListHandler.deleteRestFromList(userId);
        return ResponseEntity.noContent().build();
    }
}
