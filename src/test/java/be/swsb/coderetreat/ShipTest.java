package be.swsb.coderetreat;


import be.swsb.coderetreat.ship.Battleship;
import be.swsb.coderetreat.ship.Carrier;
import org.junit.jupiter.api.Test;

import static be.swsb.coderetreat.Direction.HORIZONTAL;
import static be.swsb.coderetreat.Direction.VERTICAL;
import static org.assertj.core.api.Assertions.assertThat;


public class ShipTest {

    @Test
    void aShipWithStartPositionZeroZeroAndHORIZONTALDirection_hasCorrectOccupiedPositions() {
        assertThat(new Carrier(new Position(0, 0), HORIZONTAL).getOccupiedPositions())
                .containsExactlyInAnyOrder(
                        new Position(0, 0),
                        new Position(1, 0),
                        new Position(2, 0),
                        new Position(3, 0),
                        new Position(4, 0)
                );

    }

    @Test
    void aShipWithStartPositionOneTwoAndVERTICALDirection_hasCorrectOccupiedPositions() {
        assertThat(new Battleship(new Position(1, 2), VERTICAL).getOccupiedPositions())
                .containsExactlyInAnyOrder(
                        new Position(1, 2),
                        new Position(1, 3),
                        new Position(1, 4),
                        new Position(1, 5)
                );

    }
}
