package payroll.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import payroll.models.Employee;
import payroll.models.Hourly;
import payroll.models.Comissioned;
import payroll.models.Salaried;
import payroll.models.Syndicate;

public class EmployeeController {
    public static Employee registerNewEmployee(Scanner input){
        Employee employee = new Employee();
        Syndicate syndicate = new Syndicate();

        String name;
        String address;
        Double salary;
        int option, syndicateOption;
        double tax = 0;

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

                employee = new Hourly(name, address, salary);
                break;
            case 2:
                System.out.println("Inform the comissioned salary: ");
                salary = input.nextDouble();
                input.nextLine();

                System.out.println("Inform the comission: ");
                Double comission = input.nextDouble();
                input.nextLine();

                employee = new Comissioned(name, address, salary, comission);
                break;
            case 3:
                System.out.println("Informe the salaried salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Salaried(name, address, salary);
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }

        System.out.println("Is affiliated to the syndicate? ");
        System.out.println("[1] - Yes\n[2] - No\n");
        System.out.print(": ");
        syndicateOption = input.nextInt();

        switch(syndicateOption){
            case 1:
                System.out.print("Inform the tax of the syndicate: ");
                tax = input.nextDouble();

                syndicate = new Syndicate(employee.getId(), true, tax);
                employee.setEmployeeSyndicate(syndicate);
                break;
            case 2:
                syndicate = new Syndicate(employee.getId(), false, tax);
                break;
            default:
                System.out.println("Invalid option! Syndicate filiation not registererd to this employee");
                syndicate = new Syndicate(employee.getId(), false, tax);
                break;
        }
        
        employee.setEmployeeSyndicate(syndicate);

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
