package ex0;

/**
 * This is the interface of a Simple Elevator in time - which has a range of floors it can visit, and has a goto and stop functionality.
 * The Elevator uses the "System" (simulated) time. e.g., for computing its current position.
 * This interface is part of Ex0 of OOP course (CS.Ariel University)
 */
public interface Elevator {
    public static final int UP = 1, LEVEL = 0, DOWN = -1, ERROR = -2;
    /** Returns the minimal floor number to which this Elevator can reach(often a negative value).
     * This is the same value as the lowest floor in the building - this elevator belongs to. */
    public int getMinFloor();
    /** Returns the maximal floor number to which this Elevator can reach.
     * This is the same value as the highest floor in the building - this elevator belongs to.  */
    public int getMaxFloor();
    /** Returns the time (in seconds it takes the Elevator to open its doors. */
    public double getTimeForOpen();
    /** Returns the time (in seconds it takes the Elevator to close its doors */
    public double getTimeForClose();
    /** Returns the Elavator's current state: {UP, DOWN, LEVEL, ERROR} the state "LEVEL" mark that the elevator has reached the floor -
     * and is now ready to load / unload and get new orders. */
    public int getState(); // UP, DOWN, LEVEL, ERROR
    /** Returns the Elevator's current position (floor) as an Integer. */
    public int getPos();
    /** Moves the elevator form the current position to the destination floor. */
    public boolean goTo(int floor);
    /** Allows the elevator to stop in an intermidiate floor between src and dest, assumeing the elevator has not yer pass the desired floor in which it needs to stop. */
    public boolean stop(int floor);
    /** Returns the speed (in floor per second), e.g. if the Elevator speed is 0.4 - it takes it 2.5 seconds to pass a single floor. */
    public double getSpeed();
    /** Return the time in seconds that it takes the elevator to start moving in full speed (assumed to be a fixed value). */
    public double getStartTime();
    /** Return the time in seconds that it takes the elevator to stop moving in full speed (assumed to be a fixed value).*/
    public double getStopTime();
    /**
     * @return the id of this elevator (simple index as in the building).
     * Note: this index should be the same as in the elevator allocation algorithm.
     */
    public int getID();
}