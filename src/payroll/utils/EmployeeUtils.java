package utils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import models.services.payment.PaymentData;
import models.services.payment.PaymentSchedule;


public class EmployeeUtils {

    public static void listHourly(ArrayList<Employee> employees){

        for(Employee employee : employees){
            if(employee instanceof Hourly){
                System.out.println(employee.toString());
            }
        }
    }

    public static void listComissioned(ArrayList<Employee> employees){

        for(Employee employee : employees){
            if(employee instanceof Comissioned){
                System.out.println(employee.toString());
            }
        }
    }

    public static boolean wasEmployeeFound(Employee employee){
        if(employee == null){
            System.out.println("Employee not found");
            return false;
        }

        return true;
    }

    public static boolean warningEmptyEmployeesList(ArrayList<Employee> employees){
        if(employees.size() == 0){
            System.out.println("\n\nThere aren't employees registered\n\n");
            return true;
        } else{
            return false;
        }
    }  

    public static void removeSpecificEmployee(String id, ArrayList<Employee> employees){
        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                employees.remove(employee);
                break;
            }
        }
    }

    public static Employee findEmployee(Scanner input, ArrayList<Employee> employees){

        if(warningEmptyEmployeesList(employees)) return null;
        
        System.out.print("Inform the employee id: ");
        String id = input.nextLine();

        Employee wantedEmployee = null;

        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                wantedEmployee = employee;
                break;
            }
        }

        if(!wasEmployeeFound(wantedEmployee)) return null;

        return wantedEmployee;
    }

    public static ValueHolder readEmployeeBasicData(Scanner input){
        String name, address, bank = "", agency = "", account = "", paymentMethod = "";

        PaymentSchedule schedule = new PaymentSchedule();
        
        Double salary, comission = 0.0, tax = 0.0;
        
        int option, syndicateOption, paymentMethodOption;
        
        PaymentData paymentData = null;
        Syndicate syndicate = null;
        Employee employee = null;

        System.out.print("Name of the employee: ");
        name = input.nextLine();

        System.out.print("Address: ");
        address = input.nextLine();

        System.out.print("Bank: ");
        bank = input.nextLine();

        System.out.print("Agency: ");
        agency = input.nextLine();

        System.out.print("Account: ");
        account = input.nextLine();

        System.out.println("Payment method: ");
        System.out.println("1 - Check in the post office\n2 - Bank deposit\n3 - In cash");
        System.out.print(":");

        paymentMethodOption = input.nextInt();

        switch(paymentMethodOption){
            case 1:
                paymentMethod = "Check in the post office";
                break;
            case 2:
                paymentMethod = "Bank deposit";
                break;
            case 3:
                paymentMethod = "In cash";
                break;
            default:
                System.out.println("\n\n invalid input, payment method not registered! \n\n");
                break;
        }

        schedule = createPaymentSchedule(input);

        paymentData = new PaymentData(bank, agency, account, paymentMethod, schedule);

        System.out.println("Which type is the employee?");
        System.out.println("1 - Hourly\n2 - Comissioned\n3 - Salaried");
        System.out.print(":");

        option = input.nextInt();

        switch(option){
            case 1:
                System.out.print("Inform the hourly salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Hourly(name, address, salary, paymentData);
                break;
            case 2:
                System.out.print("Inform the comissioned salary: ");
                salary = input.nextDouble();
                input.nextLine();

                System.out.print("Inform the comission: ");
                comission = input.nextDouble();
                input.nextLine();

                employee = new Comissioned(name, address, salary, comission, paymentData);
                break;
            case 3:
                System.out.print("Informe the salaried salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Salaried(name, address, salary, paymentData);
                break;
            default:
                System.out.println("\nInvalid option!");
                return new ValueHolder(employee, comission);
        }

        System.out.println("Is affiliated to the syndicate? ");
        System.out.println("1 - Yes\n2 - No\n");
        System.out.print(": ");
        syndicateOption = input.nextInt();

        switch(syndicateOption){
            case 1:
                System.out.print("Inform the tax of the syndicate: ");
                tax = input.nextDouble();
                input.nextLine();

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

        return new ValueHolder(employee, comission);
    }

    public static PaymentSchedule createPaymentSchedule(Scanner input){
        System.out.println("Inform a type of schedule: ");
        System.out.println("1 - Monthly\n2 - Weekly\n3 - Every two weeks");
        System.out.print(":");
        int option = input.nextInt(), week;

        DayOfWeek weekDay; 

        switch(option){
            case 1:
                System.out.println("Choose the day of the month to put in the schedule (1 - 28): ");
                int day = input.nextInt();

                if(day < 0 || day > 28){
                    return new PaymentSchedule(null, null, "Monthly");
                }
                else{
                    return new PaymentSchedule(day, null, "Monthly");
                }
            case 2:
                System.out.println("Choose the day of the week: ");
                System.out.println("[1] - Monday\n[2] - Thuesday\n[3] - Wednesday\n[4] - Thursday\n[5] - Friday");
                week = input.nextInt();

                weekDay = DayOfWeek.of(week);

                if(week < 0 || week > 5){
                    return new PaymentSchedule(null, DayOfWeek.FRIDAY, "Weekly");
                }else{
                    return new PaymentSchedule(null, weekDay, "Weekly");
                }
            case 3:
                System.out.println("Choose the day of the week: ");
                System.out.println("1 - Monday\n2 - Thuesday\n3 - Wednesday\n4 - Thursday\n5 - Friday");
                week = input.nextInt();

                weekDay = DayOfWeek.of(week);
                
                if(week < 0 || week > 5){
                    return new PaymentSchedule(null, DayOfWeek.FRIDAY, "Every two weeks");
                }else{
                    return new PaymentSchedule(null, weekDay, "Every two weeks");
                }
            default:
                System.out.println("\n\nInvalid option, created an month schedule by default!!\n\n");
                return new PaymentSchedule(null, null, "Monthly");
        }

    }
}
