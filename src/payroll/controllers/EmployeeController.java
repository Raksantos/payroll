package controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import models.services.*;

import utils.EmployeeUtils;
import utils.ValueHolder;

public class EmployeeController {

    public static void listEmployees(ArrayList<Employee> employees){
        for(Employee employee : employees){
            System.out.println(employee.toString());
        }
    }

    public static void removeEmployee(Scanner input, ArrayList<Employee> employees){

        if(EmployeeUtils.warningEmptyEmployeesList(employees)) return;

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
            System.out.println("\nCouldn't find the informed employee\n");
        }else{
            System.out.println("\nEmployee removed with success\n");
        }
    }

    public static void registerNewEmployee(Scanner input, ArrayList<Employee> employees){
        Employee employee = null;

        employee = EmployeeUtils.readEmployeeBasicData(input).getEmployee();

        if(employee == null){
            System.out.println("\nEmployee not registered!\n");
            return;
        }

        employees.add(employee);

        System.out.println(employee);

        System.out.println("\nEmployee registered with success!\n");
    }

    public static void launchTimeCard(Scanner input, ArrayList<Employee> employees){

        if(EmployeeUtils.warningEmptyEmployeesList(employees)) return;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee employee = null;

        employee = EmployeeUtils.findEmployee(employees, id);

        if(!EmployeeUtils.wasEmployeeFound(employee)) return;

        Hourly hourlyEmployee = (Hourly) employee;

        System.out.print("Inform the entry hour (HH): ");
        int entryHour = input.nextInt();

        System.out.print("Inform the out hour (HH): ");
        int outHour = input.nextInt();
        input.nextLine();

        System.out.print("Inform the date (YYYY-MM-DD): ");
        String date = input.nextLine();

        ArrayList<Integer> dateData = EmployeeUtils.convertDateToArray(date);

        LocalTime employeeEntry = LocalTime.of(entryHour, 00, 00);
        LocalTime employeeOut = LocalTime.of(outHour, 00, 00);

        LocalDate employeeDate = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));

        TimeCard timeCard = new TimeCard(employeeDate, employeeEntry, employeeOut);

        ArrayList<TimeCard> newTimeCards = hourlyEmployee.getTimeCards();

        newTimeCards.add(timeCard);

        hourlyEmployee.setTimeCards(newTimeCards);   
    }

    public static void launchSaleResult(Scanner input, ArrayList<Employee> employees){
        if(EmployeeUtils.warningEmptyEmployeesList(employees)) return;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee employee = null;

        employee = EmployeeUtils.findEmployee(employees, id);

        if(!EmployeeUtils.wasEmployeeFound(employee)) return;

        Comissioned comissionedEmployee = (Comissioned) employee;

        System.out.print("Inform the value of the sale: ");
        Double value = input.nextDouble();
        input.nextLine();

        System.out.print("Inform the date (YYYY-MM-DD): ");
        String date = input.nextLine();

        ArrayList<Integer> dateData = EmployeeUtils.convertDateToArray(date);

        LocalDate saleDate = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));

        SaleResult newSale = new SaleResult(value, saleDate);

        ArrayList<SaleResult> sales = comissionedEmployee.getSales();

        sales.add(newSale);

        comissionedEmployee.setSales(sales);
    }
    
    public static void launchServiceTax(Scanner input, ArrayList<Employee> employees){
        if(EmployeeUtils.warningEmptyEmployeesList(employees)) return;

        System.out.print("Inform the id of the employee: ");
        String id = input.nextLine();

        Employee updateEmployee = null;

        updateEmployee = EmployeeUtils.findEmployee(employees, id);

        if(!EmployeeUtils.wasEmployeeFound(updateEmployee)) return;

        if(!updateEmployee.getEmployeeSyndicate().getIsAffiliated()){
            System.out.println("\nThe employee is not filiated to the syndicate.\n");
            return;
        }
        
        System.out.print("Inform the value of the service tax: ");
        Double value = input.nextDouble();
        input.nextLine();

        System.out.print("Inform the date (YYYY-MM-DD): ");
        String date = input.nextLine();

        ArrayList<Integer> dateData = EmployeeUtils.convertDateToArray(date);

        LocalDate serviceTaxDate = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));

        ServiceTax serviceTax = new ServiceTax(value, serviceTaxDate);

        ArrayList<ServiceTax> serviceTaxList = updateEmployee.getEmployeeSyndicate().getServiceTax();
        
        serviceTaxList.add(serviceTax);


        System.out.println("\nService tax registered with success!\n");
    }

    public static void updateEmployee(Scanner input, ArrayList<Employee> employees){

        if(EmployeeUtils.warningEmptyEmployeesList(employees)) return;

        Employee employee = EmployeeUtils.gettingEmployee(input, employees);
        
        ValueHolder valueHolder = EmployeeUtils.readEmployeeBasicData(input);

        Employee auxiliarEmployee = valueHolder.getEmployee();
        
        if(auxiliarEmployee == null){
            System.out.println("\nInvalid data informed, employee not updated!\n");
            return;
        }

        employee.setName(auxiliarEmployee.getName());
        employee.setAddress(auxiliarEmployee.getAddress());
        employee.setSalary(auxiliarEmployee.getSalary());
        employee.setEmployeeSyndicate(auxiliarEmployee.getEmployeeSyndicate());
        employee.setPaymentData(auxiliarEmployee.getPaymentData());

        if(auxiliarEmployee instanceof Comissioned){
            Comissioned updatedEmployee = new Comissioned(employee.getName(), 
                                                        employee.getAddress(), employee.getSalary(),
                                                        valueHolder.getComission(), employee.getPaymentData());
            
            updatedEmployee.setId(employee.getId());
            updatedEmployee.setEmployeeSyndicate(auxiliarEmployee.getEmployeeSyndicate());
            
            EmployeeUtils.removeSpecificEmployee(employee.getId().toString(), employees);

            employees.add(updatedEmployee);

            System.out.println(updatedEmployee);
        }else{
            System.out.println(employee);
        }

        System.out.println("\nEmployee updated with success!\n");        
    }

    public static void editEmployeeSchedule(Scanner input, ArrayList<Employee> employees){

        listEmployees(employees);

        Employee employee =  EmployeeUtils.findEmployee(input, employees);
    }
}

