import utils.Menu;
import models.Company;

public class App {
    public static void main(String[] args) {
        Company company = new Company();
        
        System.out.println("Welcome to the Payroll system!!\n\nSelect your option:\n");

        Menu.menu(company);
    }
}
