package com.swp.SchoolManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import com.swp.SchoolManagement.config.Excel.ExcelHelper;
import com.swp.SchoolManagement.model.Student;
import com.swp.SchoolManagement.repository.StudentRepository;

@Service
public class ExcelService  {
    @Autowired
    StudentRepository repository;
    public void save(MultipartFile file) {
      try {
        List<Student> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
        repository.saveAll(tutorials);
      } catch (IOException e) {
        throw new RuntimeException("fail to store excel data: " + e.getMessage());
      }
    }
    public List<Student> getAllStudent() {
      return repository.findAll();
    }
}
