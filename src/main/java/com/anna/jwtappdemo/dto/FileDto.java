package com.anna.jwtappdemo.dto;

import com.anna.jwtappdemo.model.File;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {
    private String path;

    public File toFile(){
        File file=new File();
        file.setPath(path);
        return file               ;
    }

    public static FileDto fromFile(File file){
        FileDto fileDto = new FileDto();
        fileDto.setPath(fileDto.path);
        return fileDto;
    }

}
