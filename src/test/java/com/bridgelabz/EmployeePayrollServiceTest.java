package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bridgelabz.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {

    @Test
    public void givenThreeEmployees_WrittenToFile() {
        EmployeePayrollData[] empdata = {new EmployeePayrollData(1, "Bill", 1000000),
                new EmployeePayrollData(2, "Tersia", 2000000),
                new EmployeePayrollData(3, "Charlie", 300000)};
        EmployeePayrollService employeePayrollService;

        employeePayrollService = new EmployeePayrollService(Arrays.asList(empdata));
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
        employeePayrollService.printData(IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(IOService.FILE_IO);
        Assertions.assertEquals(3, entries);

    }

        @Test
        public void givenEmployeePayrollInDB_whenRetrieved_shouldMatch_EmployeeCount() throws EmployeePayrollException {
            EmployeePayrollService employeePayrollService = new EmployeePayrollService();
            List<EmployeePayrollData> empPayrollData = employeePayrollService.readEmployeePayroll(IOService.DB_IO);
            Assertions.assertEquals(3, empPayrollData.size());
        }

    @Test
    public void givenNewSalary_ForEmployee_WhenUpdated_ShouldMatch() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> empPayrollData = employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("Tersia",3000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollSyncWithDataBase("Terisa");
        Assertions.assertFalse(result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatch_EmployeeCount() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> empPayrollData = employeePayrollService.readEmployeePayrollForDateRange(IOService.DB_IO,startDate,endDate);
        Assertions.assertEquals(2, empPayrollData.size());
    }

    @Test
    public void givenPayrollData_WhenAvgSalaryRetrievedGender_ShouldReturn_ProperValue() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        Map<String, Double> avgSalaryByGender = employeePayrollService.readAvgSalaryByGender(IOService.DB_IO);
        Assertions.assertTrue(avgSalaryByGender.get("M").equals(650000.0) && avgSalaryByGender.get("F").equals(3000000.0));
    }

    @Test
    public void givenPayrollData_WhenSumSalaryRetrievedGender_ShouldReturn_ProperValue() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        Map<String, Double> sumSalaryByGender = employeePayrollService.readSumSalaryByGender(IOService.DB_IO);
        Assertions.assertTrue(sumSalaryByGender.get("M").equals(1300000.0) && sumSalaryByGender.get("F").equals(3000000.0));
    }

    @Test
    public void givenPayrollData_WhenMinSalaryRetrievedGender_ShouldReturn_ProperValue() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        Map<String, Double> minSalaryByGender = employeePayrollService.readMinSalaryByGender(IOService.DB_IO);
        Assertions.assertTrue(minSalaryByGender.get("M").equals(300000.0) && minSalaryByGender.get("F").equals(3000000.0));
    }

    @Test
    public void givenPayrollData_WhenMaxSalaryRetrievedGender_ShouldReturn_ProperValue() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        Map<String, Double> maxSalaryByGender = employeePayrollService.readMaxSalaryByGender(IOService.DB_IO);
        Assertions.assertTrue(maxSalaryByGender.get("M").equals(1000000.0) && maxSalaryByGender.get("F").equals(3000000.0));
    }

    @Test
    public void givenPayrollData_WhenCountSalaryRetrievedGender_ShouldReturn_ProperValue() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayroll(IOService.DB_IO);
        Map<String, Double> countSalaryByGender = employeePayrollService.readCountSalaryByGender(IOService.DB_IO);
        Assertions.assertTrue(countSalaryByGender.get("M").equals(2.0) && countSalaryByGender.get("F").equals(1.0));
    }
}
