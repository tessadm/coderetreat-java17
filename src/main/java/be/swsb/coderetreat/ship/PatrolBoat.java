package be.swsb.coderetreat.ship;

import be.swsb.coderetreat.Direction;
import be.swsb.coderetreat.Position;

public class PatrolBoat extends Ship {

    public PatrolBoat(Position startPosition, Direction direction) {
        super(2, startPosition, direction);
    }
}
