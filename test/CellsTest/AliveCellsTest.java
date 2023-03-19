package CellsTest;

import Board.Grid;
import Cells.AliveCells;
import Logic.Colour;
import Util.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AliveCellsTest {
    private AliveCells cells;
    private Grid grid;
    private final ArrayList<Colour> colours = new ArrayList<>();

    @BeforeEach
    void setup() {
        Coordinate.set_limits(9, 9);

        this.colours.add(Colour.BLUE);
        this.colours.add(Colour.RED);
        this.grid = new Grid();
        this.cells = new AliveCells(this.grid, colours, false);
    }

    @Test
    void equivalent() {
        this.cells = new AliveCells(this.grid, colours, true);
        Grid g2 = new Grid();
        AliveCells cells1 = new AliveCells(g2, colours, true);
        assertTrue(this.cells.equivalent(cells1));
    }

    @Test
    void equivalent_v2() {
        this.cells = new AliveCells(this.grid, colours, false);
        this.cells.create(new Coordinate(4, 4), Colour.BLUE);
        this.cells.create(new Coordinate(4, 3), Colour.BLUE);
        this.cells.create(new Coordinate(4, 5), Colour.BLUE);

        AliveCells cells1 = this.cells;

        this.cells.evolve();
        this.cells.evolve();

        assertTrue(this.cells.equivalent(cells1));
    }


    @Test
    void init() {
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.BLUE);
        colours.add(Colour.RED);

        assertTrue(this.grid.is_empty());
        AliveCells cells1 = new AliveCells(this.grid, colours, false);
        assertTrue(this.grid.is_empty());
    }

    @Test
    void init_v1() {
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.BLUE);
        colours.add(Colour.RED);

        assertTrue(this.grid.is_empty());
        AliveCells cells1 = new AliveCells(this.grid, colours, true);
        assertFalse(this.grid.is_empty());
    }

    @Test
    void init_v2() {
        ArrayList<Colour> colours1 = new ArrayList<>();
        colours1.add(Colour.BLUE);
        colours1.add(Colour.NONE);

        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, colours1, false);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);

    }

    @Test
    void init_v3() {
        this.cells.create(new Coordinate(0, 0), Colour.RED);

        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, this.colours, false);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);

    }

    @Test
    void init_v4() {
        ArrayList<Colour> colours1 = new ArrayList<>();
        colours1.add(Colour.BLUE);
        colours1.add(Colour.NONE);
        colours1.add(Colour.RED);

        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, colours1, false);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void init_v5() {
        Coordinate.set_limits(4, 0);
        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, this.colours, true);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void init_v6() {
        Coordinate.set_limits(5, 0);
        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, this.colours, true);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void init_v7() {
        Coordinate.set_limits(4, 1);
        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, this.colours, true);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void init_v8() {
        Coordinate.set_limits(5, 1);
        boolean assertion = false;
        try {
            AliveCells cells1 = new AliveCells(this.grid, this.colours, true);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    void can_create_v1() {
        assertTrue(this.cells.can_create(new Coordinate(0, 0), Colour.BLUE));
    }

    @Test
    void can_create_v2() {
        assertFalse(this.cells.can_create(new Coordinate(0, 0), Colour.GREEN));
    }

    @Test
    void can_create_v3() {
        assertTrue(this.cells.can_create(new Coordinate(0, 0), Colour.BLUE));
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        assertFalse(this.cells.can_create(new Coordinate(0, 0), Colour.BLUE));
    }

    @Test
    void can_kill_v1() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        assertTrue(this.cells.can_kill(new Coordinate(0, 0), Colour.RED));
    }

    @Test
    void can_kill_v2() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        assertFalse(this.cells.can_kill(new Coordinate(0, 0), Colour.BLUE));
    }

    @Test
    void can_kill_v3() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        assertFalse(this.cells.can_kill(new Coordinate(1, 0), Colour.RED));
    }

    @Test
    void can_kill_v4() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        assertFalse(this.cells.can_kill(new Coordinate(0, 0), Colour.GREEN));
    }

    @Test
    void can_kill_v5() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        this.cells.kill(new Coordinate(0, 0), Colour.RED);
        assertFalse(this.cells.can_kill(new Coordinate(0, 0), Colour.RED));
    }

    @Test
    void kill_v1() {
        boolean assertion = false;
        try {
            this.cells.create(new Coordinate(0, 0), Colour.BLUE);
            this.cells.kill(new Coordinate(0, 0), Colour.BLUE);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void kill_v2() {
        boolean assertion = false;
        try {
            this.cells.create(new Coordinate(0, 0), Colour.BLUE);
            this.cells.kill(new Coordinate(0, 0), Colour.RED);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    void create_v1() {
        boolean assertion = false;
        try {
            this.cells.create(new Coordinate(0, 0), Colour.GREEN);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void create_v2() {
        boolean assertion = false;
        try {
            this.cells.create(new Coordinate(0, 0), Colour.RED);
        } catch (AssertionError e) {
            assertion = true;
        }

        assertFalse(assertion);
    }

    @Test
    void alive_v1() {
        this.cells.create(new Coordinate(0, 0), Colour.RED);
        this.cells.create(new Coordinate(1, 0), Colour.BLUE);
        assertTrue(this.cells.is_alive(Colour.RED));
    }

    @Test
    void alive_v2() {
        assertFalse(this.cells.is_alive(Colour.BLUE));
    }

    @Test
    void evolve_v1() {
        this.cells.evolve();
        assertEquals(0, this.cells.get_number_of_generations());
    }

    @Test
    void evolve_v2() {
        this.cells.create(new Coordinate(2, 2), Colour.BLUE);
        this.cells.evolve();
        assertTrue(this.grid.is_empty());
        assertFalse(this.cells.is_alive(Colour.BLUE));
    }

    @Test
    void evolve_v3() {
        this.cells.create(new Coordinate(1, 2), Colour.BLUE);
        this.cells.create(new Coordinate(2, 2), Colour.BLUE);
        this.cells.create(new Coordinate(3, 2), Colour.BLUE);
        this.cells.evolve();

        String[][] draw_grid = this.grid.get_grid();
        assertTrue(this.cells.is_alive(Colour.BLUE));

        assertEquals(" ", draw_grid[1][2]);
        assertEquals(" ", draw_grid[3][2]);
        assertEquals("B", draw_grid[2][2]);
        assertEquals("B", draw_grid[2][1]);
        assertEquals("B", draw_grid[2][3]);
    }

    @Test
    void evolve_v4() {
        this.cells.create(new Coordinate(0, 0), Colour.BLUE);
        this.cells.create(new Coordinate(1, 0), Colour.BLUE);
        this.cells.create(new Coordinate(1, 1), Colour.BLUE);
        this.cells.create(new Coordinate(1, 2), Colour.BLUE);
        this.cells.create(new Coordinate(0, 2), Colour.BLUE);
        this.cells.create(new Coordinate(0, 1), Colour.BLUE);
        this.cells.evolve();

        String[][] draw_grid = this.grid.get_grid();
        assertTrue(this.cells.is_alive(Colour.BLUE));

        assertEquals(" ", draw_grid[0][1]);
        assertEquals(" ", draw_grid[1][1]);
        assertEquals("B", draw_grid[0][0]);
        assertEquals("B", draw_grid[1][0]);
        assertEquals("B", draw_grid[2][1]);
        assertEquals("B", draw_grid[0][2]);
        assertEquals("B", draw_grid[1][2]);
    }

    @Test
    void evolve_v5() {
        this.cells.create(new Coordinate(0, 1), Colour.BLUE);
        this.cells.create(new Coordinate(1, 0), Colour.BLUE);
        this.cells.create(new Coordinate(1, 1), Colour.BLUE);

        this.cells.evolve();

        String[][] draw_grid = this.grid.get_grid();
        assertTrue(this.cells.is_alive(Colour.BLUE));

        assertEquals("B", draw_grid[0][0]);
        assertEquals("B", draw_grid[1][0]);
        assertEquals("B", draw_grid[0][1]);
        assertEquals("B", draw_grid[1][1]);

    }

}




