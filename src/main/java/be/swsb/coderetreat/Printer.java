package be.swsb.coderetreat;

import be.swsb.coderetreat.ship.Ship;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Printer {

    // todo: refactor - meer logica naar Fleet en eventueel Ship trekken - nadeel is dan wel dat logica enkel nodig om te printen in het domein verzeild geraakt
    public static void print(Fleet fleet) {
        Set<Position> occupiedPositions = fleet.getOccupiedPositions().collect(Collectors.toSet());
        Set<Position> hitPositions = fleet.getShipsHit().stream().flatMap(Ship::getHits).collect(Collectors.toSet());

        for (int i = 0; i <= fleet.getField().getMaxX(); i++) {
            for (int j = 0; j <= fleet.getField().getMaxY(); j++) {
                int x = i;
                int y = j;
                if (occupiedPositions.stream().anyMatch(occupiedPosition -> Objects.equals(occupiedPosition, new Position(x, y)))) {
                    if (hitPositions.stream().anyMatch(hitPosition -> Objects.equals(hitPosition, new Position(x, y)))) {
                        System.out.print("🔥");
                    } else {
                        System.out.print("⛴️");
                    }
                } else {
                    System.out.print("🟦");
                }
            }
            System.out.println();
        }
    }
}
