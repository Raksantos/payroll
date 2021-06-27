package payroll;

import java.util.Scanner;

import payroll.controllers.EmployeeController;
import payroll.models.Employee;

import java.util.ArrayList;


public class App {
    public static void main(String[] args) {
        int option = 13;

        Scanner input = new Scanner(System.in);

        ArrayList<Employee> employees = new ArrayList<Employee>();

        System.out.println("Welcome to the Payroll system!!\n\nSelect your option:\n");

        while(option != 0){
            System.out.println("[1] Register an employee.");
            System.out.println("[2] Remove an employee.");
        
            System.out.print("\nYour option: ");
            option = input.nextInt();
            input.nextLine();

            switch(option){
                case 1:
                    Employee newEmployee = EmployeeController.registerNewEmployee(input);
                    if(newEmployee == null){
                        System.out.println("Employee not registered");
                    }else{
                        System.out.println(newEmployee.toString());
                        employees.add(newEmployee);
                        System.out.println("Employeed registered with success\n");
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("\n\nInvalid option!!\n\n");
                    break;
            }
        }
        input.close();
        System.out.println("Thank you! See you soon.");
    }
}
