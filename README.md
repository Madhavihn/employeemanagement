# Project Title

EmployeeManagement project:
IG COMPANY is employing a lot of employees. Company would like to analyze its organizational
structure and identify potential improvements. Board wants to make sure that every manager earns
at least 20% more than the average salary of its direct subordinates, but no more than 50% more
than that average. Company wants to avoid too long reporting lines, therefore we would like to
identify all employees which have more than 4 managers between them and the CEO.




# Project Description
You are given a CSV file which contains information about all the employees. File structure looks like
this:

```bash

Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
```

Each line represents an employee (CEO included). CEO has no manager specified. Number of rows
can be up to 1000.
Write a simple program which will read the file and report:
- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much
## Installation

Instructions on how to install the project.

```bash
# Clone the repository
git clone https://github.com/Madhavihn/employeemanagement

# Navigate to the project directory
cd employeemanagement
Make sure java and maven is installed in your system
# Build project
mvn clean install

#run application
mvn spring-boot:run
```
You will see the required result in command Line. Please scroll up to see the entire result.

1. UnderPaid manager list with details
2. OverPaid manager list with details
3. Employees with long reporting line

