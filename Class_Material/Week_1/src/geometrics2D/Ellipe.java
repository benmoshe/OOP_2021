package geometrics2D;

/**
 */
public class Ellipe implements Shape {
    private Point_2D _p0, _p1;
    private double _radius;
    public Ellipe(Point_2D p0, Point_2D p1, double r) {
        _p0 = new Point_2D(p0);
        _p1 = new Point_2D(p1);
        _radius = r;
    }
    @Override
    public double distance(Point_2D ot) {
        return -1; // BUG!!
    }

    @Override
    public void move(Point_2D vec) {
        this._p0.move(vec);
        this._p1.move(vec);
    }

    @Override
    public double area() {
        return -1;
    }

    @Override
    public double perimeter() {
        return -1;
    }

    @Override
    public boolean isInSide(Point_2D ot) {
        boolean ans = false;
        double dist = ot.distance(_p0) +ot.distance(_p1);
        if(dist <= this.get_radius()) {ans = true;}
        return ans;
    }

    public double get_radius() {
        return _radius;
    }
}
