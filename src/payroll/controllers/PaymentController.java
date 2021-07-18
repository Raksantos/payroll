package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.Company;
import models.Employee;
import models.services.payment.PayCheck;
import models.services.payment.PaymentList;
import utils.EmployeeUtils;

public class PaymentController {
    public static void LaunchPayroll(Scanner input, Company company){

        if(EmployeeUtils.warningEmptyEmployeesList(company.getEmployees())) return;

        String stringDate;
        PayCheck payCheck;
        PaymentList paymentList;
        Double taxes;
        ArrayList<PayCheck> payCheckList = new ArrayList<PayCheck>();

        System.out.print("Informe the date(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> dateInformation = EmployeeUtils.convertDateToArray(stringDate);

        LocalDate date = LocalDate.of(dateInformation.get(0), dateInformation.get(1), dateInformation.get(2));

        for(Employee employee : company.getEmployees()){
            taxes = employee.calculateServiceTaxes();
            payCheck = new PayCheck(employee, employee.getSalary(), taxes, false, date);
            System.out.println(payCheck.toString());
            payCheckList.add(payCheck);
        }

        paymentList = new PaymentList(payCheckList, date);

        ArrayList<PaymentList> paymentLists = company.getPaymentLists();

        paymentLists.add(paymentList);
    }

}
