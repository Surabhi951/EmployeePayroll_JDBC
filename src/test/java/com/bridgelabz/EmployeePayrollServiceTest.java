package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
