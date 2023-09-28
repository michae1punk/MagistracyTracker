package com.myakishev.magistracyTracker.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class CronJobService {
    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "${scheduler.cron:}")
    public void checkNewStudents() throws JSONException, IOException, AWTException {
        studentService.saveStudents();
    }
}
