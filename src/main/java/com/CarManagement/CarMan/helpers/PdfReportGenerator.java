package com.CarManagement.CarMan.helpers;

import com.CarManagement.CarMan.model.Car;
import com.CarManagement.CarMan.model.Expense;
import com.CarManagement.CarMan.model.FuelUsage;
import com.CarManagement.CarMan.model.MaintenanceTask;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfReportGenerator {

    public static ByteArrayInputStream generateReport(List<FuelUsage> fuelUsages, List<Expense> expenses, List<Car> cars, List<MaintenanceTask> maintenanceTasks) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Add title
        document.add(new Paragraph("Car Management Report"));

        // Add Fuel Usage table
        document.add(new Paragraph("Fuel Usage"));
        Table fuelUsageTable = createFuelUsageTable(fuelUsages);
        document.add(fuelUsageTable);

        // Add Expense table
        document.add(new Paragraph("Expenses"));
        Table expenseTable = createExpenseTable(expenses);
        document.add(expenseTable);



        // Add Car table
        document.add(new Paragraph("Cars"));
        Table carTable = createCarTable(cars);
        document.add(carTable);

        // Add Maintenance Tasks table
        document.add(new Paragraph("Maintenance Tasks"));
        Table maintenanceTable = createMaintenanceTable(maintenanceTasks);
        document.add(maintenanceTable);


        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static Table createFuelUsageTable(List<FuelUsage> fuelUsages) {
        // Adjust the column count according to your FuelUsage entity
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 25, 25, 25,25}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table headers
        table.addHeaderCell("Car Model");
        table.addHeaderCell("odometer");
        table.addHeaderCell("Liters");
        table.addHeaderCell("Price/Unit");
        table.addHeaderCell("total Price");

        // Add table data
        for (FuelUsage fuelUsage : fuelUsages) {
            table.addCell(fuelUsage.getCar().getMake() + " " + fuelUsage.getCar().getModel());
            table.addCell(String.valueOf(fuelUsage.getOdometer())+" KM").toString();
            table.addCell(String.valueOf(fuelUsage.getFuelVolume()+" L").toString());
            table.addCell(String.valueOf(fuelUsage.getPricePerUnit()+"$"));
            table.addCell(String.valueOf(fuelUsage.getTotalPrice()));

        }

        return table;
    }

    private static Table createExpenseTable(List<Expense> expenses) {
        // Adjust the column count according to your Expense entity
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 25, 40, 25}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table headers
        table.addHeaderCell("Car Model");
        table.addHeaderCell("Expense Date");
        table.addHeaderCell("description");
        table.addHeaderCell("Amount");

        // Add table data
        for (Expense expense : expenses) {
            table.addCell(expense.getCar().getMake() + " " + expense.getCar().getModel());
            table.addCell(expense.getExpenseDate().toString());
            table.addCell(expense.getDescription());
            table.addCell(String.valueOf(expense.getAmount()+" $"));
        }

        return table;
    }

    private static Table createCarTable(List<Car> cars) {
        // Adjust the column count according to your Car entity
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 20, 20, 20, 20,20}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table headers
        table.addHeaderCell("Make");
        table.addHeaderCell("Model");
        table.addHeaderCell("vinNumber");
        table.addHeaderCell("year");
        table.addHeaderCell("License Plate");
        table.addHeaderCell("currentMileage");

        // Add table data
        for (Car car : cars) {
            table.addCell(car.getMake());
            table.addCell(car.getModel());
            table.addCell(car.getVinNumber());
            table.addCell(String.valueOf(car.getYear()));
            table.addCell(car.getLicensePlate());
            table.addCell(car.getCurrentMileage()+" Km");
        }

        return table;
    }



    private static Table createMaintenanceTable(List<MaintenanceTask> maintenanceTasks) {
        // Adjust the column count according to your Car entity
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 25, 20, 20, 20,20,20,20,20}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table headers
        table.addHeaderCell("Car Model");
        table.addHeaderCell("Title");
        table.addHeaderCell("Description");
        table.addHeaderCell("DueDate");
        table.addHeaderCell("Cost");
        table.addHeaderCell("mechanicName");
        table.addHeaderCell("ServiceProvider");
        table.addHeaderCell("Completed");
        table.addHeaderCell("CompletedDate");



        // Add table data
        for (MaintenanceTask maintenanceTask : maintenanceTasks) {
            table.addCell(maintenanceTask.getCar().getMake() + " " + maintenanceTask.getCar().getModel());
            table.addCell(maintenanceTask.getTitle());
            table.addCell(maintenanceTask.getDescription());
            table.addCell(String.valueOf(maintenanceTask.getDueDate()).toString());
            table.addCell(String.valueOf(maintenanceTask.getCost()+" $")).toString();
            table.addCell(maintenanceTask.getMechanicName().toString());
            table.addCell(maintenanceTask.getServiceProvider().toString());
            table.addCell(String.valueOf(maintenanceTask.isCompleted()).toString());
            table.addCell(String.valueOf(maintenanceTask.getCompletionDate()).toString());
        }

        return table;
    }


}

