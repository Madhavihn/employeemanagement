package com.swissre.employee.management.controller;

import com.swissre.employee.management.analyzer.EmployeeAnalyzer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

import static com.swissre.employee.management.Constant.EMPLOYEE_CSV_PATH;

/**
 * Sample Controller class.
 */
@RestController
public class EmployeeController {

    private EmployeeAnalyzer employeeAnalyzer;

    public EmployeeController(EmployeeAnalyzer employeeAnalyzer) {
        this.employeeAnalyzer = employeeAnalyzer;
    }

    /**
     * Dummy API to invoke printing the results in command Line.
     * @return String
     * @throws IOException
     */
    @GetMapping("/employee/details")
    public String getEmployee() throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource(EMPLOYEE_CSV_PATH);
            File file = resource.getFile();
            EmployeeAnalyzer employeeAnalyzer = new EmployeeAnalyzer();
            employeeAnalyzer.getEmployeeDetails(file);
            return "Pease see command line for details";
        } catch (IOException e) {
            throw new RuntimeException("Exception while getting Employee details" +e);
        }



    }
}
