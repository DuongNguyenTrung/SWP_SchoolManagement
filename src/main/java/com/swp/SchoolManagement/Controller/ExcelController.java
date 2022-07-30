package com.swp.SchoolManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swp.SchoolManagement.config.Excel.ExcelHelper;
import com.swp.SchoolManagement.model.Student;
import com.swp.SchoolManagement.response.ResponseMessage;
import com.swp.SchoolManagement.services.ExcelService;

@RestController
@RequestMapping("/import")
public class ExcelController  {
    
    @Autowired
    ExcelService fileService;
    
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
      String message = "";
      if (ExcelHelper.hasExcelFormat(file)) {
        try {
          fileService.save(file);
          message = "Uploaded the file successfully: " + file.getOriginalFilename();
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
          message = "Could not upload the file: " + file.getOriginalFilename() + "!";
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
      }
      message = "Please upload an excel file!";
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @GetMapping("/Student")
    public ResponseEntity<List<Student>> getAllTutorials() {
      try {
        List<Student> tutorials = fileService.getAllStudent();
        if (tutorials.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
