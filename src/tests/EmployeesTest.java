package tests;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import payroll.models.Comissioned;
import payroll.models.Employee;
import payroll.models.Hourly;
import payroll.models.Salaried;

public class EmployeesTest extends Throwable {

    @Test
    public void shouldCreateHourlyEmployee(){
        UUID id = UUID.randomUUID();

        String name = "Rodrigo Santos da Silva";
        String address = "Rua Professor Ranildo Cavalcante, 118";
        Double salary = 200.0;

        Employee employee = new Hourly(id, name, address, salary);
        Assert.assertTrue(employee instanceof Hourly);
    }

    @Test
    public void shouldCreateSalariedEmployee(){
        UUID id = UUID.randomUUID();

        String name = "Pedro Henrique";
        String address = "Travessa Manoel Menezes, 118";
        Double salary = 1500.0;

        Employee employee = new Salaried(id, name, address, salary);
        Assert.assertTrue(employee instanceof Salaried);
    }

    @Test
    public void shouldCreateComissionedEmployee(){
        UUID id = UUID.randomUUID();

        String name = "Michael Miller";
        String address = "Rua Manoel Inácio, 118";

        Double salary = 200.0;
        Double comission = 40.0;

        Employee employee = new Comissioned(id, name, address, salary, comission);
        Assert.assertTrue(employee instanceof Comissioned);
    }
}
