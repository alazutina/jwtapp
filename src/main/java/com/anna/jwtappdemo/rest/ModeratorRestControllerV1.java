package com.anna.jwtappdemo.rest;

import com.anna.jwtappdemo.dto.FileDto;
import com.anna.jwtappdemo.dto.ModeratorDto;
import com.anna.jwtappdemo.model.File;
import com.anna.jwtappdemo.model.Status;
import com.anna.jwtappdemo.model.User;
import com.anna.jwtappdemo.service.FileService;
import com.anna.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/moderator/")
public class ModeratorRestControllerV1 {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public ModeratorRestControllerV1(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<ModeratorDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ModeratorDto result = ModeratorDto.fromUser(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(value = "{files}/{id}")
    public ResponseEntity<FileDto> deleteFileById(@PathVariable(name = "id") Long id){
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{files}/{save}")
    public ResponseEntity<FileDto> saveFile(@RequestBody File object) {
        System.out.println(object+"!");
        object.setStatus(Status.ACTIVE);
        File file1 =   fileService.register(object);
        FileDto result = FileDto.fromFile(file1);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("{files}/{update}")
    public ResponseEntity<FileDto> updateFile(@RequestBody File object) {
        File file1 =   fileService.update(object);
        FileDto result = FileDto.fromFile(file1);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
