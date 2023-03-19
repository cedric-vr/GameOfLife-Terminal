package Board;

import Logic.Colour;
import Util.Coordinate;
import Util.Display;

import java.util.HashMap;

public class Grid {
    private final int grid_size_x = Coordinate.dimension_x();
    private final int grid_size_y = Coordinate.dimension_y();
    private final HashMap<Coordinate, Square> grid = new HashMap<>();


    public Grid() {
        //initializes a Grid and the flyweight store for Square
        Square.init();
    }

    public String[][] get_grid() {
        //returns the string representation of the grid for the terminal output

        String[][] draw_grid = new String[grid_size_x][grid_size_y];

        for (int i = 0; i < grid_size_x; i++) {
            for (int j = 0; j < grid_size_y; j++) {
                if (this.grid.containsKey(new Coordinate(i, j)))
                    draw_grid[i][j] = this.grid.get(new Coordinate(i, j)).get_colour().get_colour();
                else
                    draw_grid[i][j] = Colour.NONE.get_colour();
            }
        }
        return draw_grid;
    }

    public void kill(Coordinate a) {
        // executes the kill of a Cell at Coordinate a
        grid.remove(a);
    }

    public void create(Coordinate a, Colour c) {
        // executes the creation of a Cell at Coordinate a
        this.grid.put(a, Square.get_square(c));
    }

    public void draw() {
        //draws the grid for the terminal output
        Display.draw_grid(this.get_grid());
    }

    public boolean is_empty() {
        //returns whether a grid is empty or not
        return this.grid.isEmpty();
    }

    public Colour[][] get_chunk(Coordinate a, Coordinate b) {
        //for two Coordinates a and b that represent the top left and bottom right corner of a rectangle
        //this method returns the section of the grid represented by that rectangle

        assert (Coordinate.smaller_x(a, b) && Coordinate.smaller_y(a, b)) || a.equals(b);

        Colour[][] chunk = new Colour[b.get_x() - a.get_x() + 1][b.get_y() - a.get_y() + 1];

        for (int x = 0; x <= b.get_x() - a.get_x(); x++) {
            for (int y = 0; y <= b.get_y() - a.get_y(); y++) {
                Coordinate temp = new Coordinate(a.get_x() + x, a.get_y() + y);

                if (this.grid.containsKey(temp))
                    chunk[x][y] = this.grid.get(temp).get_colour();
                else
                    chunk[x][y] = Colour.NONE;
            }
        }
        return chunk;
    }
}
