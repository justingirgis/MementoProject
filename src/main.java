
import java.io.IOException;
import java.util.Scanner;

/**
 * Authors: Justin Girgis and Serenity Brown
 * Purpose: Asking user for input file name and testing program
 * Date: 03/27/2020
 */

public class main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of your file? ");
        String fileName = scanner.nextLine();
        System.out.println("Please type in name of binary file");
        PersonCaretaker caretaker = new PersonCaretaker(fileName);

        int input = 2;
        System.out.println("prompting you for persons:");
        System.out.print("Enter the persons last name: ");
        String fname = scanner.nextLine();
        System.out.print("Enter the persons first name: ");
        String lname = scanner.nextLine();

        Person person = new Person(lname, fname);

        while(input != 1) {

            System.out.println("0:\tBLACK\n1:\tBLONDE\n2:\tRED\n3:\tAUBURN\n4:\tSALT_AND_PEPPER\n5:\tGREY" +
                    "\n6:\tWHITE\n7:\tBALD");


            System.out.print("Please enter the # corresponding to the hair color: ");
            person.setHairColor(Person.HairColor.getColor(scanner.nextInt()));

            System.out.println("You entered color: " + person.getHairColor());
            System.out.print("Person's height in feet and inches(space in between): ");
            person.setHeight(scanner.nextInt(), scanner.nextInt());

            System.out.print("Their weight in pounds: ");
            person.setWeight(scanner.nextInt());

            System.out.println(person.toString());
            //caretaker.addPersonMemento(new PersonMemento(person));
            caretaker.addPersonMemento(person.save());

            System.out.println("Are we done here? 1 for yes 2 for no ");
            input = scanner.nextInt();
            scanner.nextLine();
        }

            //PART 2 OF OUTPUT
            System.out.println("Skinniest version");


            System.out.println(caretaker.getMemento().getSavedPerson());

            System.out.print("What weight do you want to search?");
            int findWeight = scanner.nextInt();

            if(caretaker.getMemento(findWeight) == null) {
                System.out.println("Weight was not found!");
            } else
                System.out.println("Sought after version: " + caretaker.getMemento(findWeight).getSavedPerson());




    }
}
