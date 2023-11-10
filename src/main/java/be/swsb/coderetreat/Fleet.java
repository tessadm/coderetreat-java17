package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.Ship;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Fleet {
    private final Set<Ship> ships;
    private final Field field = new Field(); // todo: lijkt niet logisch dat een fleet een field heeft, eerder omgekeerd?

    Fleet(Ship... ships) {
        this.ships = Set.of(ships);
        validate();
    }

    private void validate() {
        shipsCannotOverlap();
        shipsCannotBeOutsideBorders();
    }

    private void shipsCannotOverlap() {
        Stream<Position> occupiedPositions = getOccupiedPositions();

        if (!occupiedPositions.allMatch(new HashSet<>()::add)) {
            throw new ValidationException("Ships on same location detected");
        }
    }

    private void shipsCannotBeOutsideBorders() {
        Stream<Position> occupiedPositions = getOccupiedPositions();

        if ( occupiedPositions.anyMatch(field::isOutsideField)) {
            throw new ValidationException("Ships outside the borders detected");
        };
    }

    private Stream<Position> getOccupiedPositions() {
        return ships.stream().flatMap(Ship::getOccupiedPositions);
    }
}
