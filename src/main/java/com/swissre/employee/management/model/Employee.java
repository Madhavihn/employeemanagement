package com.swissre.employee.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.swissre.employee.management.Constant.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty(EMPLOYEE_ID)
    private Integer id;

    @JsonProperty(FIRSTNAME)
    private String firstName;

    @JsonProperty(LASTNAME)
    private String lastName;

    @JsonProperty(SALARY)
    private Double salary;

    @JsonProperty(MANAGER_ID)
    private Integer managerId;

    @JsonIgnore
    private List<Employee> subordinates = new ArrayList<>();

    @JsonIgnore
    private Employee manager;

    @JsonIgnore
    private double underPaidAmount;

    @JsonIgnore
    private double overPaidAmount;

    @JsonIgnore
    private Integer longReportingLength;
}
