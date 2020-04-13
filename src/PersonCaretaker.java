
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
       this.objectOutputStream.close();
       this.fileOutput.close();
       PersonMemento temp = null;

       try {
        this.objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        //try and catch to know when cursor is at the end of file
        PersonMemento find = null;
        temp = (PersonMemento) objectInputStream.readObject();
        //Person person = temp.getSavedPerson().restore(temp);
        int minWeight = temp.getSavedPerson().getWeightPounds();
        System.out.println(minWeight);

                do {
                    System.out.println("running");
                    find = (PersonMemento) objectInputStream.readObject();
                    //temp = find.getSavedPerson();
                    if(find.getSavedPerson().getWeightPounds() < minWeight) {
                        minWeight = find.getSavedPerson().getWeightPounds();
                        temp = find;
                    }
                } while(find != null);

                objectInputStream.close();
            }
            catch (IOException e){
                //e.getMessage();
            }
            catch (ClassNotFoundException f) {
                //f.getMessage();
            }


        //this.objectInputStream = new ObjectInputStream(new FileInputStream(fileName));

        return temp;
    }

    /**
     * See if the weight that the user puts in matches other
     * weights in the memento
     * @param weight that user puts in
     * @return first instance of PersonMemento that matches the weight that user put in
     * @throws IOException
     */

    public PersonMemento getMemento(int weight) {

        PersonMemento firstPerson = null;


        try {
            objectOutputStream.close();
            fileOutput.close();
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            PersonMemento temp = null;
            do {
                temp = (PersonMemento) objectInputStream.readObject();
                Person person = temp.getSavedPerson().restore(temp);
                if(person.getWeightPounds() == weight){
                    //found = true;
                    firstPerson = temp;
                    System.out.println("check");
                }

            } while (temp != null);
        }catch (IOException io){
            io.getMessage();
        }catch (ClassNotFoundException classNotFound){
            classNotFound.getException();
        }
        return firstPerson;
    }



}
