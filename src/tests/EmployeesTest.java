package tests;

import org.junit.Assert;
import org.junit.Test;

import payroll.models.Comissioned;
import payroll.models.Employee;
import payroll.models.Hourly;
import payroll.models.Salaried;
import payroll.models.Syndicate;

public class EmployeesTest extends Throwable {

    @Test
    public void shouldCreateHourlyEmployee(){
        String name = "Rodrigo Santos da Silva";
        String address = "Rua Professor Ranildo Cavalcante, 118";
        Double salary = 200.0;

        Employee employee = new Hourly(name, address, salary);

        Syndicate syndicate = new Syndicate(employee.getId(), true, 20.0);

        employee.setEmployeeSyndicate(syndicate);

        Assert.assertTrue(employee instanceof Hourly);
        Assert.assertTrue(syndicate instanceof Syndicate);
    }

    @Test
    public void shouldCreateSalariedEmployee(){
        String name = "Pedro Henrique";
        String address = "Travessa Manoel Menezes, 118";
        Double salary = 1500.0;

        Employee employee = new Salaried(name, address, salary);
        Assert.assertTrue(employee instanceof Salaried);
    }

    @Test
    public void shouldCreateComissionedEmployee(){
        String name = "Michael Miller";
        String address = "Rua Manoel Inácio, 118";

        Double salary = 200.0;
        Double comission = 40.0;

        Employee employee = new Comissioned(name, address, salary, comission);
        Assert.assertTrue(employee instanceof Comissioned);
    }
}
