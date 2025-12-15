package com.example.palapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.palapi.entity.Pal;
import com.example.palapi.service.PalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pals")
@CrossOrigin(origins = "*")
@Tag(name = "Pals", description = "API de gestion des Pals Palworld")
public class PalController {

    private final PalService palService;

    public PalController(PalService palService) {
        this.palService = palService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les Pals")
    public ResponseEntity<List<Pal>> getAllPals() {
        return ResponseEntity.ok(palService.getAllPals());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un Pal par ID")
    public ResponseEntity<Pal> getPalById(@PathVariable Long id) {
        return palService.getPalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Récupérer un Pal par nom")
    public ResponseEntity<Pal> getPalByName(@PathVariable String name) {
        return palService.getPalByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau Pal")
    public ResponseEntity<Pal> createPal(@RequestBody Pal pal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(palService.savePal(pal));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un Pal")
    public ResponseEntity<Pal> updatePal(@PathVariable Long id, @RequestBody Pal pal) {
        return palService.getPalById(id)
                .map(existingPal -> {
                    pal.setId(id);
                    return ResponseEntity.ok(palService.savePal(pal));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un Pal")
    public ResponseEntity<Map<String, String>> deletePal(@PathVariable Long id) {
        palService.deletePal(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pal supprimé avec succès");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Rechercher par type")
    public ResponseEntity<List<Pal>> getPalsByType(@PathVariable String type) {
        return ResponseEntity.ok(palService.getPalsByType(type));
    }
}