package com.swissre.employee.management;

import com.swissre.employee.management.analyzer.EmployeeAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static com.swissre.employee.management.Constant.EMPLOYEE_CSV_PATH;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
        try {
        ClassPathResource resource = new ClassPathResource(EMPLOYEE_CSV_PATH);
        File file = resource.getFile();
		EmployeeAnalyzer employeeAnalyzer = new EmployeeAnalyzer();
        employeeAnalyzer.getEmployeeDetails(file);
        } catch (IOException e) {
            throw new RuntimeException("Exception while getting Employee details" +e);
        }
    }

}
