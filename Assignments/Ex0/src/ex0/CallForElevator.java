package ex0;

/**
 * This interface represents a call for an elevator - with a dedicated destination (aka smart Elevators).
 * The call has few states: {Init, Going2SRC, Going2Dest, Done}, each state has a time stamp (in seconds).
 */
public interface CallForElevator {
    public static final int INIT=0, GOING2SRC=1, GOIND2DEST=2, DONE=3;
    public static final int UP=1, DOWN=-1;
    /** returns this call current state. */
    public int getState();
    /** Returns the time (in second) of the given state, if "not there yet" returns -1
     * @param state - the int representing the state for which the time stamp is requested. */
    public double getTime(int state);
    /** @return the source floor of this elevator call was init at. */
    public int getSrc();
    /** @return the destenation floor to which this elevator call is targeted to. */
    public int getDest();
    /** @return the type of this call {UP,DOWN}; */
    public int getType();
    /** This methods return the index of the Elevator in the building to which this call was assigned to, if not yet Assigned --> return -1 */
    public int allocatedTo() ;
}
