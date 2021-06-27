package payroll.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import payroll.models.Employee;
import payroll.models.Hourly;
import payroll.models.Comissioned;
import payroll.models.Salaried;
import java.util.UUID;

public class EmployeeController {
    public static Employee registerNewEmployee(Scanner input){
        Employee employee = null;
        
        String name;
        String address;
        Double salary;
        UUID id = UUID.randomUUID();
        int option;

        System.out.println("Name of the employee: ");
        name = input.nextLine();

        System.out.println("Address: ");
        address = input.nextLine();

        System.out.println("Which type is the employee?");
        System.out.println("1 - Hourly\n2 - Comissioned\n3 - Salaried");
        System.out.print(":");

        option = input.nextInt();

        switch(option){
            case 1:
                System.out.println("Inform the hourly salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Hourly(id, name, address, salary);
                break;
            case 2:
                System.out.println("Inform the comissioned salary: ");
                salary = input.nextDouble();
                input.nextLine();

                System.out.println("Inform the comission: ");
                Double comission = input.nextDouble();
                input.nextLine();

                employee = new Comissioned(id, name, address, salary, comission);
                break;
            case 3:
                System.out.println("Informe the salaried salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Salaried(id, name, address, salary);
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }

        return employee;
    }

    public static void listEmployees(ArrayList<Employee> employees){
        for(Employee employee : employees){
            System.out.println(employee.toString());
        }
    }

    public static void removeEmployee(Scanner input, ArrayList<Employee> employees){
        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee removedEmployee = null;

        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                removedEmployee = employee;
                employees.remove(employee);
                break;
            }
        }

        if(removedEmployee == null){
            System.out.println("Couldn't find the informed employee");
        }else{
            System.out.println("Employee removed with success");
        }
    }
}
