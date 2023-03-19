package Logic;

import Util.Display;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Colour {
    BLUE("B"),
    RED("R"),
    GREEN("G"),
    YELLOW("Y"),
    NONE(" ");

    private final String colour;

    Colour(String symbol) {
        this.colour = symbol;
    }

    public String get_colour() {
        return this.colour;
    }

    public static ArrayList<Colour> get_colours() {
        // returns a list of all available colours except NONE colour
        ArrayList<Colour> list = new ArrayList<>(Arrays.asList(Colour.values()));
        list.remove(Colour.NONE);
        return list;
    }

    public static Colour input() {
        // returns a Colour from user input
        Scanner scanner = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("^[A-Z]$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find() && Colour.is_valid_symbol(input))
            return Colour.reverse_lookup(input);
        else {
            Display.invalid_colour();
            return Colour.input();
        }
    }

    public static boolean is_valid_symbol(String input) {
        // returns whether a string like "A" is a valid representation of a Colour

        for (Colour c : Colour.values()) {
            if (Objects.equals(input, c.get_colour()) && c != Colour.NONE)
                return true;
        }
        return false;
    }

    public static Colour reverse_lookup(String input) {
        // does the reverse-lookup for a String and returns the corresponding colour
        // default return is NONE
        assert (is_valid_symbol(input));

        Colour result = Colour.NONE;

        for (Colour c : Colour.values()) {
            if (Objects.equals(input, c.get_colour())) {
                result = c;
                break;
            }
        }
        return result;
    }
}

