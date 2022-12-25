package com.bridgelabz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO
    }

    public EmployeePayrollDBService employeePayrollDBService;
    public List<EmployeePayrollData> employeePayrollList = new ArrayList<>();//creating list object

    public EmployeePayrollService(List<EmployeePayrollData> list) {//constructor with parameter
        this.employeePayrollList = list;
    }

    public EmployeePayrollService() {//constructor
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    };

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayRollList = new ArrayList<>();//creating object of arraylist
        EmployeePayrollService empPayRollService = new EmployeePayrollService(employeePayRollList);//creating object of EmployeePayrollService class
        Scanner consoleReader = new Scanner(System.in);//creating object of scanner class
        empPayRollService.readEmployeePayrollData(consoleReader);
        empPayRollService.writeEmployeePayrollData();//using EmployeePayrollService object calling writeEmployeePayrollData methd
        empPayRollService.writeEmployeePayrollData(IOService.FILE_IO);
    }

    public void readEmployeePayrollData(Scanner consoleReader) {
        System.out.println("Enter emp id :");
        int id = consoleReader.nextInt();
        System.out.println("Enter emp name :");
        String name = consoleReader.next();
        System.out.println("Enter emp salary :");
        double salary = consoleReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));//Added data in employeePayrollList
    }

    public void writeEmployeePayrollData() {
        System.out.println("writing emp payroll to console :" + employeePayrollList);//printing employeePayrollList
    }

    public void writeEmployeePayrollData(IOService ioService) {

        if (ioService.equals(IOService.CONSOLE_IO)) {
            System.out.println("writing emp payroll to console :" + employeePayrollList);
        } else if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeData(employeePayrollList);//calling writeData method

        }
    }

    //printData method with parameter
    public void printData(IOService ioService) {

        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }

    }

    // countEntries method which is of long type with parameter
    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return 0;
    }

    //readEmployeePayroll method with parameter which of list type
    public List<EmployeePayrollData> readEmployeePayroll(IOService ioService) throws EmployeePayrollException {
        if (ioService.equals(IOService.DB_IO)) {
            this.employeePayrollList = employeePayrollDBService.readData();
        }

        return this.employeePayrollList;
    }

    //updateEmployeeSalary method with parameters name, salary
    public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if (result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream().filter(empPayrollDataItem -> empPayrollDataItem.name.equals(name))
                .findFirst().orElse(null);//Returns a stream consisting of the elements of this stream that match the given predicate

    }

    public boolean checkEmployeePayrollSyncWithDataBase(String name) throws EmployeePayrollException {
        return employeePayrollList.get(0).equals(getEmployeePayrollData(name));
    }
}
