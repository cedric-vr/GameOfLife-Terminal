import Logic.Game;
import Util.Coordinate;


public class Main {
    public static void main(String[] args) {
        // sets the limits for the coordinates globally
        Coordinate.set_limits(9, 9);

        Game game = Game.getInstance();
        game.start();
    }

}