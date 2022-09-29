package com.anna.jwtappdemo.service;

import com.anna.jwtappdemo.model.Event;

import java.util.List;

public interface EventService {
    Event add(Event event);
    List<Event> getAll();
    Event findById(Long id);
    void delete(Long id);
}
