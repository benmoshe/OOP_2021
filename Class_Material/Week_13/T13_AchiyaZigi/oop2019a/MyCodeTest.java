package oop2019a;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class MyCodeTest {
    public void simpleOpen() {
        car_code c = new MyCode();
        assertFalse(c.open("3213#"));
        assertTrue(c.open("1231#"));
    }

    public void simpleIsOpen() {
        car_code c = new MyCode();
        assertTrue(c.open("1231#"));
        try {
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(c.isOpen());
        try {
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(c.isOpen());

    }

    public void simpleCanBeOpen() {
        car_code c = new MyCode();
        assertFalse(c.open("1331#"));
        assertTrue(c.canBeOpen());
        assertFalse(c.open("1332#"));
        assertTrue(c.canBeOpen());
        assertFalse(c.open("1332#"));
        assertFalse(c.canBeOpen());
        try {
            Thread.sleep(1000 * 60 * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(c.canBeOpen());

    }
}
