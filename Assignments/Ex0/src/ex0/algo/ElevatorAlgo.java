package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;

/**
 * This interface represents the main Algorithms for the "load-balancing" the Calls for elevators in a building (aka OOP Ex0).
 * It assumes online algorithm.
 */
public interface ElevatorAlgo {
    /**
     * @return the Building on which the (online) elevator algorithm works on.
     */
    public Building getBuilding();
    /**
     * @return he algorithm name - can be any String.
     */
    public String algoName();
    /**
     * This method is the main optimal allocation (aka load-balancing) algorithm for allocating the
     * "best" elevator for a call (over all the elevators in the building).
     * @param c the call for elevator (src, dest)
     * @return the index of the elevator to which this call was allocated to.
     */
    public int allocateAnElevator(CallForElevator c);

    /**
     * This method is the low level command for each elevator in terms of the elevator API: GoTo, Stop,
     * The simulator calls the method every time stamp (dt), note: in most cases NO action is needed.
     * @param elev the current Elevator index on which the operation is performs.
     */
    public void cmdElevator(int elev);
}
