package payroll.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import payroll.models.Employee;
import payroll.models.Hourly;
import payroll.models.Comissioned;
import payroll.models.Salaried;
import payroll.models.Syndicate;
import payroll.models.services.SaleResult;
import payroll.models.services.TimeCard;


public class EmployeeController {
    public static Employee registerNewEmployee(Scanner input){
        Employee employee = null;
        Syndicate syndicate = null;

        String name;
        String address;
        Double salary;
        int option, syndicateOption;
        double tax = 0;

        System.out.print("Name of the employee: ");
        name = input.nextLine();

        System.out.print("Address: ");
        address = input.nextLine();

        System.out.println("Which type is the employee?");
        System.out.println("1 - Hourly\n2 - Comissioned\n3 - Salaried");
        System.out.print(":");

        option = input.nextInt();

        switch(option){
            case 1:
                System.out.print("Inform the hourly salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Hourly(name, address, salary);
                break;
            case 2:
                System.out.print("Inform the comissioned salary: ");
                salary = input.nextDouble();
                input.nextLine();

                System.out.print("Inform the comission: ");
                Double comission = input.nextDouble();
                input.nextLine();

                employee = new Comissioned(name, address, salary, comission);
                break;
            case 3:
                System.out.print("Informe the salaried salary: ");
                salary = input.nextDouble();
                input.nextLine();

                employee = new Salaried(name, address, salary);
                break;
            default:
                System.out.println("Invalid option!");
                return employee;
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

    private static boolean warningEmptyEmployeesList(ArrayList<Employee> employees){
        if(employees.size() == 0){
            System.out.println("\n\nThere aren't employees registered\n\n");
            return true;
        } else{
            return false;
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

    private static ArrayList<Integer> convertDateToArray(String date){
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

    public static void launchTimeCard(Scanner input, ArrayList<Employee> employees){

        if(warningEmptyEmployeesList(employees)) return;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee updateEmployee = null;

        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                updateEmployee = employee;
                break;
            }
        }

        if(updateEmployee == null)
        {
            System.out.println("Employee not found!");
            return;
            //change to an exception in the future
        }

        Hourly hourlyEmployee = (Hourly) updateEmployee;

        System.out.print("Inform the entry hour (HH): ");
        int entryHour = input.nextInt();

        System.out.print("Inform the out hour (HH): ");
        int outHour = input.nextInt();
        input.nextLine();

        System.out.print("Inform the date (YYYY-MM-DD): ");
        String date = input.nextLine();

        ArrayList<Integer> dateData = convertDateToArray(date);

        LocalTime employeeEntry = LocalTime.of(entryHour, 00, 00);
        LocalTime employeeOut = LocalTime.of(outHour, 00, 00);

        LocalDate employeeDate = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));

        TimeCard timeCard = new TimeCard(employeeDate, employeeEntry, employeeOut);

        ArrayList<TimeCard> newTimeCards = hourlyEmployee.getTimeCards();

        newTimeCards.add(timeCard);

        hourlyEmployee.setTimeCards(newTimeCards);   
    }

    public static void launchSaleResult(Scanner input, ArrayList<Employee> employees){
        if(warningEmptyEmployeesList(employees)) return;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee updateEmployee = null;

        for(Employee employee : employees){
            if(employee.getId().toString().equals(id)){
                updateEmployee = employee;
                break;
            }
        }

        Comissioned comissionedEmployee = (Comissioned) updateEmployee;

        System.out.print("Inform the value of the sale: ");
        Double value = input.nextDouble();
        input.nextLine();

        System.out.print("Inform the date (YYYY-MM-DD): ");
        String date = input.nextLine();

        ArrayList<Integer> dateData = convertDateToArray(date);

        LocalDate saleDate = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));

        SaleResult newSale = new SaleResult(value, saleDate);

        ArrayList<SaleResult> sales = comissionedEmployee.getSales();

        sales.add(newSale);

        comissionedEmployee.setSales(sales);
    }
    
    public static void launchServiceTax(Scanner input, ArrayList<Employee> employees){
        if(warningEmptyEmployeesList(employees)) return;
    }
}
