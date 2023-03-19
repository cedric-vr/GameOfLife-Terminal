package UtilTest;

import Util.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    @BeforeEach
    void setup() {
        Coordinate.set_limits(9, 9);
    }

    @Test
    void Coordinate_v1() {
        Coordinate a = new Coordinate(0, 0);
        assertEquals(0, a.get_x());
        assertEquals(0, a.get_y());
    }

    @Test
    void Coordinate_v2() {
        boolean assertion = false;
        try {
            Coordinate a = new Coordinate(-1, -1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v3() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(0, -1);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v4() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(-1, 0);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v5() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(100, 100);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v6() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(-1, 100);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v7() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(100, -1);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v8() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(100, 0);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void Coordinate_v9() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(0, 100);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void distance_v1() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(1, 0);
        assertEquals(1, Coordinate.distance(a, b));
    }

    @Test
    void distance_v2() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(1, 0);
            Coordinate b = new Coordinate(0, 1);
            int d = Coordinate.distance(a, b);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void distance_v3() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 1);
        assertEquals(1, Coordinate.distance(a, b));
    }

    @Test
    void with_distance_v1() {
        Coordinate a = new Coordinate(Coordinate.get_min_x(), Coordinate.get_min_y());
        ArrayList<Coordinate> points = a.with_distance(1);

        assertEquals(3, points.size());
    }

    @Test
    void with_distance_v2() {
        Coordinate a = new Coordinate(Coordinate.get_max_x(), Coordinate.get_min_y());
        ArrayList<Coordinate> points = a.with_distance(1);

        assertEquals(3, points.size());
    }

    @Test
    void with_distance_v3() {
        Coordinate a = new Coordinate(Coordinate.get_min_x(), Coordinate.get_max_y());
        ArrayList<Coordinate> points = a.with_distance(1);

        assertEquals(3, points.size());
    }

    @Test
    void with_distance_v4() {
        Coordinate a = new Coordinate(Coordinate.get_max_x(), Coordinate.get_max_y());
        ArrayList<Coordinate> points = a.with_distance(1);

        assertEquals(3, points.size());
    }

    @Test
    void with_distance_v5() {
        Coordinate a = new Coordinate(2, 2);
        ArrayList<Coordinate> points = a.with_distance(1);

        assertEquals(8, points.size());
    }

    @Test
    void with_distance_v6() {
        Coordinate a = new Coordinate(0, 0);
        ArrayList<Coordinate> points = a.with_distance(1000);

        assertEquals(0, points.size());
    }

    @Test
    void with_distance_v7() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(1, 0);
            ArrayList<Coordinate> points = a.with_distance(-1);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void with_distance_corner() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(1, 0);
            ArrayList<Coordinate> points = a.with_distance_corner(-1);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void with_distance_cross() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(1, 0);
            ArrayList<Coordinate> points = a.with_distance_cross(-1);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void get_range_v1() {
        boolean assertion;
        try {
            Coordinate a = new Coordinate(1, 0);
            Coordinate b = new Coordinate(0, 1);
            ArrayList<Coordinate> points = Coordinate.get_range(a, b);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void get_Range_v2() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 2);
        ArrayList<Coordinate> points = Coordinate.get_range(a, b);

        assertEquals(3, points.size());
    }

    @Test
    void get_Range_v3() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(2, 0);
        ArrayList<Coordinate> points = Coordinate.get_range(a, b);

        assertEquals(3, points.size());
    }

    @Test
    void get_Range_v4() {
        Coordinate a = new Coordinate(2, 0);
        Coordinate b = new Coordinate(0, 0);
        ArrayList<Coordinate> points = Coordinate.get_range(a, b);

        assertEquals(3, points.size());
    }

    @Test
    void get_Range_v5() {
        Coordinate a = new Coordinate(0, 2);
        Coordinate b = new Coordinate(0, 0);
        ArrayList<Coordinate> points = Coordinate.get_range(a, b);

        assertEquals(3, points.size());
    }

    @Test
    void get_Range_v6() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);
        ArrayList<Coordinate> points = Coordinate.get_range(a, b);

        assertEquals(1, points.size());
    }

    @Test
    void equals_v1() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);
        assertEquals(a, b);
    }

    @Test
    void equals_v2() {
        Coordinate a = new Coordinate(1, 0);
        Coordinate b = new Coordinate(0, 0);
        assertNotEquals(a, b);
    }

    @Test
    void equals_v3() {
        Coordinate a = new Coordinate(1, 0);
        assertEquals(a, a);
    }

    @Test
    void equals_v4() {
        Coordinate a = new Coordinate(1, 0);
        assertNotEquals(null, a);
    }

    @Test
    void hashcode() {
        Coordinate a = new Coordinate(0, 0);
        assertEquals(0, a.hashCode());
    }

    @Test
    void get_copy() {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = a.get_copy();
        assertEquals(a, b);
    }

    @Test
    void input_v1() {
        Coordinate a = new Coordinate(0, 0);
        String input = "0,0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //StringWriter output = new StringWriter();

        Coordinate b = Coordinate.input();
        assertEquals(a, b);
    }


    @Test
    void can_convert_v1() {
        String input = "0,0";
        assertTrue(Coordinate.can_convert(input));
    }

    @Test
    void can_convert_v2() {
        String input = "0,0,0";
        assertFalse(Coordinate.can_convert(input));
    }

    @Test
    void can_convert_v3() {
        String input = "A,0";
        assertFalse(Coordinate.can_convert(input));
    }

    @Test
    void set_limits_v1() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(1, -1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void set_limits_v2() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(-1, 1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void set_limits_v3() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(-1, -1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void set_limits_v4() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(1, 1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    void translation_v1() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(1, 1);
            Coordinate a = new Coordinate(0, 0);
            Coordinate b = a.translation(100, 100);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void translation_v2() {
        boolean assertion = false;
        try {
            Coordinate.set_limits(10, 10);
            Coordinate a = new Coordinate(0, 0);
            Coordinate b = a.translation(1, 1);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }


}




