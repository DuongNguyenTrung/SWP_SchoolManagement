package com.swp.SchoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Timetable")
public class Timetable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "timetable_id")
  long timetableId;

  @Column(name = "subject_id")
  String subjectId;

  @Column(name = "day_slot_id")
  long daySlotId;

  @Column(name = "class_id")
  int classId;

  @Column(name = "status")
  int status;
}
