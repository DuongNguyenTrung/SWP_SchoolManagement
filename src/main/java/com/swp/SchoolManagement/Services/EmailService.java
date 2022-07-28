package com.swp.SchoolManagement.services;

import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.DTO.EmailDetails;
import com.swp.SchoolManagement.DTO.EmailDetailsBCC;

@Service
public interface EmailService {
  // Method
  // To send a simple email
  String sendSimpleMail(EmailDetails details);

  String sendMailBBC(EmailDetailsBCC bcc);

  // Method
  // To send an email with attachment
  String sendMailWithAttachment(EmailDetails details);
}
