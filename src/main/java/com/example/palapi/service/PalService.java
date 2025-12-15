package com.example.palapi.service;

import com.example.palapi.entity.Pal;
import com.example.palapi.repository.PalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PalService {
    private final PalRepository palRepository;

    public PalService(PalRepository palRepository) {
        this.palRepository = palRepository;
    }

    // GET ALL Pals
    public List<Pal> getAllPals() {
        return palRepository.findAll();
    }

    // GET by id
    public Optional<Pal> getPalById(Long id) {
        return palRepository.findById(id);
    }

    // GET by name
    public Optional<Pal> getPalByName(String name) {
        return palRepository.findByName(name);
    }

    // GET by type
    public List<Pal> getPalsByType(String type) {
        return palRepository.findByTypesContaining(type);
    }

    // SAVE new Pal
    public Pal savePal(Pal pal) {
        return palRepository.save(pal);
    }

    // GET skills of a Pal
    public List<Pal.Skill> getSkillsForPal(Pal pal) {
        return pal.getSkills();
    }

    public Pal addSkillToPal(Pal pal, Pal.Skill newSkill) {
        List<Pal.Skill> skills = pal.getSkills();
        skills.add(newSkill);
        pal.setSkills(skills);
        return palRepository.save(pal);
    }

    // MODIFY a skill of a Pal
    public Pal updateSkillForPal(Pal pal, Pal.Skill updatedSkill) {
        List<Pal.Skill> skills = pal.getSkills().stream()
                .map(skill -> skill.getName().equals(updatedSkill.getName()) ? updatedSkill : skill)
                .collect(Collectors.toList());
        pal.setSkills(skills);
        return palRepository.save(pal);
    }

    // GET types of a Pal
    public List<String> getTypesForPal(Pal pal) {
        return pal.getTypes();
    }

    // ADD a type to a Pal
    public Pal addTypeToPal(Pal pal, String type) {
        pal.getTypes().add(type);
        return palRepository.save(pal);
    }

    // REMOVE a type from a Pal
    public Pal removeTypeFromPal(Pal pal, String type) {
        pal.getTypes().remove(type);
        return palRepository.save(pal);
    }

    // REMOVE a PAL by id
    public void deletePal(Long id) {
        palRepository.deleteById(id);
    }

    // GET x Pals sorted by rarity
    public List<Pal> getPalsSortedByRarity(int limit) {
        return palRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getRarity().compareTo(p1.getRarity()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    // GET x Pals sorted by price
    public List<Pal> getPalsSortedByPrice(int limit) {
        return palRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}