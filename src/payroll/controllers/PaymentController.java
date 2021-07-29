package controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Scanner;

import models.Company;
import models.Employee;
import models.services.payment.PayCheck;
import models.services.payment.PaymentList;
import models.services.payment.PaymentSchedule;
import utils.EmployeeUtils;
import utils.GeneralUtils;

public class PaymentController {
    public static void LaunchPayroll(Scanner input, Company company){

        if(EmployeeUtils.warningEmptyEmployeesList(company.getEmployees())) return;

        int count, week = -1;

        String stringDate;
        PayCheck payCheck;
        PaymentList paymentList = null;
        ArrayList<PayCheck> payCheckList = new ArrayList<PayCheck>();

        System.out.print("Informe the first date of the month(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> firstDate = GeneralUtils.convertDateToArray(stringDate);

        System.out.print("Informe the last date of the month(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> lastDate = GeneralUtils.convertDateToArray(stringDate);

        LocalDate first = LocalDate.of(firstDate.get(0), firstDate.get(1), firstDate.get(2));

        LocalDate last = LocalDate.of(lastDate.get(0), lastDate.get(1), lastDate.get(2));

        long size = ChronoUnit.DAYS.between(first, last.plusDays(1));

        for(count = 0; count < size; count++){
            LocalDate current = first.plusDays(count);

            if(current.getDayOfWeek() == first.getDayOfWeek()){
                week++;
            }

            for(Employee employee : company.getEmployees()){
                if(verifyPayDate(employee, week, current)){
                    payCheck = employee.makePayment(current);
    
                    System.out.println(payCheck.toString());
                    payCheckList.add(payCheck);
                }
            }   
        }

        paymentList = new PaymentList(payCheckList, last);

        ArrayList<PaymentList> paymentLists = company.getPaymentLists();

        paymentLists.add(paymentList);

        company.setPaymentLists(paymentLists);

        System.out.println(company.getPaymentLists());
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

    public static boolean verifyPayDate(Employee employee, int week, LocalDate current){
        boolean alreadyPay = false;
        boolean dateInSchedule = false;
        PaymentSchedule empSchedule = employee.getPaymentData().getPaymentSchedule();

        switch (empSchedule.getSchedule()) {
            case "Monthly":
                if (empSchedule.getMonthDay() == null) {
                    dateInSchedule = current.isEqual(GeneralUtils.getLastJobDay(current.with(TemporalAdjusters.lastDayOfMonth())));
                } else {
                    dateInSchedule = (empSchedule.getMonthDay() == current.getDayOfMonth());
                }
                break;
            case "Weekly":
                dateInSchedule = (empSchedule.getWeekDay() == current.getDayOfWeek());
                break;
            case "Every two weeks":
                dateInSchedule = (empSchedule.getWeekDay() == current.getDayOfWeek() && week%2==0);
                break;
        }

        for(PayCheck pc : employee.getPaymentData().getPayChecks()){
            if (pc.getDate() == current) {
                alreadyPay = true;
                break;
            }
        }

        return (dateInSchedule && (!alreadyPay));
    }

}
