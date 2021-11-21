/**
 * This interface represents a simple 2D geometric shape,
 * with area, distance (Point2D),
 */
public interface Shape {
    /** computes a none negative value (distance) between the closest
     * point on this shape and the query point ot
     *
     * @param ot
     * @return
     */
    public double distance(Point2D ot);

    /**
     * This method translate the geometric shape according to the given vector
     * represented by a geometrics2D.Point_2D as a vector.
     * @param vec
     */
    public void move(Point2D vec);
    public double area();
    public double perimeter();
    public boolean isInSide(Point2D ot);
    public default String getName() {
        String ans = this.getClass().getName();
        return ans;
    }
}
