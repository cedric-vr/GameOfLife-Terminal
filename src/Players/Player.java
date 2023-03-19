package Players;

import Logic.Colour;

public class Player {

    private final Colour colour;
    private final String name;

    Player(Colour colour, String name) {
        // initializes a player
        this.colour = colour;
        this.name = name;
    }

    Colour get_colour() {
        // returns the colour of a player
        return this.colour;
    }

    String get_name() {
        // returns a player's name
        return this.name;
    }


}
