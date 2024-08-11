package com.swissre.employee.management.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.swissre.employee.management.model.Employee;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CSV parser class for parsing input CSV file.
 */
public class CsvParser {

    /**
     * Map CSV File content to Employee Bean.
     * @param csvFile input file
     * @return List of Employees
     * @throws IOException
     */
    public static List<Employee> mapCsvToBean(File csvFile) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper
                .schemaFor(Employee.class)
                .withHeader()
                .withColumnReordering(true);

        try (MappingIterator<Employee> iterator = csvMapper.readerFor(Employee.class)
                .with(csvSchema).readValues(csvFile)) {
            return iterator.readAll();
        }
    }
}
