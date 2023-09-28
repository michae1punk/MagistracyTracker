package com.myakishev.magistracyTracker.service;

import com.myakishev.magistracyTracker.dao.StudentDao;
import com.myakishev.magistracyTracker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private HtmlService htmlService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private StudentDao dao;

    public void saveStudents() throws IOException, AWTException {
        List<Student> students = htmlService.getNewStudents();
        for (Student st : students) {
            Student dbStudent = dao.findOneBySnils(st.getSnils());
            if (dbStudent == null) {
                notificationService.sendNotification(st);
                dao.save(st);
            } else {
                dbStudent.setNumberInList(st.getNumberInList());
                dao.save(dbStudent);
            }
        }
    }
}
