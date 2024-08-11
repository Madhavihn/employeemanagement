/*
package com.swissre.employee.management.analyzer;

import com.swissre.employee.management.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAnalyzerTest {

    private static final String TEST_EMPLOYEE_CSV_PATH = "static/employees.csv";

    static File testCsvFile;

    private static EmployeeAnalyzer employeeAnalyzer;


    @BeforeAll
    public static void setUp() {
        try {
            ClassPathResource resource = new ClassPathResource(TEST_EMPLOYEE_CSV_PATH);
            testCsvFile = resource.getFile();
            employeeAnalyzer = new EmployeeAnalyzer();
        } catch (IOException e) {
            throw new RuntimeException("Exception while setting up test " +e);
        }
    }

    @Test
    void testLoadEmployeeData() throws IOException {
        Map<Integer, Employee> map = employeeAnalyzer.loadEmployeeData(testCsvFile);
        assertFalse(map.isEmpty());

    }

    

}
*/
