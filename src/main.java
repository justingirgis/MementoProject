
import java.io.IOException;
import java.util.Scanner;

/**
 * Authors: Justin Girgis and Serenity Brown
 * Purpose: Asking user for input file name and testing program
 * Date: 03/27/2020
 */

public class main {

    public static void main(String[] args) throws IOException {
	// write your code here
       // PersonCaretaker p1 = new PersonCaretaker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of your file? ");
        String fileName = scanner.nextLine();
        System.out.println("Please type in name of binary file" + new PersonCaretaker(fileName));


        String input = "n";
        Person person = new Person();
        System.out.println("prompting you for persons:");

        while(!input.equalsIgnoreCase("Y")) {
            System.out.print("Enter the persons last name: ");
            input = scanner.nextLine();
            System.out.println("Enter the persons first name: ");
            input = scanner.nextLine();

        }


    }
}
