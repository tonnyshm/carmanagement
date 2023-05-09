package com.CarManagement.CarMan.services;

import com.CarManagement.CarMan.helpers.PdfReportGenerator;
import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.FuelUsage;
import com.CarManagement.CarMan.model.MaintenanceTask;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FuelUsageServices fuelUsageService;

    @Autowired
    private ExpenseServices expenseService;

    @Autowired
    private CarService carService;

    @Autowired
    private MaintenanceTaskService maintenanceTaskService;

    public void sendReport(String email, String username) {
        // Retrieve user data
        List<FuelUsage> fuelUsages = fuelUsageService.findByUsername(username);
        List<Expense> expenses = expenseService.findByUsername(username);
        List<Car> cars = carService.findByUsername(username);
        List<MaintenanceTask> maintenanceTasks = maintenanceTaskService.findByUsername(username);

        // Generate PDF report
        ByteArrayInputStream bis = PdfReportGenerator.generateReport(fuelUsages, expenses, cars,maintenanceTasks);

        // Send email
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Car Management Report");
            helper.setText("Please find your car management report attached.");
            helper.addAttachment("report.pdf", new ByteArrayResource(IOUtils.toByteArray(bis)));
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}

