package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.request.RestListRequest;
import com.plazoleta.application.dto.response.RestListResponse;
import com.plazoleta.application.handler.IRestListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class RestaurantRestController {

    private final IRestListHandler restListHandler;

    @PostMapping("/saveRest")
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<Void> saveRest(@RequestBody RestListRequest restListRequest) {
        restListHandler.saveRestInList(restListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getRests/size/{size}/page/{page}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<List<RestListResponse>> getRestsFromList(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(restListHandler.getAllRestFromList(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestListResponse> getRestFromId(@PathVariable Long id) {
        return ResponseEntity.ok(restListHandler.getRestFromList(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteRestFromId(@PathVariable Long id) {
        restListHandler.deleteRestFromList(id);
        return ResponseEntity.noContent().build();
    }
}
