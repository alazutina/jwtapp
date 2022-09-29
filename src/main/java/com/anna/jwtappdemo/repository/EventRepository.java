package com.anna.jwtappdemo.repository;

import com.anna.jwtappdemo.model.Event;
import com.anna.jwtappdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

}
