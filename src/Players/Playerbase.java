package Players;

import Logic.Colour;
import Util.Display;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playerbase {
    private final int number_of_players;
    private final ArrayList<Player> players;
    int current_player = 0;

    public Playerbase(int number) {
        assert (number > 0);
        this.players = new ArrayList<>();
        number_of_players = number;
    }

    public void add(String name, Colour colour) {
        // adds a player to the playerbase with name and colour
        assert (this.colour_is_available(colour) && this.name_is_available(name) && this.players.size() < this.number_of_players);

        this.players.add(new Player(colour, name));
        this.players.sort(Comparator.comparing(Player::get_name));
    }

    public void next() {
        // moves the current player to the next
        if (this.players.size() == number_of_players) {
            this.current_player += 1;
            this.current_player = this.current_player % this.number_of_players;
        }
    }

    private Player get_current_player() {
        // returns the current player
        assert (this.players.size() == this.number_of_players);
        return this.players.get(this.current_player);
    }

    public Colour get_current_colour() {
        // returns the colour of the current player
        return this.get_current_player().get_colour();
    }

    public String get_current_name() {
        // returns the name of the current player
        return this.get_current_player().get_name();
    }

    public Colour get_colour() {
        // returns a valid colour for a new player from user input
        Display.colours(Colour.get_colours());
        Colour colour = Colour.input();
        while (!this.colour_is_available(colour)) {
            Display.colour_taken();
            colour = Colour.input();
        }
        return colour;
    }

    public String get_name() {
        // returns a valid name for a new player from user input
        Scanner scanner = new Scanner(System.in);
        Display.input_name(this.players.size() + 1);
        String input = scanner.nextLine();
        while (!this.name_validation(input)) {
            Display.invalid_name();
            input = scanner.nextLine();
        }
        return input;
    }

    private boolean name_validation(String name) {
        // validates that a name has the correct format and is not already taken by another player
        return this.name_is_available(name) && name_pattern(name);
    }

    public String get_name_of_colour(Colour c) {
        // returns the name of a player associated with a colour
        // default return is empty string
        for (Player p : this.players) {
            if (p.get_colour() == c)
                return p.get_name();
        }
        return "";
    }

    public ArrayList<Colour> get_colours() {
        // returns an arraylist of the colours used by the player
        ArrayList<Colour> colours = new ArrayList<>();
        for (Player p : this.players) {
            colours.add(p.get_colour());
        }
        return colours;
    }


    private boolean name_pattern(String name) {
        // Input validation for player names
        // name must start with a big letter and have at least one character
        // name can have lower/upper case letters, numbers, . and _

        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z0-9 ._]*$");
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    private boolean colour_is_available(Colour c) {
        // returns whether a colour is available or taken by another player
        for (Player p : this.players) {
            if (p.get_colour() == c)
                return false;
        }
        return true;
    }

    private boolean name_is_available(String name) {
        // returns whether a name is available or taken by another player
        for (Player p : this.players) {
            if (Objects.equals(p.get_name(), name))
                return false;
        }
        return true;
    }

}
