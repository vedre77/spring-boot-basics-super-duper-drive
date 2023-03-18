package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(Model model) {
        model.addAttribute("result", "error");
        model.addAttribute("message", "Maximum file size of 1MB exceeded, please try again.");
        return "result";
    }

}
