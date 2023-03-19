package BoardTest;

import Board.Grid;
import Cells.AliveCells;
import Logic.Colour;
import Util.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    private Grid grid;
    private AliveCells cells;

    @BeforeEach
    void setup() {
        Coordinate.set_limits(9, 9);
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.BLUE);
        colours.add(Colour.RED);
        this.grid = new Grid();
        this.cells = new AliveCells(this.grid, colours, false);
    }

    void chunk_setup() {
        Coordinate.set_limits(10000, 10000);
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.BLUE);
        colours.add(Colour.RED);
        this.grid = new Grid();
        this.cells = new AliveCells(this.grid, colours, true);
    }

    @Test
    void draw_grid() {
        String[][] draw_grid = this.grid.get_grid();

        for (int i = Coordinate.get_min_x(); i < Coordinate.get_max_x(); i++) {
            for (int j = Coordinate.get_min_y(); j < Coordinate.get_max_y(); j++) {
                assertEquals(Colour.NONE.get_colour(), draw_grid[i][j]);
            }
        }
    }

    @Test
    void is_empty_v1() {
        assertTrue(this.grid.is_empty());
    }

    @Test
    void is_empty_v2() {
        cells.create(new Coordinate(Coordinate.get_min_x(), Coordinate.get_min_y()), Colour.RED);
        assertFalse(this.grid.is_empty());
    }

    @Test
    void kill() {
        Coordinate c = new Coordinate(Coordinate.get_min_x(), Coordinate.get_min_y());
        cells.create(c, Colour.RED);
        assertFalse(this.grid.is_empty());
        this.cells.kill(c, Colour.BLUE);
        assertTrue(this.grid.is_empty());
    }

    @Test
    void get_chunk_v1() {
        //chunk_setup();
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);

        Colour[][] chunk = grid.get_chunk(a, b);
        assertEquals(Colour.NONE, chunk[0][0]);
    }

    @Test
    void get_chunk_v2() {
        boolean assertion = false;
        try {
            Coordinate a = new Coordinate(1, 0);
            Coordinate b = new Coordinate(0, 0);

            Colour[][] chunk = grid.get_chunk(a, b);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void get_chunk_v3() {
        boolean assertion = false;
        try {
            Coordinate a = new Coordinate(0, 1);
            Coordinate b = new Coordinate(0, 0);

            Colour[][] chunk = grid.get_chunk(a, b);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void get_chunk_v4() {
        chunk_setup();
        Coordinate a = new Coordinate(4900, 4900);
        Coordinate b = new Coordinate(5100, 5100);

        assertFalse(this.grid.is_empty());
        Colour[][] chunk = grid.get_chunk(a, b);
        assertEquals(Colour.BLUE, chunk[100 - 2][100]);
    }


}
