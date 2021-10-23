import geometrics2D.Point_2D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {
    public final double EPS = 0.001, EPS2 = EPS*EPS;
    @Test
    void distanceTest() {
        double a=1.1, b=-2.0002;
        double dx=3,dy=4, dxy = Math.sqrt(dx*dx +dy*dy);
        Point_2D p0 = new Point_2D(a,b);
        Point_2D p1 = new Point_2D(a+dx,b+dy);
        double dist01 = p0.distance(p1);
        double dist10 = p1.distance(p0);
        double dist00 = p0.distance(p0);

        assertEquals(dist00,0,EPS2);
        assertEquals(dist01,dxy,EPS2);
        assertEquals(dist01,dist10,EPS2);
    }


    @Test
    void moveTest() {
        // make sure you can come up with a proper testing strategy for this method
    }

    @Test
    void areaTest() {
        // make sure you can come up with a proper testing strategy for this method
    }
}