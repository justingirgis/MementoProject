
public class Person {

    /**
     * Demonstration class that we will preserve into an instance of Memento.
     * @author David Brown
     *Performs the duties of the Originator (Person knows how to save itself)
     */
    /** Height of the person in inches.  Note that we do not STORE the height in feet, that's calculated.
     */
    private int heightInches;
    /** The weight of the person in pounds.  Ounces not significant. */
    private int weightPounds;
    /** Last Name of the person. */
    private String lName;
    /** First Name of the person.*/
    private String fName;
    /** The person's hair color */
    private HairColor hairColor;
    /** Ratio of inches to feet. */
    public static final int inchesPerFoot = 12;
    /** Maximum believable height in inches.*/
    private static final int maxHeight = 7 * inchesPerFoot;
    /** Minimum believable height in inches. */
    private static final int minHeight = 1 * inchesPerFoot;
    /** Maximum believable weight in pounds.*/
    public static final int maxWeight = 400;
    /** Minimum believable weight in pounds.*/
    private static final int minWeight = 20;
    /** String of person*/
    private Person person;


    public enum HairColor {
        BLACK, BLONDE, RED, AUBURN, SALT_AND_PEPPER, GREY, WHITE, BALD; //each is an instance of Color
        public static HairColor getColor (int colorNumber) {
            int currentColor = 0;
            boolean found = false;
            HairColor results = HairColor.BLACK;
            for (HairColor color:values() ) {
                if (currentColor == colorNumber) {
                    results = color;
                    found = true;
                    break;
                }
                currentColor++;
            }
            if (found) {
                return results;
            } else {
                throw new IllegalArgumentException ("Color number outside of range!");
            }
        }
    }
    /**
     * Build a new person.
     * @param lName				Last name.
     * @param fName 			First name.
     * @param color				Color of the person's hair.
     * @param heightInFeet		Height of the person in feet.
     * @param heightInInches	Inches portion of their height.
     * @param weightInPounds	Weight of the person, in pounds.
     */
    public Person (String lName, String fName, HairColor color, int heightInFeet, int heightInInches, int weightInPounds) {
        setHeight (heightInFeet, heightInInches);					//Update the height
        setWeight (weightInPounds);
        this.lName = lName;
        this.fName = fName;
        this.hairColor = color;
    }

    public Person (String lName, String fName) {
        this.lName = lName;
        this.fName = fName;
        this.hairColor = HairColor.BALD;
    }

    public String getlName () {
        return this.lName;
    }

    public String getfName () {
        return this.fName;
    }

    /**
     * Retrieve the person's hair color.
     * @return	Their current hair color.
     */
    public HairColor getHairColor () {return this.hairColor;}

    /**
     * Set the person's hair color.
     * @param newColor	Their new hair color.
     */
    public void setHairColor (HairColor newColor) {this.hairColor = newColor;}

    public void setHeight (int feet, int inches) {
        int totalInches = netHeight (feet, inches);
        if (totalInches < minHeight || totalInches > maxHeight) {
            throw new IllegalArgumentException("Height out of bounds!");
        } else {
            this.heightInches = totalInches;
        }
    }

    public void setWeight (int pounds) {
        if (pounds < minWeight || pounds > maxWeight) {
            throw new IllegalArgumentException ("Weight out of bounds!");
        } else {
            this.weightPounds = pounds;
        }
    }


    /**
     * Give the height of the person in inches.
     * @return Height in inches.
     */
    public int getHeightInches () {
        return this.heightInches;
    }

    /**
     * Return the feet (floor) of the person.
     * @return
     */
    public int getHeightFeet () {
        return this.getHeightInches()/inchesPerFoot;
    }

    /**
     * Return the number of lbs the person weighs.
     * @return	The number of pounds that the person weighs.
     */
    public int getWeightPounds () {
        return this.weightPounds;
    }

    /**
     * Calculate height from feet & inches.
     * @param feet	The feet in height.
     * @param inches	The inches above the last foot that need to be added to get height in inches.
     * @return	Total height in inches.
     */
    private static int netHeight (int feet, int inches) {
        return feet * inchesPerFoot + inches;
    }

    /**
     * Save a person to the memento
     * @return a new instance of person and save it to the memento
     */
    public PersonMemento save(){
        return new PersonMemento(this.person);
    }

    /**
     * Method that returns the instance of Person that has the same instance
     * That has the same state of instance stored in PersonMemento
     * @param archive
     */
    public void restore(PersonMemento archive){
        person = archive.getSavedPerson();

    }

    /**
     * Return string version of the Person.
     * @return	Display string of the person.
     */
    @Override
    public String toString () {
        return String.format("Name: %s, %s, Hair Color: %s, Height:%d'%d, Weight #: %d",
                this.lName, this.fName, "" + this.hairColor, this.getHeightFeet(),
                this.getHeightInches()%inchesPerFoot,
                this.weightPounds);//So we can print the Person.
    }
}
