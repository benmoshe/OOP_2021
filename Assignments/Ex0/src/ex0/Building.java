package ex0;

/** This interface represents a building with several floors and elevators.
 * The interface is part of Ex0 - OOP (Ariel U.) */
public interface Building {
    /** @return the building name - this method is only for "GUI" and testing - it is not needed for Ex0. */
    public String getBuildingName();
    /** @return the minimal floor in this building. */
    public int minFloor();
    /** @return the maximal floor in this building. */
    public int maxFloor();
    /** @return the number of elevators (1 or more) in this building */
    public int numberOfElevetors();
    /** @param i the index of the elevator.
     * @return the i"th elevator (ArrayList like). */
    public Elevator getElevetor(int i);
}
