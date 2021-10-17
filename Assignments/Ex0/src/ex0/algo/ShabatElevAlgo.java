package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

/**
 * This class represents the simplest algorithm for elevator allocation - it uses a trivial concept of Shabat-Elevator (ignoring all the calls).
 * It simply stops on any floor on the way up and then stops on any floor on the way down.
 */
public class ShabatElevAlgo implements ElevatorAlgo {
    public static final int UP=1, DOWN=-1;
    private int _direction;
    private Building _building;
    public ShabatElevAlgo(Building b) {
        _building = b;
        _direction = UP;
    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_Basic_Trivial_Shabat_Elevator_Algo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ind = rand(0,_building.numberOfElevetors()); // Random allocation of an elevator - you know better than that!!
        return ind;
    }

    @Override
    /**
     * Simply stops on any floor on the way up and then stops on any floor on the way down.
     */
    public void cmdElevator(int elev) {
        Elevator curr = this.getBuilding().getElevetor(elev);
        if(curr.getState() == Elevator.LEVEL) {
            int dir = this.getDirection();
            int pos = curr.getPos();
            boolean up2down = false;
            if(dir == UP) {
                if(pos<curr.getMaxFloor()) {
                    curr.goTo(pos+1);
                }
                else {
                    _direction = DOWN;
                    curr.goTo(pos-1);
                    up2down = true;
                }
            }
            if(dir == DOWN && !up2down) {
                if(pos>curr.getMinFloor()) {
                    curr.goTo(pos-1);
                }
                else {
                    _direction = UP;
                    curr.goTo(pos+1);
                }
            }
        }
    }
    public int getDirection() {return this._direction;}
    /**
     * return a random in [min,max)
     * @param min
     * @param max
     * @return
     */
    private static int rand(int min, int max) {
        if(max<min) {throw new RuntimeException("ERR: wrong values for range max should be >= min");}
        int ans = min;
        double dx = max-min;
        double r = Math.random()*dx;
        ans = ans + (int)(r);
        return ans;
    }
}
