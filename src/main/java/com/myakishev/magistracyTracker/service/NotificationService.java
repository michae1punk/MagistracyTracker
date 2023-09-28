package com.myakishev.magistracyTracker.service;

import com.myakishev.magistracyTracker.model.Student;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class NotificationService {
    public void sendNotification(Student student) throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage("images/tray.png");
            TrayIcon trayIcon = new TrayIcon(image);
            tray.add(trayIcon);
            String notificationMessage = "Новый студент c " + student.getSumPoints() + " баллами появился на " + student.getNumberInList() + " месте";
            trayIcon.displayMessage("НОВЫЙ СТУДЕНТ", notificationMessage,
                    TrayIcon.MessageType.INFO);
        }
    }
}
