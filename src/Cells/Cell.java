package Cells;

import Logic.Colour;

import java.util.HashMap;

class Cell {
    private final Colour colour;
    private static final HashMap<Colour, Cell> cells = new HashMap<>();

    private Cell(Colour c) {
        this.colour = c;
    }

    static void init() {
        // initializes the flyweight store
        cells.clear();

        for (Colour c : Colour.values())
            cells.put(c, new Cell(c));
    }

    static Cell get_cell(Colour c) {
        // returns a Flyweight cell for a requested colour
        assert cells.containsKey(c);

        return cells.get(c);
    }

    Colour get_colour() {
        // returns the colour of a cell
        return this.colour;
    }

    boolean is_alive() {
        // returns whether a cell is alive or not
        return this.colour != Colour.NONE;
    }


    // overrides the default equals and hashcode method
    @Override
    public boolean equals(Object o) {
        // overrides the default equals method in Object
        Cell c;
        if (o == this)
            return true;
        else if (!(o instanceof Cell))
            return false;
        else
            c = (Cell) o;
        return this.colour.equals(c.colour);
    }

    @Override
    public int hashCode() {
        return this.colour.hashCode();
    }


}
