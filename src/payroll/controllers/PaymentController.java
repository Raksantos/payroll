package controllers;

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

        System.out.print("Inform the first date of the month(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> firstDate = GeneralUtils.convertDateToArray(stringDate);

        System.out.print("Inform the last date of the month(YYYY-MM-DD): ");
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
                    payCheckList.add(payCheck);
                }
            }   
        }


        paymentList = new PaymentList(payCheckList, last);
        ArrayList<PaymentList> paymentLists = company.getPaymentLists();

        paymentLists.add(paymentList);

        company.setPaymentLists(paymentLists);

        System.out.println(payCheckList);
    }

    public static boolean verifyPayDate(Employee employee, int week, LocalDate current){
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

        return dateInSchedule;
    }

}
