package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.Ship;

import java.util.HashSet;
import java.util.Optional;
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

    public Field getField() {
        return field;
    }

    public Stream<Position> getOccupiedPositions() {
        return ships.stream().flatMap(Ship::getOccupiedPositions);
    }

    public Set<Ship> getShipsHit() {
        return ships.stream().filter(Ship::isHit).collect(toSet());
    }

    public boolean allShipsAreSunken() {
        return ships.stream().allMatch(Ship::isSunken);
    }

    public void receiveShot(Position position) {
        ships.forEach(ship -> ship.receiveShot(position));
    }

    public String print(Position position) {
        return ships.stream()
                .map(ship -> ship.print(position))
                .flatMap(Optional::stream)
                .findFirst()
                .orElse("🟦");
    }

    private void shipsCannotOverlap() {
        Stream<Position> occupiedPositions = getOccupiedPositions();

        if (!occupiedPositions.allMatch(new HashSet<>()::add)) {
            throw new IllegalArgumentException("Ships on same location detected");
        }
    }

    private void shipsCannotBeOutsideBorders() {
        Stream<Position> occupiedPositions = getOccupiedPositions();

        if ( occupiedPositions.anyMatch(field::isOutsideField)) {
            throw new IllegalArgumentException("Ships outside the borders detected");
        };
    }
}
