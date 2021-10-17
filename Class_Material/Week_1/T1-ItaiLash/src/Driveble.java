import java.awt.geom.Point2D;

public interface Driveble {

    /**
     *
     * @param p set the position of vehicle to p
     */
    void initPos(Point2D p);

    /**
     *
     * @return return the position of the vehicle
     */
    Point2D getPos();

    /**
     *
     * @param dst 2d destination point
     * @return the time for the trip
     * This function make the car drive from sec to dst with respect to it's fuel constipation and fuel capacity
     */
    double drive( Point2D dst);


}
