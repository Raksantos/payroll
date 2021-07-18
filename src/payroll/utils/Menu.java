package utils;

import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.EmployeeController;
import controllers.PaymentController;
import models.Company;

public class Menu {
    public static void menu(Company company){

        int option = 13;

        Scanner input = new Scanner(System.in);
        
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
                System.out.println("[8] Launch Payroll.");
            
                System.out.print("\nYour option: ");
                option = input.nextInt();
                input.nextLine();
    
                switch(option){
                    case 0:
                        System.out.println("Thank you! See you soon.");
                        break;
                    case 1:
                        EmployeeController.registerNewEmployee(input, company.getEmployees());
                        
                        break;
                    case 2:
                        EmployeeController.listEmployees(company.getEmployees());    
    
                        EmployeeController.removeEmployee(input, company.getEmployees());
                        
                        break;
                    case 3:
                    
                        EmployeeUtils.listHourly(company.getEmployees());
                        
                        EmployeeController.launchTimeCard(input, company.getEmployees());
    
                        break;
                    case 4:
                        EmployeeUtils.listComissioned(company.getEmployees());
    
                        EmployeeController.launchSaleResult(input, company.getEmployees());
                        break;
                    case 5:
                        EmployeeController.listEmployees(company.getEmployees());
    
                        EmployeeController.launchServiceTax(input, company.getEmployees());
                        break;
                    case 6:
                        EmployeeController.listEmployees(company.getEmployees());
    
                        EmployeeController.updateEmployee(input, company.getEmployees());
                        break;
                    case 7:

                        if(!EmployeeUtils.warningEmptyEmployeesList(company.getEmployees())){
                            EmployeeController.listEmployees(company.getEmployees());
                        }
                        
                        break;
                    case 8:
                        PaymentController.LaunchPayroll(input, company);

                        break;
                    default:
                        System.out.println("\n\nInvalid option!!\n\n");
                        break;
                }   
            }
        }catch(InputMismatchException err){
            option = 13;
            System.out.println("\n\nInvalid option!!\n\n");
            menu(company);
        }catch(NumberFormatException err){
            System.out.println("\n\nInvalid entry!!\n\n");
            menu(company);
        }catch(DateTimeException err){
            System.out.println("\n\nInvalid date!!\n\n");
            menu(company);
        }
        input.close();
    }
}
