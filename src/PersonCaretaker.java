
import java.io.*;
import java.util.Scanner;

/**
 * Author: Justin Girgis and Serenity Brown
 * Purpose: Caretaker, takes care of file input and output
 * adding and getting mementos and writing them into the file
 * Date: 3/27/2020
 */

public class PersonCaretaker implements Serializable {

    private String fileName;
    /** Our member variables*/
    FileOutputStream fileOutput;
    FileInputStream file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public PersonCaretaker(String fileName) throws IOException {

        this.fileName = fileName + ".bin";
        fileOutput = new FileOutputStream(this.fileName);
        this.file = new FileInputStream(this.fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutput);
        this.objectInputStream = new ObjectInputStream(file);

    }

    /**
     * Append the new memento to the end of binary file
     */

    public void addPersonMemento(PersonMemento personMemento) throws IOException {
        objectOutputStream.writeObject(personMemento);
    }

    /**
     *Get instance of person with lowest weight in memento
     * @return an instance of the person with the lowest weight
     * @throws IOException
     */


   public PersonMemento getMemento() throws IOException, ClassNotFoundException {

       ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        //try and catch to know when cursor is at the end of file
        PersonMemento find = null;
        PersonMemento temp = (PersonMemento) objectInputStream.readObject();
        Person person = temp.getSavedPerson().restore(temp);
        int minWeight = person.getWeightPounds();


            try {
                do {
                    find = (PersonMemento) objectInputStream.readObject();
                    person = find.getSavedPerson().restore(temp);
                    //temp = find.getSavedPerson();
                    System.out.println("check");
                    if(person.getWeightPounds() < minWeight) {
                        minWeight = find.getSavedPerson().getWeightPounds();
                        temp = find;
                    }
                } while(find != null);

                objectInputStream.close();
            }
            catch (IOException e){
                e.getMessage();
            }

        objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        //objectInputStream.close();
        return temp;
    }

    /**
     * See if the weight that the user puts in matches other
     * weights in the memento
     * @param weight that user puts in
     * @return first instance of PersonMemento that matches the weight that user put in
     * @throws IOException
     */

    public PersonMemento getMemento(int weight) throws IOException, ClassNotFoundException {


        PersonMemento firstPerson = (PersonMemento) objectInputStream.readObject();

        try {
            while (objectInputStream.readObject() != null) {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName + ".bin"));
                firstPerson = (PersonMemento) input.readObject();
                if(firstPerson.getSavedPerson().getWeightPounds() == weight){
                    return firstPerson;
                }else{
                    return null;
                }
            }
        }catch (IOException io){
            io.getMessage();
        }catch (ClassNotFoundException classNotFound){
            classNotFound.getException();
        }
        return firstPerson;

    }



}
