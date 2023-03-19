package LogicTest;

import Logic.Colour;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ColourTest {
    @Test
    void get_colour() {
        assertEquals("B", Colour.BLUE.get_colour());
    }

    @Test
    void get_colours() {
        assertEquals(4, Colour.get_colours().size());
    }

    @Test
    void is_valid_symbol() {
        assertTrue(Colour.is_valid_symbol("R"));
        assertFalse(Colour.is_valid_symbol(" "));
        assertFalse(Colour.is_valid_symbol("X"));
    }

    @Test
    void reverse_lookup_v1() {
        boolean assertion;
        try {
            Colour.reverse_lookup(" ");
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void reverse_lookup_v2() {
        boolean assertion;
        try {
            Colour.reverse_lookup("X");
            assertion = false;
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void reverse_lookup_v3() {
        assertEquals(Colour.YELLOW, Colour.reverse_lookup("Y"));
    }

    @Test
    void input_v1() {
        String input = "R";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Colour c = Colour.input();
        assertEquals(Colour.RED, c);
    }


}
