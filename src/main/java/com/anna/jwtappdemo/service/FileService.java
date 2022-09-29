package com.anna.jwtappdemo.service;

import com.anna.jwtappdemo.model.File;

import java.util.List;

public interface FileService {
        File add(File file);
        List<File> getAll();
        File findByPath(String path);
        File findById(Long id);
//        File update(File file);
        void delete(Long id);

    }

