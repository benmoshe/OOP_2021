import gui.Point3D;
import gui.Points3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class Points3DTest {
    private Points3D _pp0, _pp1;
    @BeforeEach
    void setUp() {
        _pp0 = new Points3D();
        for(int i=0;i<10;i++) {
            Point3D p = new Point3D(i*0.5, i*2.0, i);
            _pp0.add(p);
        }
        _pp1 = new Points3D();
        Iterator<Point3D> iter = _pp0.iterator();
        while(iter.hasNext()) {
            _pp1.add(new Point3D(iter.next()));
        }
    }

    @Test
    @DisplayName("Save & Load tester")
    void saveAndLoad() {
        String file_name = "test_for_Points3D.obj";
        _pp0.save(file_name);
        Points3D pp3  = new Points3D();
        pp3.load(file_name);
        Assertions.assertEquals(pp3,_pp0);
    }

    @Test
    //@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void equals1() {
        Assertions.assertEquals(_pp0, _pp1);
        _pp0.add(Point3D.ORIGIN);
        _pp1.add(Point3D.ORIGIN);
        _pp1.add(Point3D.ORIGIN);
        _pp1.add(Point3D.ORIGIN);
        Assertions.assertEquals(_pp0, _pp1);
        _pp1.get(0).add(6,6,6);
        Assertions.assertNotEquals(_pp0, _pp1);
    }
    @Test
    void runTimeTest() {
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            //Thread.sleep(100);
            equals1();
        });
    }
}