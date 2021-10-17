package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

/**
 * A bit better than ShabatElevAlgo: odd (index) elevators are going up, while even (index) elevators are going down.
 */
public class ShabatElev2Algo implements ElevatorAlgo {
    public static final int UP=1, DOWN=-1;
    private int _direction;
    private Building _building;
    public ShabatElev2Algo(Building b) {
        _building = b;
        _direction = UP;
    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_Basic_Shabat_Elevator_Algo(2)";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ans = 0;
        if (_building.numberOfElevetors() > 1) {
            if (c.getSrc() < c.getDest()) { // UP
                while (ans % 2 == 0) {
                    ans = rand(0, _building.numberOfElevetors()); // this is totally inefficient
                }
            }
            else {
                ans = 1;
                while (ans % 2 == 1) {
                    ans = rand(0, _building.numberOfElevetors());  // this is totally inefficient
                }
            }

        }
        return ans;
    }

    @Override
    public void cmdElevator(int elev) {
        if (this._building.numberOfElevetors() == 1) {
            f0();
        }
        else {
            if(elev%2==0) {f2(elev);}
            else {f1(elev);}
        }

    }
    private void f1(int elev) {
        Elevator curr = this.getBuilding().getElevetor(elev);
        if(curr.getState() == Elevator.LEVEL) {
            int dir = this.getDirection();
            int pos = curr.getPos();
            //boolean up2down = false;
            if(pos<curr.getMaxFloor()) { curr.goTo(pos+1); }
            else {
                int min = this._building.minFloor();
                curr.goTo(min);
            }
        }
    }
    private void f2(int elev) {
        Elevator curr = this.getBuilding().getElevetor(elev);
        if(curr.getState() == Elevator.LEVEL) {
            int dir = this.getDirection();
            int pos = curr.getPos();
            //boolean up2down = false;
            if(pos>curr.getMinFloor()) { curr.goTo(pos-1); }
            else {
                int max = this._building.maxFloor();
                curr.goTo(max);
            }
        }
    }
    private void f0() {
        Elevator curr = this.getBuilding().getElevetor(0);
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
