package com.example.palapi.controller;

import com.example.palapi.entity.Counter;
import com.example.palapi.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/counters")
@Tag(name = "Counters", description = "API de gestion des compteurs")
public class CounterController {
    
    private final CounterService counterService;
    
    // Constructeur pour injection de dépendances
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }
    
    @GetMapping
    @Operation(summary = "Récupérer tous les compteurs")
    public ResponseEntity<List<Counter>> getAllCounters() {
        return ResponseEntity.ok(counterService.getAllCounters());
    }
    
    @GetMapping("/{name}")
    @Operation(summary = "Récupérer un compteur par nom")
    public ResponseEntity<Counter> getCounter(@PathVariable String name) {
        return ResponseEntity.ok(counterService.getCounterByName(name));
    }
    
    @PostMapping
    @Operation(summary = "Créer un nouveau compteur")
    public ResponseEntity<Counter> createCounter(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(counterService.createCounter(name));
    }
    
    @PostMapping("/{name}/increment")
    @Operation(summary = "Incrémenter un compteur")
    public ResponseEntity<Counter> increment(@PathVariable String name) {
        return ResponseEntity.ok(counterService.increment(name));
    }
    
    @PostMapping("/{name}/decrement")
    @Operation(summary = "Décrémenter un compteur")
    public ResponseEntity<Counter> decrement(@PathVariable String name) {
        return ResponseEntity.ok(counterService.decrement(name));
    }
    
    @PostMapping("/{name}/reset")
    @Operation(summary = "Réinitialiser un compteur à 0")
    public ResponseEntity<Counter> reset(@PathVariable String name) {
        return ResponseEntity.ok(counterService.resetCounter(name));
    }
    
    @DeleteMapping("/{name}")
    @Operation(summary = "Supprimer un compteur")
    public ResponseEntity<Void> deleteCounter(@PathVariable String name) {
        counterService.deleteCounter(name);
        return ResponseEntity.noContent().build();
    }
}
