package PlayersTest;

import Logic.Colour;
import Players.Playerbase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerbaseTest {

    @Test
    void init_v1() {
        boolean assertion;
        try {
            Playerbase pb = new Playerbase(0);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void init_v2() {
        boolean assertion;
        try {
            Playerbase pb = new Playerbase(2);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    void add_v1() {
        boolean assertion;
        Playerbase pb = new Playerbase(1);
        try {
            pb.add("A", Colour.RED);
            pb.add("B", Colour.BLUE);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void add_v2() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        try {
            pb.add("A", Colour.RED);
            pb.add("A", Colour.BLUE);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void add_v3() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        try {
            pb.add("A", Colour.RED);
            pb.add("B", Colour.RED);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void add_v4() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        try {
            pb.add("A", Colour.RED);
            pb.add("B", Colour.BLUE);
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    public void next_v1() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        pb.add("A", Colour.RED);
        pb.add("B", Colour.BLUE);

        pb.next();

        Colour c = pb.get_current_colour();
        assertEquals(Colour.BLUE, c);
    }

    @Test
    public void next_v2() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        pb.add("A", Colour.RED);
        pb.next();
        pb.add("B", Colour.BLUE);
        pb.next();

        Colour c = pb.get_current_colour();
        assertEquals(Colour.BLUE, c);
    }

    @Test
    void get_current_player() {
        boolean assertion;
        Playerbase pb = new Playerbase(2);
        try {
            pb.add("A", Colour.RED);
            String s = pb.get_current_name();
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void get_colours() {
        Playerbase pb = new Playerbase(2);
        pb.add("A", Colour.RED);
        pb.add("B", Colour.BLUE);
        ArrayList<Colour> list = pb.get_colours();
        assertEquals(2, list.size());
    }

    @Test
    void get_colours_v2() {
        Playerbase pb = new Playerbase(2);
        ArrayList<Colour> list = pb.get_colours();
        assertEquals(0, list.size());
    }

    @Test
    void get_name_of_colour_v1() {
        Playerbase pb = new Playerbase(2);
        pb.add("A", Colour.RED);
        pb.add("B", Colour.BLUE);
        String s = pb.get_name_of_colour(Colour.BLUE);
        assertEquals("B", s);
    }

    @Test
    void get_name_of_colour_v2() {
        Playerbase pb = new Playerbase(2);
        String s = pb.get_name_of_colour(Colour.BLUE);
        assertEquals("", s);
    }

    @Test
    void get_colour() {
        String input = "R";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Playerbase pb = new Playerbase(2);
        Colour c = pb.get_colour();
        assertEquals(Colour.RED, c);
    }

    @Test
    void get_name() {
        String input = "Roland";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Playerbase pb = new Playerbase(2);
        String s = pb.get_name();
        assertEquals("Roland", s);
    }
}

