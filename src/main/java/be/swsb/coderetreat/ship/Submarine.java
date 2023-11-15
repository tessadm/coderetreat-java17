package be.swsb.coderetreat.ship;

import be.swsb.coderetreat.Direction;
import be.swsb.coderetreat.Position;
import be.swsb.coderetreat.ship.Ship;

public class Submarine extends Ship {

    public Submarine(Position startPosition, Direction direction) {
        super(3, startPosition, direction);
    }

    @Override
    public String getStringReprestentation() {
        return "🤿";
    }
}
