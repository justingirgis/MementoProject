
/**
 * Author: Justin Girgis and Serenity Brown
 * Purpose: Memento, takes instance of the person or the state of a person
 * then it also has the duty of saving a person
 * Date: 3/27/2020
 */

public class PersonMemento {
    private Person person;


    public PersonMemento(Person person){
        this.person = person;
    }
    public Person getSavedPerson(){
        return this.person;
    }
}
