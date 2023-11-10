package be.swsb.coderetreat.ship;

import be.swsb.coderetreat.Direction;
import be.swsb.coderetreat.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Ship {
    private final int length;
    private final Position startPosition;
    private final Direction direction;

    public Ship(
            int length,
            Position startPosition,
            Direction direction) {
        this.length = length;
        this.startPosition = startPosition;
        this.direction = direction;
    }

    public Stream<Position> getOccupiedPositions() {
        List<Position> positions = new ArrayList<>(List.of(startPosition));
        for(int i = 0; i< length -1; i++) {
            positions.add(direction.getNextPosition(positions.get(positions.size() -1)));
        }
        return positions.stream();
    }
}
