package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollFileIOService {

    public static String FILE_PATH = "C:\\Users\\chetan bhagat\\IdeaProjects\\EmployeePayroll_JDBC\\src\\main\\resources\\EmployeeInfo.txt";

    //writeData method with parameters
    public void writeData(List<EmployeePayrollData> employeePayRollList) {
        StringBuffer empBuffer = new StringBuffer();//creating object of stringBuffer class
        employeePayRollList.forEach(employee -> {
            String empDataString = employee.toString().concat("\n");
            empBuffer.append(empDataString);

        });
        try {
            Files.write(Paths.get(FILE_PATH), empBuffer.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //printData method in try-catch block
    public void printData() {
        try {
            Files.lines(new File(FILE_PATH).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**countEntries method which is of long type
     * This method count the entries in employee_payroll_new table
     */
    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_PATH).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return entries;

    }
}
