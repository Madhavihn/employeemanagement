package com.swissre.employee.management.analyzer;


import com.swissre.employee.management.csv.CsvParser;
import com.swissre.employee.management.model.Employee;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Employee Analyzer class which performs necessary operations on employee csv data.
 */
@Component
public class EmployeeAnalyzer {

    public EmployeeAnalyzer() {
    }

    /**
     * Load CSV data onto a bean class
     * @param csvFile
     * @return Map having key - as employeeId/maanagerId - list of employees reporting to that manager.
     * @throws IOException
     */
    public Map<Integer, Employee> loadEmployeeData(File csvFile) throws IOException {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        List<Employee> employeeList = CsvParser.mapCsvToBean(csvFile);

        // Populate the employees map
        for (Employee employee : employeeList) {
            employeeMap.put(employee.getId(), employee);
        }

        // Set manager and subordinates relationships
        for (Employee employee : employeeMap.values()) {
            if (employee.getManagerId() != null) {
                Employee manager = employeeMap.get(employee.getManagerId());
                employee.setManager(manager);
                manager.getSubordinates().add(employee);
            }
        }
        return employeeMap;
    }

    /**
     * Analyze manager salaries and generate list of overpaid and underpaid managers.
     * @param employeeMap manager-emplpyeesList map
     */
    private void analyzeManagerSalaries(Map<Integer, Employee> employeeMap) {
        List<Employee> underPaidManagerList = new ArrayList<>();
        List<Employee> overpaidManagerList = new ArrayList<>();
        for (Employee manager : employeeMap.values()) {

            if (!manager.getSubordinates().isEmpty()) {
                double averageSubordinateSalary = manager.getSubordinates().stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0.0);

                double lowerBound = averageSubordinateSalary * 1.2;
                double upperBound = averageSubordinateSalary * 1.5;

                if (manager.getSalary() < lowerBound) {
                    double underPaidAmount = lowerBound - manager.getSalary();
                    manager.setUnderPaidAmount(underPaidAmount);
                    underPaidManagerList.add(manager);

                } else if (manager.getSalary() > upperBound) {
                    double overPaidAmount =  manager.getSalary() - upperBound;
                    manager.setOverPaidAmount(overPaidAmount);
                    overpaidManagerList.add(manager);
                }
            }
        }

        System.out.println("Here is the list of " + underPaidManagerList.size() + " UNDERPAID Managers: ");
        underPaidManagerList.forEach(employee ->  {
            String printString =  String.format("Manager Name: %s %s, UnderPaid Amount: %f",
                    employee.getFirstName(), employee.getLastName(), employee.getUnderPaidAmount());
            System.out.println(printString);
        });
        System.out.println("------------------------------------------------------------------");
        System.out.println("Here is the list of " + overpaidManagerList.size() + " OVERPAID Managers: ");
        overpaidManagerList.forEach(employee ->  {
            String printString =  String.format("Manager Name: %s %s, OverPaid Amount: %f",
                    employee.getFirstName(), employee.getLastName(), employee.getOverPaidAmount());
            System.out.println(printString);
        });
    }

    /**
     * Analyze and generate reporting line for each employees and generate a report.
     * @param employeeMap manager-emplpyeesList map
     */
    private void findEmployeesWithLongReportingLines(Map<Integer, Employee> employeeMap) {
        List<Employee> longReportingEmployees = new ArrayList<>();

        System.out.println("------------------------------------------------------------------");
        System.out.println("Here is the list of Employees who have reporting length more than 4: ");
        System.out.println("PS: THIS ALSO INCLUDES EMPLOYEES WHO ARE NOT MANAGERS!!!");

        for (Employee employee : employeeMap.values()) {
            int reportingLineLength = getReportingLineLength(employee);
            employee.setLongReportingLength(reportingLineLength);

            if (reportingLineLength > 4) {

                String printString =  String.format("Employee Name: %s %s, has a reporting line that is too long by: %d",
                        employee.getFirstName(), employee.getLastName(), employee.getLongReportingLength());
                System.out.println(printString);
                longReportingEmployees.add(employee);
            }
        }
        System.out.println("There are " + longReportingEmployees.size() + "  employees who have reporting line more than 4");
    }

    /**
     * Helper method for generating employee to CEO reporting length
     * @param employee
     * @return
     */
    private int getReportingLineLength(Employee employee) {
        int length = 0;
        Employee current = employee.getManager();

        while (current != null) {
            length++;
            current = current.getManager();
        }

        return length;
    }

    /**
     * public method which will consolidate the result.
     * @param csvFile
     * @throws IOException
     */
    public void getEmployeeDetails(File csvFile) throws IOException {
        Map<Integer, Employee> employeeMap = loadEmployeeData(csvFile);
        if(!employeeMap.isEmpty()) {
            analyzeManagerSalaries(employeeMap);
            findEmployeesWithLongReportingLines(employeeMap);
        }

    }

}
