package controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.Company;
import models.Employee;
import models.services.payment.PayCheck;
import models.services.payment.PaymentList;
import models.services.payment.PaymentSchedule;
import utils.EmployeeUtils;

public class PaymentController {
    public static void LaunchPayroll(Scanner input, Company company){

        if(EmployeeUtils.warningEmptyEmployeesList(company.getEmployees())) return;

        String stringDate;
        PayCheck payCheck;
        PaymentList paymentList;
        ArrayList<PayCheck> payCheckList = new ArrayList<PayCheck>();

        System.out.print("Informe the date(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> dateInformation = EmployeeUtils.convertDateToArray(stringDate);

        LocalDate date = LocalDate.of(dateInformation.get(0), dateInformation.get(1), dateInformation.get(2));

        for(Employee employee : company.getEmployees()){
            payCheck = employee.makePayment(date);

            System.out.println(payCheck.toString());
            payCheckList.add(payCheck);
        }

        paymentList = new PaymentList(payCheckList, date);

        ArrayList<PaymentList> paymentLists = company.getPaymentLists();

        paymentLists.add(paymentList);
    }

    public static PaymentSchedule createPaymentSchedule(Scanner input){
        System.out.println("Inform a type of schedule: ");
        System.out.println("[1] - Monthly\n[2] - Weekly\n[3] - Every to weeks");
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
                System.out.println("[1] - Monday\n[2] - Thuesday\n[3] - Wednesday\n[4] - Thursday\n[5] - Friday");
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
