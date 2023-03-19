package PerformanceTests;

import Board.Grid;
import Cells.AliveCells;
import Logic.Colour;
import Util.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTest {
    private AliveCells cells;
    private Grid grid;
    private final ArrayList<Colour> colours = new ArrayList<>();

    @Test
    void performance_on_large_grid_v1() {
        int size = 20;
        int psize = (int) Math.pow(2, size);

        Coordinate.set_limits(psize, psize);

        this.grid = new Grid();
        this.colours.add(Colour.BLUE);
        this.colours.add(Colour.RED);

        this.cells = new AliveCells(this.grid, colours, true);

        Coordinate center_p1 = new Coordinate(Coordinate.dimension_x() / 2, Coordinate.dimension_y() / 2 - 1);
        Coordinate center_p2 = new Coordinate(Coordinate.dimension_x() / 2 + Coordinate.dimension_x() % 2 - 1, Coordinate.dimension_y() / 2 - 1);

        Colour c1 = this.colours.get(0);
        Colour c2 = this.colours.get(1);

        this.cells.kill(center_p2.translation(2, 0), c1);
        this.cells.create(center_p1.translation(-1, 0), c1);
        this.cells.evolve();

        this.cells.kill(center_p1.translation(-2, -1), c2);
        this.cells.create(center_p2.translation(2, -1), c2);
        this.cells.evolve();

        assertFalse(this.cells.is_alive(c1));
        assertEquals(5, this.cells.get_number_of_cells(c2));
    }

    @Test
    void performance_on_large_grid_v2() {
        int size = 20;
        int psize = (int) Math.pow(2, size);
        int number_of_cell_clusters = 10000;

        Coordinate.set_limits(psize, psize);

        this.grid = new Grid();
        this.colours.add(Colour.BLUE);
        this.colours.add(Colour.RED);

        this.cells = new AliveCells(this.grid, colours, false);

        for (int i = 0; i < number_of_cell_clusters; i++) {
            int x = (Coordinate.get_min_x() + 1) + (int) (Math.random() * (Coordinate.get_max_x() - 1));
            int y = (Coordinate.get_min_y() + 1) + (int) (Math.random() * (Coordinate.get_max_y() - 1));
            Coordinate c1 = new Coordinate(x, y);

            try {
                this.cells.create(c1.translation(1, 0), Colour.BLUE);
                this.cells.create(c1, Colour.BLUE);
                this.cells.create(c1.translation(-1, 0), Colour.BLUE);
            } catch (Exception ignored) {
            }
        }

        AliveCells cells1 = this.cells;

        this.cells.evolve();
        this.cells.evolve();

        assertTrue(this.cells.equivalent(cells1));
    }

    /*

    @Test
    void grid_with_optimizations(){
        int size = 14;

        int p_size = (int)Math.pow(2,size);
        Coordinate.set_limits(p_size, p_size);

        Square.init();

        Grid grid = new Grid();

        for(int x = 0; x < Coordinate.get_max_x()/16; x++) {
            for (int y = 0; y < Coordinate.get_max_x()/16; y++) {
                grid.create(new Coordinate(x, y), Colour.BLUE);
            }
        };
        assertFalse(grid.is_empty());
    }


    @Test
    void grid_with_naive_approach() {
        int size = 14;

        int p_size = (int) Math.pow(2, size);
        Coordinate.set_limits(p_size, p_size);

        Square.init();

        Square[][] grid = new Square[Coordinate.dimension_x()][Coordinate.dimension_y()];

        for (int x = 0; x < Coordinate.get_max_x(); x++) {
            for (int y = 0; y < Coordinate.get_max_x(); y++) {
                grid[x][y] = new Square(Colour.NONE);
            }
        }

        for (int x = 0; x <= Coordinate.get_max_x()/16; x++) {
            for (int y = 0; y <= Coordinate.get_max_x()/16; y++) {
                grid[x][y] = new Square(Colour.RED);
            }
        }
        assertEquals(Colour.RED, grid[0][0].get_colour());
    }

    @Test
    void grid_with_naive_approach_flyweight() {
        int size = 14;

        int p_size = (int) Math.pow(2, size);
        Coordinate.set_limits(p_size, p_size);

        Square.init();

        Square[][] grid = new Square[Coordinate.dimension_x()][Coordinate.dimension_y()];

        for (int x = 0; x < Coordinate.get_max_x(); x++) {
            for (int y = 0; y < Coordinate.get_max_x(); y++) {
                grid[x][y] = Square.get_square(Colour.NONE);
            }
        }

        for (int x = 0; x <= Coordinate.get_max_x()/16; x++) {
            for (int y = 0; y <= Coordinate.get_max_x()/16; y++) {
                grid[x][y] = Square.get_square(Colour.RED);
            }
        }
        assertEquals(Colour.RED, grid[0][0].get_colour());
    }

    */
}
