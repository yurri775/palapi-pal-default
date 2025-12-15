package com.example.palapi.service;

import com.example.palapi.entity.Counter;
import com.example.palapi.repository.CounterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CounterService {
    
    private final CounterRepository counterRepository;
    
    // Constructeur pour injection de dépendances
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }
    
    public List<Counter> getAllCounters() {
        return counterRepository.findAll();
    }
    
    public Counter getCounterByName(String name) {
        return counterRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Counter not found: " + name));
    }
    
    @Transactional
    public Counter createCounter(String name) {
        if (counterRepository.existsByName(name)) {
            throw new RuntimeException("Counter already exists: " + name);
        }
        Counter counter = new Counter();
        counter.setName(name);
        counter.setValue(0L);
        return counterRepository.save(counter);
    }
    
    @Transactional
    public Counter increment(String name) {
        Counter counter = counterRepository.findByName(name)
                .orElseGet(() -> {
                    Counter newCounter = new Counter();
                    newCounter.setName(name);
                    newCounter.setValue(0L);
                    return newCounter;
                });
        counter.setValue(counter.getValue() + 1);
        return counterRepository.save(counter);
    }
    
    @Transactional
    public Counter decrement(String name) {
        Counter counter = getCounterByName(name);
        counter.setValue(counter.getValue() - 1);
        return counterRepository.save(counter);
    }
    
    @Transactional
    public void deleteCounter(String name) {
        Counter counter = getCounterByName(name);
        counterRepository.delete(counter);
    }
    
    @Transactional
    public Counter resetCounter(String name) {
        Counter counter = getCounterByName(name);
        counter.setValue(0L);
        return counterRepository.save(counter);
    }
}
