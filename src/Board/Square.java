package Board;

import Logic.Colour;

import java.util.HashMap;

public class Square {
    private static final HashMap<Colour, Square> squares = new HashMap<>();
    private final Colour colour;

    /**
     * Note: For some tests in the PerformanceTest folder it is necessary to create Squares that
     * are not flyweight objects. Therefore, square and all of its methods need to be public even
     * though package private (no modifier) would be appropriate and sufficient for the class
     * and all its methods.
     **/

    public Square(Colour colour) {
        // initializes a square with a provided colour
        this.colour = colour;
    }

    public static void init() {
        // initializes the flyweight store
        squares.clear();

        for (Colour c : Colour.values())
            squares.put(c, new Square(c));
    }


    public static Square get_square(Colour c) {
        // returns a Flyweight square for a requested colour
        assert squares.containsKey(c);

        return squares.get(c);
    }

    public Colour get_colour() {
        // returns the colour of a square
        return this.colour;
    }

}
