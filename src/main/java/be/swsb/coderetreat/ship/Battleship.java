package be.swsb.coderetreat.ship;

import be.swsb.coderetreat.Direction;
import be.swsb.coderetreat.Position;

public class Battleship extends Ship {

    public Battleship(Position startPosition, Direction direction) {
        super(4, startPosition, direction);
    }

    @Override
    public String getStringReprestentation() {
        return "🔫"
        ;
    }
}
