package com.CarManagement.CarMan.controllers;

import com.CarManagement.CarMan.model.*;
import com.CarManagement.CarMan.repository.MaintenanceTaskRepository;
import com.CarManagement.CarMan.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private UsersDetailsServicesImpl userService;

    @Autowired
    private CarService carService;

    @Autowired
    private MaintenanceTaskService maintenanceTaskService;

    @Autowired
    private FuelUsageServices fuelUsageServices;

    @Autowired
    private ExpenseServices expenseServices;

    @Autowired
    private CarPhotoService carPhotoService;

    @Autowired
    private MaintenanceTaskRepository maintenanceTaskRepository;

    @Autowired
    private ScheduleMaintenanceService scheduleMaintenanceService;


    @GetMapping("/schedule_maintenance_form")
    public ModelAndView getScheduleMaintenanceForm() {
        ModelAndView modelAndView = new ModelAndView("schedule_maintenance_form");
        List<Car> cars = carService.getCarsForCurrentUser();
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("scheduleMaintenance", new ScheduleMaintenance());
        return modelAndView;
    }


    @PostMapping("/schedule-maintenance")
    public String createScheduleMaintenance(@ModelAttribute  ScheduleMaintenance scheduleMaintenance) {

        scheduleMaintenanceService.createScheduleMaintenance(scheduleMaintenance);
        return "redirect:/home";
    }



    @GetMapping("/scheduled-events")
    public List<ScheduleMaintenance> getAllScheduledEvents() {
        return scheduleMaintenanceService.getAllScheduleMaintenances();
    }

    @GetMapping("upload-photo")
    public String showUploadPhotoForm(Model model) {
        List<Car> cars = carService.getCarsForCurrentUser();
        model.addAttribute("cars", cars);
        return "upload-photo";
    }

    @PostMapping("/cars/upload-photo")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("carId") Long carId, Principal principal) throws IOException {
        Car car = carService.getCarById(carId);
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        carPhotoService.savePhoto(file, car,user);
        return "redirect:/home";
    }





    @GetMapping("/cardisplay")
    public String showCarPhotos(Model model, Principal principal) {
        // Get the logged-in user
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        // Get the cars associated with the user
        List<Car> cars = carService.getCarsByUser(user);

        // Get the car photos for each car
        List<CarPhoto> carPhotos = new ArrayList<>();
        for (Car car : cars) {
            List<CarPhoto> photos = carPhotoService.getCarPhotosByCar(car);
            if (!photos.isEmpty()) {
                carPhotos.addAll(photos);
            }
        }

        // Convert the image data to base64 for displaying in the HTML template
        for (CarPhoto carPhoto : carPhotos) {
            String base64Image = Base64.getEncoder().encodeToString(carPhoto.getData());
            carPhoto.setBase64Image(base64Image);
        }

        // Add the car photos to the model
        model.addAttribute("carPhotos", carPhotos);

        return "cardisplay";
    }


    @GetMapping("/photo/{id}")
    public void downloadCarPhoto(@PathVariable Long id, HttpServletResponse response) throws IOException {
        CarPhoto carPhoto = carPhotoService.getCarPhotoById(id);
        byte[] data = carPhoto.getData();

        response.setContentType("image/jpeg");
        response.setContentLength(data.length);
        response.setHeader("Content-Disposition", "attachment; filename=car_photo_" + id + ".jpg");

        try (OutputStream out = response.getOutputStream()) {
            out.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download car photo", e);
        }
    }


    @GetMapping("/cars/photo/{id}")
    public ResponseEntity<byte[]> downloadCarPhoto(@PathVariable Long id) {
        CarPhoto carPhoto = carPhotoService.getCarPhotoById(id);

        if (carPhoto != null) {
            byte[] data = carPhoto.getData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDispositionFormData("attachment", "car_photo_" + id + ".jpg");

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






    @PostMapping("/cars/delete-photo")
    public String deleteCarPhoto(@RequestParam("id") Long id) {
        carPhotoService.deleteCarPhoto(id);
        return "redirect:/cardisplay";
    }





        @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("car", new Car());
        return "redirect:/login";
    }



    @GetMapping("/add_car")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "add_car";
    }

    @GetMapping("/addcar")
    public String getBackOnHomePage(Model model) {
        model.addAttribute("car", new Car());
        return "home";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("car", new Car());
        return "home";
    }




    @GetMapping("/maintenance_task")
    public String showMaintenanceForm(Model model) {
        model.addAttribute("maintenance", new MaintenanceTask());
        return "maintenance_task";
    }

    @GetMapping("/maintenance_task_list_incomplete")
    public String showMaintenanceTaskList(Model model) {
        model.addAttribute("maintenance", new MaintenanceTask());
        return "maintenance_task_list_incomplete";
    }




    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Car> cars = carService.findByUser(user);
        model.addAttribute("cars", cars);
        model.addAttribute("car", new Car());
        return "home";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car car, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        carService.save(car, user);
        return "redirect:/home";
    }


    @GetMapping("/maintenance")
    public String showMaintenanceTaskForm(Model model) {
        MaintenanceTask maintenanceTask = new MaintenanceTask();
        List<Car> cars = carService.getCarsForCurrentUser();
        model.addAttribute("maintenanceTask", maintenanceTask);
        model.addAttribute("cars", cars);
        return "maintenance_task";
    }


    @PostMapping("/maintenance_task")
    public String submitMaintenanceTaskForm(@ModelAttribute("maintenanceTask") MaintenanceTask maintenanceTask) {
        // Save the maintenanceTask to the database using the MaintenanceTaskService
        maintenanceTaskService.saveMaintenanceTask(maintenanceTask);
        // Redirect to a success page or display a message
        return "redirect:/home";
    }

    @GetMapping("/maintenance_tasks/incomplete")
    public String listIncompleteMaintenanceTasks(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<MaintenanceTask> tasks = maintenanceTaskService.findMaintenanceByUserAndCompleted(user, false);
        model.addAttribute("tasks", tasks);
        return "maintenance_task_list_incomplete";
    }







    @GetMapping("/expenses")
    public String showExpenseForm(Model model) {
        Expense expense = new  Expense();
        List<Car> cars = carService.getCarsForCurrentUser();
        model.addAttribute("expense",expense);
        model.addAttribute("cars", cars);
        return "expenses";
    }


    @PostMapping("/expenses/save")
    public String submitExpenseForm(@ModelAttribute("expense") Expense expense) {
        // Save the maintenanceTask to the database using the MaintenanceTaskService
        expenseServices.saveExpense(expense);
        // Redirect to a success page or display a message
        return "redirect:/home";
    }

    // Change the mapping to /expenses_list
    @GetMapping("/expenses_list")
    public String showExpenses(Model model, Authentication authentication,
                               @RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "10") int pageSize) {

        User user = userService.findByUsername(authentication.getName());
        Page<Expense> page = expenseServices.findByUser(user, pageNo, pageSize);
        model.addAttribute("page", page);

        return "expenses_list";
    }

    @GetMapping("/search_expenses")
    public String searchExpenses(Model model, Authentication authentication,
                                 @RequestParam String searchTerm,
                                 @RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "10") int pageSize) {

        User user = userService.findByUsername(authentication.getName());
        Page<Expense> page = expenseServices.findByModelAndUser(searchTerm, user, pageNo, pageSize);
        model.addAttribute("page", page);
        model.addAttribute("searchTerm", searchTerm);

        return "expenses_list";
    }

    @GetMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        expenseServices.delete(expense);
        model.addAttribute("expenses", expenseServices.findAll());
        return "expenses_list";
    }

    @GetMapping("/update-expense/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Expense expense = expenseServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        List<Car> cars = carService.findAll();
        model.addAttribute("expense", expense);
        model.addAttribute("cars", cars);
        return "update_expense";
    }


    @PostMapping("/expenses/update")
    public String updateExpense(@ModelAttribute("expense") @Valid Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "update_expense";
        }

        expenseServices.saveExpense(expense);
        return "redirect:/expenses";
         }


    @GetMapping("/fuel_usage_list")
    public ModelAndView fuelUsageList(@RequestParam(required = false) String searchTerm) {
        ModelAndView modelAndView = new ModelAndView("fuel_usage_list");
        List<FuelUsage> fuelUsages;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            fuelUsages = fuelUsageServices.searchFuelUsages(searchTerm);
        } else {
            fuelUsages = fuelUsageServices.findAllFuelUsages();
        }
        modelAndView.addObject("fuelUsages", fuelUsages);
        return modelAndView;
    }


    @GetMapping("/fuel_usage/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        FuelUsage fuelUsage = fuelUsageServices.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid fuel usage id: " + id));
        model.addAttribute("fuelUsage", fuelUsage);
        // Add the car list for the select box in the update form
        model.addAttribute("cars", carService.findAll());
        return "update_fuel_usage";
    }

    @PostMapping("/fuel_usage/update")
    public String updateFuelUsage(@ModelAttribute("fuelUsage") FuelUsage fuelUsage) {
        fuelUsageServices.saveFuelUsage(fuelUsage);
        return "redirect:/fuel_usage_list";
    }

    @GetMapping("/fuel_usage/delete/{id}")
    public String deleteFuelUsage(@PathVariable("id") Long id) {
        FuelUsage fuelUsage = fuelUsageServices.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid fuel usage id: " + id));
        fuelUsageServices.delete(fuelUsage);
        return "redirect:/fuel_usage_list";
    }



    @GetMapping("/fuel_usage")
    public String showFuelUsageForm(Model model) {
        FuelUsage fuelUsage = new FuelUsage();
        List<Car> cars = carService.getCarsForCurrentUser();
        model.addAttribute("fuelUsage",fuelUsage);
        model.addAttribute("cars", cars);
        return "fuel_usage";
    }


    @PostMapping("/fuel_usage/save")
    public String submitFuelUsageForm(@ModelAttribute("fuelUsage") FuelUsage fuelUsage) {
        // Save the maintenanceTask to the database using the MaintenanceTaskService
        fuelUsageServices.saveFuelUsage(fuelUsage);
        // Redirect to a success page or display a message
        return "redirect:/home";
    }


    @GetMapping("/car_list")
    public ModelAndView carList(@RequestParam(required = false) String searchTerm) {
        ModelAndView modelAndView = new ModelAndView("car_list");
        List<Car> cars;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            cars = carService.searchCars(searchTerm);
        } else {
            cars = carService.findAllCars();
        }
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("/update-car/{id}")
    public ModelAndView updateCar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("add_car");
        modelAndView.addObject("car", carService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update-car/{id}")
    public String updateCar(@PathVariable("id") Long id, @Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "add_car";
        }
        car.setId(id);
        carService.save(car);
        return "redirect:/car_list";
    }


    @GetMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/car_list";
    }






    @PostMapping("/maintenance_task/complete/{id}")
    public String updateMaintenance(@PathVariable("id") Long id, @Valid MaintenanceTask maintenanceTask, BindingResult result) {
        if (result.hasErrors()) {
            return "home";
        }
        maintenanceTaskService.saveMaintenanceTask(maintenanceTask);
        return "redirect:/maintenance_task_list_incomplete";
    }

    @GetMapping("/maintenance_task/complete/{id}")
    public String updateMaintenance(@PathVariable("id") long id, Model model) {
        MaintenanceTask maintenanceTask =  maintenanceTaskService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Maintenance Id:" + id));
        List<Car> cars = carService.findAll();
        model.addAttribute("maintenance_task", maintenanceTask);
        model.addAttribute("cars", cars);
        return "complete_maintenance";
    }









}

