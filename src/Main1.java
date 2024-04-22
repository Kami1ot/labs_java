import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "Petya9644");
        Menu menu = new Menu(helper);
        while (true) {
            menu.displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            menu.executeMenuOption(choice);
        }
    }
}