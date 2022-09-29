package com.anna.jwtappdemo.repository;

import com.anna.jwtappdemo.model.File;
import com.anna.jwtappdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByPath(String path);
}
