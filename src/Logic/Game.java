package Logic;

import Board.Grid;
import Cells.AliveCells;
import Players.Playerbase;
import Util.Coordinate;
import Util.Display;

import java.util.ArrayList;

public class Game {
    private static Game instance;
    private AliveCells cells;
    private Grid grid;
    private Playerbase playerbase;

    private Game() {}
    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void start() {
        this.setup();
        this.loop();
        this.end();
    }

    private void setup() {
        // setup process of the game
        this.playerbase = new Playerbase(2);
        add_player();
        add_player();
        this.grid = new Grid();
        this.cells = new AliveCells(grid, this.playerbase.get_colours(), true);
    }

    private void add_player() {
        // adds a player to the playerbase
        String name = playerbase.get_name();
        Colour colour = playerbase.get_colour();
        this.playerbase.add(name, colour);
    }

    private void loop() {
        // executes the game as long as there is no winner
        while (!this.game_over()) {
            Colour current_colour = this.playerbase.get_current_colour();
            String name = this.playerbase.get_current_name();
            inner_loop(current_colour, name);
            this.playerbase.next();
        }
    }

    private void inner_loop(Colour temp, String player) {
        // performs a single player's turn in the gama
        Display.turn(player);
        this.grid.draw();

        Display.kill();
        Coordinate c = Coordinate.input();

        while (!this.cells.can_kill(c, temp)) {
            Display.draw_invalid_kill();
            c = Coordinate.input();
        }

        this.cells.kill(c, temp);
        this.grid.draw();
        Display.create();

        c = Coordinate.input();

        while (!this.cells.can_create(c, temp)) {
            Display.draw_invalid_create();
            c = Coordinate.input();
        }

        this.cells.create(c, temp);
        this.cells.evolve();

        this.stats();
    }

    private void end() {
        // ends the game and displays the corresponding messages
        ArrayList<Colour> colours = this.playerbase.get_colours();

        for (Colour c : colours) {
            if (this.cells.is_alive(c)) {
                Display.winner(this.playerbase.get_name_of_colour(c));
                break;
            }
        }
        this.grid.draw();
    }

    private boolean game_over() {
        // returns whether the game is over or not (a player has run out of cells or not)
        ArrayList<Colour> colours = this.playerbase.get_colours();

        int number = 0;

        for (Colour c : colours) {
            if (this.cells.is_alive(c))
                number++;
        }
        return number < 2;
    }

    private void stats() {
        // displays the stats after each turn (number of generations and cells for each player)
        int number_of_generations = this.cells.get_number_of_generations();
        Display.generation_stat(number_of_generations);

        for (Colour colour : this.playerbase.get_colours()) {
            int number_of_cells = this.cells.get_number_of_cells(colour);
            String name = this.playerbase.get_name_of_colour(colour);
            Display.player_stat(name, number_of_cells);
        }
    }
}
