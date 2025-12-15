package com.example.palapi.dataloader;

import com.example.palapi.entity.Pal;
import com.example.palapi.repository.PalRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class PalDataLoader implements CommandLineRunner {

    private final PalRepository palRepository;
    private final ObjectMapper objectMapper;

    public PalDataLoader(PalRepository palRepository, ObjectMapper objectMapper) {
        this.palRepository = palRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPalData();
    }

    private void loadPalData() {
        if (palRepository.count() == 0) {
            List<Pal> pals = loadPalsFromJson();
            palRepository.saveAll(pals);
            System.out.println("Loaded " + pals.size() + " pals from JSON file.");
        }
    }

    private List<Pal> loadPalsFromJson() {
        try {
            ClassPathResource resource = new ClassPathResource("pals.json");
            // ✅ Utiliser getInputStream() au lieu de getFile()
            // Fonctionne aussi bien en développement qu'en JAR
            try (InputStream inputStream = resource.getInputStream()) {
                return objectMapper.readValue(inputStream, new TypeReference<List<Pal>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du fichier Pals JSON", e);
        }
    }
}