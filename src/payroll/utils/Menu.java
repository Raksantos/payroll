package payroll.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import payroll.controllers.EmployeeController;
import payroll.models.Employee;

public class Menu {
    public static void menu(){
        int option = 13;

        Scanner input = new Scanner(System.in);

        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        try{
            while(option != 0){
                System.out.println("[0] Exit.");
                System.out.println("[1] Register an employee.");
                System.out.println("[2] Remove an employee.");
                System.out.println("[3] Launch a timecard.");
                System.out.println("[4] Launch a sale result.");
                System.out.println("[5] Launch a service tax.");
                System.out.println("[6] Update an employee data.");
                System.out.println("[7] List the employees.");
            
                System.out.print("\nYour option: ");
                option = input.nextInt();
                input.nextLine();
    
                switch(option){
                    case 0:
                        System.out.println("Thank you! See you soon.");
                        break;
                    case 1:
                        EmployeeController.registerNewEmployee(input, employees);
                        
                        break;
                    case 2:
                        EmployeeController.listEmployees(employees);    
    
                        EmployeeController.removeEmployee(input, employees);
                        
                        break;
                    case 3:
                    
                        EmployeeController.listHourly(employees);
                        
                        EmployeeController.launchTimeCard(input, employees);
    
                        break;
                    case 4:
                        EmployeeController.listComissioned(employees);
    
                        EmployeeController.launchSaleResult(input, employees);
                        break;
                    case 5:
                        EmployeeController.listEmployees(employees);
    
                        EmployeeController.launchServiceTax(input, employees);
                        break;
                    case 6:
                        EmployeeController.listEmployees(employees);
    
                        EmployeeController.updateEmployee(input, employees);
                        break;
                    case 7:
                        EmployeeController.listEmployees(employees);
                        break;
                    default:
                        System.out.println("\n\nInvalid option!!\n\n");
                        break;
                }   
            }
        }catch(InputMismatchException err){
            option = 13;
            System.out.println("\n\nInvalid option!!\n\n");
            menu();
        }
        input.close();
    }
}
