package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        employeePayRollService.readEmployeePayroll(IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2010, 10, 30);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> empPayRollData = employeePayRollService.readEmployeePayrollForDateRange(IOService.DB_IO,startDate,endDate);
        Assertions.assertEquals(3, empPayRollData.size());
    }
}
