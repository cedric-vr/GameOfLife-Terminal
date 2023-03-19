package Cells;

import Board.Grid;
import Logic.Colour;
import Util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AliveCells {
    private final Grid grid;
    private final ArrayList<Colour> colours;
    private final HashMap<Coordinate, Cell> cells;
    private int number_of_generations = 0;
    private final HashMap<Coordinate, ArrayList<Coordinate>> potential_changes = new HashMap<>();

    public AliveCells(Grid grid, ArrayList<Colour> colours, boolean filled) {
        // initializes a new AliveCells object and calls setup() to fill the grid

        assert (grid.is_empty() && !colours.contains(Colour.NONE) && colours.size() == 2);

        Cell.init();
        this.grid = grid;
        this.colours = colours;
        this.cells = new HashMap<>();

        if (filled)
            this.setup();
    }


    private void setup() {
        // fills the grid with the initial configuration
        // this process is scalable and works for any size grid larger or equal to 6x2.

        assert Coordinate.dimension_x() >= 6 && Coordinate.dimension_y() >= 2;

        Coordinate center_p1 = new Coordinate(Coordinate.dimension_x() / 2, Coordinate.dimension_y() / 2 - 1);
        Coordinate center_p2 = new Coordinate(Coordinate.dimension_x() / 2 + Coordinate.dimension_x() % 2 - 1, Coordinate.dimension_y() / 2 - 1);

        Colour c1 = this.colours.get(0);
        Colour c2 = this.colours.get(1);

        this.create(center_p1.translation(-3, 0), c1);
        this.create(center_p1.translation(-2, 0), c1);
        this.create(center_p1.translation(-3, 1), c1);
        this.create(center_p1.translation(-2, 1), c1);

        this.create(center_p2.translation(2, 0), c2);
        this.create(center_p2.translation(3, 0), c2);
        this.create(center_p2.translation(2, 1), c2);
        this.create(center_p2.translation(3, 1), c2);
    }

    public int get_number_of_generations() {
        // returns the number of generations that have passed
        return this.number_of_generations;
    }

    public int get_number_of_cells(Colour colour) {
        // returns the number of cells for a given colour
        int number = 0;
        for (Cell c : this.cells.values()) {
            if (c.get_colour() == colour)
                number += 1;
        }
        return number;
    }

    public void create(Coordinate a, Colour c) {
        // creates a new Cell of colour c at coordinate a
        assert this.can_create(a, c);

        this.cells.put(a, Cell.get_cell(c));
        this.grid.create(a, c);

        //this.potential_changes_add(a);
    }

    public void kill(Coordinate a, Colour c) {
        // kills a cell at coordinate a when the current player has colour c
        assert this.can_kill(a, c);

        this.cells.remove(a);
        this.grid.kill(a);

        //this.potential_changes_remove(a);
    }

    public void evolve() {
        // makes the cell go through one generation if there are still cells left
        if (!this.cells.isEmpty()) {
            //this.evolution();
            HashSet<Coordinate> coordinates = this.find();
            HashMap<Coordinate, Colour> changes = this.calculate(coordinates);
            this.execute(changes);
            this.number_of_generations += 1;
        }
    }

    private HashSet<Coordinate> find() {
        // finds and returns a list of all coordinates where a change (creation or deletion) of a cell might happen
        // during a change of generation
        HashSet<Coordinate> potential_coordinates = new HashSet<>();

        for (Coordinate c : this.cells.keySet()) {
            potential_coordinates.addAll(c.with_distance(1));
            potential_coordinates.add(c);
        }
        return potential_coordinates;
    }

    private HashMap<Coordinate, Colour> calculate(HashSet<Coordinate> coordinates) {
        // calculates the necessary changes for cells from one generation to the next

        // this hashmap stores all the changes that will have to be executed
        HashMap<Coordinate, Colour> changes = new HashMap<>();

        // iterate through all coordinates with potential changes
        for (Coordinate a1 : coordinates) {

            // hashmap to count the occurrences of colours of the neighbours of a coordinate
            HashMap<Colour, Integer> neighbours = new HashMap<>();

            // initialization of the hashmap (all colours have occurrence 0)
            for (Colour colour : Colour.values()) {
                neighbours.put(colour, 0);
            }

            // generates a list of points at distance 1 (the 8 neighbours of every square)
            ArrayList<Coordinate> neighbour_coordinates = a1.with_distance(1);

            // updates the hashmap with occurrences of colours for the 8 neighbours
            for (Coordinate a2 : neighbour_coordinates) {
                if (this.cells.containsKey(a2)) {
                    Colour temp_colour = this.cells.get(a2).get_colour();
                    int temp_count = neighbours.get(temp_colour);
                    neighbours.put(temp_colour, temp_count + 1);
                }
            }

            neighbours.remove(Colour.NONE);

            // the result of the above for-loop is a hash-map with the occurrences for every of the two colours
            // now check whether one of the criteria is met for a cell to come to live or die
            // if this is the case, the change is added to the change hashmap
            if (this.cells.containsKey(a1)) {
                Colour this_colour = this.cells.get(a1).get_colour();

                if ((neighbours.get(this_colour) < 2 || neighbours.get(this_colour) > 3))
                    changes.put(a1, Colour.NONE);
            } else if (neighbours.values().stream().reduce(0, Integer::sum) == 3) {
                for (Colour colour : neighbours.keySet()) {
                    if (neighbours.get(colour) >= 2)
                        changes.put(a1, colour);
                }
            }

        }
        return changes;
    }

    private void execute(HashMap<Coordinate, Colour> changes) {
        // executes all the changes that were previously calculated
        for (Coordinate a : changes.keySet()) {
            this.change(a, changes.get(a));
        }
    }

    private void change(Coordinate a, Colour c) {
        // creates or kills cells according to a given instruction
        if (c == Colour.NONE && this.can_kill(a, c))
            this.kill(a, c);

        else if (c != Colour.NONE && this.can_create(a, c))
            this.create(a, c);
    }

    public boolean can_create(Coordinate a, Colour c) {
        // returns whether a cell can be created at coordinate a when the current player has colour c
        return this.colours.contains(c) && !this.cells.containsKey(a);
    }

    public boolean can_kill(Coordinate a, Colour c) {
        // returns whether a cell can be killed at coordinate a when the current player has colour c
        if (!this.cells.containsKey(a))
            return false;

        else if (!this.colours.contains(c) && c != Colour.NONE)
            return false;

        else if (this.cells.get(a).get_colour() == c)
            return false;
        else
            return this.cells.get(a).is_alive();
    }

    public boolean is_alive(Colour colour) {
        // returns whether there are any alive cells for a given colour c
        return this.get_number_of_cells(colour) > 0;
    }

    public boolean equivalent(AliveCells cells_2) {
        // returns whether two AliceCells objects are equivalent (have same hashmap).
        // is used for the PerformanceTests only
        return this.cells.equals(cells_2.cells);
    }
}
