package com.anna.jwtappdemo.rest;

import com.anna.jwtappdemo.dto.FileDto;
import com.anna.jwtappdemo.model.File;
import com.anna.jwtappdemo.model.Status;
import com.anna.jwtappdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/files/")
public class FileRestControllerV1 {

        private final FileService fileService;

        @Autowired
        public FileRestControllerV1(FileService fileService) {
            this.fileService = fileService;
        }

        @GetMapping(value = "")
        public ResponseEntity<List<FileDto>> getAllFiles() {
            List<File> arrayList = fileService.getAll();
            if(arrayList==null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<FileDto> fileDtos=new ArrayList<>();
            for( File f : arrayList){

            fileDtos.add(FileDto.fromFile(f));
            }
            return new ResponseEntity<>(fileDtos,HttpStatus.OK);
        }

    @GetMapping(value = "{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable(name = "id") Long id){
            File file = fileService.findById(id);
        if(file==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        FileDto result = FileDto.fromFile(file);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<FileDto> deleteFileById(@PathVariable(name = "id") Long id){
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<FileDto> save(@RequestBody File object) {
        System.out.println(object+"!");
        object.setStatus(Status.ACTIVE);
        File file1 =   fileService.register(object);
        FileDto result = FileDto.fromFile(file1);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<FileDto> update(@RequestBody File object) {
        File file1 =   fileService.update(object);
        FileDto result = FileDto.fromFile(file1);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}


