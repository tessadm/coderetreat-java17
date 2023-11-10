package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.Ship;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Fleet {
    final Set<Ship> ships;

    Fleet(Ship... ships) {
        this.ships = Set.of(ships);
        validate();
    }

    private void validate() {
        Stream<Position> occupiedPositions = ships.stream().flatMap(Ship::getOccupiedPositions);

        if (!occupiedPositions.allMatch(new HashSet<>()::add)) {
            throw new ValidationException("Ships on same location detected");
        };
    }

}
