package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload")MultipartFile fileUpload, Model model) throws IOException {
        String fileName = fileUpload.getOriginalFilename();
        String userName = authentication.getName();
        String [] savedFileNames = fileService.getFileNameList();
        for (String savedFilename: savedFileNames) {
            if (Objects.equals(savedFilename, fileName )) {
                model.addAttribute("result", "error");
                model.addAttribute("message", "File with the same name already exists.");
                return "result";
            }
        }
        fileService.saveFile(fileUpload, userName);
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping(
            value = "/get-file/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<byte[]> viewFile(@PathVariable String fileName) throws IOException {
        FileModel fileModel = fileService.getFile(fileName);
        String fileType = fileService.getFile(fileName).getContenttype();
        MediaType mediaType = MediaType.parseMediaType(fileType);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(fileModel.getFiledata().length)
                .body(fileModel.getFiledata());
    }

    @GetMapping("/delete-file/{fileName}")
    public String deleteFile(@PathVariable String fileName, Model model) {
        fileService.deleteFile(fileName);
        model.addAttribute("result", "success");
        return "result";
    }
}
