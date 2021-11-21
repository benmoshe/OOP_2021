/**
 * This class represents a simple 2D point in the
 * plane. It supports several geometric operations:
 * distance, move, ...
 */
public class Point2D implements Shape{
    public static final Point2D _OO = new Point2D(0,0);
    private double _x,_y;

    public Point2D(double a, double b) {
        _x=a;
        _y=b;
    }
    public Point2D(Point2D ot) {
        this(ot._x, ot._y);
    }
    @Override
    public double distance(Point2D ot) {
        double ans =0;
        double dx = ot._x - this._x;
        double dy = ot._y - this._y;
        double d = dx*dx + dy*dy;
        ans = Math.sqrt(d);
        return ans;
    }



    /**
     * This method computes the distance from this
     * point to the (0,0).
     * @return
     */
    public double distance(){
        return this.distance(_OO);
    }

    @Override
    public void move(Point2D vec) {
        _x += vec._x;
        _y += vec._y;
    }
    public void move(double x, double y) {
        _x += x;
        _y += y;
    }
    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public boolean isInSide(Point2D ot) {
        return this.distance()==0;
    }
    @Override
    public String toString() {
        return "["+_x+","+_y+"]";
    }
    @Override
    public  String getName() {
        String ans = "  ***  "+this.getClass().getName();
        return ans;
    }
}

