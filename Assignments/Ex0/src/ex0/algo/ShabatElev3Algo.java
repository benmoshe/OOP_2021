package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

/**
 * A bit better than ShabatElev2Algo: randomly spread the elevators in the init stage, allocate the "closest" elevator..
 */
public class ShabatElev3Algo implements ElevatorAlgo {
    public static final int UP=1, DOWN=-1;
    private int _direction;
    private Building _building;
    private boolean[] _firstTime;
    public ShabatElev3Algo(Building b) {
        _building = b;
        _direction = UP;
        _firstTime = new boolean[_building.numberOfElevetors()];
        for(int i=0;i<_firstTime.length;i++) {_firstTime[i] = true;}
    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_Basic_Shabat_Elevator_Algo(3)";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ans = 0, elevNum = _building.numberOfElevetors();
        if (elevNum > 1) {
            if (c.getSrc() < c.getDest()) { // UP
                ans=1;
                for(int i=3;i<elevNum;i+=2) {
                    int src = c.getSrc();
                    if(dist(src, i)<dist(src, ans)) {ans = i;}
                }
            }
            else {   // down
                for(int i=2;i<elevNum;i+=2) {
                    int src = c.getSrc();
                    if(dist(src, i)<dist(src, ans)) {ans = i;}
                }
            }
        }
        return ans;
    }
    private double dist(int src, int elev) {
        double ans = -1;
        Elevator thisElev = this._building.getElevetor(elev);
        int pos = thisElev.getPos();
        double speed = thisElev.getSpeed();
        int min = this._building.minFloor(), max = this._building.maxFloor();
        double up2down = (max-min)*speed;
        double floorTime = speed+thisElev.getStopTime()+thisElev.getStartTime()+thisElev.getTimeForOpen()+thisElev.getTimeForClose();
        if(elev%2==1) { // up
            if(pos<=src) {ans = (src-pos)*floorTime;}
            else {
                ans = ((max-pos) + (pos-min))*floorTime + up2down;
            }
        }
        else {
            if(pos>=src) {ans = (pos-src)*floorTime;}
            else {
                ans = ((max-pos) + (pos-min))*floorTime + up2down;
            }
        }
        return ans;
    }
    @Override
    public void cmdElevator(int elev) {
        if(!_firstTime[elev]) {
            if (this._building.numberOfElevetors() == 1) {
                f0();
            } else {
                if (elev % 2 == 0) {
                    f2(elev);
                } else {
                    f1(elev);
                }
            }
        }
        else {
            _firstTime[elev] = false;
            int min = this._building.minFloor(), max = this._building.maxFloor();
            for(int i=0;i<this._building.numberOfElevetors();i++) {
                Elevator curr = this.getBuilding().getElevetor(elev);
                int floor = rand(min,max);
                curr.goTo(floor);
            }
        }

    }
    private void f1(int elev) { // odd --> UP
        Elevator curr = this.getBuilding().getElevetor(elev);
        if(curr.getState() == Elevator.LEVEL) {
            int dir = this.getDirection();
            int pos = curr.getPos();
            if(pos<curr.getMaxFloor()) { curr.goTo(pos+1); }
            else {
                int min = this._building.minFloor();
                curr.goTo(min);
            }
        }
    }
    private void f2(int elev) { // even --> Down
        Elevator curr = this.getBuilding().getElevetor(elev);
        if(curr.getState() == Elevator.LEVEL) {
            int dir = this.getDirection();
            int pos = curr.getPos();
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
