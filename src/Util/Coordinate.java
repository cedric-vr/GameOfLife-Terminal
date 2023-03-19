package Util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NOTE!!!: This coordinate class it copied from our Battleship game from assignment one. However, the format
 * of the coordinates was changed from LetterNumber to Number,Nummber. Example: "A0" now becomes "0,1".
 * Therefore, the comments explaining the methods are inconsistent  and use both formats. However, all methods
 * were adapted so that they all work correctly.
 * <p>
 * Additionally, we tweaked/added some methods to accommodate the needs for this assignment.
 **/
public class Coordinate {
    private static final int min_x = 0;
    private static int max_x = 9;
    private static final int min_y = 0;
    private static int max_y = 9;
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        // note Coordinates are constructed in the format a = new Coordinate(x,y)
        // with y as the horizontal component and x as the vertical component
        assert (is_valid_coordinate(x, y));

        this.x = x;
        this.y = y;
    }


    // the following methods implement getter methods for the private instance variables.
    public int get_x() {
        return this.x;
    }

    public int get_y() {
        return this.y;
    }

    public static int get_min_x() {
        return min_x;
    }

    public static int get_min_y() {
        return min_y;
    }

    public static int get_max_x() {
        return max_x;
    }

    public static int get_max_y() {
        return max_y;
    }

    public Coordinate get_copy() {
        return new Coordinate(this.x, this.y);
    }

    public static boolean is_valid_coordinate(int x, int y) {
        // validates whether a potential coordinate is valid or not
        return (min_x <= x && max_x >= x) && (min_y <= y && max_y >= y);
    }

    public static void set_limits(int maxx, int maxy) {
        //sets the limits for the x-axis and y-axis
        assert (maxx >= 0 && maxy >= 0);
        max_x = maxx;
        max_y = maxy;
    }


    // the following methods implement simple boolean comparisons for coordinates
    // note: "equal" and "equals" (last method in the class) are two different methods with varying functionality and implementation
    public static boolean is_in_line(Coordinate a, Coordinate b) {
        return equal_x(a, b) || equal_y(a, b);
    }

    public static boolean equal_x(Coordinate a, Coordinate b) {
        return a.x == b.x;
    }

    public static boolean equal_y(Coordinate a, Coordinate b) {
        return a.y == b.y;
    }

    public static boolean equal(Coordinate a, Coordinate b) {
        return equal_x(a, b) && equal_y(a, b);
    }

    public static boolean smaller_x(Coordinate a, Coordinate b) {
        return a.x < b.x;
    }

    public static boolean smaller_y(Coordinate a, Coordinate b) {
        return a.y < b.y;
    }

    public static int distance(Coordinate a, Coordinate b) {
        // calculates the horizontal or vertical distance between two coordinates (but not diagonally!)
        // Example: Distance from A0 to A4 is 3 (and not 4). Distance from A0 to C1 does not exist.
        assert is_in_line(a, b);

        if (equal_y(a, b))
            return Math.abs(a.x - b.x);
        else
            return Math.abs(a.y - b.y);
    }


    // the following eight boolean methods evaluate whether a certain coordinate can be incremented in a direction n-times or not
    // Example: A0 cannot be incremented up at all (A-1 is not a valid coordinate). A0 incremented down by 1 would become A1.
    // Example: B4 incremented to the right by 3 would become E4
    private boolean can_increment_up_by(int n) {
        return is_valid_coordinate(this.x - n, this.y);
    }

    private boolean can_increment_down_by(int n) {
        return is_valid_coordinate(this.x + n, this.y);
    }

    private boolean can_increment_left_by(int n) {
        return is_valid_coordinate(this.x, this.y - n);
    }

    private boolean can_increment_right_by(int n) {
        return is_valid_coordinate(this.x, this.y + n);
    }

    private boolean can_increment_up_right_by(int n) {
        return can_increment_right_by(n) && can_increment_up_by(n);
    }

    private boolean can_increment_up_left_by(int n) {
        return can_increment_left_by(n) && can_increment_up_by(n);
    }

    private boolean can_increment_down_left_by(int n) {
        return can_increment_down_by(n) && can_increment_left_by(n);
    }

    private boolean can_increment_down_right_by(int n) {
        return can_increment_down_by(n) && can_increment_right_by(n);
    }


    // the following eight methods return a new Coordinated that is incremented n-times in the corresponding direction
    // example: A0 incremented 4 times to the right becomes E0.
    private Coordinate increment_up_by(int n) {
        return new Coordinate(this.x - n, this.y);
    }

    private Coordinate increment_down_by(int n) {
        return new Coordinate(this.x + n, this.y);
    }

    private Coordinate increment_left_by(int n) {
        return new Coordinate(this.x, this.y - n);
    }

    private Coordinate increment_right_by(int n) {
        return new Coordinate(this.x, this.y + n);
    }

    private Coordinate increment_up_right_by(int n) {
        return new Coordinate(this.x - n, this.y + n);
    }

    private Coordinate increment_up_left_by(int n) {
        return new Coordinate(this.x - n, this.y - n);
    }

    private Coordinate increment_down_left_by(int n) {
        return new Coordinate(this.x + n, this.y - n);
    }

    private Coordinate increment_down_right_by(int n) {
        return new Coordinate(this.x + n, this.y + n);
    }

    public ArrayList<Coordinate> with_distance_cross(int d) {
        // returns all Coordinates at distance d in horizontal and vertical direction only .
        // Example: all points at distance 2 from A0 are: C0 and A2
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();

        if (this.can_increment_up_by(d))
            points.add(this.increment_up_by(d));

        if (this.can_increment_down_by(d))
            points.add(this.increment_down_by(d));

        if (this.can_increment_left_by(d))
            points.add(this.increment_left_by(d));

        if (this.can_increment_right_by(d))
            points.add(this.increment_right_by(d));

        return points;
    }

    public ArrayList<Coordinate> with_distance_corner(int d) {
        // returns all Coordinates at distance d in both diagonal directions
        // Example: all points at distance 1 from A0 are: B1
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();

        if (this.can_increment_up_right_by(d))
            points.add(this.increment_up_right_by(d));

        if (this.can_increment_up_left_by(d))
            points.add(this.increment_up_left_by(d));

        if (this.can_increment_down_left_by(d))
            points.add(this.increment_down_left_by(d));

        if (this.can_increment_down_right_by(d))
            points.add(this.increment_down_right_by(d));

        return points;
    }

    public ArrayList<Coordinate> with_distance(int d) {
        // returns all Coordinates at distance d in a list (vertical, horizontal and both diagonals)
        // Example: all points at distance 1 from A0 are: B0, A1, B1
        // Note: the list can be empty. Example: all points at distance 11 from A0 in a 10x10 grid (there are none)
        assert (d > 0);

        ArrayList<Coordinate> points = new ArrayList<>();
        points.addAll(this.with_distance_cross(d));
        points.addAll(this.with_distance_corner(d));

        return points;
    }


    // the following two methods build a range of coordinates in the corresponding direction (x or y)
    // Example: The range from A0 to A4 is the list {A0,A1,A2,A3,A4}
    // Example The range from A4 to A0 is also the list {A0,A1,A2,A3,A4} because the first element is always the smallest
    private static ArrayList<Coordinate> build_range_x(Coordinate a, Coordinate b) {
        //assert equal_y(a,b);

        ArrayList<Coordinate> range = new ArrayList<>();
        range.add(a);

        for (int i = a.x + 1; i < b.x; i++) {
            Coordinate c = new Coordinate(i, a.get_y());
            range.add(c);
        }
        range.add(b);
        return range;
    }

    private static ArrayList<Coordinate> build_range_y(Coordinate a, Coordinate b) {
        //assert equal_x(a,b);

        ArrayList<Coordinate> range = new ArrayList<>();
        range.add(a);

        for (int i = a.y + 1; i < b.y; i++) {
            Coordinate c = new Coordinate(a.get_x(), i);
            range.add(c);
        }
        range.add(b);
        return range;
    }

    public static ArrayList<Coordinate> get_range(Coordinate a, Coordinate b) {
        // this method builds the correct range for two given coordinates
        // Example: the range from A0 to A4 is the list {A0,A1,A2,A3,A4}
        // in case of a <=> b the list containing only Coordinate a is returned
        // the coordinates a and b must be on the same x- or y-axis
        assert is_in_line(a, b);

        if (equal(a, b)) {
            ArrayList<Coordinate> range = new ArrayList<>();
            range.add(a);
            return range;
        } else if (equal_x(a, b)) {
            if (smaller_y(a, b)) {
                return build_range_y(a, b);
            } else {
                return build_range_y(b, a);
            }
        } else {
            if (smaller_x(a, b)) {
                return build_range_x(a, b);
            } else {
                return build_range_x(b, a);
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        // overrides the default equals method in Object
        if (o == this)
            return true;
        else if (!(o instanceof Coordinate))
            return false;
        else
            return equal(this, (Coordinate) o);
    }

    @Override
    public int hashCode() {
        //overrides the default hash-code method
        int a = this.x;
        int b = this.y;
        return (a + b) * (a + b + 1) / 2 + a;
    }

    public static Coordinate input() {
        //returns a valid coordinate from user input
        Scanner scanner = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("^[0-9]*,[0-9]*$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find() && can_convert(input))
            return convert(input);
        else {
            Display.draw_invalid_coordinate_input();
            return input();
        }
    }

    public static boolean can_convert(String input) {
        //returns whether a string can be converted to a coordinate or not
        // valid examples are: "1,1" or "2,2"
        //invalid are: "1,     1" or "   1,1" or "1,1," ...
        try {
            String[] s = input.split(",");

            if (s.length > 2)
                return false;

            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);

            return is_valid_coordinate(x, y);
        } catch (Exception e) {
            return false;
        }
    }

    public static Coordinate convert(String input) {
        //converts a string to a coordinate if possible
        assert can_convert(input);

        String[] s = input.split(",");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);
        return new Coordinate(x, y);
    }

    public static int dimension_x() {
        //returns the size of the coordinate space in the x-axis
        return Coordinate.get_max_x() - Coordinate.get_min_x() + 1;
    }

    public static int dimension_y() {
        //returns the size of the coordinate space in the y-axis
        return Coordinate.get_max_y() - Coordinate.get_min_y() + 1;
    }

    public Coordinate translation(int x, int y) {
        //returns a coordinate shifted by x and y
        assert is_valid_coordinate(this.x + x, this.y + y);

        return new Coordinate(this.x + x, this.y + y);
    }
}



