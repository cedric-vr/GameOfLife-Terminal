package Util;

import Logic.Colour;

import java.util.ArrayList;

public class Display {

    /**
     * The Display CLass handles all the prints to the console for the game.
     */
    private static final String border = " +-+-+-+-+-+-+-+-+-+-+";
    private static final String endline = "=======================";
    private static final String separator = "-----------------------";

    public static void draw_grid(String[][] grid) {
        int grid_y = grid[0].length;
        StringBuilder letters = new StringBuilder();
        letters.append(" ");

        for (int i = 0; i < grid_y; i++)
            letters.append(" ").append(i);

        System.out.println(endline);
        System.out.println(letters);
        System.out.println(border);

        for (int i = 0; i < grid.length; i++) {
            System.out.print(i);
            System.out.print("|");
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j]);
                System.out.print("|");
            }
            System.out.print(i);
            System.out.println();
        }

        System.out.println(border);
        System.out.println(letters);
        System.out.println(endline);
    }

    public static void draw_invalid_coordinate_input() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid format of coordinate input.");
        System.out.println("Format is: x,y  (vertical,horizontal)");
    }


    public static void draw_invalid_kill() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid coordinate to kill a cell.");
    }

    public static void draw_invalid_create() {
        // message for an invalid coordinate (like "A$")
        System.out.println("Invalid coordinate to create a cell.");
    }

    public static void winner(String player) {
        System.out.println(player + " has won.");
    }

    public static void turn(String player) {
        System.out.println(player + ": it is your turn!");
    }

    public static void kill() {
        System.out.println("Enter coordinate to kill");
    }

    public static void create() {
        System.out.println("Enter coordinate to place a new cell");
    }

    public static void player_stat(String name, int number) {
        System.out.println(name + " has " + number + " cells");
    }

    public static void generation_stat(int number) {
        if (number == 1)
            System.out.println(number + " generation has taken place so far");
        else
            System.out.println(number + " generations have taken place so far");
    }

    public static void input_name(int player) {
        System.out.println("Enter a name for player Nr. " + player);
    }

    public static void invalid_name() {
        System.out.println("This name is not valid or already assigned");
    }

    public static void invalid_colour() {
        System.out.println("This colour is not valid");
    }

    public static void colour_taken() {
        System.out.println("This colour is already assigned");
    }

    public static void colours(ArrayList<Colour> colours) {
        System.out.println("Choose a colour:");
        for (Colour c : colours) {
            System.out.print(c.get_colour() + " ");
        }
    }

}
