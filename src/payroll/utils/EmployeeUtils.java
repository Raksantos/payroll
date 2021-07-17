package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.*;
import models.services.payment.PaymentData;


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

    public static ArrayList<Integer> convertDateToArray(String date){
        List<String> dateData = Stream.of(date.split("-"))
        .map(elem -> new String(elem))
        .collect(Collectors.toList());

        int year = Integer.parseInt(dateData.get(0));
        int month = Integer.parseInt(dateData.get(1));
        int day = Integer.parseInt(dateData.get(2));

        ArrayList<Integer> data = new ArrayList<Integer>();
        
        data.add(year);
        data.add(month);
        data.add(day);

        return data;
    }

    public static boolean warningEmptyEmployeesList(ArrayList<Employee> employees){
        if(employees.size() == 0){
            System.out.println("\n\nThere aren't employees registered\n\n");
            return true;
        } else{
            return false;
        }
    }
    
    public static Employee gettingEmployee(Scanner input, ArrayList<Employee> employees){
        if(warningEmptyEmployeesList(employees)) return null;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee employee = null;

        employee = findEmployee(employees, id);

        if(!wasEmployeeFound(employee)) return null;

        return employee;
    }

    public static void removeSpecificEmployee(String id, ArrayList<Employee> employees){
        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                employees.remove(employee);
                break;
            }
        }
    }

    public static Employee findEmployee(ArrayList<Employee> employees, String id){

        Employee wantedEmployee = null;

        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                wantedEmployee = employee;
                break;
            }
        }

        return wantedEmployee;
    }

    public static ValueHolder readEmplyeeBasicData(Scanner input){
        String name, address, bank, agency, account;
        
        Double salary, comission = 0.0, tax = 0.0;
        
        int option, syndicateOption, paymentMethod, schedule;
        
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
        System.out.println("1 - Check in the post office\n2 - Bank deposit\n3 - in cash");
        System.out.print(":");
        paymentMethod = input.nextInt();
        input.nextLine();

        System.out.println("Schedule: ");
        System.out.println("1 - Monthly\n2 - Weekly\n3 - Bi-weekly");
        System.out.print(":");
        schedule = input.nextInt();

        if(paymentMethod > 3 || paymentMethod < 1){
            System.out.println("\nInvalid payment method");   
            paymentData = new PaymentData(null, null, null, 0, 0); 
        }else{
            paymentData = new PaymentData(bank, agency, account, paymentMethod, schedule);
        }


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
        System.out.println("[1] - Yes\n[2] - No\n");
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
}
