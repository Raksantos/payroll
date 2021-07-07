package payroll.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import payroll.models.Employee;

public class EmployeeUtils {
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
}
