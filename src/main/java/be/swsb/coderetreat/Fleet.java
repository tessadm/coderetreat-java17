package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.Ship;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Fleet {
    private final Set<Ship> ships;
    private final Field field = new Field();

    Fleet(Ship... ships) {
        this.ships = Set.of(ships);
        validate();
    }

    private void validate() {
        shipsCannotOverlap();
        shipsCannotBeOutsideBorders();
        // todo: other constraints: no duplicate ships, of every type 1 ship,...?
    }

    public void receiveShot(Position position) {
        ships.forEach(ship -> ship.receiveShot(position));
    }

    public Stream<Position> getOccupiedPositions() {
        return ships.stream().flatMap(Ship::getOccupiedPositions);
    }

    public Set<Ship> getShipsHit() {
        return ships.stream().filter(Ship::isHit).collect(toSet());
    }

    public Field getField() {
        return field;
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
}
