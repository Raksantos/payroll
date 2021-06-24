package payroll;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int option = 13;
        Scanner input = new Scanner(System.in);

        while(option != 0){
            System.out.println("Welcome to the Payroll system!!\n\nSelect your option:\n");
            System.out.println("[1] Register an employee.");
            
            System.out.println("\nYour option: ");
            option = input.nextInt();
            //input.nextLine();

            switch(option){
                case 1:
                    System.out.println("Oi");
                    break;
                default:
                    System.out.println("\n\nInvalid option!!\n\n");
                    break;
            }
        }
        System.out.println("Thank you! See you soon.");

    }
}
